import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite{
    private boolean isWalking = true;
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 200;
    private Direction direction = Direction.WEST;
    private int health = 100;

    public DynamicSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void drawHealth(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y-10, (int)width*health/100, 8);
        g.setColor(Color.black);
        g.drawRect((int)x,(int)y-10, (int)width, 8);
    }

    @Override
    public void draw(Graphics g){
        int index = (int)((System.currentTimeMillis()/timeBetweenFrame)%spriteSheetNumberOfColumn);
        int attitude = direction.getFrameLineNumber();

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (attitude*height),
                (int) ((index+1)*this.width), (int)((attitude+1)*this.height),null);
        drawHealth(g);
    }

    private void move(){
        switch (direction){
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){

        Rectangle2D.Double hitbox = new Rectangle2D.Double(x, y, width, height);
        switch (direction){
            case NORTH:
                hitbox.y -= speed;
                break;
            case SOUTH:
                hitbox.y += speed;
                break;
            case EAST:
                hitbox.x += speed;
                break;
            case WEST:
                hitbox.x -= speed;
                break;
        }

        for(Sprite sprite : environment)
            if ((sprite instanceof SolidSprite) && (sprite != this))
                if (hitbox.intersects(sprite.getHitBox()))
                    return false;
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment))
            move();
    }

    public void collision(ArrayList<Sprite> environment){
        Rectangle2D.Double hitbox = new Rectangle2D.Double(x, y, width, height);
        for(Sprite sprite : environment)
            if ((sprite instanceof StaticEnnemie))
                if (hitbox.intersects(sprite.getHitBox()))
                    health -= 3;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }
}
