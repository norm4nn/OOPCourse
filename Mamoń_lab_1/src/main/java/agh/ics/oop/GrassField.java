package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    final private int grassAmount;
    public GrassField(int grassAmount) {
        int i = 0;
        this.grassAmount = grassAmount;
        while (i < grassAmount){
            Vector2d randomVector = new Vector2d((int)(Math.random() * (Math.sqrt(grassAmount*10) + 1)),
                                                 (int)(Math.random() * (Math.sqrt(grassAmount*10) + 1)));

            if (!(objectAt(randomVector) instanceof Grass)) {
                this.grassHashMap.put(randomVector, new Grass(randomVector));
                ++i;
            }
        }
    }
    @Override
    protected Vector2d getLowerLeft() {

        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Map.Entry<Vector2d, Animal> entry : this.animalHashMap.entrySet())
            lowerLeft = lowerLeft.lowerLeft(entry.getKey());

        for (Map.Entry<Vector2d, Grass> entry : this.grassHashMap.entrySet())
            lowerLeft = lowerLeft.lowerLeft(entry.getKey());

        return lowerLeft;
    }
    @Override
    protected Vector2d getUpperRight() {

        Vector2d upperRight = new Vector2d(0,0);

        for (Map.Entry<Vector2d, Animal> entry : this.animalHashMap.entrySet())
            upperRight = upperRight.upperRight(entry.getKey());

        for (Map.Entry<Vector2d, Grass> entry : this.grassHashMap.entrySet())
            upperRight = upperRight.upperRight(entry.getKey());

        return upperRight;
    }

    @Override
    protected boolean isInScope(Vector2d position) {
        return position.follows(new Vector2d(0, 0));
    }

}
