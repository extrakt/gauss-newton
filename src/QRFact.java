/**
 * Implements QR factorization
 * @author louis
 */
public class QRFact {
    
    private Matrix Q;
    private Matrix R;
    private Matrix H;
    
    public void doHouseholder(Matrix a) {
        int k = 0; // Iteration
        while (k < a.getWidth()) {
            // Get the first column of the matrix, with the kth iteration
            Vector x = a.getSubMatrix(0, a.getHeight(), k, 1).getColumn(0);

            // 1st column of the identity matrix
            Vector e1 = Matrix.identity(a.getHeight()).getColumn(1);

            Vector u = x.add(e1.mult(x.mag())).dir();
            Matrix uTranspose = u.transpose();

            // H = I - 2*u*utranspose
            Matrix h = Matrix.identity(a.getHeight()).add(u.mult(uTranspose).mult(-2));
            
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