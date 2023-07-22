package com.company.engine;

public class Point {
    private int X;
    private int Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    Point translate(Translation translation) {
        return new Point(this.getX() + translation.getX(), this.getY() + translation.getY());
    }

    Point copy() {
        return new Point(this.getX(), this.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return X == point.X && Y == point.Y;
    }
}
