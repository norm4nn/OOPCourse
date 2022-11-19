package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        }
            catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
//        Application.launch(App.class, args);
//        try {
//            MoveDirection[] directions;
//            directions = new  OptionsParser().parse("f b r l f f r r f f f f f f f f".split(" "));
//            AbstractWorldMap map = new GrassField(10);
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//            System.out.println(map);
//            Application.launch(App.class, args);
//        }
//        catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
}