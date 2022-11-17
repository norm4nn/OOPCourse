package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();
    protected HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();

    protected abstract boolean isInScope(Vector2d position);
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    @Override
    public void  positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animalHashMap.get(oldPosition);
        this.animalHashMap.remove(oldPosition);
        this.animalHashMap.put(newPosition, animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInScope(position) && !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.animalHashMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return  this.objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animalHashMap.containsKey(position))
            return this.animalHashMap.get(position);
        return this.grassHashMap.get(position);
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.getLowerLeft(), this.getUpperRight());
    }

}
