package Players;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected int posX;
    protected int posY;

    public GameObject(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    abstract public void defineLook(GraphicsContext gc);
    abstract public void render(GraphicsContext gc);
}
