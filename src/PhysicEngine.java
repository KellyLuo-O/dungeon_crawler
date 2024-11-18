import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList = new ArrayList<DynamicSprite>();
    private ArrayList<Sprite> environment = new ArrayList<Sprite>();

    public PhysicEngine() {
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        movingSpriteList.add(sprite);
    }

    public void addToEnvironment(Sprite sprite){
        this.environment.add(sprite);
    }

    public void addToEnvironment(ArrayList<Sprite> environment){
        this.environment.addAll(environment);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
        for(DynamicSprite sprite : movingSpriteList)
        {
            sprite.moveIfPossible(environment);
            sprite.collision(environment);
        }


    }
}
