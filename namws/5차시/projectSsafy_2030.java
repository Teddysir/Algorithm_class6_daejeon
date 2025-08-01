package com.ssafy.hw.step4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SpiderWeb {
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultCount = 0;
		int[] resultPos = {-1,-1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				
				int sum = 0;
				for(int k = 0; k < 8; k++) { // 8방향 탐
					int obstacleCount = 0;
					
					int nx = i;
					int ny = j;
					while(nx >= 0 && nx < N && ny >= 0 && ny < N && obstacleCount < 2) {
						
						if(board[nx][ny] == 0) {
							sum++;
							obstacleCount = 0;
						} else {
							obstacleCount++;
						}
						
						nx += dx[k];
						ny += dy[k];
					}
				}
				
				if(board[i][j] == 0) { // 시작지점이 0인 경우 8번 중복되었을 테니 -7
					sum -= 7;
				}
				
				if(sum > resultCount) { // 더 큰 경우 update
					resultCount = sum;
					resultPos[0] = i;
					resultPos[1] = j;
				}
			}
		}
		
		sb.append(resultCount).append("\n").append(resultPos[0]).append(",").append(resultPos[1]);
		System.out.print(sb);
	}
}
