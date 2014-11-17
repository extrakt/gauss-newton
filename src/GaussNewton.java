public class GaussNewton {

    private Vector[] vectorPairs;
    private Vector betaVector;
    private final int numberOfIterations;
    public Vector residuals;
    double[][] arrayOfPairs;
    double[] numOfResiduals;
    double residualValue;
    Matrix jacobian;
    Matrix qJacobian;
    Matrix eJacobian;
    Matrix lJacobian;
    Matrix rJacobian;

    // input: arrayOfPairs = double[][] array of of n pairs of floating point numbers.
    // betaTriple = a double[] array of a triple of floating point numbers (beta).
    //  n = and a number of "n" interations.
    public GaussNewton(double[][] arrayOfPairs, double[] betaTriple, int numberOfIterations) {// ax2 + bx + c
        betaVector = new Vector(betaTriple);
        this.numberOfIterations = numberOfIterations;
        residuals = new Vector(arrayOfPairs.length);
        jacobian = new Matrix(residuals.getLength(), betaVector.getLength());
        vectorPairs = new Vector[arrayOfPairs.length];
        for (int i = 0; i < arrayOfPairs.length; i++) {
            vectorPairs[i] = new Vector(arrayOfPairs[i]);
        }
    }

    public Vector getB() {
        return betaVector;
    }

    public Vector getR(Vector residual) {
        return residual;
    }

    public void getGaussQuadratic() {
        for (int n = 0; n < numberOfIterations; n++) {
            for (int i = 0; i < vectorPairs.length; i++) {
                double q = (Math.pow(vectorPairs[i].get(0), 2) * betaVector.get(0)) + (vectorPairs[i].get(0) * betaVector.get(1)) + betaVector.get(2);

                residuals.set(i, vectorPairs[i].get(1) - q);// r = yi - (ax2 + bx + c)
                double tempX = vectorPairs[i].get(0);
                double coA = -Math.pow(tempX, 2);
                double coB = -tempX;
                double coC = -1;

                //builds up Jacobi Matrix
                jacobian.set(i, 0, coA);
                jacobian.set(i, 1, coB);
                jacobian.set(i, 2, coC);
            }
            updateBeta();
        }
    }

    public void getGaussExponential() {
        for (int n = 0; n < numberOfIterations; n++) {
            for (int i = 0; i < vectorPairs.length; i++) {
                double e = (betaVector.get(0) * Math.exp((vectorPairs[i].get(0) * betaVector.get(1))) + betaVector.get(2));
                residuals.set(i, vectorPairs[i].get(1) - e); // r = yi - (a*e^(bx) + c)
                double tempX = vectorPairs[i].get(0);
                double coA = -Math.exp(tempX * betaVector.get(1));
                double coB = -betaVector.get(0) * tempX * Math.exp(tempX * betaVector.get(1));
                double coC = -1;

                //builds up Jacobi Matrix
                jacobian.set(i, 0, coA);
                jacobian.set(i, 1, coB);
                jacobian.set(i, 2, coC);
                //print out to see.           
            }
            updateBeta();
        }
    }

    public void getGaussLogarithmic() {
        for (int n = 0; n < numberOfIterations; n++) {
            for (int i = 0; i < vectorPairs.length; i++) {
                double e = (betaVector.get(0) * Math.log((vectorPairs[i].get(0) * betaVector.get(1))) + betaVector.get(2));
                residuals.set(i, vectorPairs[i].get(1) - e); // r = yi - (a*e^(bx) + c)
                double tempX = vectorPairs[i].get(0);

                double coA = -Math.log(tempX + betaVector.get(1));
                double coB = -(betaVector.get(0)) / (tempX + betaVector.get(1));
                double coC = -1;

                //builds up Jacobi Matrix
                jacobian.set(i, 0, coA);
                jacobian.set(i, 1, coB);
                jacobian.set(i, 2, coC);
            }
            updateBeta();
        }
    }

    public void getGaussRational() {
        for (int n = 0; n < numberOfIterations; n++) {
            for (int i = 0; i < vectorPairs.length; i++) {
                double e = (((betaVector.get(0) * vectorPairs[i].get(0)) / (vectorPairs[i].get(0) + betaVector.get(1))) + betaVector.get(2));

                residuals.set(i, vectorPairs[i].get(1) - e); // r = yi - (a*e^(bx) + c)
                double tempX = vectorPairs[i].get(0);

                double coA = (-tempX) / (tempX + betaVector.get(1));
                double coB = -(((betaVector.get(0) * tempX) / (Math.pow(tempX + betaVector.get(1), 2))));
                double coC = -1;

                //builds up Jacobi Matrix
                jacobian.set(i, 0, coA);
                jacobian.set(i, 1, coB);
                jacobian.set(i, 2, coC);
                //print out to see.           
            }
            updateBeta();
        }
    }

    private void updateBeta() { //β = β − (R−1Q⊤r).
        System.out.println("J " + jacobian);

        QRFact qr = new QRFact(jacobian);
        qr.doHouseholder();
        Vector b = qr.solve(residuals);
        betaVector = betaVector.add(b.mult(-1));
        System.out.println(betaVector);
    }
}
