import java.util.*;
public class Sum_of_Subarray_Minimums {

    public static int[] findNSE(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        int n = arr.length;
        st.push(n-1);
        ans[n-1] = n;
        for(int i=n-2; i>=0; i--){
            if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                ans[i] = st.peek();
                st.push(i);
            }else{
                while(!st.isEmpty()){
                    st.pop();
                    if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                        ans[i] = st.peek();
                        st.push(i);
                        break;
                    }
                }
                if(st.isEmpty()){
                    st.push(i);
                    ans[i] = n;
                }
            }
        }
        return ans;
    }

    public static int[] findPSEE(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        int n = arr.length;
        ans[0] = -1;
        st.push(0);
        for(int i=1; i<n; i++){
            if(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                ans[i] = st.peek();
                st.push(i);
            }else{
                while(!st.isEmpty()){
                    st.pop();
                    if(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                        ans[i] = st.peek();
                        st.push(i);
                        break;
                    }
                }
                if(st.isEmpty()){
                    st.push(i);
                    ans[i] = -1;
                }
            }
        }
        return ans;
    }

    public static int sumSubarrayMins(int[] arr) {
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);
        long sum = 0;
        int mod = (int)1e9 + 7;
        for(int i=0; i<arr.length; i++){
            long ans = (long)(nse[i] - i)*(i - psee[i]);
            sum = (sum + ans * arr[i]) % mod;
        }
        return (int)sum;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }

}
