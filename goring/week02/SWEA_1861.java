package week02;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_1861 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] visited;
	static int[][] map;
	static int N;
	static int[] answer = new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st;
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dfs(i, j, 1);									
				}
			}
		}
		System.out.println(answer[0]);
	}
	
	public static int dfs(int x, int y, int level) {
		if(visited[x][y] > 0) return 0;
		
		int now = 0;
		for(int i=0; i<4; i++) {
			if(inArray(x+dx[i], y+dy[i])) {
				// 주변에 연속되는 수가 있음
				if(map[x+dx[i]][y+dy[i]] == map[x][y]+1 || map[x+dx[i]][y+dy[i]] == map[x][y]-1) {
					now = dfs(x+dx[i], y+dy[i], level+1);
					if(now>0) {
						visited[x][y] = level;
						return now-1;
					}
					else {
						visited[x][y] = level + 1 + now;
						return now+1;
					}
				}
				// 주변에 연속되는 수가 없음
				else {
					if(answer[1] < level) {
						answer[0] = map[x][y];
						answer[1] = level;
						visited[x][y] = level;						
					}
					return level/2 - 1;
				}
			}
		}
		return 0;

	}
	
	public static boolean inArray(int x, int y) {
		if(x<N && x>=0 && y<N && y>=0) return true;
		else return false;
	}
}
