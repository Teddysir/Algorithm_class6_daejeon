package com.ssafy.hw.step4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class SpiderWeb {

	static int N, max, maxX, maxY, tempTotal;
	static int map[][];

	static int[] dx = { 1, -1, 0, 0, -1, 1, -1, 1 };
	static int[] dy = { 0, 0, 1, -1, 1, -1, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tempMax = search(i, j);
				if (tempMax > max) {
					max = tempMax;
					maxX = i;
					maxY = j;
				}
			}
		}
		
		sb.append(max).append("\n");
		sb.append(maxX).append(", ").append(maxY);

		System.out.println(sb);
		

	}

	static int search(int x, int y) {
		tempTotal = 1;

		for (int i = 0; i < 8; i++) {
			tempTotal += search_dir(x, y, i);
		}
		return tempTotal;

	}

	static int search_dir(int x, int y, int dir) {

		int nx = x + dx[dir];
		int ny = y + dy[dir];

		int obstacleCount = 0;
		int one_dir_total = 0;

		if (map[x][y] == 1) {
			obstacleCount = 1;
		}

		while (true) {
			
	        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
	            break; 
	        }

			if (map[nx][ny] == 1) {
				obstacleCount++;
				if (obstacleCount == 2) {
					break;
				}
			} else {
				one_dir_total++;
				obstacleCount = 0;
			}

			nx += dx[dir];
			ny += dy[dir];
		}

		return one_dir_total;

	}
}
