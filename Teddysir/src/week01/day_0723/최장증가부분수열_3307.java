package week01.day_0723;

import java.io.*;
import java.util.*;;

public class 최장증가부분수열_3307 {

	static int T, N, max;
	static int[] arr, dp;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {

			N = Integer.parseInt(br.readLine());
			max = 0;
			arr = new int[N];
			dp = new int[N];
			for (int j = 0; j < N; j++) {
				dp[j] = 1;
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 1; j < N; j++) {
				for (int k = 0; k < j; k++) {
					if (arr[j] > arr[k]) {
						dp[j] = Math.max(dp[j], dp[k] + 1);
					}
				}
			}

			for (int j = 0; j < dp.length; j++) {
				max = Math.max(max, dp[j]);
			}

			sb.append("#").append(i).append(" ").append(max).append("\n");
		}

		System.out.println(sb);

	}

}

// 처음엔 아래처럼 해보려다가 아예접근 방식 자체가 틀렸었네용..

//N = Integer.parseInt(br.readLine());
//
//StringTokenizer st = new StringTokenizer(br.readLine());
//arr = new int[N];
//Stack<Integer> stack = new Stack<Integer>();
//
//for (int j = 0; j < N; j++) {
//	arr[j] = Integer.parseInt(st.nextToken());
//}
//stack.add(arr[0]);
//
//for (int j = 1; j < N; j++) {
//	if (stack.peek() > arr[j]) {
//		stack.pop();
//		stack.add(arr[j]);
//	} else {
//		stack.add(arr[j]);
//	}
//}
