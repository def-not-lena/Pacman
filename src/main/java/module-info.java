module org.example.canvas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.canvas to javafx.fxml;
    exports org.example.canvas;
}