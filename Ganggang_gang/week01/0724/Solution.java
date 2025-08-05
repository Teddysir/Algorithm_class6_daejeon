import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <= TC; t ++) {
			sb.append('#').append(t).append(' ');
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			
			int ans = -1; // 정답 변수
			int cnt = 2; //정확히 두봉지
			
			StringTokenizer value = new StringTokenizer(br.readLine());
			
			for(int j =0; j<n; j++) {
				arr[j] = Integer.parseInt(value.nextToken());
			}
			
			//완전탐색 시작
			for(int i =0; i<n-1; i++) {
				for(int j =i+1; j<n; j++) {
					if (arr[i] + arr[j] <= m) {
						ans = Math.max(ans, arr[i] + arr[j]);
					}
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

}
