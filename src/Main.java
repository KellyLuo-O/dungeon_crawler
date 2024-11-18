import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;


    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(3);       // 3 = EXIT_ON_CLOSE

        Playground level = new Playground("./data/level2.txt");
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 200, 300, 48, 50);

        renderEngine = new RenderEngine();
        gameEngine = new GameEngine(hero);
        physicEngine = new PhysicEngine();

        Timer renderTimer = new Timer(50, (time)->renderEngine.update());
        Timer gameTimer = new Timer(50, (time)->gameEngine.update());
        Timer physicTimer = new Timer(50, (time)->physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        displayZoneFrame.addKeyListener(gameEngine);

        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);

        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());
        physicEngine.addToEnvironment(level.getEnnemieSpriteList());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Main main = new Main();
    }

}