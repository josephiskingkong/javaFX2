package org.example.javafx2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {
    private final Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Button addCircleBtn = new Button("Circle");
        Button addRectangleBtn = new Button("Rectangle");
        Button addTriangleBtn = new Button("Triangle");

        addCircleBtn.setOnAction(e -> pane.getChildren().add(createRandomCircle()));
        addRectangleBtn.setOnAction(e -> pane.getChildren().add(createRandomRectangle()));
        addTriangleBtn.setOnAction(e -> pane.getChildren().add(createRandomTriangle()));

        HBox buttonBox = new HBox(10, addCircleBtn, addRectangleBtn, addTriangleBtn);

        buttonBox.setStyle("-fx-alignment: center");

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(buttonBox);

        root.setStyle("-fx-padding: 20px");

        Scene scene = new Scene(root, 1024, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2D Example");
        primaryStage.show();
    }

    private Circle createRandomCircle() {
        double radius = 25 + random.nextDouble() * 50;
        double x = random.nextDouble() * 1024;
        double y = random.nextDouble() * 800;
        Circle circle = new Circle(x, y, radius, getRandomColor());
        enableDragging(circle);
        return circle;
    }

    private Rectangle createRandomRectangle() {
        double width = 50 + random.nextDouble() * 100;
        double height = 50 + random.nextDouble() * 100;
        double x = random.nextDouble() * (1024 - width);
        double y = random.nextDouble() * (800 - height);
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(getRandomColor());
        enableDragging(rectangle);
        return rectangle;
    }

    private Polygon createRandomTriangle() {
        double size = 50 + random.nextDouble() * 150;
        double x = random.nextDouble() * 1024;
        double y = random.nextDouble() * 800;
        Polygon triangle = new Polygon(
                x, y,
                x + size, y,
                x + size / 2, y - size
        );
        triangle.setFill(getRandomColor());
        enableDragging(triangle);
        return triangle;
    }

    private Color getRandomColor() {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    private void enableDragging(javafx.scene.shape.Shape shape) {
        shape.setOnMousePressed(event -> shape.toFront());
        shape.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                shape.setFill(getRandomColor());
            }
        });
        shape.setOnMouseDragged(event -> {
            if (shape instanceof Circle) {
                ((Circle) shape).setCenterX(event.getX());
                ((Circle) shape).setCenterY(event.getY());
            } else if (shape instanceof Rectangle) {
                ((Rectangle) shape).setX(event.getX() - ((Rectangle) shape).getWidth() / 2);
                ((Rectangle) shape).setY(event.getY() - ((Rectangle) shape).getHeight() / 2);
            } else if (shape instanceof Polygon) {
                Polygon triangle = (Polygon) shape;
                double offsetX = event.getX() - triangle.getPoints().get(0);
                double offsetY = event.getY() - triangle.getPoints().get(1);
                for (int i = 0; i < triangle.getPoints().size(); i += 2) {
                    triangle.getPoints().set(i, triangle.getPoints().get(i) + offsetX);
                    triangle.getPoints().set(i + 1, triangle.getPoints().get(i + 1) + offsetY);
                }
            }
            shape.toFront();
        });
    }
}