// Emre Tekin - 19120205027

import java.util.Random;

public class PeakFinder2D {
    public static int[][] a;
    public static int nrow = 5, ncol = 7; // nrow & ncol: number of row & column
    public static int startRow = 0, startCol = 0;
    // public static int stepCounter = 0; // uses in divideAndConquer2 function for
    // deciding which if block will run.

    public static void PeakFinder2D(int nrow, int ncol) {
        a = new int[nrow][ncol];
        int row;// row index
        int col;// column index
        Random rand = new Random();
        for (row = 0; row < nrow; row++) {
            for (col = 0; col < ncol; col++) {
                a[row][col] = rand.nextInt(100);
            }
        }
    }

    public static int greedyAlg() {
        int peak = a[0][0];
        int row = 0;// current row index
        int col = 0;// current column index

        for (int i = 0; i < (nrow + ncol - 2) && row < nrow && col < ncol; i++) { // i for loop
            // (nrow+ncol-2) max step coutity in a matris
            // TODO
            if (col != ncol - 1 && row != nrow - 1) {// controls the out of bounds situation
                if (peak < a[row][col + 1]) {// compare with rigth
                    peak = a[row][col + 1];
                    col++;
                } else if (peak < a[row + 1][col]) {// compare with below
                    peak = a[row + 1][col];
                    row++;
                }
            }

            if (a[0][0] == peak) {
                break;
            }
        }
        return peak;
    }

    public int findMax(int[] b) { // return max value's index at the given array
        int imax = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] > b[imax]) {
                imax = i;
            }
        }
        return imax;
    }

    public static int findMaxOnCol(int col) {
        int imax = startCol;
        for (int i = 0; i < nrow; i++) {
            if (a[i][col] > a[imax][col]) {
                imax = i;
            }
        }
        return imax;
    }

    public static int findMaxOnRow(int row) {
        int imax = startRow;
        for (int i = 0; i < ncol; i++) {
            if (a[row][i] > a[row][imax]) {
                imax = i;
            }
        }
        return imax;
    }

    public static int divideAndConquer1(int startcol, int endcol) {
        // TODO correct and complete the code
        int midcol = (startcol + endcol) / 2; // column index at the middle of matrix
        int imax = findMaxOnCol(midcol); // max value's row index at the mid column.
        boolean outOfBounds = (midcol != endcol && imax != nrow);
        if (outOfBounds) { // controls the out of bounds situation

            /// base case TODO: boundary conditions
            if (a[imax][midcol] >= a[imax][midcol + 1] && a[imax][midcol] >= a[imax][midcol - 1]) {
                return a[imax][midcol];
            }
            if (a[imax][midcol] <= a[imax][midcol + 1]) { // compare with right
                return divideAndConquer1(midcol, endcol);
            }
            if (a[imax][midcol] <= a[imax][midcol - 1]) { // compare with left
                return divideAndConquer1(startcol, midcol);
            }
        }
        return 0; // never need this but unless wrote this there is an error.
    }

    // /**
    // * derste anlatilan divide and conquer yontemini kullanarak O(n+m) zamanda
    // peak
    // * bulan algoritmanin implemantasyonunu yaziniz
    // */
    public static int divideAndConquer2(int startrow, int startcol, int endrow, int endcol) {
        // TODO
        int midcol = (startcol + endcol) / 2;
        
        int imaxCol = findMaxOnCol(midcol);// max value's row index at the mid column.
        if (imaxCol < endcol && midcol < endcol) { // controls the out of bounds situation
            boolean outOfBounds = (midcol != endcol && imaxCol != endrow);

            if (a[imaxCol][midcol] >= a[imaxCol][midcol + 1] && a[imaxCol][midcol] >= a[imaxCol][midcol - 1]
                    && outOfBounds) {
                return a[imaxCol][midcol];
            }
            if (a[imaxCol][midcol] <= a[imaxCol][midcol + 1] && outOfBounds){ // compare with right

                if (a[imaxCol][midcol + 1] <= a[imaxCol - 1][midcol + 1] && outOfBounds) { // compare with above
                    return divideAndConquer2(imaxCol - 1, midcol + 1, endrow, endcol);

                } else if (a[imaxCol][midcol + 1] <= a[imaxCol + 1][midcol + 1] && outOfBounds) { // compare with below
                    return divideAndConquer2(imaxCol + 1, midcol + 1, endrow, endcol);

                } else {
                    return divideAndConquer2(imaxCol, midcol + 1, endrow, endcol);
                }
            }
            if (a[imaxCol][midcol] <= a[imaxCol][midcol - 1] && outOfBounds) { // compare with left

                if (a[imaxCol][midcol - 1] <= a[imaxCol - 1][midcol - 1] && outOfBounds) { // compare with above
                    return divideAndConquer2(imaxCol - 1, midcol - 1, endrow, endcol);
                }
                 else if (a[imaxCol][midcol - 1] <= a[imaxCol + 1][midcol + 1] && outOfBounds) { // compare with below
                    return divideAndConquer2(imaxCol + 1, midcol + 1, endrow, endcol);
                } 
                else {
                    return divideAndConquer2(imaxCol, midcol - 1, endrow, endcol);
                }
            }
        }

        return 0;
    }

    /** prints elements of a */
    public static void printArray() {

        for (int i = 0; i < nrow; i++) {// i for row
            for (int j = 0; j < ncol; j++) {// j for column
                System.out.printf("%3d", a[i][j]);
            }
            System.out.println();
        }
    }

    static void testGreedyAlg() {
        // TODO
        System.out.println(greedyAlg());
    }

    static void testDivideAndConq1() {
        // TODO
        System.out.println(divideAndConquer1(startCol, ncol - 1));
    }

    static void testDivideAndConq2() {
        // TODO
        System.out.println(divideAndConquer2(startRow, startCol, nrow, ncol));
    }

    public static void main(String[] args) {
        System.out.println("Hello! Welcome to Peak Finder. Enjoy it!\n");

        PeakFinder2D.PeakFinder2D(nrow, ncol);// creates a random matrix by given sizes.
        PeakFinder2D.printArray();// prints random matrix

        System.out.print("Greedy Peak: ");
        PeakFinder2D.testGreedyAlg(); // finds a peak value by using Greedy Algorithm

        System.out.print("DivideAndConquer1 Peak: ");
        PeakFinder2D.testDivideAndConq1(); // finds a peak value by using DivideAndConquer1

        System.out.print("DivideAndConquer2 Peak: ");
        PeakFinder2D.testDivideAndConq2(); // finds a peak value by using DivideAndConquer2

        Greedy greedy = new Greedy();
         greedy.randomMatrixGenerator(nrow, ncol);
        System.out.println(greedy.greedyAlg());
        
        

        

    }

}