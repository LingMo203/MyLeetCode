package design;

//304. 二维区域和检索 - 矩阵不可变

class NumMatrix {
    int[][] prefixSum;
    int m,n;
    public NumMatrix(int[][] matrix) {
        m=matrix.length;
        prefixSum=new int[m][];
        for (int i=0;i<m;i++){
            int sum=0;
            n=matrix[i].length;
            prefixSum[i]=new int[n];
            for (int j=0;j<n;j++){
                sum+=matrix[i][j];
                prefixSum[i][j]=sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum=0;
        for (int i=row1;i<=row2;i++){
            int rowSum;
            if (col1==0) rowSum=prefixSum[i][col2];
            else rowSum=prefixSum[i][col2]-prefixSum[i][col1 - 1];
            sum+=rowSum;
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
