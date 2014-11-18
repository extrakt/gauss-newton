/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author louis
 */
public class TestProject {

    public static void main(String[] args) {
        Matrix a = new Matrix(new double[][]{
            {1, 2, 0},
            {5, 12, 1},
            {5, 11, 1}
        });

//        Matrix b = new Matrix(new double[][] {
//            {1,0,0},
//            {0,1,0},
//            {0,0,1}
//        });
        QRFact qr = new QRFact(a);
        qr.doGivens();
        System.out.println("Q " + qr.getQ());
        System.out.println("R " + qr.getR());
        System.out.println("A " + qr.getQ().mult(qr.getR()));

        Vector b = new Vector(new double[] {1,1,1});
        Vector x = qr.solve(b);
        System.out.println("X " + x);
        System.out.println("b " + a.mult(x));

    }
}
