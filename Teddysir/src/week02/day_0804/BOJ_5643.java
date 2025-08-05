package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5643 {
	static int TC, N, M, ans;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		for (int k = 1; k <= TC; k++) {

			N = Integer.parseInt(br.readLine()); // �л� ��
			M = Integer.parseInt(br.readLine()); // �� Ƚ��

			arr = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				arr[a][b] = 1;
			}
			ans = 0;

			for (int i = 1; i <= N; i++) {
				boolean flag = true;
				visited = new boolean[N + 1];
				small(i);
				big(i);
				for(int j = 1; j <= N; j++) {
					if(visited[j] == false) {
						flag = false;
					}
				}
				if(flag) {
					ans++;
				}
			}
			

			sb.append("#").append(k).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static void small(int start) {

		visited[start] = true;
		for (int i = 1; i <= N; i++) {
			if (arr[i][start] == 1 && !visited[i]) {
				visited[i] = true;
				small(i);
			}
		}
	}

	static void big(int start) {
		visited[start] = true;
		for (int i = 1; i <= N; i++) {
			if (arr[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				big(i);
			}
		}
	}

}
