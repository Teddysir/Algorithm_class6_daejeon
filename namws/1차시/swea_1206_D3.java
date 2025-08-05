import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int T = 10;
		for(int testCase = 1; testCase <= 10; testCase++) {
			// 입력값
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 완전탐색으로 풀이, 각 index마다 -2와 +2 사이의  max값의 차이를 계산
			int result = 0;
			for(int i = 2; i < N-2; i ++) {
				result += Math.max(0, arr[i] - Math.max(Math.max(arr[i-2], arr[i-1]), Math.max(arr[i+1], arr[i+2])) );
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}

