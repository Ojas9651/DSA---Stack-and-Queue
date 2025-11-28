import java.util.*;
public class Sum_of_Subarray_Minimums {

    /* Following is the Brute Fore Solution*/

    public static int sumSubarrayMins(int[] arr) {
        int sum = 0;
        int mod = (int)1e9 + 7;
        for(int i=0; i<arr.length; i++){
            int min = arr[i];
            for(int j=i; j<arr.length; j++){
                min = Math.min(min, arr[j]);
                sum += min;
            }
        }
        sum = sum%mod;
        return sum;
    }

    public static int findNSE(int[] arr, int i){
        Stack<Integer> st = new Stack<>();
        int nse = arr.length;
        for(int j=arr.length-1; j>=i; j--){
            if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                nse = st.peek();
                st.push(j);
            }else{
                while(!st.isEmpty()){
                    st.pop();
                    if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                        nse = st.peek();
                        st.push(j);
                        break;
                    }
                }
                if(st.isEmpty()){
                    st.push(j);
                }
            }
        }
        return nse-i;
    }

    public static int findPSEE(int[] arr, int i){
        Stack<Integer> st = new Stack<>();
        int psee = -1;
        for(int j=0; j<=i; j++){
            if(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                psee = st.peek();
                st.push(j);
            }else{
                while(!st.isEmpty()){
                    st.pop();
                    if(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                        psee = st.peek();
                        st.push(j);
                        break;
                    }
                }
                if(st.isEmpty()){
                    st.push(j);
                }
            }
        }
        return i-psee;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        double sum = 0;
        int mod = (int)1e9 + 7;
        for(int i=0; i<arr.length; i++){
            int nse = findNSE(arr, i);
            int pse = findPSEE(arr, i);
            sum += ((nse*pse)*arr[i]);
        }
        sum = sum%mod;
        System.out.println((int)sum);
    }
}
