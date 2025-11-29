/*
        Brute Force Solution
            1- Find NSE of every height
            2- Find PSE of every height
            3- Loop (0 -> n) for heights -
                .) for every height area = height[i] * (nse - pse -1);
*/

import java.util.*;
public class Largest_Rectangle_in_Histogram {
        public static int[] findNSE(int[] arr){
            Stack<Integer> st = new Stack<>();
            int n = arr.length;
            int[] nse = new int[n];
            for(int i=n-1; i>=0; i--){
                if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                    nse[i] = st.peek();
                    st.push(i);
                }else{
                    while(!st.isEmpty()){
                        st.pop();
                        if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                            nse[i] = st.peek();
                            st.push(i);
                            break;
                        }
                    }
                    if(st.isEmpty()){
                        st.push(i);
                        nse[i] = n;
                    }
                }
            }
            return nse;
        }

        public static int[] findPSE(int[] arr){
            Stack<Integer> st = new Stack<>();
            int n = arr.length;
            int[] pse = new int[n];
            for(int i=0; i<n; i++){
                if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                    pse[i] = st.peek();
                    st.push(i);
                }else{
                    while(!st.isEmpty()){
                        st.pop();
                        if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                            pse[i] = st.peek();
                            st.push(i);
                            break;
                        }
                    }
                    if(st.isEmpty()){
                        st.push(i);
                        pse[i] = -1;
                    }
                }
            }
            return pse;
        }
        public static int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] nse = new int[n];
            nse = findNSE(heights);
            int[] pse = new int[n];
            pse = findPSE(heights);
            int ans = 0;
            for(int i=0; i<heights.length; i++){
                int area = (((i - pse[i]) -1) + (nse[i] - i))*heights[i];
                ans = Math.max(ans, area);
            }
            return ans;
        }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}
