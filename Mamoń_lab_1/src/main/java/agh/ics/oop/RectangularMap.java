package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{

    final ArrayList<Object> objects = new ArrayList<>();
    final private Vector2d bottomLeft;
    final private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.bottomLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    protected Vector2d getLowerLeft() {
        return this.bottomLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        return this.upperRight;
    }

    @Override
    protected boolean isInScope(Vector2d position) {
        return position.follows(this.bottomLeft) && position.precedes(this.upperRight);
    }


}
