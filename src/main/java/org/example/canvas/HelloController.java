package org.example.canvas;

import Players.Player;
import Players.Wall;
import Players.YummyPoint;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    public Canvas canvas;

    @FXML
    public Label counter;

    public int pos = 100;

    private GameRenderer renderer;

    private List<Wall> walls;
    private List<YummyPoint> points;

    private Player pacman;

    @FXML
    public void initialize() {
        this.canvas.setHeight(HelloApplication.CANVAS_HEIGHT);
        this.canvas.setWidth(HelloApplication.CANVAS_WIDTH);
        walls = new ArrayList<>();
        points = new ArrayList<>();
        int columns = 0;
        int row = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader("feld.txt"))){
            String line;
            while((line = reader.readLine())!= null){
                columns = line.length();
                row++;
            }
        }catch (IOException e){
            System.out.println("Fehler");
        }
        int blockHeight = HelloApplication.CANVAS_HEIGHT/row;
        int blockLength = HelloApplication.CANVAS_WIDTH/columns;
        int radius;
        int diameter;
        if(blockLength <= blockHeight){
            diameter = blockLength/4;
            radius = blockLength/3;
        }else {
            radius = blockHeight/3;
            diameter = blockHeight/4;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("feld.txt"))){
            String line;
            int counter = 0;
            while((line = reader.readLine())!= null){
                for (int i = 0; i < line.length(); i++) {

                    if(line.charAt(i) == '#'){
                        walls.add(new Wall(i*blockLength,counter*blockHeight,blockHeight,blockLength));
                    }else if(line.charAt(i) == 'P'){
                        pacman = new Player(i*blockLength+((blockLength-(radius*2))/2),counter*blockHeight+((blockHeight-(radius*2))/2), 5, true, radius);
                    }else if(line.charAt(i) == '-'){
                        points.add(new YummyPoint(i*blockLength+((blockLength-diameter)/2),counter*blockHeight+((blockHeight-diameter)/2),diameter));
                    }
                }
                System.out.println();
                counter++;
            }
        }catch (IOException e){
            System.out.println("Fehler");
        }
        renderer = new GameRenderer(canvas, walls, pacman,points, counter);
        renderer.start();
    }

    public void handleKey(KeyCode direction) {
        pacman.setDirection(direction);
    }

}

