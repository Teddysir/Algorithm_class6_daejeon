import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_1249 {

	static int TC, N, ans;
	static int[][] map;
	static int[][] dist;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class Node implements Comparable<Node> {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}

	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		for (int k = 1; k <= TC; k++) {

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j) - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}

			ans = solution();

			sb.append("#").append(k).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		dist[0][0] = map[0][0];
		pq.add(new Node(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Node temp = pq.poll();

			int curX = temp.x;
			int curY = temp.y;
			int curCost = temp.cost;

			if (curCost > dist[curX][curY]) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {

					int newCost = dist[curX][curY] + map[nx][ny];
					if (dist[nx][ny] > newCost) {
						dist[nx][ny] = newCost;
						pq.add(new Node(nx, ny, newCost));
					}
				}
			}

		}

		return dist[N - 1][N - 1];
	}
}
