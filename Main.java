import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    private static void printSquare(int height, int textRow, String text) {
        int textOffset = (height - text.length()) / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                if (y == textRow - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
    }

    private static void printTriangle(int height, int textRow, String text) {
        for (int y = 0; y <= height; y++) {

            for (int space = height - y; space > 0; space--) {
                System.out.print(" ");
            }
            for (int x = 0; x < y; x++) {
                System.out.print("X ");
            }
            System.out.println();
        }
    }

    private static void printDiamond(int height, int textRow, String text) {
        int halfHeight = (height / 2) + (height % 2);
        for (int y = 0; y < halfHeight; y++) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");
            }
            for (int x = 0; x < y; x++) {
                System.out.print("X   ");
            }
            System.out.println();
        }
        for (int y = halfHeight; y > 0; y--) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");
            }
            for (int x = 0; x < y; x++) {
                System.out.print("X   ");
            }
            System.out.println();
        }
    }

    private static void printRectangle(int height, int textRow, String text) {
        int textOffset = (height * 2 - text.length()) / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height * 2; x++) {
                if (y == textRow - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
    }

    private static int getNextValidInt(String messagePrompt) {
        boolean validInput;
        Scanner validNumScanner = new Scanner(System.in);
        int validNum;
        do {
            System.out.print(messagePrompt);
            validInput = validNumScanner.hasNextInt();
            if (!validInput) {
                System.out.print("\n** Error: Please Enter A VALID Number **");
                validNumScanner.next();
            }

        } while (!validInput);

        do {
            validNum = validNumScanner.nextInt();

            if (validNum <= 0) {
                System.out.print("\n** Error: Please Enter A POSITIVE Number **");
                System.out.print(messagePrompt);
            }

        } while (validNum <= 0);

        validNumScanner.nextLine(); 
        return validNum;
    }

    private static void shapeBuilder(Vector <String> shapeOptions) {
        Scanner shapeBuilderInput = new Scanner(System.in);
        String shapeType, shapeText;
        int shapeHeight, shapeTextRow;

        do {
            System.out.print("\nWhat shape should I draw:  ");
            shapeType = shapeBuilderInput.nextLine();

            if (!shapeOptions.contains(shapeType)) {
                System.out.print("\n** Error: please enter a valid shape **");
            }

        } while (!shapeOptions.contains(shapeType));

        shapeHeight = getNextValidInt("\nEnter the height of the shape: ");


        System.out.print("\nWhat label should I print on this " + shapeType + " (Leave Blank for \"LU\")?");
        shapeText = shapeBuilderInput.nextLine();

        if (Objects.equals(shapeText, "")) {
            shapeText = "LU";
        }

        do {
            shapeTextRow = getNextValidInt("\nWhat row should I print " + shapeText + "\": ");

            if (shapeTextRow > shapeHeight) {
                System.out.print("\n** Error: please Enter A number LESS THAN " + shapeHeight + "! **");
            }
        } while (shapeTextRow > shapeHeight);

        System.out.println("\n\n\n");
        switch (shapeType) {
            case "Square":
            case "square":
                printSquare(shapeHeight, shapeTextRow, shapeText);
                break;
            case "Rectangle":
            case "rectangle":
                printRectangle(shapeHeight, shapeTextRow, shapeText);
                break;
            case "Diamond":
            case "diamond":
                printDiamond(shapeHeight, shapeTextRow, shapeText);
                break;
            case "Triangle":
            case "triangle":
                printTriangle(shapeHeight, shapeTextRow, shapeText);
                break;
            default:
                printSquare(shapeHeight, shapeTextRow, shapeText);
        }
    }


    public static void main(String[] args) {

        boolean buildANotherShape;
        String nextShapeResponse;
        Scanner nextShapeScanner = new Scanner(System.in);
        Vector<String> shapeOptions = new Vector<>();
        shapeOptions.add("Square");
        shapeOptions.add("Triangle");
        shapeOptions.add("Diamond");
        shapeOptions.add("Rectangle");
        shapeOptions.add("square");
        shapeOptions.add("triangle");
        shapeOptions.add("diamond");
        shapeOptions.add("rectangle");

        System.out.println("\nSHAPE CHOICES: Diamond, Square, Rectangle, Triangle");

        do {
            shapeBuilder(shapeOptions);

            System.out.print("Do you want to build another shape?(Y/N)");
            nextShapeResponse = nextShapeScanner.nextLine();;
            buildANotherShape = (Objects.equals(nextShapeResponse, "Yes") || Objects.equals(nextShapeResponse, "yes") || Objects.equals(nextShapeResponse, "y") || Objects.equals(nextShapeResponse, "Y"));

        } while(buildANotherShape);
    }
}