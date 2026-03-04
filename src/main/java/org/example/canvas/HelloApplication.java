package org.example.canvas;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public final  static int CANVAS_HEIGHT = 700;
    public final  static int CANVAS_WIDTH = 700;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load() );
        HelloController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                controller.handleKey(code);
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
