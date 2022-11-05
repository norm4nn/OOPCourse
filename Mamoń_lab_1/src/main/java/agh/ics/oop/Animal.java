package agh.ics.oop;


public class Animal extends AbstractWorldMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    final private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.map = new RectangularMap(5, 5);
    }

    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "^";
            case SOUTH -> "V";
            case WEST -> "<";
            case EAST -> ">";
        };
    }

    public void move(MoveDirection direction) {
        if (direction.equals(MoveDirection.LEFT) || direction.equals(MoveDirection.RIGHT)){
            switch (direction) {
                case LEFT -> this.orientation = this.orientation.previous();
                case RIGHT -> this.orientation = this.orientation.next();
            }
            return;
        }

        Vector2d modifier = this.orientation.toUnitVector();
        if (direction.equals(MoveDirection.BACKWARD)) {
            modifier = this.orientation.toUnitVector().opposite();
        }

        final Vector2d newPosition = this.position.add(modifier);

        if (this.map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

}
