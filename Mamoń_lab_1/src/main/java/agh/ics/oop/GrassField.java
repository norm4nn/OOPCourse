package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GrassField extends AbstractWorldMap{
    final private int grassAmount;
    public GrassField(int grassAmount) {
        int i = 0;
        this.grassAmount = grassAmount;
        while (i < grassAmount){
            Vector2d randomVector = new Vector2d((int)(Math.random() * (Math.sqrt(grassAmount*10) + 1)),
                                                 (int)(Math.random() * (Math.sqrt(grassAmount*10) + 1)));

            if (!(objectAt(randomVector) instanceof Grass)) {
                this.mapElements.add(new Grass(randomVector));
                ++i;
            }
        }
    }
    @Override
    protected Vector2d getLowerLeft() {
        Vector2d lowerLeft = this.mapElements.get(0).getPosition();

        for (AbstractWorldMapElement element:this.mapElements)
            if (element.getPosition().lowerLeft(lowerLeft).precedes(lowerLeft))
                lowerLeft = lowerLeft.lowerLeft(element.getPosition());

        return lowerLeft;
    }
    @Override
    protected Vector2d getUpperRight() {
        Vector2d upperRight = this.mapElements.get(0).getPosition();
        for (AbstractWorldMapElement element:this.mapElements)
            if (element.getPosition().upperRight(upperRight).follows(upperRight))
                upperRight = upperRight.upperRight(element.getPosition());

        return upperRight;
    }

    @Override
    protected boolean isInScope(Vector2d position) {
        return position.follows(new Vector2d(0, 0));
    }

}
