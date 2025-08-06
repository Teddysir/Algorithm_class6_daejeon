package week03;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SWEA_1249 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int dx[] = {0, 1, -1, 0}; // 우하상좌
		int dy[] = {1, 0, 0, -1};
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			int[][] result = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(result[i], -1);
			}
			String now;
			for(int i=0; i<N; i++) {
				now = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = now.charAt(j) - '0';
				}
			}
			
			result[0][0] = 0;
			Deque<Point> list = new ArrayDeque<>();
			Point p = new Point(0, 0);
			list.add(p);
			while(!list.isEmpty()) {
				p = list.pollFirst();
				for(int a=0; a<4; a++) { // 우하
					if(inArray(p.x+dx[a], p.y+dy[a])) {
						// 최초값
						if(result[p.x+dx[a]][p.y+dy[a]] == -1) {
							result[p.x+dx[a]][p.y+dy[a]] = result[p.x][p.y] + map[p.x+dx[a]][p.y+dy[a]];
							list.add(new Point(p.x+dx[a], p.y+dy[a]));
						}
						// 갱신 -> 이미 해당 노드가 list에 있음
						else {
							if(result[p.x][p.y] + map[p.x+dx[a]][p.y+dy[a]] < result[p.x+dx[a]][p.y+dy[a]]) {
								result[p.x+dx[a]][p.y+dy[a]] = result[p.x][p.y] + map[p.x+dx[a]][p.y+dy[a]];
								list.add(new Point(p.x+dx[a], p.y+dy[a]));
							}
						}
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(result[N-1][N-1]);
			System.out.println(sb.toString());
		}
	}
	
	public static boolean inArray(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<N) return true;
		else return false;
	}
}
