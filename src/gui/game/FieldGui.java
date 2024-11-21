package gui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import logic.field.Field;
import logic.util.Vector;

public class FieldGui extends JPanel {
    public FieldGui() {
        init();
    }

    public void init() {
        Field field = Field.getInstance();
        setKeyBindings();
        setPreferredSize(new Dimension(field.getTileSize() * (field.getBoardNum() * (field.getTileNum() + 3) - 1),
                field.getTileSize() * (field.getBoardNum() * (field.getTileNum() + 3) - 1)));

        setSize(getPreferredSize());
    }

    public void newField() {
        Field field = Field.getInstance();
        Field.newInstance(field.getGame(), this, field.getBoardNum(), field.getTileNum());
        init();
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
        Field field = Field.getInstance();
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!field.getPlayer().getOriginalDir().equals(dir.negated())) {
                    field.getPlayer().setDir(new Vector(dir));
                }
            }
        };
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Field.getInstance().draw(g);
    }
}
