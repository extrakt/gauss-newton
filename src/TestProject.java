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
        Matrix a = new Matrix(new double[][] {
            {1,0,1},
            {0,1,1},
            {1,1,0}
        });
        
//        Matrix b = new Matrix(new double[][] {
//            {1,0,0},
//            {0,1,0},
//            {0,0,1}
//        });
        
        QRFact qr = new QRFact(a);
        qr.doHouseholder();
    }
}
