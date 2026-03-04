package Players;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends GameObject{
    protected int wallHeight;
    protected int wallWidth;

    public Wall(int posX, int posY, int wallHeight, int wallWidth) {
        super(posX, posY);
        this.wallHeight = wallHeight;
        this.wallWidth = wallWidth;
    }

    @Override
    public void defineLook(GraphicsContext gc) {
        gc.setFill(Color.PINK);
        gc.setStroke(Color.BLACK);

    }

    @Override
    public void render(GraphicsContext gc) {
        defineLook(gc);
        gc.strokeRect(posX, posY, wallWidth, wallHeight);
        gc.fillRect(posX, posY, wallWidth, wallHeight);

    }
}
