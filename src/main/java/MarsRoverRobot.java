import java.util.Scanner;

public class MarsRoverRobot {

    public static void main(String[] args) {

        System.out.println(" ");
        System.out.println("The current position of the robot is: X:0, Y:0, N");
        System.out.println(" ");

        String[] choose = getCommandsFromUserInput();

        simulateRoverMovement(0, 0, "N", choose);

    }

    private static String[] getCommandsFromUserInput() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the L or R commands to rotate the robot and F or B to move forward or backward.");

        String commandEnter = sc.nextLine().toUpperCase();

        return commandEnter.split("");
    }

    private static void simulateRoverMovement(Integer x, Integer y, String initialDirection, String[] commands) {

        String save = initialDirection;
        Integer actualX = x;
        Integer actualY = y;

        for (String moving : commands) {

            movingOrRoting(moving);

            waitAndPrintDots();


            if (moving.equals("L")) {

                save = rotateLeft(save);

            }
            if (moving.equals("R")) {

                save = rotateRight(save);

            }

            if (moving.equals("F")) {

                if (save.equals("N") || save.equals("S")) {

                    actualY = moveInY(save, actualY);

                }

                if (save.equals("E") || save.equals("O")) {

                    actualX = moveInX(save, actualX);
                }

            }

            if (moving.equals("B")) {

                if (save.equals("N") || save.equals("S")) {

                    actualY = moveBackInY(save, actualY);
                }

                if (save.equals("E") || save.equals("O")) {

                    actualX = moveBackInX(save, actualX);
                }

            }

            System.out.println("The robot is in: " + "X:" + actualX + " " + "Y:" + actualY + " looking at: " + save);
        }

            Scanner sc = new Scanner(System.in);
            System.out.println("Continue game, YES or NO");
            String choose = sc.nextLine().toUpperCase();

            continueGameOrNot(actualX,actualY,save,choose);
    }

    private static void movingOrRoting(String moving){

        if (moving.equals("L")) {
            System.out.print("Rotating to the left");
        } else if (moving.equals("R")) {
            System.out.print("Rotating to the right");
        } else {
            System.out.print("Moving");
        }

    }

    private static Integer moveBackInY(String currentDirection, Integer y) {

        if (currentDirection.equals("N")) {
            if (y > 0) {
                return y - 1;

            } else {
                return 10;
            }

        } else if (y < 10) {
            return y + 1;
        } else {
            return 0;
        }
    }

    private static Integer moveBackInX(String currentDirection, Integer x) {

        if (currentDirection.equals("E")) {
            if (x > 0) {
                return x - 1;

            } else {
                return 10;
            }

        } else if (x < 10) {
            return x + 1;
        } else {
            return 0;
        }


    }

    private static Integer moveInY(String currentDirection, Integer y) {

        if (currentDirection.equals("N")) {
            if (y < 10) {
                return y + 1;

            } else {
                return 1;
            }

        } else if (y > 0) {
            return y - 1;
        } else {
            return 10;
        }

    }

    private static Integer moveInX(String currentDirection, Integer x) {

        if (currentDirection.equals("E")) {
            if (x < 10) {
                return x + 1;

            } else {
                return 0;
            }

        } else if (x > 0) {
            return x - 1;
        } else {
            return 10;
        }

    }

    private static String rotateRight(String currentDirection) {

        return switch (currentDirection) {
            case "N" -> "E";
            case "S" -> "O";
            case "E" -> "S";
            default -> "N";
        };

    }

    private static String rotateLeft(String currentDirection) {

        return switch (currentDirection) {
            case "N" -> "O";
            case "S" -> "E";
            case "E" -> "N";
            default -> "S";
        };

    }

    private static void continueGameOrNot(Integer x , Integer y , String currentDirection, String choose){

        if (choose.equals("YES")){
            String[] commands = getCommandsFromUserInput();
            simulateRoverMovement(x,y,currentDirection , commands);
        }

    }

    private static void waitAndPrintDots() {

        try {
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}


