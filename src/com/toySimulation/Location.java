package com.toySimulation;

public class Location {
    // Location has a row coordinate and a column coordinate
    private int x_coordinate;
    private int y_coordinate;
    public Location(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }
    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }
    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }
}
