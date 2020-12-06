import java.util.Random;

public class Greedy {

    public int [][] matrix;
    public int nrow, ncol;

    public Greedy(){
        System.out.println();
    }

    public void randomMatrixGenerator(int nrow, int ncol) {
        matrix = new int[nrow][ncol];
        int row;// row index
        int col;// column index
        Random rand = new Random();
        for (row = 0; row < nrow; row++) {
            for (col = 0; col < ncol; col++) {
                matrix[row][col] = rand.nextInt(100);
            }
        }
    }

    public int greedyAlg() {
        int peak = matrix[0][0];
        int row = 0;// current row index
        int col = 0;// current column index

        for (int i = 0; i < (nrow + ncol - 2) && row < nrow && col < ncol; i++) { // i for loop
            // (nrow+ncol-2) max step coutity in a matris
            // TODO
            if (col != ncol - 1 && row != nrow - 1) {// controls the out of bounds situation
                if (peak < matrix[row][col + 1]) {// compare with rigth
                    peak = matrix[row][col + 1];
                    col++;
                } else if (peak < matrix[row + 1][col]) {// compare with below
                    peak = matrix[row + 1][col];
                    row++;
                }
            }

            if (matrix[0][0] == peak) {
                break;
            }
        }
        return peak;
    }
}
