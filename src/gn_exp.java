/*
 * Gauss Newton Method for the Exponential curve.
 *
 * @author Leia Glezer
 */

public class gn_exp {
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

    public gn_exp(double[][] arrayOfPairs, double[] betaTriple, int numberOfIterations) {// ax2 + bx + c
        betaVector = new Vector(betaTriple);
        this.numberOfIterations = numberOfIterations;
        double[] numOfResiduals = new double[arrayOfPairs.length];
        Vector residuals = new Vector(numOfResiduals);
        for (int i = 0; i < arrayOfPairs.length; i++) {
            vectorPairs[i] = new Vector(arrayOfPairs[i]);
        }
    }
    
    public void get_Gauss_Exponential(double[][] arrayOfPairs, double[] betaTriple) {

        for (int i = 0; i < arrayOfPairs.length; i++) {
            double e = (betaVector.get(0) * Math.exp((arrayOfPairs[i][0] * betaVector.get(1))) + betaVector.get(2));
            residuals.set(i, arrayOfPairs[i][1] - e); // r = yi - (a*e^(bx) + c)
            double coA = Math.exp(arrayOfPairs[i][0] * betaVector.get(1));
            double coB = arrayOfPairs[i][0] * Math.exp(arrayOfPairs[i][0] * betaVector.get(1));
            double coC = 1;

            //builds up Jacobi Matrix
            eJacobian.set(i, 0, coA);
            eJacobian.set(i, 1, coB);
            eJacobian.set(i, 2, coC);
            //print out to see.           
        }
    }    
}
