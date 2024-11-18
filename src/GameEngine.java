import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class GameEngine implements Engine,KeyListener {
    private final DynamicSprite hero;

    public GameEngine(DynamicSprite hero){
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
            {
                hero.setDirection(Direction.WEST);
                break;
            }
            case KeyEvent.VK_UP:
            {
                hero.setDirection(Direction.NORTH);
                break;
            }
            case KeyEvent.VK_RIGHT:
            {
                hero.setDirection(Direction.EAST);
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                hero.setDirection(Direction.SOUTH);
                break;
            }
            case KeyEvent.VK_CONTROL:
            {
                hero.setSpeed(8);
                break;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_CONTROL: {
                hero.setSpeed(5);
            }
        }

    }

}
