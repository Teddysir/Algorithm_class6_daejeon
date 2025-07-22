import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] v = new int[n+1];
            int[] c = new int[n+1];
         
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            // 2차원 DP 테이블 행은 물건의 가지수, 열은 최대 무게의 경우를 나열
            int[][] dp = new int[n+1][k+1];

            for (int i = 1; i <= n; i++) {
                int weight = v[i];
                int value  = c[i];
                for (int w = 1; w <= k; w++) { // 무게의 경우의 수 만큼 비교하기
                    if (w < weight) { // 현재 값의 무게가 해당 열을 넘어가게 된다면 무게가 초과되는 형식으로 진행
                        dp[i][w] = dp[i-1][w]; //현재값을 갱신을 할 수 없으니깐 그 전의 value를 dp 에 저장
                    } else {
                        dp[i][w] = Math.max(dp[i-1][w],dp[i-1][w - weight] + value);
                        //그 전의 상태 혹은 그 전의 상태에서 새로운 무게값을 더할 경우의 최댓값을 넣는다.
                        //dp[i-1][w] 같은 경우는 현재의 값을 추가하지 않아도 큰 경우 (즉 최적의 선택이 아닐 경우)
                        //dp[i-1][w -weight] + value 는 현재의 짐을 반영했을때에 가치가 큰 경우 (즉 현재의 짐이 최대의 가치를 가질 경우)
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, dp[n][k]);
        }
    }

}
