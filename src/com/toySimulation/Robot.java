package com.toySimulation;

public class Robot {

    private Square square;
    // Improvement : Is this the active Robot?
    // Facing towards which direction?
    private Direction facingTowardsDirection;

    public Robot(Square square, Direction facingTowardsDirection) {
        this.square = square;
        this.facingTowardsDirection = facingTowardsDirection;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Direction getFacingTowardsDirection() {
        return facingTowardsDirection;
    }

    public void setFacingTowardsDirection(Direction facingTowardsDirection) {
        this.facingTowardsDirection = facingTowardsDirection;
    }





}
