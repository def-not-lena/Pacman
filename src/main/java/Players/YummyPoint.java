package Players;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class YummyPoint extends GameObject{
    private int diameter;
    public YummyPoint(int posX, int posY, int diameter){
        super(posX,posY);
        this.diameter = diameter;
    }

    @Override
    public void defineLook(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.CADETBLUE);
    }

    @Override
    public void render(GraphicsContext gc) {
        defineLook(gc);
        gc.fillOval(posX,posY, diameter, diameter);
        gc.strokeOval(posX,posY, diameter, diameter);
    }

    public int getRadius(){
        return diameter/2;
    }
}
