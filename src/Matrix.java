/**
 *
 * @author Leia
 */
public class Matrix {

    private final double[][] matrix;
    private final int height;
    private final int width;
    private String toStr;

    /**
     * Initialize instance variables
     *
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
        height = this.matrix.length;
        width = this.matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     *
     * @param i row
     * @param j column
     * @return double located at row i and column j in matrix
     */
    public double get(int i, int j) {
        if (i > height || j > width) {
            throw new IndexOutOfBoundsException();
        }
        return matrix[i][j];
    }

    public void set(int i, int j, double value) {
        matrix[i][j] = value;
    }

    /**
     * Gets the height of the matrix.
     *
     * @return number of rows in matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the matrix.
     *
     * @return number of columns in matrix
     */
    public int getWidth() {
        return width;
    }

    public Vector getColumn(int j) {
        double[] column = new double[height];
        for (int i = 0; i < height; i++) {
            column[i] = get(i, j);
        }
        return new Vector(column);
    }

    /**
     *
     * @param rowStart Start row, inclusive
     * @param rowLen Length of row selection
     * @param colStart Start column, inclusive
     * @param colLen Length of column selection
     * @return Sub Matrix
     */
    public Matrix getMinorMatrix(int rowStart, int rowLen, int colStart, int colLen) {

        double[][] sub = new double[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                sub[i][j] = get(i + rowStart, j + colStart);
            }
        }
        return new Matrix(sub);
    }

    /**
     * Addition of Matricies if compatible
     *
     * @param m1 Matrix1
     * @param m2 Matrix2
     * @return result of type Matrix
     * @throws IllegalOperandException Width and height of matricies don't match
     */
    public Matrix add(Matrix m2) {
        int widthM1 = getWidth();
        int heightM1 = getHeight();
        int widthM2 = m2.getWidth();
        int heightM2 = m2.getHeight();

        double[][] result = new double[heightM1][widthM1];

        for (int i = 0; i < heightM1; i++) {
            for (int j = 0; j < widthM1; j++) {
                result[i][j] = get(i, j) + m2.get(i, j);
            }
        }
        return new Matrix(result);
    }

    public Matrix mult(double a) {
        double[][] mult = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mult[i][j] = get(i, j) * a;
            }
        }
        return new Matrix(mult);
    }

    public Matrix mult(Matrix b) {
        double[][] mult = new double[height][b.width];
        for (int iRes = 0; iRes < height; iRes++) {
            for (int jRes = 0; jRes < b.width; jRes++) {
                for (int i = 0; i < width; i++) {
                    mult[iRes][jRes] += get(iRes, i) * b.get(i, jRes);
                }
            }
        }
        return new Matrix(mult);
    }

    public Vector mult(Vector b) {
        if (b.getLength() != width)
            throw new IndexOutOfBoundsException();
        double[] mult = new double[b.getLength()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mult[i] += get(i, j) * b.get(j);
            }
        }
        return new Vector(mult);
    }

    /**
     * Gets String representation of matrix. Columns separated by tabs, rows by
     * new lines.
     *
     * @return String representation of matrix.
     */
    public String toString() {
        String toStr = "Matrix:\n";
        for (int i = 0; i < height; i++) {
            toStr += "[";
            for (int j = 0; j < width; j++) {
                toStr += matrix[i][j] + "\t";
            }
            toStr += "]\n";
        }
        return toStr;
    }

    /**
     * Return identity matrix
     *
     * @param n Size of identity matrix
     * @return identity matrix of size n
     */
    public static Matrix identity(int n) {
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                } else {
                    identity[i][j] = 0;
                }
            }
        }
        return new Matrix(identity);
    }

    /**
     * Return transpose matrix
     *
     * @param n transpose matrix
     * @return identity matrix of size n
     */
    public Matrix transpose() {
        double[][] trans = new double[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                trans[j][i] = get(i, j);
            }
        }
        return new Matrix(trans);
    }
}
