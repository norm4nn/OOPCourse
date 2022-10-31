package agh.ics.oop;

import agh.ics.oop.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine {

    final private MoveDirection[] moves;

    final private IWorldMap map;
    final private ArrayList<Animal> animalArrayList;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;
        LinkedList<Animal> animalLinkedList = new LinkedList<>();
        for(Vector2d position:initialPositions) {
            if (map.canMoveTo(position))
                animalLinkedList.add(new Animal(map, position));
        }

        this.animalArrayList = new ArrayList<>(animalLinkedList);
    }

    @Override
    public void run() {
        for(int i = 0; i < this.moves.length; ++i) {
//            System.out.println(this.map);
            this.animalArrayList.get(i % this.animalArrayList.size()).move(moves[i]);
        }
//        System.out.println(this.map);
    }
}
