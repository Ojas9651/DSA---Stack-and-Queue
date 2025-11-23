import java.util.*;
public class AsteroidCollission {
        public static int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> st = new Stack<>();
            int n = asteroids.length;
            for(int i=0; i<n; i++){
                if(asteroids[i] > 0){
                    st.push(asteroids[i]);
                }
                else{
                    if(st.isEmpty() || st.peek() < 0){
                        st.push(asteroids[i]);
                    }
                    else{
                        if(st.peek() + asteroids[i] == 0) st.pop();
                        else if(st.peek() > asteroids[i] && st.peek() + asteroids[i] > 0) continue;
                        else{
                            while(!st.isEmpty() && st.peek() + asteroids[i] < 0){
                                st.pop();
                                if(st.isEmpty()){
                                    st.push(asteroids[i]);
                                    break;
                                }
                                if(!st.isEmpty() && st.peek() + asteroids[i] == 0){
                                    st.pop();
                                    break;
                                }
                                if(!st.isEmpty() && st.peek() < 0){
                                    st.push(asteroids[i]);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            int[] ans = new int[st.size()];
            for(int i=ans.length-1; i>=0; i--){
                ans[i] = st.pop();
            }
            return ans;
        }
    public static void main(String[] args) {
        int[] asteroids = {3,5,-6,2,-1,4};
        int[] ans = new int[asteroidCollision(asteroids).length];
        ans = asteroidCollision(asteroids);
        for(int val: ans){
            System.out.print(val+" ");
        }
    }
}