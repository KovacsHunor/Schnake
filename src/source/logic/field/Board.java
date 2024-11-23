package source.logic.field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import source.logic.util.Dir;
import source.logic.util.MyPolygon;
import source.logic.util.Utils;
import source.logic.util.Vector;

public class Board {
    private BufferedImage img;
    private Graphics2D g2d;
    private final Point pos;
    private final EnumMap<Dir, Side> sides;

    private final GridTile[][] grid;
    private static final MyPolygon[] sidePolygons = new MyPolygon[2];

    public static void setPolygons() {
        int tileNum = Field.getInstance().getTileNum();
        int tileSize = Field.getInstance().getTileSize();

        int offset = (int) (0.433f * tileSize);
        int trix = ((tileNum) * tileSize);

        MyPolygon arrow = new MyPolygon(
                new int[]{0, trix - offset, trix, trix - offset, 0, offset},
                new int[]{0, 0, tileSize / 4, tileSize / 2, tileSize / 2, tileSize / 4},
                6
        );
        sidePolygons[0] = arrow;

        MyPolygon rectangle = new MyPolygon(
                new int[]{0, trix, trix, 0},
                new int[]{0, 0, tileSize / 2, tileSize / 2},
                4
        );
        sidePolygons[1] = rectangle;
    }

    public Board(Point pos) {
        int tileNum = Field.getInstance().getTileNum();
        int tileSize = Field.getInstance().getTileSize();

        grid = new GridTile[tileNum][tileNum];

        img = new BufferedImage(tileNum * tileSize + 3 * tileSize, tileNum * tileSize + 3 * tileSize, BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();
        for (GridTile[] gridTiles : grid) {
            for (int i = 0; i < gridTiles.length; i++) {
                gridTiles[i] = new GridTile();
            }
        }

        sides = new EnumMap<>(Dir.class);
        
        for (Dir dir : Dir.values()) {
            sides.put(dir, new Side(new Color(0, 0, 0), this, dir));
        }

        this.pos = pos;
    }

    public GridTile getTile(Vector v) {
        return grid[v.x][v.y];
    }

    public void putOnTile(Vector v, GridObject go) {
        grid[v.x][v.y].put(go);
    }

    public Side getSide(Dir d) {
        return sides.get(d);
    }

    public Map<Dir, Side> getSides() {
        return sides;
    }

    //  draw the teleporter sides
    public void drawSides() {
        int tileNum = Field.getInstance().getTileNum();
        int tileSize = Field.getInstance().getTileSize();
        Side side;
        Vector translation;
        MyPolygon polygon = sidePolygons[0];

        
        side = sides.get(Dir.UP);
        g2d.setColor(side.getColor());
        if (side.isDefaultOriented()) {
            g2d.fillPolygon(polygon.translate(new Vector(tileSize, 0)));
        } else {
            g2d.fillPolygon(polygon.mirrorH().translate(new Vector(tileSize * (1 + tileNum), 0)));
        }

        side = sides.get(Dir.DOWN);
        g2d.setColor(side.getColor());
        if (side.isDefaultOriented()) {
            translation = new Vector(tileSize * (1 + tileNum), (int) ((tileNum + 1.5) * tileSize));
            g2d.fillPolygon(polygon.mirrorH().translate(translation));
        } else {
            translation = new Vector(tileSize, (int) ((1.5 + tileNum) * tileSize));
            g2d.fillPolygon(polygon.translate(translation));
        }

        side = sides.get(Dir.LEFT);
        g2d.setColor(side.getColor());
        if (side.isDefaultOriented()) {
            translation = new Vector(0, ((tileNum + 1) * tileSize));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        } else {
            translation = new Vector(0, tileSize);
            g2d.fillPolygon(polygon.rotate().translate(translation));

        }

        side = sides.get(Dir.RIGHT);
        g2d.setColor(side.getColor());
        if (side.isDefaultOriented()) {
            translation = new Vector((int) (tileSize * (1.5 + tileNum)), tileSize);
            g2d.fillPolygon(polygon.rotate().translate(translation));
        } else {
            translation = new Vector((int) (tileSize * (1.5 + tileNum)), ((tileNum + 1) * tileSize));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        }
    }

    public void draw(Graphics g) {
        int tileNum = Field.getInstance().getTileNum();
        int tileSize = Field.getInstance().getTileSize();

        g2d.setColor(Utils.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, tileNum * tileSize + 3 * tileSize, tileNum * tileSize + 3 * tileSize);

        drawSides();

        // draw the checkered grid
        for (int row = 0; row < tileNum; row++) {
            for (int col = 0; col < tileNum; col++) {
                if (!grid[col][row].isEmpty()) {
                    g2d.setColor(grid[col][row].upper().getColor());
                } else {
                    if ((row + col) % 2 == 1) {
                        g2d.setColor(new Color(60, 60, 60));
                    } else {
                        g2d.setColor(new Color(50, 50, 50));
                    }
                }

                g2d.fillRect(
                        (col + 1) * tileSize,
                        (row + 1) * tileSize,
                        tileSize,
                        tileSize);
            }
        }

        g.drawImage(img, (pos.x) * (tileNum * tileSize + 3 * tileSize),
                (pos.y) * (tileNum * tileSize + 3 * tileSize), null);
    }

    public Point getPos() {
        return pos;
    }
}
