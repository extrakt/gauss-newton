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
        while (k < A.getWidth() - 1) {
            // Minor matrix of A
            Matrix aMinor = A.getMinorMatrix(k, A.getHeight() - k, k, 1);
            System.out.println(aMinor);
            // Get the first column of the matrix, with the kth iteration
            Vector x = aMinor.getColumn(0);
            // 1st column of the identity matrix
            Vector e1 = Matrix.identity(aMinor.getHeight()).getColumn(0);
            
            Vector u = x.add(e1.mult(x.mag())).dir();
            Matrix uTranspose = u.transpose();

            // H = I - 2*u*utranspose
            Matrix hMinor = Matrix.identity(aMinor.getHeight()).add(u.mult(uTranspose).mult(-2));
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
            System.out.println(hMajor);
            k++;
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
