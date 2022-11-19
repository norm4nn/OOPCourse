package agh.ics.oop;

import agh.ics.oop.src.test.java.Vector2dTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MapBoundary implements IPositionChangeObserver {
    final private LinkedList<Vector2d> OX = new LinkedList<>();
    final private LinkedList<Vector2d> OY = new LinkedList<>();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (this.OX.get(0).equals(oldPosition)) this.OX.remove(0);
        else if (this.OX.get(this.OX.size() - 1).equals(oldPosition)) this.OX.remove(this.OX.size() - 1);

        if (this.OY.get(0).equals(oldPosition)) this.OY.remove(0);
        else if (this.OY.get(this.OY.size() - 1).equals(oldPosition)) this.OY.remove(this.OY.size() - 1);

        this.addPosition(newPosition);
    }

    private void addToOX(Vector2d element) {

        for(int i=0; i < this.OX.size(); ++i )
            if (!element.lowerLeft(this.OX.get(i)).equals(this.OX.get(i)) && element.getX() <= this.OX.get(i).getX()) {
                this.OX.add(i, element);
                return;
            }

        this.OX.add(element);
    }

    private void addToOY(Vector2d element) {

        for(int i=this.OY.size() - 1; i >= 0; --i )
            if (!element.upperRight(this.OY.get(i)).equals(this.OY.get(i)) && element.getY() >= this.OY.get(i).getY()) {
                this.OY.add(i + 1, element);
                return;
            }
        this.OY.addFirst(element);
    }

    public void addPosition(Vector2d element) {
        this.addToOX(element);
        this.addToOY(element);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(this.OX.get(0).getX(), this.OY.get(0).getY());
    }

    public Vector2d getUpperRight() {
        return new Vector2d(this.OX.get(this.OX.size() - 1).getX(), this.OY.get(this.OY.size() - 1).getY());
    }
}
