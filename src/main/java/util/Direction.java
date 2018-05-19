package main.java.util;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    public int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static final Direction[] ADJACENTS = new Direction[]{UP, DOWN, LEFT, RIGHT};
    public static final Direction[] DIAGONALS = new Direction[]{UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};
    public static final Direction[] SURROUNDING = new Direction[]{UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};

}
