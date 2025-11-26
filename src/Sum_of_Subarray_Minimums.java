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
    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }
}
