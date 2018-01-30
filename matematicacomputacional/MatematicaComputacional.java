/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matematicacomputacional;

import Rotinas.DecomposicaoLU;
import Rotinas.Gauss;
import Rotinas.Jacobi;
import Rotinas.Seidel;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class MatematicaComputacional {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);
        Boolean op = true;

        System.out.println("Bem vindo à ferramenta de solução para sistemas lineares!");

        while (op) {
            System.out.println("Insira o código da operação que você deseja realizar.");
            System.out.println("0 - Sair.");
            System.out.println("1 - Decomposição LU;");
            System.out.println("2 - Método de Gauss;");
            System.out.println("3 - Método de Gauss-Jacobi;");
            System.out.println("4 - Método de Gauss-Seidel;");
            System.out.println("5 - Método Simplex.");
            System.out.print("Digite: ");
            int in = reader.nextInt();
            
            int n;
            double[][] M;

            switch (in) {
                case 0:
                    System.out.println("Obrigado por usar a nossa ferramenta! Até mais!");
                    op = false;
                    break;

                case 1:
                    System.out.println("Digite o tamanho da matriz: (0 para voltar)");
                    n = reader.nextInt();
                    
                    if(n == 0)
                    {
                        break;
                    }
                    M = criaMatriz(n);
                    
                    DecomposicaoLU lu = new DecomposicaoLU();
                    lu.luDecomposition(M);
                    break;

                case 2:
                    System.out.println("Digite o tamanho da matriz: (0 para voltar)");
                    n = reader.nextInt();
                    
                    if(n == 0)
                    {
                        break;
                    }
                    
                    M = criaMatriz(n);
                    
                    Gauss gauss = new Gauss();
                    gauss.solve(M);
                    break;

                case 3:
                    System.out.println("Digite o tamanho da matriz: (0 para voltar)");
                    n = reader.nextInt();
                    
                    if(n == 0)
                    {
                        break;
                    }
                    
                    M = criaMatriz(n);
                    
                    Jacobi jacobi = new Jacobi(M);

                    if (!jacobi.tornaDominante()) {
                        System.out.println("O sistema não é diagonalmente dominante, portanto o método não pode garantir convergencia.");
                    }
                    System.out.println();
                    jacobi.solve();
                    break;

                case 4:
                    System.out.println("Digite o tamanho da matriz: (0 para voltar)");
                    n = reader.nextInt();
                    
                    if(n == 0)
                    {
                        break;
                    }
                    
                    M = criaMatriz(n);
                    
                    Seidel seidel = new Seidel(M);

                    if (!seidel.tornaDominante()) {
                        System.out.println("O sistema não é diagonalmente dominante, portanto o método não pode garantir convergencia.");
                    }

                    System.out.println();
                    seidel.print();
                    seidel.solve();
                    break;

                case 5:
                    System.out.println("Em contrução...");
                    break;

                default:
                    System.out.println("Código inválido. Digite novamente.");
                    break;
            }
        }

    }

    public static double[][] criaMatriz(int n) throws IOException {

        double[][] M;
        
        System.out.println("Digite as linhas da matriz");
        System.out.println("Exemplo de uso:");
        System.out.println("5 -2  3 -1\n-3  9  1  2\n2 -1 -7  3");
        BufferedReader tokenReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);
        
        n = Integer.parseInt(tokenReader.readLine());
        
        if(n == 0)
        {
            
        }
                
        M = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer strtk = new StringTokenizer(tokenReader.readLine());

            //while (strtk.hasMoreTokens())
            for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++) {
                M[i][j] = Integer.parseInt(strtk.nextToken());
            }
        }

        return M;
    }

}
