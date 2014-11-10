/**
 * Immutable abstraction for Vector
 *
 * @author Leia Glezer
 * @version 1.2
 */
public class Vector {

    private final double[] vector;
    private final int length;


    /**
     * Initialize instance variables
     * @param vector array representation of vector
     */
    public Vector(double[] vector) {
        this.vector = vector;
        this.length = vector.length;
    }

    /**
     * Gets value located at specified index
     * @param i index in vector
     * @return double located at index 'i' in vector
     */
    public double get(int i){
        return vector[i];
    }
    
    public void set(int i, double val) { //value at index
       vector[i] = val;
    }

    /**
     * Gets the length of the Vector.
     * @return number of components in vector
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns magnitude of vector
     * @return Magnitude of vector
     */
    public double mag() {
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += Math.pow(vector[i], 2);
        }
        return Math.sqrt(sum);
    }
    
    /**
     * Returns the unit direction vector
     * @return Direction
     */
    public Vector dir() {
        return mult(1/mag());
    }
    
    /**
     * Multiplies by a scalar multiple
     * @param a Scalar multiple
     * @return Result
     */
    public Vector mult(double a) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = a * vector[i];
        }
        return new Vector(result);
    }
    
    /**
     * Multiplies by a matrix of height 1 (a transpose vector)
     * @param a Scalar multiple
     * @return Result
     */
    public Matrix mult(Matrix a) {
        double[][] result = new double[vector.length][a.getWidth()];
        
        // Each row in the vector
        for (int i = 0; i < vector.length; i++) {
            // Each column in the matrix (transpose vector)
            for (int j = 0; j < a.getWidth(); j++) {
                result[i][j] = a.get(0, j) * vector[i];
            }
        }
        return new Matrix(result);
    }
    
    /**
     * Addition of Vectors if compatible
     * @param v2 Vector 
     * @return result of type Vector
     * @throws IllegalOperandException
     */
    public Vector add(Vector v2) {
        if (this.getLength() != v2.getLength()) {
            return null;
        }
        double[] result = new double[this.getLength()];
        for (int i = 0; i < this.getLength(); i++) {
            result[i] = this.get(i) + v2.get(i);
        }
        return new Vector(result);
    }
    
    /**
     * Transposes a vector
     * @return Transpose of vector
     */
    public Matrix transpose(){
        double[][] result = new double[1][length];
        for (int i = 0; i < length; i++) {
            result[0][i] = get(i);
        }
        return new Matrix(result);
    }
   
    /**
     * String representation of vector with components
     * separated by tabs
     * @return String representation of vector
     */
    public String toString() {
        String toStr = "Vector:\n(";
        for (int i = 0; i < length; i++) {
            toStr += vector[i] + ",\t";
        }
        return toStr + ")";
    }
    
    
}
