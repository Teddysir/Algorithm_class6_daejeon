package week01;

import java.io.*;
import java.util.*;

public class 문제해결_기본_1일차_View {

	static int apt, count;
	static int[] heights;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {
			count = 0;

			apt = Integer.parseInt(br.readLine()); // 아파트 수
			heights = new int[apt];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < apt; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 2; i < apt - 2; i++) {
				int tempLeft_step_1 = heights[i] - heights[i - 1];
				int tempLeft_step_2 = heights[i] - heights[i - 2];
				int temp_left = Math.min(tempLeft_step_1, tempLeft_step_2);

				int tempRight_step1 = heights[i] - heights[i + 1];
				int tempRight_step2 = heights[i] - heights[i + 2];
				int temp_right = Math.min(tempRight_step1, tempRight_step2);

				if (temp_left > 0 && temp_right > 0) {
					count += Math.min(temp_left, temp_right);
				}

			}

			sb.append("#").append(test_case).append(" ").append(count).append("\n");

		}
		System.out.println(sb);

	}
}
