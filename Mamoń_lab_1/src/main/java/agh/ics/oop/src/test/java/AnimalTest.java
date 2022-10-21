package agh.ics.oop.src.test.java;

import agh.ics.oop.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnimalTest {
    private int testCases;
    private Animal[] animals;
    private String[][] allArgs;
    private Vector2d[] vectors;
    private MapDirection[] mapDirections;



    @BeforeAll
    private void setup() {
        this.testCases = 7;

        String[] args_1 = new String[]{"s", "forward","f","f", "b","r", "f", "x", "left"};//(3,3) NORTH
        String[] args_2 = new String[]{"sas", "b","left","f", "l","forward","f", "xs", "xs", "left"};//(1,0) EAST
        String[] args_3 = new String[]{"sas", "bas"};//(2,2) NORTH
        String[] args_4 = new String[]{"sas","left", "r", "r","rabus","right", "bas"};//(2,2) SOUTH
        String[] args_5 = new String[]{};//(2,2) NORTH
        String[] args_6 = new String[]{"lef", "left", "f", "front", "forward", "forward", "f", "f", "right", "right", "b", "r", "backward"};//(0,3) SOUTH
        String[] args_7 = new String[]{"lef", "left", "backward", "br", "b", "b"};//(4,2) WEST

        String[][] allArgs = new String[][]{args_1, args_2, args_3, args_4, args_5, args_6, args_7};

        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[][] moveDirections = new MoveDirection[testCases][];
        this.animals = new Animal[testCases];

        for (int i=0; i < this.testCases; ++i) {

            this.animals[i] = new Animal();
            moveDirections[i] = optionsParser.parse(allArgs[i]);

            for(MoveDirection moveDirection:moveDirections[i]) {
                this.animals[i].move(moveDirection);
            }
        }

    }

    @BeforeEach
    private void init() {
        Vector2d vector2d_1 = new Vector2d(3, 3);
        Vector2d vector2d_2 = new Vector2d(1, 0);
        Vector2d vector2d_3 = new Vector2d(2, 2);
        Vector2d vector2d_4 = new Vector2d(2, 2);
        Vector2d vector2d_5 = new Vector2d(2, 2);
        Vector2d vector2d_6 = new Vector2d(0, 3);
        Vector2d vector2d_7 = new Vector2d(4, 2);

        this.vectors = new Vector2d[]{vector2d_1, vector2d_2, vector2d_3, vector2d_4, vector2d_5, vector2d_6, vector2d_7};

        this.mapDirections = new MapDirection[]{MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.SOUTH,
                                                MapDirection.NORTH, MapDirection.SOUTH, MapDirection.WEST};

    }


    @Test
    public void testToString() {
        assertEquals("[Pozycja: (3,3) | Orientacja: Północ]", this.animals[0].toString());
        assertEquals("[Pozycja: (1,0) | Orientacja: Wschód]", this.animals[1].toString());
        assertEquals("[Pozycja: (2,2) | Orientacja: Północ]", this.animals[2].toString());
        assertEquals("[Pozycja: (2,2) | Orientacja: Południe]", this.animals[3].toString());
        assertEquals("[Pozycja: (2,2) | Orientacja: Północ]", this.animals[4].toString());
        assertEquals("[Pozycja: (0,3) | Orientacja: Południe]", this.animals[5].toString());
        assertEquals("[Pozycja: (4,2) | Orientacja: Zachód]", this.animals[6].toString());
    }

    @Test
    public void testPosition() {
        for(int i=0; i < this.testCases; ++i)
            assertTrue(this.animals[i].isAt(vectors[i]));

        for(int i=0; i < this.testCases; ++i)
            vectors[i] = vectors[i].opposite();

        for(int i=0; i < this.testCases; ++i)
            assertFalse(this.animals[i].isAt(vectors[i]));
    }

    @Test
    public void  testOrientation() {
        for(int i=0; i < this.testCases; ++i)
            assertEquals(this.mapDirections[i], this.animals[i].getOrientation());

        for(int i=0; i < this.testCases; ++i)
            this.mapDirections[i] = this.mapDirections[i].previous();

        for(int i=0; i < this.testCases; ++i)
            assertNotEquals(this.mapDirections[i], this.animals[i].getOrientation());
    }

}
