package com.example.view;

import com.example.Configuration;
import com.example.model.Node;
import com.example.presenter.CatLaserPresenter;
import com.example.presenter.ICatLaserPresenter;
import com.google.inject.Inject;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class CatLaserView implements ICatLaserView {

    private TreeMap<Node, Circle> nodes = new TreeMap<>();
    private List<Circle> circles = new LinkedList<>();


    private ScrollPane scrollPane;
    private final Pane drawingPane = new Pane();
    private final double gridSize = 20.0; // Rozmiar komórki siatki
    private ICatLaserPresenter presenter;
    private static int PREF_WIDTH = Configuration.PREF_WIDTH;
    private static int PREF_HEIGHT = Configuration.PREF_HEIGHT;

    @Inject
    public CatLaserView() {
        setupScrollPane();
        drawGrid();
    }

    @Inject
    @Override
    public void setPresenter(ICatLaserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void toggleNodeColor(Node node) {
        Circle circle = nodes.get(node);
        circle.setFill(circle.getFill() == Color.RED ? Color.GREEN : Color.RED);
    }

    private void drawGrid() {
        for (double i = 0; i <= PREF_WIDTH; i += gridSize) {
            for (double j = 0; j <= PREF_HEIGHT; j += gridSize) {
                Circle circle = new Circle(i, j, 2, Color.LIGHTGRAY);
                drawingPane.getChildren().add(circle);
            }
        }
    }

    private void setupScrollPane() {
        scrollPane = new ScrollPane(drawingPane);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        drawingPane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                presenter.onSpacePressed();
            }
        });
    }

    @Override
    public synchronized void addNode(Node node) {
        if (!nodes.containsKey(node)) {
            Circle circle = new Circle(node.getX(), node.getY(), 10, Color.RED);
            circle.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    System.out.println("Double click");
                    presenter.onNodeDoubleClicked(node);
                } else if (event.getButton() == MouseButton.PRIMARY) {
                    presenter.onNodeSelected(node);
                }
            });
            circle.setOnMouseDragged(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    System.out.println("Dragged " + event.getX() + " " + event.getY());
                    presenter.onNodeDragged(node, constraint(event.getX(),PREF_WIDTH), constraint(event.getY(),PREF_HEIGHT));
                    event.consume();
                }
            });

            circles.add(circle);
            nodes.put(node, circle);
            drawingPane.getChildren().add(circle);
        }
    }

    public int constraint(double value, int maxValue){
        return (int) Math.max(0, Math.min(value, maxValue));
    }

    @Override
    public void resetNodes(Iterable<Node> nodes) {
        drawingPane.getChildren().clear();
        this.nodes.clear();
        this.circles.clear();
        drawGrid();
        nodes.forEach(this::addNode);
        relinkNodes();
    }

    @Override
    public void repositionNodes() {
        nodes.forEach((node, circle) -> {
            circle.setCenterX(node.getX());
            circle.setCenterY(node.getY());
        });
        relinkNodes();
    }

    private void relinkNodes() {
        Iterator<Circle> it = circles.iterator();
        Circle circle = it.next();
        while (it.hasNext()) {
            Circle nextCircle = it.next();
            Line line = createLine(circle, nextCircle);
            drawingPane.getChildren().add(line);
            circle = nextCircle;
        }
        drawingPane.getChildren().add(createLine(circle, circles.get(0)));
    }

    private Line createLine(Circle start, Circle end) {
        Line line = new Line();
        line.startXProperty().bind(start.centerXProperty());
        line.startYProperty().bind(start.centerYProperty());
        line.endXProperty().bind(end.centerXProperty());
        line.endYProperty().bind(end.centerYProperty());
        return line;
    }

    @Inject
    public void setPresenter(CatLaserPresenter presenter) {
        this.presenter = presenter;
    }

    public ScrollPane getView() {
        return scrollPane;
    }

}
