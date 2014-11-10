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
            System.out.println(aMinor);
            // Get the first column of the matrix, with the kth iteration
            Vector x = aMinor.getColumn(0);
            // 1st column of the identity matrix
            Vector e1 = Matrix.identity(x.getLength()).getColumn(0);

            Vector v = x.add(e1.mult(x.mag()));
            Vector u = v.dir();

            Matrix vTranspose = v.transpose();

            // H = I - 2*u*utranspose
            Matrix hMinor = Matrix.identity(v.getLength())
                    .add(v.mult(vTranspose).mult(-2/(v.dot(v))));
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

    }

    public Matrix getQ() {
        return Q.getMinorMatrix(0, Q.getHeight(), 0, 3);
    }

    public Matrix getR() {
        return R.getMinorMatrix(0, 3, 0, 3);
    }
}
