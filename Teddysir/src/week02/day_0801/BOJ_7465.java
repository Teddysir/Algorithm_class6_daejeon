import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7465 {
	static int N, M, T, ans;
	static int[][] friends;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int k = 1; k <= T; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			ans = 0;

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			friends = new int[N + 1][N + 1];
			visited = new boolean[N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				friends[a][b] = friends[b][a] = 1;
			}

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					dfs(i);
					ans++;
				}
			}

			sb.append("#").append(k).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static void dfs(int start) {
		visited[start] = true;
		for (int i = 1; i <= N; i++) {
			if (friends[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

}
