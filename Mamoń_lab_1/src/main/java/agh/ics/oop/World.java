package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {


        Animal animal = new Animal();
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(args);

        System.out.println(animal);

        for(MoveDirection direction:directions)
            animal.move(direction);

        System.out.println(animal);
    }



    public static List<MoveDirection> stringsToDirects(String[] args){
        List<MoveDirection> result = new ArrayList<>();
        for (String argument : args) {
            switch (argument) {
                case "f" -> result.add(MoveDirection.FORWARD);
                case "b" -> result.add(MoveDirection.BACKWARD);
                case "r" -> result.add(MoveDirection.RIGHT);
                case "l" -> result.add(MoveDirection.LEFT);
            }
        }
        return result;
    }
}