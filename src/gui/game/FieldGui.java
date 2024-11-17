package gui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import logic.util.Utils;
import logic.util.Vector;

public class FieldGui extends JPanel {

    Field field;

    public FieldGui() {
        setPreferredSize(new Dimension(Utils.tileSize * (Utils.fieldSize * (Utils.boardSize + 3)-1),
        Utils.tileSize * (Utils.fieldSize * (Utils.boardSize + 3)-1)));
        setKeyBindings();
    }

    public Field getField(){
        return field;
    }
    public void setField(Field f){
        field = f;
    }

    private void setKeyBindings() {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "up");
        getActionMap().put("up", buttonAction(new Vector(0, -1)));

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "down");
        getActionMap().put("down", buttonAction(new Vector(0, 1)));

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "left");
        getActionMap().put("left", buttonAction(new Vector(-1, 0)));

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "right");
        getActionMap().put("right", buttonAction(new Vector(1, 0)));
    }

    private Action buttonAction(Vector dir) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!field.getPlayer().getOriginalDir().equals(dir.negated())) {
                    field.getPlayer().setDir(new Vector(dir));
                }
            }
        }; 
    }

    public void newField(){
        field = new Field(field.getGame(), this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        field.draw(g);
    }
}
