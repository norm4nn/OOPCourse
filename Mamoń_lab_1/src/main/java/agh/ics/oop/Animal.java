package agh.ics.oop;


public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);


    public String toString() {
        return "[Pozycja: "+this.position+" | Orientacja: "+this.orientation+"]";
    }

    public Boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {

        if (direction.equals(MoveDirection.LEFT) || direction.equals(MoveDirection.RIGHT)){
            switch (direction) {
                case LEFT -> this.orientation = this.orientation.previous();
                case RIGHT -> this.orientation = this.orientation.next();
            }
            return;
        }

        final Vector2d startVector = new Vector2d(0,0);
        final Vector2d endVector = new Vector2d(4, 4);

        Vector2d modifier = this.orientation.toUnitVector();

        if (direction.equals(MoveDirection.BACKWARD)) {
            modifier = this.orientation.toUnitVector().opposite();
        }

        final Vector2d newPosition = this.position.add(modifier);

        if (newPosition.follows(startVector) && newPosition.precedes(endVector)) {
            this.position = newPosition;
        }
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }


}
