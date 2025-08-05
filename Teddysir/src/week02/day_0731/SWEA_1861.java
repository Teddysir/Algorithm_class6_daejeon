package practice;

import java.io.*;
import java.util.*;

public class SWEA_1861 {

	static int T, size, maxX, maxY, max, tempMax;
	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int k = 1; k <= T; k++) {
			size = Integer.parseInt(br.readLine());

			map = new int[size][size];

			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tempMax = 0;
			max = 0;
			maxX = 0;
			maxY = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					tempMax = bfs(i, j);
					if (tempMax == max) {
						if (map[i][j] < map[maxX][maxY]) {
							maxX = i;
							maxY = j;
						}
					}
					if (tempMax > max) {
						max = tempMax;
						maxX = i;
						maxY = j;

					}
				}
			}

			sb.append("#").append(k).append(" ").append(map[maxX][maxY]).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		int count = 1;

		while (!q.isEmpty()) {
			int temp[] = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];

				if (nx >= 0 && ny >= 0 && nx < size && ny < size) {
					if (map[nx][ny] - 1 == map[nx - dx[i]][ny - dy[i]]) {
						count++;
						q.add(new int[] { nx, ny });
					}
				}
			}

		}

		return count;
	}

}
