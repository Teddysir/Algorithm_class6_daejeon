import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[i][0] = a;
				arr[i][1] = b;
			}
			
			Arrays.sort(arr, (a,b) -> Integer.compare(a[0], b[0])); // 물건의 부피 크기로 오름차순 정렬
			
			
			int[] dp = new int[K+1]; //무게별 최대 가치를 dp로 계산
			int[] past = new int[K+1]; // 이전 시행 결과
			
			for(int i = 0; i < N; i++) { // 각 item이 추가되었을 때 dp를 update
				int volume = arr[i][0];
				int value = arr[i][1];

				for(int j = volume; j <= K; j++) {
					dp[j] = Math.max(past[j], past[j - volume] + value); // item을 추가했을 때와 추가하지 않았을 때 중 더 큰 값을 저장
				}
				past = Arrays.copyOf(dp, dp.length);
			}
			
			sb.append("#").append(testCase).append(" ").append(dp[K]).append("\n");
		}
		System.out.print(sb);
	}
}

