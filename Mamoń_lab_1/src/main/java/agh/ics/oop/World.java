package agh.ics.oop;
import  agh.ics.oop.Direction;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        List<Direction> directs = stringsToDirects(args);
        run(directs);
        System.out.println("system zakończył działanie");
    }

    public static void run(List<Direction> directs){


        int[] counter = {0};
        for(Direction direction : directs) {
            if (counter[0]++ != 0) {
                System.out.println(", ");
            }
            switch (direction) {
                case FORWARD -> System.out.print("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.print("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.print("Zwierzak skręca w prawo");
                case LEFT -> System.out.print("Zwierzak skręca w lewo");
            }
        }
        if (counter[0] > 0)
            System.out.println(".");
    }

    public static List<Direction> stringsToDirects(String[] args){
        List<Direction> result = new ArrayList<>();
        for (String argument : args) {
            switch (argument) {
                case "f" -> result.add(Direction.FORWARD);
                case "b" -> result.add(Direction.BACKWARD);
                case "r" -> result.add(Direction.RIGHT);
                case "l" -> result.add(Direction.LEFT);
            }
        }
        return result;
    }
}