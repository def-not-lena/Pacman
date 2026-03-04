package org.example.canvas;
import Players.YummyPoint;
import Players.Player;
import Players.Wall;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer extends AnimationTimer {
    private  final Canvas canvas;
    private final List<Wall> walls;
    private final Player player;
    private List<YummyPoint> points;
    private Label counter;

    public GameRenderer(Canvas canvas, List<Wall> walls, Player player, List<YummyPoint> points, Label counter){
        this.canvas = canvas;
        this.walls = walls;
        this.player = player;
        this.points = points;
        this.counter = counter;
    }

    long last = 0;
    long interval = 166_666_667;
    List<YummyPoint> clear = new ArrayList<>();
    int cnt = 0;
    @Override
    public void handle(long now) {
        this.canvas.getGraphicsContext2D().clearRect(0,0,HelloApplication.CANVAS_WIDTH, HelloApplication.CANVAS_HEIGHT);
        for(Wall i : walls){
            i.render(this.canvas.getGraphicsContext2D());
            if (player.intersectsRectCircle(i)) {
                player.setDirection(null);
            }
        }

        for(YummyPoint i : points){
            i.render(this.canvas.getGraphicsContext2D());
            if(player.collisionYummyPoint(i)){
                System.out.println("Collected");
                clear.add(i);
                cnt++;
                counter.setText("Count: " + cnt);
            }
        }

        for (YummyPoint i : clear) {
            points.remove(i);
        }
        clear.clear();

        if (now - last >= interval) {
            player.updatePos();
            last = now;
        }
        player.render(this.canvas.getGraphicsContext2D());
    }
}
