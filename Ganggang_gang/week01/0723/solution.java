

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            //수열 구하는 배열 선언
            int[] results = new int[n];
            int size = 0;
            
            for (int x : arr) {
                // x가 tails 배열에서 들어갈 자리(=lower_bound)를 찾는다
                int idx = Arrays.binarySearch(results, 0, size, x);
                if (idx < 0) {
                    // 앞에 들어가야할 경우 대
                    idx = -idx - 1;
                }
                // 해당 위치 값 갱신
                results[idx] = x;
                // 뒤에 들어간경우 최대 값 갱신하
                if (idx == size) {
                    size++;
                }
            }
            
            sb.append("#").append(t).append(" ").append(size).append("\n");
        }
        
        System.out.print(sb);
    }
}