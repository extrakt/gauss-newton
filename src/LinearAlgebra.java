/**
 * Driver for Linear Algebra.
 *
 * @author Leia Glezer
 * @version 1.2
 */

public class LinearAlgebra {

    /**
     * Multiplies Vectors and Matricies if compatible
     * @param m Matrix
     * @param v Vector
     * @return result of type Vector
     * @throws IllegalOperandException Width of matrix does not equal height of vector
     */

    public static Vector matrixVectorMultiply(Matrix m, Vector v)
        throws IllegalOperandException {

        int widthM = m.getWidth(), heightM = m.getHeight();
        int lengthV = v.getLength();

        if (widthM != lengthV) {
            throw new IllegalOperandException("Nice try! But this matrix"
                + " is size " + heightM + " by " + widthM
                + " while this vector is size " + heightM + " by " + widthM);
        }

        double[] result = new double[v.getLength()];

        for (int i = 0; i < heightM; i++) {
            for (int j = 0; j < widthM; j++) {
                result[i] += m.get(i, j) * v.get(j);
            }
        }
        return new Vector(result);
    }

    /**
     * Addition of Matricies if compatible
     * @param m1 Matrix1
     * @param m2 Matrix2
     * @return result of type Matrix
     * @throws IllegalOperandException Width and height of matricies don't match
     */

    public static Matrix matrixAdd(Matrix m1, Matrix m2)
        throws IllegalOperandException {
        int widthM1 = m1.getWidth();
        int heightM1 = m1.getHeight();
        int widthM2 = m2.getWidth();
        int heightM2 = m2.getHeight();


        if (widthM1 != widthM2 || heightM1 != heightM2) {
            throw new IllegalOperandException("Nice try! But this"
                + " matrix is size " + heightM1 + " by " + widthM1
                + " while your other matrix is size " + heightM1 + " by "
                + widthM1);
        }

        double[][] result = new double[heightM1][widthM1];

        for (int i = 0; i < heightM1; i++) {
            for (int j = 0; j < widthM1; j++) {
                result[i][j] = m1.get(i, j) + m2.get(i, j);
            }
        }
        return new Matrix(result);
    }

    /**
     * Dot Product of Vectors if compatible
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return result of type Matrix
     * @throws IllegalOperandException 
     */

    public static double dotProduct(Vector v1, Vector v2)
        throws IllegalOperandException {
        double vDot = 0;
        int lengthV1 = v1.getLength();
        int lengthV2 = v2.getLength();

        if (lengthV1 != lengthV2) {
            throw new IllegalOperandException("Nice try! But this"
                + " vector is size " + lengthV1 + " while your other vector is"
                + " size " + lengthV2);
        }

        for (int i = 0; i < v1.getLength(); i++) {
            vDot += v1.get(i) * v2.get(i);
        }
        return vDot;
    }

    /**
     * Addition of Vectors if compatible
     * @param v1 Vector 
     * @param  v2 Vector 
     * @return result of type Vector
     * @throws IllegalOperandException
     */

    public static Vector vectorAdd(Vector v1, Vector v2)
        throws IllegalOperandException {
        if (v1.getLength() != v2.getLength()) {
            throw new IndexOutOfBoundsException("Nice try! But this"
                + " vector is size " + v1.getLength()
                + " while your other vector" + " is size " + v2.getLength());
        }
        double[] result = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            result[i] = v1.get(i) + v2.get(i);
        }
        return new Vector(result);
    }
    
    /**
     * Transposes a matrix
     */
    public static Matrix transpose(Matrix a){
        // TODO
        return null;
    }
    
    
    
    /**
     * 
     */
}