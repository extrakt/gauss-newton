import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project class with main method
 *
 * @author Sidney Durant
 * @author Leia Glezer
 * @author Louis Williams
 */
public class ProjectMain {

    public static void main(String[] args) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        double[][] arrayOfInputs = getInput();
        double[] betaGuess = getBetaGuess();
        int n = getN();
        int f = getFunction();
        //test to make sure it works
        for (double[] l : arrayOfInputs) {
            for (double s : l) {
                System.out.print(s + ",");
            }
            System.out.println();
        }

        GaussNewton gn = new GaussNewton(arrayOfInputs, betaGuess, n);

        switch (f) {
            case 1:
                gn.getGaussQuadratic();
                break;
            case 2:
                gn.getGaussExponential();
                break;
            case 3:
                gn.getGaussLogarithmic();
                break;
            case 4:
                gn.getGaussRational();

        }

        System.out.println(gn.getB());
    }

    private static double[][] getInput() {
        System.out.println("Please enter the name of the text file of points.");
        Scanner scan = new Scanner(System.in);
        String someUserFile = scan.nextLine();
        File file = new File(someUserFile);
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }

        //creates the matrix- pre-reads
        int rows = 0;
        while (inputFile.hasNextLine()) {
            inputFile.nextLine(); //consumes all lines!
            ++rows;
        }
        inputFile.close();
        try {
            inputFile = new Scanner(file); //reinitializes
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }

        double[][] a = new double[rows][2];

        //puts entires into matrix from text fiile
        for (int i = 0; i < rows; ++i) {
            String[] array = inputFile.nextLine().split(",");
            a[i][0] = Double.parseDouble(array[0]);
            a[i][1] = Double.parseDouble(array[1]);
        }
        return a;
    }

    private static double[] getBetaGuess() { //returns a 1d double array of guesses that the user typed in
        System.out.println("Please enter a first number");
        double[] betaArray = new double[3];
        Scanner scan = new Scanner(System.in);
        betaArray[0] = scan.nextDouble();
        System.out.println("Please enter a second number");
        betaArray[1] = scan.nextDouble();
        System.out.println("Please enter a third number");
        betaArray[2] = scan.nextDouble();
        return betaArray;
    }

    private static int getN() {
        System.out.println("Enter number of iterations");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        return n;
    }

    private static int getFunction() {
        System.out.println("Select a function type [Quadratic: 1, Exponential: 2, Logarithmic: 3, Rational: 4]\n");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
}
