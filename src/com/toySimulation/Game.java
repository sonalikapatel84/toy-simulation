package com.toySimulation;

import java.util.Scanner;

public class Game {

    private int xCoordinate;
    private int yCoordinate;
    private Direction direction;

    public static void main(String[] args) throws OutOfTableBoardException {
        Scanner myObj = new Scanner(System.in);
        String userInput = null;
        Robot robot = null;

        System.out.println("Enter command");
        userInput = myObj.nextLine().toUpperCase();
        String[] userInputs = userInput.split(" ");
        if ((userInputs != null) && (userInputs.length != 2)) {
            System.out.println("Please make sure the command follows the pattern \"place 0,0,north\" ");
            throw new RuntimeException();
        }
        if (!userInputs[0].equals("PLACE")) { // Assuming the command is given like this PLACE X,Y,F
            System.out.println("Please make a \"Place\" command to start the game.");
        } else {
            String[] strings = userInputs[1].split(",");
            Location location = new Location(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            if (outOfEdge(location)) {
                throw new OutOfTableBoardException();
            } else {
                Square square = new Square(location);
                robot = new Robot(square, getDirection(strings[2]));
            }
        }

        do {
            userInput = myObj.nextLine().toUpperCase();
            switch (userInput) {
                case "PLACE":
                    // This would be a future solution when multiple robots can be created and placed
                    // in the board simultaneously.
                    break;
                case "MOVE":
                    goStraight(robot);
                    break;
                case "LEFT":
                    goLeft(robot);
                    break;
                case "RIGHT":
                    goRight(robot);
                    break;
                case "REPORT":
                    getReport(robot);
                    break;
                default:
            }
        } while (userInput != null && !userInput.equals("quit"));
    }

    /**
     * Method to return the Direction object for the string direction.
     * @param dir The string direction. e.g. East
     * @return The {@link Direction} object. e.g, Direction.EAST
     */
    private static Direction getDirection(String dir) {
        if (dir.equals("EAST")) {
            return Direction.EAST;
        } else if (dir.equals("WEST")) {
            return Direction.WEST;
        } else if (dir.equals("NORTH")) {
            return Direction.NORTH;
        } else if (dir.equals("SOUTH")) {
            return Direction.SOUTH;
        }
        return null;
    }

    /**
     * Method to face direction of robot when "right" command placed.
     * @param robot The {@link Robot} object.
     */
    private static void goRight(Robot robot) {
        Direction direction = robot.getFacingTowardsDirection();
        switch (direction) {
            case EAST:
                robot.setFacingTowardsDirection(Direction.SOUTH);
                break;
            case WEST:
                robot.setFacingTowardsDirection(Direction.NORTH);
                break;
            case NORTH:
                robot.setFacingTowardsDirection(Direction.EAST);
                break;
            case SOUTH:
                robot.setFacingTowardsDirection(Direction.WEST);
                break;
            default:
                break;
        }
    }

    /**
     * Method to face direction of robot when "left" command placed.
     * @param robot The {@link Robot} object.
     */
    private static void goLeft(Robot robot) {
        Direction direction = robot.getFacingTowardsDirection();
        switch (direction) {
            case EAST:
                robot.setFacingTowardsDirection(Direction.NORTH);
                break;
            case WEST:
                robot.setFacingTowardsDirection(Direction.SOUTH);
                break;
            case NORTH:
                robot.setFacingTowardsDirection(Direction.WEST);
                break;
            case SOUTH:
                robot.setFacingTowardsDirection(Direction.EAST);
                break;
            default:
                break;
        }
    }

    /**
     * Method to face direction of robot when "move" command placed.
     * @param robot The {@link Robot} object.
     */
    private static void goStraight(Robot robot) throws OutOfTableBoardException {
        if (robot.getFacingTowardsDirection().equals(Direction.NORTH) ||
                robot.getFacingTowardsDirection().equals(Direction.SOUTH)) {
            Location newLocation = new Location(robot.getSquare().getLocation().getX_coordinate(),
                    robot.getSquare().getLocation().getY_coordinate() + 1);
            if (outOfEdge(newLocation)) {
                throw new OutOfTableBoardException();
            } else {
                robot.setSquare(new Square(newLocation));
            }
        }
        if (robot.getFacingTowardsDirection().equals(Direction.EAST) ||
                robot.getFacingTowardsDirection().equals(Direction.WEST)) {
            Location newLocation = new Location(robot.getSquare().getLocation().getX_coordinate() + 1,
                    robot.getSquare().getLocation().getY_coordinate());
            if (outOfEdge(newLocation)) {
                throw new OutOfTableBoardException();
            } else {
                robot.setSquare(new Square(newLocation));
            }
        }
    }

    private static void getReport(Robot robot) {
        System.out.println("Output: " + robot.getSquare().getLocation().getX_coordinate() + ", " +
                robot.getSquare().getLocation().getY_coordinate() + ", " + robot.getFacingTowardsDirection());
    }

    /**
     * Method to check if the location is out of the table coordinates.
     * This is to make sure the next move is correct and the robot should not fall.
     * @param location The corrodinates of the {@link Location} object.
     * @return true, if the location is out of the table., Otherwise false.
     */
    public static boolean outOfEdge(Location location) {
        return location.getX_coordinate() < 0  || location.getY_coordinate() < 0 ||
                location.getX_coordinate() > 4 || location.getY_coordinate() > 4;
    }

    /**
     * Exception class which can be thrown when the outOfEdge case happens.
     */
    public static class OutOfTableBoardException extends Exception {
        public void OutOfTableBoardException(String errorMessage) {
            System.out.println("This location is out of board and the toy might fall, hence ignoring the move.");
        }
    }
}