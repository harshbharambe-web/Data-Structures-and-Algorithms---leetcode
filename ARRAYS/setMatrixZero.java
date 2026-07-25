class Solution {
    public void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(matrix[i][j] == 0) {

                    // Mark Row
                    for(int k = 0; k < cols; k++) {
                        if(matrix[i][k] != 0)
                            matrix[i][k] = -1;
                    }

                    // Mark Column
                    for(int k = 0; k < rows; k++) {
                        if(matrix[k][j] != 0)
                            matrix[k][j] = -1;
                    }
                }
            }
        }

        // Convert -1 to 0
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }
}
