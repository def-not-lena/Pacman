package Players;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends MovingObject{
    private KeyCode direction;
    private final int diameter;
    private final int radius;
    private final KeyCode[] allowedKeys = new KeyCode[]{
            KeyCode.W,
            KeyCode.A,
            KeyCode.S,
            KeyCode.D,
            KeyCode.UP,
            KeyCode.DOWN,
            KeyCode.LEFT,
            KeyCode.RIGHT
    };

    public Player(int posX, int poxY, int velocity, boolean alive, int radius) {
        super(posX, poxY, velocity, alive);
        this.radius = radius;
        this.diameter = radius*2;
    }

    @Override
    public void defineLook(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTYELLOW);
    }

    @Override
    public void render(GraphicsContext gc) {
        defineLook(gc);
        gc.fillOval(posX,posY, diameter, diameter);
        gc.strokeOval(posX,posY, diameter, diameter);
    }

    @Override
    public  void updatePos() {
        if(this.isAlive()){
            if(direction != null) {
                switch (direction) {
                    case KeyCode.W:
                        this.posY -= velocity;
                        break;
                    case KeyCode.D:
                        this.posX += velocity;
                        break;
                    case KeyCode.A:
                        this.posX -= velocity;
                        break;
                    case KeyCode.S:
                        this.posY += velocity;
                        break;
                    case KeyCode.UP:
                        this.posY -= velocity;
                        break;
                    case KeyCode.RIGHT:
                        this.posX += velocity;
                        break;
                    case KeyCode.LEFT:
                        this.posX -= velocity;
                        break;
                    case KeyCode.DOWN:
                        this.posY += velocity;
                        break;
                }
            }
        }
    }

    public void setDirection(KeyCode direction){

        if(direction == null){
            this.direction = null;
            return;
        }

        for (KeyCode allowed : allowedKeys) {
            if (direction.equals(allowed)) {
                this.direction = direction;
                break;
            }
        }

    }

    public boolean intersectsRectCircle(Wall wall) {
        double wX = wall.posX;
        double wY = wall.posY;
        double width = wall.wallWidth;
        double height = wall.wallHeight;
        double centerX = this.posX + radius;
        double centerY = this.posY + radius;
        double radius = this.radius;

        // Find closest point on rectangle to circle center
        double closestX = clamp(centerX, wX, wX + width);
        double closestY = clamp(centerY, wY, wY + height);

        // Compute squared distance
        double dx = centerX - closestX;
        double dy = centerY - closestY;
        double distanceSquared = dx * dx + dy * dy;

        return distanceSquared <= radius * radius;
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public boolean collisionYummyPoint(YummyPoint point){

        return radius + point.getRadius() >= Math.abs((point.posX + point.getRadius()) - (this.posX + radius)) && radius + point.getRadius() >= Math.abs((point.posY + point.getRadius()) - (this.posY + radius));
    }

}
