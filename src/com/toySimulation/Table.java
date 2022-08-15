package com.toySimulation;

public class Table {
    private final int ROW = 5;
    private final int COLUMN = 5;
    Square[][] tableSquares = new Square[ROW][COLUMN];
    private Robot robot;

    public Table() {
        // Loop through all rows
        for (int i = 0; i < tableSquares.length; i++ ) {
            for (int j = 0; j < tableSquares.length; j++) {
                Location location = new Location(i, j);
                Square newSquare = new Square(location);
                tableSquares[i][j] = newSquare;
            }
        }
    }

    public void printBoard() {
        for (Square[] row : tableSquares) {
            for (Square square : row) {
                System.out.println(square.getLocation().getX_coordinate() + " " + square.getLocation().getY_coordinate());
            }
        }
    }
}
