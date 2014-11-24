import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Louis
 */
public class CommandParser {

    static final String usage = "Usage: java -jar gn_[qua | exp | log | rat].jar -f points_file -b a,b,c -i iterations [-h | -g]\n"
            + "Gauss-Newton method for approximating functions.\n\n"
            + "\tgn_qua: Approximates a quadratic function\n"
            + "\tgn_exp: Approximates a exponential function\n"
            + "\tgn_log: Approximates a logarithmic function\n"
            + "\tgn_rat: Approximates a rational function\n"
            + "\t-f points_file: CSV of x,y coordiate pairs\n"
            + "\t-b a,b,c: a, b, and c componenets of the initial Beta vector\n"
            + "\t-i iterations: Number of iterations\n"
            + "\t-h Use Householder reflections\n"
            + "\t-g Use Given's rotations";

    private String pointsFile = null;
    private int iterations = -1;
    private Vector beta = null;
    private boolean useHouseHolder = false;
    private boolean useGivens = false;

    public void parse(String[] args) {

        /**
         * Handle command line options
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("-[A-Za-z]")) {
                switch (args[i]) {
                    case "-f":
                        if (args.length <= i + 1) {
                            exitUsage("No file specified");
                        }
                        pointsFile = args[i + 1];
                        break;
                    case "-b":
                        if (args.length <= i + 1) {
                            exitUsage("No beta vector specified");
                        }
                        String[] parts = args[i + 1].split(",");
                        if (parts.length != 3) {
                            exitUsage("Beta vector incorrect length");
                        }
                        double[] values = new double[3];
                        for (int j = 0; j < parts.length; j++) {
                            try {
                                values[j] = Double.parseDouble(parts[j]);
                            } catch (NumberFormatException e) {
                                exitUsage("Bad value for beta: " + parts[j]);
                            }
                        }
                        beta = new Vector(values);

                        break;
                    case "-i":
                        if (args.length <= i + 1) {
                            exitUsage("No iterations specified");
                        }
                        iterations = Integer.parseInt(args[i + 1]);
                        break;
                    case "-h":
                        useHouseHolder = true;
                        break;
                    case "-g":
                        useGivens = true;
                        break;
                    default:
                        exitUsage("Bad option: " + args[i]);
                        break;
                }
            }
        }

        if (pointsFile == null) {
            exitUsage("Specify a file using '-f pointsfile'");
        }
        if (iterations == -1) {
            exitUsage("Specify iterations using '-i iterations'");
        }
        if (beta == null) {
            exitUsage("Specity beta vector components using '-b a,b,c'");
        }
        if (!useGivens && !useHouseHolder) {
            exitUsage("Specify QR decomposition method using either '-g' or '-h'");
        }
        if (useGivens && useHouseHolder) {
            exitUsage("Specify only one of Householder Relfections or Given's Rotations");
        }
    }

    public Vector getBeta() {
        return beta;
    }

    public boolean useHouseholder() {
        return useHouseHolder;
    }

    public boolean useGivens() {
        return useGivens;
    }

    public int getIterations() {
        return iterations;
    }

    public Vector[] getPoints() {
        List<Vector> pointsList = new ArrayList<Vector>();
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(new File(pointsFile));
        } catch (FileNotFoundException e) {
            exitUsage("File not found!");
        }

        //puts entires into matrix from text fiile
        while (inputFile != null && inputFile.hasNextLine()) {
            String[] array = inputFile.nextLine().split(",");
            Vector v = new Vector(new double[]{Double.parseDouble(array[0]),
                Double.parseDouble(array[1])});
            pointsList.add(v);
        }
        Vector[] points = new Vector[pointsList.size()];
        pointsList.toArray(points);
        return points;
    }

    public static void exitUsage(String message) {
        System.err.println("Error: " + message + "\n");
        System.err.println(usage);
        System.exit(1);
    }
}
