/*
    Convert the given matrix to a prefix sum matrix
    pass each row matrix to the histogram function
 */

import java.util.*;
public class Maximal_Rectangle {

        public static int[][] prefixSumMatrix(char[][] mat){
            int n = mat.length;
            int m = mat[0].length;
            int[][] matrix = new int[n][m];
            int[] sum = new int[m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(mat[i][j] == '0'){
                        matrix[i][j] = 0;
                        sum[j] = 0;
                    }else{
                        matrix[i][j] = sum[j] + (mat[i][j] - '0');
                        sum[j] = matrix[i][j];
                    }
                }
            }
            return matrix;
        }

        public static int largestRectangleArea(int[] heights) {
            Stack<Integer> st = new Stack<>();
            int n = heights.length;
            int ans = 0;
            for(int i=0; i<n; i++){
                if(!st.isEmpty() && heights[st.peek()] < heights[i]){
                    st.push(i);
                }else{
                    while(!st.isEmpty()){
                        int a = heights[st.peek()];
                        st.pop();
                        int b = 0;
                        if(st.isEmpty()){
                            b = -1;
                        }else{
                            b = st.peek();
                        }
                        int area = a * (i - b -1);
                        ans = Math.max(area, ans);
                        if(!st.isEmpty() && heights[st.peek()] < heights[i]){
                            st.push(i);
                            break;
                        }
                    }
                    if(st.isEmpty()){
                        st.push(i);
                    }
                }
            }
            while(!st.isEmpty()){
                int a = heights[st.peek()];
                int nse = n;
                st.pop();
                int pse = 0;
                if(st.isEmpty()) pse = -1;
                else pse = st.peek();
                int area = a * (nse - pse -1);
                ans = Math.max(area, ans);
            }
            return ans;
        }

        public static int maximalRectangle(char[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            int[][] mat = new int[n][m];
            mat = prefixSumMatrix(matrix);
            int ans = 0;
            for(int i=0; i<n; i++){
                int area = largestRectangleArea(mat[i]);
                ans = Math.max(area, ans);
            }
            return ans;
        }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','1','1'},
                {'0','1','0','1','0'},
                {'1','1','0','1','1'},
                {'1','1','0','1','1'},
                {'0','1','1','1','1'}};

        System.out.println(maximalRectangle(matrix));
    }
}
