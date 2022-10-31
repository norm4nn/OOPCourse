package agh.ics.oop;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class RectangularMap implements IWorldMap{

    ArrayList<Object> objects = new ArrayList<>();
    final private Vector2d bottomLeft;
    final private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.bottomLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(this.bottomLeft, this.upperRight);
    }

    private boolean isInScope(Vector2d position) { return position.follows(this.bottomLeft) && position.precedes(this.upperRight); }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.isInScope(position) && !(this.isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.objects.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (!this.isInScope(position)) return true;
        return  this.objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.isInScope(position)) {
            for (Object object: this.objects) {
                if ( object instanceof Animal && ((Animal) object).isAt(position))   return object;
            }
        }
        return null;
    }

}
