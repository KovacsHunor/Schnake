package source.gui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import source.logic.field.Field;
import source.logic.util.Vector;

/**
 * The class of the panel in which the field is drawn
 */
public class FieldGui extends JPanel {
    /**
     * The constructor
     */
    public FieldGui() {
        init();
    }
    
    /**
     * Sets up the space where the field will be drawn, sets the controls and the size of the panel.
     */
    public void init() {
        setKeyBindings();

        Field field = Field.getInstance();
        setPreferredSize(new Dimension(field.getTileSize() * (field.getBoardNum() * (field.getTileNum() + 3) - 1),
                field.getTileSize() * (field.getBoardNum() * (field.getTileNum() + 3) - 1)));
        setSize(getPreferredSize());
    }

    /**
     * Creates a new Field, and alters its settings to accomodate changes.
     */
    public void newField() {
        Field field = Field.getInstance();
        Field.newInstance(field.getBoardNum(), field.getTileNum());
        init();
    }

    /**
     * Sets the keyBindings (WASD and arrows) 
     */
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

    /**
     *  Alters the players direction based on the button pressed
     * @param dir   The direction determined by the button pressed
     * @return      The Action to execute when pressing a directional button
     */
    private Action buttonAction(Vector dir) {
        Field field = Field.getInstance();
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!field.getSnake().getOriginalDir().equals(dir.negated())) {
                    field.getSnake().setDir(new Vector(dir));
                }
            }
        };
    }

    /**
     * Calls the tick function of the current field
     */
    public void tick(){
        Field.getInstance().tick();
    }

    /**
     * Draws itself and the field.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Field.getInstance().draw(g);
    }
}
