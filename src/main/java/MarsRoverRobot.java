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

        System.out.println("Insert L and R commands to rotate the robot and M to advance");

        String commandEnter = sc.nextLine();

        return commandEnter.split("");
    }

    private static void simulateRoverMovement(Integer x, Integer y, String initialDirection, String[] commands) {

        String save = initialDirection;
        Integer actualX = x;
        Integer actualY = y;

        for (String moving : commands) {

            System.out.print("Moving");
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(300);
                    System.out.print(".");
                }
                System.out.println();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


            if (moving.equals("L")) {

                save = rotateLeft(save);

            }
            if (moving.equals("R")) {

                save = rotateRight(save);

            }

            if (moving.equals("M")) {

                if (save.equals("N") || save.equals("S")) {

                    actualX = moveInX(save, actualX);
                }

                if (save.equals("E") || save.equals("O")) {

                    actualY = moveInY(save, actualY);
                }

            }

            System.out.println("The robot is in: " + "X:" + actualX + " " + "Y:" + actualY + " looking at: " + save);
        }



    }

    private static Integer moveInY(String currentDirection, Integer y) {

        if (currentDirection.equals("E")) {
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

        if (currentDirection.equals("N")) {
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

}


