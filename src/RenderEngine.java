import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;

    public RenderEngine(){
        renderList = new ArrayList<Displayable>();
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
        for (Displayable displayable : renderList)
            displayable.draw(g);
    }
}
