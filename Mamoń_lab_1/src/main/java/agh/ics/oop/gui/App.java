package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private int width;
    private int height;
    private AbstractWorldMap map;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);


        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100d /this.width);

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100d /this.height);


        Label startLabel = new Label("y/x");
        gridPane.add(startLabel, 0, 0, 1, 1);
        GridPane.setHalignment(startLabel, HPos.CENTER);

//        gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
//        gridPane.getRowConstraints().add(new RowConstraints(this.height));

        gridPane.getColumnConstraints().add(cc);
        gridPane.getRowConstraints().add(rc);

        for (int x = this.lowerLeft.getX(); x <= this.upperRight.getX(); x++) {
            Label label2Add = new Label("" + x);
            gridPane.add(label2Add, x - this.lowerLeft.getX() + 1, 0, 1, 1);
            GridPane.setHalignment(label2Add, HPos.CENTER);
//            gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
            gridPane.getColumnConstraints().add(cc);
        }

        for (int y = this.lowerLeft.getY(); y <= this.upperRight.getY(); y++) {
            Label label2Add = new Label("" + y);
            gridPane.add(label2Add, 0, this.upperRight.getY() - y + 1, 1, 1);
            GridPane.setHalignment(label2Add, HPos.CENTER);
//            gridPane.getRowConstraints().add(new RowConstraints(this.height));
            gridPane.getRowConstraints().add(rc);
        }

        for (int x = this.lowerLeft.getX(); x <= this.upperRight.getX(); x++)
            for (int y = this.lowerLeft.getY(); y <= this.upperRight.getY(); y++) {
                String value2Add;
                if (this.map.isOccupied(new Vector2d(x, y)))
                    value2Add = this.map.objectAt(new Vector2d(x, y)).toString();
                else
                    value2Add = " ";
                Label label2Add = new Label(value2Add);
                gridPane.add(label2Add,
                        x - this.lowerLeft.getX() + 1,
                        this.upperRight.getY() - y + 1, 1, 1);
                GridPane.setHalignment(label2Add, HPos.CENTER);
            }

        gridPane.setMinWidth(2000);
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        getParameters().getRaw();
        MoveDirection[] directions = new OptionsParser().parse("f b r l f f r r f f f f f f f f".split(" "));
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, this.map, positions);
        engine.run();
        this.lowerLeft = map.getLowerLeft();
        this.upperRight = map.getUpperRight();
        this.width = this.upperRight.getX() - this.lowerLeft.getX() + 1;
        this.height = this.upperRight.getY() - this.lowerLeft.getY() + 1;
    }
}
