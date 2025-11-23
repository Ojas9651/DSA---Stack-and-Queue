/*
Notes to Solve this Problem -

Virtually Double the Array
For Example - Original Array is nums = {1,2,3,4,3};
So make it like [1,2,3,4,3,1,2,3,4,3] but virtually

after that now n will be 2*n -1;
if(i > n)
index will be i%n;

if ind < st.peek() - st.push -- nums[ind]
else pop till st.peek > nums[ind]

when (i < n) just repeat the above part but this time store the ans[i] = nums[i];

When you double the array so by the time you reach the actual array
while traversing stack will hold the next greater of last actual element in array)
*/
import java.util.*;
public class NextGreaterElement2 {
        public static int[] nextGreaterElements(int[] nums) {
            Stack<Integer> st = new Stack<>();
            int[] ans = new int[nums.length];
            int n = nums.length;
            int m = 2*n -1;
            for(int i=m; i>=0; i--){
                if(i<n){
                    if(nums[i] < st.peek()){
                        ans[i] = st.peek();
                        st.push(nums[i]);
                    }else{
                        while(!st.isEmpty() && st.peek() <= nums[i]){
                            st.pop();
                            if(!st.isEmpty() && st.peek() > nums[i]){
                                ans[i] = st.peek();
                                st.push(nums[i]);
                                break;
                            }
                        }
                        if(st.isEmpty()){
                            st.push(nums[i]);
                            ans[i] = -1;
                        }
                    }
                }else{
                    int ind = i%n;
                    if(st.isEmpty() || nums[ind] < st.peek()){
                        st.push(nums[ind]);
                    }else{
                        while(!st.isEmpty() && nums[ind] > st.peek()){
                            st.pop();
                            if(!st.isEmpty() && st.peek() > nums[ind]){
                                st.push(nums[ind]);
                                break;
                            }
                        }
                        if(st.isEmpty()){
                            st.push(nums[ind]);
                        }
                    }
                }
            }
            return ans;
        }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        int[] ans = new int[nums.length];
        ans = nextGreaterElements(nums);
        for(int val: ans){
            System.out.print(val+" ");
        }
    }
}
