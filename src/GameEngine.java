import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class GameEngine implements Engine,KeyListener {
    private final DynamicSprite hero;
    private GameState gameState = GameState.titleScreen;
    private int timeStart = 0;
    private int time;

    public GameEngine(DynamicSprite hero){
        this.hero = hero;
    }

    public int getTime() {
        time = (50-(((int)System.currentTimeMillis() - timeStart)/1000));
        if (time == 0)
            gameState = GameState.gameOver;
        return time;
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void update() {
        switch (gameState){
            case GameState.game :
                if (hero.getHealth()<=0){
                    gameState=GameState.gameOver;
                }
                break;
        }
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
        if (gameState==GameState.titleScreen)
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_ENTER:
                        gameState = GameState.game;
                        timeStart = (int)System.currentTimeMillis();
                        hero.setHealth(100);
                        break;
                }
        else if (gameState==GameState.game)
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        hero.setDirection(Direction.WEST);
                        break;
                    case KeyEvent.VK_UP:
                        hero.setDirection(Direction.NORTH);
                        break;
                    case KeyEvent.VK_RIGHT:
                        hero.setDirection(Direction.EAST);
                        break;
                    case KeyEvent.VK_DOWN:
                        hero.setDirection(Direction.SOUTH);
                        break;
                    case KeyEvent.VK_CONTROL:
                        hero.setSpeed(8);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        gameState = GameState.gameOver;
                        break;
                }
        else if (gameState==GameState.gameOver)
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_SPACE:
                        gameState = GameState.titleScreen;
                        break;
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
