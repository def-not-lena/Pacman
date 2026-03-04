package Players;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public abstract class MovingObject extends GameObject{

    protected int velocity;
    private boolean alive;
    protected KeyCode direction;

    public MovingObject(int posX, int posY, int velocity, boolean alive) {
        super(posX, posY);
        this.velocity = velocity;
        this.alive = alive;
    }

    @Override
    abstract public void defineLook(GraphicsContext gc);

    @Override
    abstract public void render(GraphicsContext gc);

    public abstract void updatePos();

    public boolean isAlive() {
        return alive;
    }

}
