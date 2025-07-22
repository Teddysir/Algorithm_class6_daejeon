package week01.day_0721;

import java.util.*;
import java.io.*;

public class Knapsack_3282 {

	static int T, max_volume, max_value, total_bag, ans;
	static HashMap<Integer, int[]> map;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			total_bag = Integer.parseInt(st.nextToken());
			max_volume = Integer.parseInt(st.nextToken());

			ans = 0;
			map = new HashMap<Integer, int[]>();
			arr = new int[total_bag + 1][max_volume + 1];
//			for (int i = 0; i <= total_bag; i++) {
//				Arrays.fill(arr[i], -1);
//			}

			for (int i = 0; i < total_bag; i++) {
				st = new StringTokenizer(br.readLine());
				int volume = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				map.put(i, new int[] { volume, value });
			}
			ans = dp(0, 0);

			sb.append("#").append(test_case).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static int dp(int count, int volume) {

		if(arr[count][volume] != 0) {
			return arr[count][volume]; // 이미 있는거면 계산할 필요 없다.
		}
		
		if(count == total_bag) {
			return 0; // 마지막은 끗 
		}
		
		int result = dp(count+1, volume); // -> 그냥 넘어갈때,
		int[] temp = map.get(count);
		if(volume + temp[0] <= max_volume) { // 안넘으면 
			result = Math.max(result, dp(count+1, volume + temp[0]) + temp[1]);
		}
		
		arr[count][volume] = result;
		return result;
	}

}
