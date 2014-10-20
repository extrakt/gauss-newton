/**
 * Implements QR factorization
 * @author louis
 */
public class QRFact {
    
    public Matrix[] householder(Matrix a) {
        int k = 0; // Iteration
        // Get the first column of the matrix, with the kth iteration
        Vector x = a.getSubMatrix(0, a.getHeight(), k, 1).getColumn(0);
        
        // 1st column of the identity matrix
        Vector e1 = Matrix.identity(a.getHeight()).getColumn(1);
        
        Vector v = x.add(e1.mult(x.mag()));
        
        Vector u = v.dir();
        
        
        return new Matrix[] {a};
    }
}