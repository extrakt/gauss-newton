/**
 * Implements QR factorization
 *
 * @author louis
 */
public class QRFact {

    private Matrix Q;
    private Matrix R;
    private Matrix H;
    private Matrix A;

    public QRFact(Matrix a) {
        this.A = a;
    }

    public void doHouseholder() {
        int k = 0; // Iteration
        Q = Matrix.identity(A.getHeight());
        R = A;
        while (k < A.getWidth()) {
            // Minor matrix of A
            Matrix aMinor = R.getMinorMatrix(k, R.getHeight() - k, k, 1);
            // Get the first column of the matrix, with the kth iteration
            Vector x = aMinor.getColumn(0);
            // 1st column of the identity matrix
            Vector e1 = Matrix.identity(x.getLength()).getColumn(0);

            Vector v = x.add(e1.mult(x.mag()));

            Matrix vTranspose = v.transpose();

            // H = I - 2*u*utranspose
            Matrix hMinor = Matrix.identity(v.getLength())
                    .add(v.mult(vTranspose).mult(-2 / (v.dot(v))));
            Matrix hMajor;
            // Augment the minor matrix with the identity matrix
            // First check if we need to augment
            if (hMinor.getHeight() != A.getHeight()) {
                hMajor = Matrix.identity(A.getHeight());

                for (int i = 0; i < hMinor.getHeight(); i++) {
                    for (int j = 0; j < hMinor.getWidth(); j++) {
                        hMajor.set(i + k, j + k, hMinor.get(i, j));
                    }
                }
            } else {
                hMajor = hMinor;
            }
            Q = Q.mult(hMajor);
            System.out.println("Q " + Q);

            R = hMajor.mult(R);
            System.out.println("R " + R);

            k++;
        }
        R = R.getMinorMatrix(0, 3, 0, 3);
        Q = Q.getMinorMatrix(0, Q.getHeight(), 0, 3);
    }

    public Matrix getQ() {
        return Q;
    }

    public Matrix getR() {
        return R;
    }

    /**
     * Solves Rx=Qtb for the vector x, given the vector b. R is upper
     * triangular, and Q is orthonormal. Uses back-substitution.
     *
     * @param b Vector
     * @return Vector
     */
    public Vector solve(Vector b) {
        if (R == null || Q == null) {
            return null;
        }
        double[] xVector = new double[R.getHeight()];
        Vector qb = Q.transpose().mult(b);
        for (int i = R.getHeight() - 1; i >= 0; i--) {
            double x = qb.get(i);
            for (int j = R.getWidth() - 1; j > i; j--) {
                x -= R.get(i, j) * xVector[j];
            }
            xVector[i] = x / R.get(i, i);

        }
        return new Vector(xVector);
    }
}
