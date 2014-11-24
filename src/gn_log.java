/*
 * Gauss Newton Method for the Logarithmic curve.
 *
 * @author Leia Glezer
 */

public class gn_log {
    public static void main(String[] args) {
        CommandParser parser = new CommandParser();
        parser.parse(args);

        GaussNewton gn = new GaussNewton(parser.getPoints(), parser.getBeta(),
                parser.getIterations(), new QRFact((qr) ->  {
                    if (parser.useGivens())
                        qr.doGivens();
                    else
                        qr.doHouseholder();
                })
        );
        gn.doGaussLogarithmic();
        System.out.println("Beta: " + gn.getBeta());
    }
}
