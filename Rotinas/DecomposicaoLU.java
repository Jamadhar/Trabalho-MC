package Rotinas;

import java.util.Arrays;

public class DecomposicaoLU {

    public double[] luDecomposition(double[][] matrix) {
        // decomposition of matrix

        int n = matrix.length;
        double[] rightPart = new double[n];
        Arrays.fill(rightPart, 0);
        double[][] lu = new double[n][n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            rightPart[i] = matrix[i][n];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += lu[i][k] * lu[k][j];
                }
                lu[i][j] = matrix[i][j] - sum;
            }
            for (int j = i + 1; j < n; j++) {
                sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += lu[j][k] * lu[k][i];
                }
                lu[j][i] = (1 / lu[i][i]) * (matrix[j][i] - sum);
            }
        }

        //Ly = b
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int k = 0; k < i; k++) {
                sum += lu[i][k] * y[k];
            }
            y[i] = rightPart[i] - sum;
        }
        //Ux = y
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            sum = 0;
            for (int k = i + 1; k < n; k++) {
                sum += lu[i][k] * x[k];
            }
            x[i] = (1 / lu[i][i]) * (y[i] - sum);
        }

        System.out.println("L: ");
        for (int i = 0; i < n; i++) 
        {
            
            for (int j = 0; j < n; j++) {
                if(i == j)
                {
                    System.out.print("1.0 ");
                }
                else if(i > j)
                {
                    System.out.print(lu[i][j] + " ");
                }
                else
                {
                    System.out.print("0.0 ");
                }
            }
            System.out.println();
        }
        
        System.out.println();
        
        System.out.println("U: ");
        for (int i = 0; i < n; i++) 
        {
            
            for (int j = 0; j < n; j++) {
                if(i <= j)
                {
                    System.out.print(lu[i][j] + " ");
                }
                else
                {
                    System.out.print("0.0 ");
                }
            }
            System.out.println();
        }
        
        System.out.println();
        
        System.out.print("X^T = { ");
        for(int i = 0; i < n; i++)
        {
            System.out.print(x[i] + ", ");
        }
        System.out.println("}");
        return x;
    }
}
