package week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA_3307 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] dp = new int[N];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            for(int i=0; i<N-1; i++) {
                for(int j=i+1; j<N; j++) {
                    if(arr[i] < arr[j]) {
                        dp[j] = Math.max(dp[i]+1, dp[j]);
                    }
                }
            }
             
            int answer = 0;
            for(int i=0; i<N; i++) {
                answer = Math.max(answer, dp[i]);
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(answer+1);
            System.out.println(sb.toString());
        }
    }
}
