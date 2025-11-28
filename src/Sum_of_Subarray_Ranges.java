import java.util.*;
public class Sum_of_Subarray_Ranges {

        public static long findNGE(int[] arr, int i){
            Stack<Integer> st = new Stack<>();
            long nge = arr.length;
            for(int j=arr.length-1; j>=i; j--){
                if(!st.isEmpty() && arr[st.peek()] > arr[i]){
                    nge = st.peek();
                    st.push(j);
                }else{
                    while(!st.isEmpty()){
                        st.pop();
                        if(!st.isEmpty() && arr[st.peek()] > arr[i]){
                            nge = st.peek();
                            st.push(j);
                            break;
                        }
                    }
                    if(st.isEmpty()){
                        st.push(j);
                    }
                }
            }
            return nge-i;
        }

        public static long findPGEE(int[] arr, int i){
            Stack<Integer> st = new Stack<>();
            long pgee = -1;
            for(int j=0; j<=i; j++){
                if(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                    pgee = st.peek();
                    st.push(j);
                }else{
                    while(!st.isEmpty()){
                        st.pop();
                        if(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                            pgee = st.peek();
                            st.push(j);
                            break;
                        }
                    }
                    if(st.isEmpty()){
                        st.push(j);
                    }
                }
            }
            return i-pgee;
        }

        public static long findSubArrayMax(int[] arr){
            long sum = 0;
            for(int i=0; i<arr.length; i++){
                long nge = findNGE(arr, i);
                long pgee = findPGEE(arr, i);
                sum += (nge*pgee)*arr[i];
            }
            return sum;
        }

        public static long findNSE(int[] arr, int i){
            Stack<Integer> st = new Stack<>();
            long nse = arr.length;
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

        public static long findPSEE(int[] arr, int i){
            Stack<Integer> st = new Stack<>();
            long psee = -1;
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

        public static long findSubArrayMin(int[] arr){
            long sum = 0;
            for(int i=0; i<arr.length; i++){
                long nse = findNSE(arr, i);
                long psee = findPSEE(arr, i);
                sum += (nse*psee)*arr[i];
            }
            return sum;
        }

        public static long subArrayRanges(int[] nums) {
            long max = findSubArrayMax(nums);
            long min = findSubArrayMin(nums);
            long ans = max - min;
            return ans;
        }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subArrayRanges(nums));;
    }
}
