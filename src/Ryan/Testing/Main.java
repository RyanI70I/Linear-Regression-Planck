package Ryan.Testing;

import java.util.ArrayList;

import org.apache.commons.math3.analysis.function.Min;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Main {
    public static ArrayList<double[]> slopes = new ArrayList<double[]>();

    public static void main(String[] args) throws InterruptedException {
        // write your code here
//        double[] Volts416 = {1.563, 1.627, 1.668, 1.708, 1.755, 1.781, 1.817, 1.861, 1.885, 1.914, 1.935, 1.948, 2.004, 2.026, 2.046, 2.081, 2.123, 2.189, 2.25, 2.303, 2.382, 2.458, 2.598, 2.663, 2.884, 3.109, 3.229, 3.464, 3.692};
//        double[] Amps416 = {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.11, 0.12, 0.14, 0.15, 0.16, 0.18, 0.2, 0.24, 0.27, 0.3, 0.35, 0.4, 0.5, 0.55, 0.72, 0.9, 1, 1.2, 1.4};
//
//        double[] Volts501 = {1.511, 1.663, 1.849, 1.847, 1.862, 1.986, 2.01, 2.101, 2.211, 2.325, 2.457, 2.586, 2.753, 3.008, 3.257, 3.496, 3.731};
//        double[] Amps501 = {0.01, 0.04, 0.06, 0.09, 0.1, 0.14, 0.15, 0.2, 0.26, 0.32, 0.4, 0.49, 0.61, 0.8, 1, 1.2, 1.4};
//
//        double[] Volts565 = {1.154, 1.288, 1.449, 1.538, 1.629, 1.796, 1.934, 2.068, 2.2, 2.32, 2.437, 2.549, 2.668, 2.782, 2.893, 2.999, 3.111, 3.208, 3.304, 3.434};
//        double[] Amps565 = {0.01, 0.04, 0.1, 0.14, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1, 1.1, 1.2, 1.3, 1.4, 1.49, 1.58, 1.7};
//
//        double[] Volts590 = {1.176, 1.306, 1.436, 1.632, 1.793, 1.934, 2.067, 2.186, 2.422, 2.647};
//        double[] Amps590 = {0.01, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.8, 0.99};
//
//        double[] Volts645 = {0.982, 1.155, 1.277, 1.454, 1.598, 1.74, 1.865, 1.997, 2.112, 2.229, 2.345, 2.454, 2.562, 2.784, 2.998, 3.1, 3.339};
//        double[] Amps645 = {0.01, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1, 1.1, 1.3, 1.4, 1.6, 1.83};
//
//        double[] Volts875 = {0.707, 0.934, 1.102, 1.255, 1.369, 1.492, 1.605, 1.836, 2.066, 2.271, 2.484, 2.686, 2.906, 3.091};
//        double[] Amps875 = {0.01, 0.1, 0.2, 0.31, 0.4, 0.5, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};
//
//        double[] Volts940 = {0.676, 0.812, 0.906, 1.065, 1.211, 1.33, 1.57, 1.788, 2.007, 2.222, 2.433, 2.636, 2.85, 3.105};
//        double[] Amps940 = {0.01, 0.06, 0.1, 0.2, 0.3, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};


        double[] ActVolt = {1.564, 1.512, 1.155, 1.176, 0.982, 0.71, 0.676};
        double[] uncert = new double[7];
        final double[] InvLambda = {(double)1/416, (double)1/501, (double)1/565, (double)1/590, (double)1/645,(double)1/875,(double)1/940};

        for (int i = 0; i < ActVolt.length; i++) {
            uncert[i] = (ActVolt[i] *.03 + .002) +.1;
        }

        double[][] ActVolts = new double[7][10];
        for (int i = 0; i < ActVolt.length; i++) {
            for (int j = 0; j < 5; ) {
                j++;

                ActVolts[i][j-1] = ActVolt[i] + uncert[i] * .2 * j;
            }
            for (int j = 5; j < 10;) {
                j++;
                ActVolts[i][j-1] = ActVolt[i] - (uncert[i] * .2 * (j/2));
            }
        }
        SimpleRegression model = new SimpleRegression();

        int count = 0;
        double max = -9999;
        double min = 99999;
        for (int j = 0; j < 10; j++) {
            System.out.println(min);
            System.out.println(max);
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < 10; l++) {
                    for (int m = 0; m < 10; m++) {
                        for (int n = 0; n <10 ; n++) {
                            for (int o = 0; o < 10; o++) {
                                for (int p = 0; p < 10; p++) {
                                    for (int q = 0; q < 10; q++) {

                                        model.addData(InvLambda[0], ActVolts[0][k]);
                                        model.addData(InvLambda[1], ActVolts[1][j]);
                                        model.addData(InvLambda[2], ActVolts[2][l]);
                                        model.addData(InvLambda[3], ActVolts[3][m]);
                                        model.addData(InvLambda[4], ActVolts[4][m]);
                                        model.addData(InvLambda[5], ActVolts[5][o]);
//                                            model.addData(InvLambda[6], ActVolts[6][p]);
                                        if(model.getSlope() > max) {
                                            max = model.getSlope();
                                        } else if(model.getSlope() < min) {
                                            min = model.getSlope();
                                        }
//                                            System.out.println(ActVolts[1][k]);
//                                            System.out.println(ActVolts[0][j]);
//                                            System.out.println(ActVolts[2][l]);
//                                            System.out.println(ActVolts[3][m]);
//                                            System.out.println(ActVolts[4][m]);
//                                            System.out.println(ActVolts[5][o]);
//                                            System.out.println(ActVolts[6][p]);
//                                            System.out.println(InvLambda[0]);
//                                            System.out.println(InvLambda[1]);
//                                            System.out.println(InvLambda[2]);
//                                            System.out.println(InvLambda[3]);
//                                            System.out.println(InvLambda[4]);
//                                            System.out.println(InvLambda[5]);
//                                            System.out.println(InvLambda[6]);
//                                            System.out.println();
//                                            Thread.sleep(5000);
                                        model.clear();

//                                            slopes.add(new double[] {ActVolts[0][j], ActVolts[1][k], ActVolts[0][l], ActVolts[1][m], ActVolts[0][n], ActVolts[1][o], ActVolts[0][p]});
                                    }


                                }

                            }

                        }

                    }

                }

            }


        }
        System.out.println("FINAL MIN SLOPE " + min);
        System.out.println("FINAL MAX SLOPE " + + max);



    }
}


