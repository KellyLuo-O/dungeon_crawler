import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private final GameEngine gameEngine;

    private Image imageTitleScreen;
    private Image imageGameOver;

    public RenderEngine(GameEngine gameEngine){
        renderList = new ArrayList<Displayable>();

        this.gameEngine = gameEngine;

        try{
            imageTitleScreen = ImageIO.read(new File("./img/titleScreen.png"));
            imageGameOver = ImageIO.read(new File("./img/gameOver.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setRenderList(ArrayList<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        for(Displayable d : displayable)
            renderList.add(d);
    }

    @Override
    public void update() {
        //System.out.println("RenderEngine updated :");
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        switch (gameEngine.getGameState()){
            case titleScreen :
                g.drawImage(imageTitleScreen, 0, 0, 400, 600, null);
                break;
            case gameOver :
                g.drawImage(imageGameOver, 0, 0, 400, 600, null);
                break;
            case game:
                for (Displayable displayable : renderList)
                    displayable.draw(g);
                g.setFont(new Font ("Courier New", Font.BOLD, 20));
                g.drawString("TIMER : " + Integer.toString(gameEngine.getTime()), 20, 20);
                break;
        }

    }
}
