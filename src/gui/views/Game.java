package gui.views;

import gui.game.Field;
import gui.game.FieldGui;
import gui.main.Main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.field.Board;
import logic.util.Utils;

public final class Game extends JPanel {

    private FieldGui field;
    JPanel fieldPanel = new JPanel(new GridBagLayout());
    private JPanel right = new JPanel();
    private final JLabel pointLabel = new JLabel("0");

    public Game() {
        setLayout(new GridBagLayout());

        field = new FieldGui();
        field.setField(new Field(this, field));

        fieldPanel.setPreferredSize(new Dimension(1050, 1050));
        fieldPanel.add(field);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(ae -> {
            Main.switchTo("menu");
            field.getField().reset();
        });

        pointLabel.setFont(new Font("Serif", Font.BOLD, 64));

        left.add(menuButton);
        pointLabel.setAlignmentX(CENTER_ALIGNMENT);
        right.add(pointLabel);
        right.add(fieldPanel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

    }

    public void setPointLabel(int point) {
        pointLabel.setText("" + point);
    }

    public FieldGui getFieldGui() {
        return field;
    }

    public void reset() {
        Utils.updateTileSize();
        field.getField().reset();
        int side = Utils.tileSize * (Utils.fieldSize * (Utils.boardSize + 3) - 1);
        field.setPreferredSize(new Dimension(side, side));
        field.setSize(field.getPreferredSize());
        Board.setPolygons();
        revalidate();
        repaint();
    }

    public void start() {
        field.getField().start();
    }
}
