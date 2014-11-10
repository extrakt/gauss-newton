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
        while (k < A.getWidth()) {
            // Get the first column of the matrix, with the kth iteration
            Vector x = A.getMinorMatrix(0, A.getHeight(), k, 1).getColumn(0);

            // 1st column of the identity matrix
            Vector e1 = Matrix.identity(A.getHeight()).getColumn(1);

            Vector u = x.add(e1.mult(x.mag())).dir();
            Matrix uTranspose = u.transpose();

            // H = I - 2*u*utranspose
            Matrix hMinor = Matrix.identity(A.getHeight()).add(u.mult(uTranspose).mult(-2));
            Matrix hMajor = new Matrix(A.getHeight(), A.getWidth());

            // Augment the minor matrix with the identity matrix
            for (int i = 0; i < hMajor.getHeight(); i++) {
                for (int j = 0; j < hMajor.getWidth(); j++) {
                    if (i == k || j == k) {
                        if (i == k && j == k) {
                            hMajor.set(i, j, 1);
                        } else {
                            hMajor.set(i, j, 0);
                        }
                    } else {
                        hMajor.set(i, j, hMinor.get(i + k - 1, j + k - 1));
                    }
                }
            }
            k++;
            System.out.println(hMajor);
        }
    }

    public Matrix getQ() {
        return Q;
    }

    public Matrix getR() {
        return R;
    }

    public Matrix getH() {
        return H;
    }
}
