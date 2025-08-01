import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] board;
	static int[][] dp;
	static boolean[][] isVisited;
	
	static int resultNum;
	static int resultCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			
			int n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			dp = new int[n][n]; //dp에는 해당 index의 cnt
			isVisited = new boolean[n][n]; // 방문처리
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			resultNum = -1;
			resultCnt = -1;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(isVisited[i][j]) continue; // 이미 방문한 경우 탐색할 필요 x
					
					Deque<Pos> queue = new ArrayDeque<>();
					queue.add(new Pos(i,j));
					int cnt = 0;

					while(!queue.isEmpty()) {
						Pos now =  queue.poll();
						
						if(dp[now.x][now.y] > 0) { // 만약 이전에 계산했던 데이터라면 탐색하지 않고 바로 사용
							cnt += dp[now.x][now.y];
							break;
						}
						
						cnt++;
						isVisited[now.x][now.y] = true;
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = now.x + dx[dir];
							int ny = now.y + dy[dir];
							
							if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
								if(board[nx][ny] == board[now.x][now.y] + 1) {
									queue.add(new Pos(nx, ny));
								}
							}
						}
					}
					
					dp[i][j] = cnt;
					if(resultCnt < cnt || (resultCnt == cnt && resultNum > board[i][j])) {
						resultCnt = cnt;
						resultNum = board[i][j];
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(resultNum).append(" ").append(resultCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Pos{
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}