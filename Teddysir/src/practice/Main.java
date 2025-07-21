package practice;

import java.io.*;
import java.util.*;

public class Main {

	static int M, N, count;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					q.add(new int[] { i, j });
				}
				map[i][j] = temp;
			}
		}

		System.out.println(BFS());

	}

	static int BFS() {

		count = 0;

		while (!q.isEmpty()) {

			int[] temp = q.poll();
			int px = temp[0];
			int py = temp[1];

			for (int i = 0; i < 4; i++) {

				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = map[px][py] + 1;
						q.add(new int[] { nx, ny });
					}
				}
			}

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					return -1;
				}
				count = Math.max(count, map[i][j]);
			}
		}
		if (count == 1) {
			return 0;
		} else {
			return count - 1;
		}
	}

}
