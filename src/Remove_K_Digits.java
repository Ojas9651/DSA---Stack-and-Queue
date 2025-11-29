/*
    Important Points -
        1- Use String Builder because it is mutable while string is immutable
        that means every time you concatenate a new string object is created.
        2- In StringBuilder you can append.
        3- str.reverse() - to reverse string builder
        4- Always convert String Builder to string - str.toString();
 */

import java.util.*;
public class Remove_K_Digits {
    public static String removeKdigits(String num, int k) {
        if(k == num.length()){
            String ans = "0";
            return ans;
        }
        Stack<Character> st = new Stack<>();
        st.push(num.charAt(0));
        for(int i=1; i<num.length(); i++){
            if(num.charAt(i) >= st.peek()){
                st.push(num.charAt(i));
            }else{
                while(!st.isEmpty()){
                    st.pop();
                    k = k-1;
                    if(k == 0){
                        while(i < num.length()){
                            st.push(num.charAt(i));
                            i++;
                        }
                        break;
                    }
                    if(!st.isEmpty() && num.charAt(i) >= st.peek()){
                        st.push(num.charAt(i));
                        break;
                    }
                }
                if(k == 0) break;
                if(st.isEmpty()){
                    st.push(num.charAt(i));
                }
            }
        }
        while(k != 0){
            st.pop();
            k--;
        }
        StringBuilder subString = new StringBuilder();
        while(!st.isEmpty()){
            subString.append(st.peek());
            st.pop();
        }
        StringBuilder s = new StringBuilder();
        for(int i=subString.length()-1; i>=0; i--){
            if(s.length() == 0 && subString.charAt(i) == '0') continue;
            s.append(subString.charAt(i));
        }
        String ans = s.toString();
        if(ans.length() == 0) ans = "0";
        return ans;
    }

    public static void main(String[] args) {
        String num = "1432219";
        String ans = removeKdigits(num, 3);
        System.out.println(ans);
    }
}
