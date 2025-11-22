import java.util.*;
public class NextGreaterElement1 {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[nums1.length];
        int n = nums2.length;
        for(int j=n-1; j>=0; j--){
            System.out.println("j = "+j);
            if(!st.isEmpty()){
                if(st.peek() > nums2[j]){
                    hm.put(nums2[j], st.peek());
                    System.out.println("Key - "+nums2[j]+", Value - "+st.peek());
                    st.push(nums2[j]);
                }else{
                    while(!st.isEmpty() && st.peek() < nums2[j]){
                        st.pop();
                        if(st.peek() > nums2[j]){
                            hm.put(nums2[j], st.peek());
                            System.out.println("Key - "+nums2[j]+", Value - "+st.peek());
                            st.push(nums2[j]);
                        }
                    }
                    if(st.isEmpty()){
                        st.push(nums2[j]);
                        hm.put(nums2[j], -1);
                        System.out.println("Key - "+nums2[j]+", Value - -1");
                    }
                }
            }else{
                st.push(nums2[j]);
                hm.put(nums2[j], -1);
                System.out.println("Key - "+nums2[j]+", Value - -1");
            }
        }
        //hm.forEach((key, value) -> System.out.println(key+" "+" "+value+" "));
        for(int i=0; i<nums1.length; i++){
            ans[i] = hm.get(nums1[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums1 = {1,3,5,2,4};
        int[] nums2 = {6,5,4,3,2,1,7};
        int[] ans = new int[nums1.length];
        ans = nextGreaterElement(nums1, nums2);
        System.out.println();
        for(int val: ans){
            System.out.print(val+" ");
        }
    }
}
