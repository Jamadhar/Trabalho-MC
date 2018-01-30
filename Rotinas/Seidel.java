package Rotinas;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seidel {

  public static final int MAX_ITERATIONS = 100;
  private double[][] M;
  
  public Seidel(double [][] matrix) { M = matrix; }

  public void print()
  {
    int n = M.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n + 1; j++)
        System.out.print(M[i][j] + " ");
      System.out.println();
    }
  }

  public boolean transformaEmDominante(int r, boolean[] V, int[] R)
  {
    int n = M.length;
    if (r == M.length) {
      double[][] T = new double[n][n+1];
      for (int i = 0; i < R.length; i++) {
        for (int j = 0; j < n + 1; j++)
          T[i][j] = M[R[i]][j];
      }

      M = T;
      
      return true;
    }

    for (int i = 0; i < n; i++) {
      if (V[i]) continue;

      double sum = 0;
      
      for (int j = 0; j < n; j++)
        sum += Math.abs(M[i][j]);

      if (2 * Math.abs(M[i][r]) > sum) {
        V[i] = true;
        R[r] = i;

        if (transformaEmDominante(r + 1, V, R))
          return true;

        V[i] = false;
      }
    }

    return false;
  }
  
  public boolean tornaDominante()
  {
    boolean[] visited = new boolean[M.length];
    int[] rows = new int[M.length];

    Arrays.fill(visited, false);

    return transformaEmDominante(0, visited, rows);
  }

  public void solve()
  {
    int iterations = 0;
    int n = M.length;
    double epsilon = 1e-15;
    double[] X = new double[n];
    double[] P = new double[n];
    Arrays.fill(X, 0);

    while (true) {
      for (int i = 0; i < n; i++) {
        double sum = M[i][n];

        for (int j = 0; j < n; j++)
          if (j != i)
            sum -= M[i][j] * X[j];

        X[i] = 1/M[i][i] * sum;   
      }

      System.out.print("Iteração" + iterations + " = {");
      for (int i = 0; i < n; i++)
        System.out.print(X[i] + " ");
      System.out.println("}");

      iterations++;
      if (iterations == 1) continue;

      boolean stop = true;
      for (int i = 0; i < n && stop; i++)
        if (Math.abs(X[i] - P[i]) > epsilon)
          stop = false;

      if (stop || iterations == MAX_ITERATIONS) break;
      P = (double[])X.clone();
    }
    System.out.println();
  }
}
