/*
 * Gauss Newton Method for the Quadratic curve.
 *
 * @author Leia Glezer
 */

public class gn_qua {
    private Vector[] vectorPairs;
    private Vector betaVector;
    private final int numberOfIterations;
    public Vector residuals;
    double[][] arrayOfPairs;
    double[] numOfResiduals;
    double residualValue;
    Matrix qJacobian;
    Matrix eJacobian;
    Matrix lJacobian;
    Matrix rJacobian;

    // input: arrayOfPairs = double[][] array of of n pairs of floating point numbers.
    // betaTriple = a double[] array of a triple of floating point numbers (beta).
    //  n = and a number of "n" interations.
    public gn_qua(double[][] arrayOfPairs, double[] betaTriple, int numberOfIterations) {// ax2 + bx + c
        betaVector = new Vector(betaTriple);
        this.numberOfIterations = numberOfIterations;
        double[] numOfResiduals = new double[arrayOfPairs.length];
        Vector residuals = new Vector(numOfResiduals);
        for (int i = 0; i < arrayOfPairs.length; i++) {
            vectorPairs[i] = new Vector(arrayOfPairs[i]);
        }
    }

    public Vector getB(Vector beta) {
        return betaVector;
    }

    public Vector getR(Vector residual) {
        return residual;
    }

    public void get_Gauss_Quadratic(double[][] arrayOfPairs, double[] betaTriple) {
        for (int i = 0; i < arrayOfPairs.length; i++) {
            double q = (Math.pow(arrayOfPairs[i][0], 2) * betaVector.get(0)) + (arrayOfPairs[i][0] * betaVector.get(1)) + betaVector.get(2);
            residuals.set(i, arrayOfPairs[i][1] - q);// r = yi - (ax2 + bx + c)
            double tempX = arrayOfPairs[i][0];
            double coA = Math.pow(tempX, 2);
            double coB = tempX;
            double coC = 1;

            //builds up Jacobi Matrix
            qJacobian.set(i, 0, coA);
            qJacobian.set(i, 1, coB);
            qJacobian.set(i, 2, coC);
            //print out to see.           
        }
    }
}
