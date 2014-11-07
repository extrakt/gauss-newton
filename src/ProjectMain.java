/**
 * Project class with main method
 * @author Sidney Durant
 * @author Leia Glezer
 * @author Louis Williams
 */
public class ProjectMain {
  
  public static void main(String[] args) {
      Matrix a = new Matrix(new double[][]{
          {3,1,0},
          {2,4,1},
          {0,2,1}
      });
      QRFact qr = new QRFact(a);
      
      qr.doHouseholder();
  }  
}