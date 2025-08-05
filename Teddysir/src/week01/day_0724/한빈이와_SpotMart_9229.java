package week01.day_0724;

import java.util.*;
import java.io.*;

class 한빈이와_SpotMart {
	static int TC, N, M, max;
	static int[] snack;
//	static int[][] snack_set;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());
		for (int i = 1; i <= TC; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = -1;

//			snack_set = new int[N][N];
			snack = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				snack[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if (snack[j] + snack[k] <= M) {
						max = Math.max(max, snack[j] + snack[k]);
					}
//					snack_set[j][k] = snack[j] + snack[k];
				}
			}

			sb.append("#").append(i).append(" ").append(max).append("\n");
		}

		System.out.println(sb);

	}

}
