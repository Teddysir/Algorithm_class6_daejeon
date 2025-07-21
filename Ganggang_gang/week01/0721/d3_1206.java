package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_1206 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        for (int T = 1; T <= 10; T++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            // 양쪽으로 2칸씩 볼 수 있는 최소 i=2, 최대 i=n-3
            for (int i = 2; i < n - 2; i++) {
                // 주변 4칸 중 가장 높은 건물 높이 구하기
                int maxvalue = 0;
                for (int j = 1; j <= 2; j++) {
                    maxvalue = Math.max(maxvalue, arr[i - j]); // 뒤쪽
                    maxvalue = Math.max(maxvalue, arr[i + j]); // 앞쪽
                }
                // 현재 건물이 네 이웃보다 높으면 그 차이만큼 뷰 계산
                if (arr[i] > maxvalue) {
                    ans += arr[i] - maxvalue;
                }
            }

            // 결과 출력
            System.out.println("#" + T + " " + ans);
        }
    }
}