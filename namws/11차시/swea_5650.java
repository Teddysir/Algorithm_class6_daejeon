import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] board;
	static int N;
	static int result;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][][] wormwhole; // 웜홀을 저장
	static boolean[] isWormwhole; // 웜홀 존재 여부
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			
			board = new int[N][N];
			wormwhole = new int[5][2][2];
			isWormwhole = new boolean[5];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					// 웜홀일 경우 미리 저장
					if(6 <= board[i][j]) {
						if(isWormwhole[board[i][j] - 6]) {
							wormwhole[board[i][j] - 6][1][0] = i;
							wormwhole[board[i][j] - 6][1][1] = j;
						}else {
							wormwhole[board[i][j] - 6][0][0] = i;
							wormwhole[board[i][j] - 6][0][1] = j;
							isWormwhole[board[i][j] - 6] = true;
						}
					}
				}
			}
			
			result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] != 0) continue;
					run(i ,j);
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static void run(int x, int y) {
		for(int standDir = 0; standDir < 4; standDir++) {
			int dir = standDir;
			int score = 0;
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 보드 검사
			while(true) {
				
				// 보드를 벗어난 경우: 점수 추가 후 반대방향 이동
				if(!(nx >= 0 && nx < N && ny >= 0 && ny < N)) {
					dir = (dir + 2) % 4;
					nx += dx[dir];
					ny += dy[dir];
					score++;
					continue;
				}
				
				// 종료조건 도달 시 종료
				if((nx == x & ny == y) || board[nx][ny] == -1) {
					break;
				}
				
				// 웜홀일 경우: 다른 웜홀로 이동
				if(board[nx][ny] >= 6) {
					int index = board[nx][ny] - 6;
					if(nx == wormwhole[index][0][0] && ny == wormwhole[index][0][1]) {
						nx = wormwhole[index][1][0];
						ny = wormwhole[index][1][1];
					} else {
						nx = wormwhole[index][0][0];
						ny = wormwhole[index][0][1];
					}
				}
				
				// 벽인 경우: 점수 추가 후 방향 변경
				if(board[nx][ny] >= 1 && board[nx][ny] <= 5) {
					// 이동 방향 변경을 깔끔하게 배열로 빼둘까 했는데, 그게 더 귀찮을 거 같아서 그냥 하드코딩으로...
					if(board[nx][ny] == 1) {
						if(dir == 0) {
							dir = 2;
						}else if(dir == 1) {
							dir = 0;
						}else if(dir == 2) {
							dir = 3;
						}else {
							dir = 1;
						}
					}else if(board[nx][ny] == 2) {
						if(dir == 0) {
							dir = 2;
						}else if(dir == 1) {
							dir = 3;
						}else if(dir == 2) {
							dir = 1;
						}else {
							dir = 0;
						}
					}else if(board[nx][ny] == 3) {
						if(dir == 0) {
							dir = 1;
						}else if(dir == 1) {
							dir = 3;
						}else if(dir == 2) {
							dir = 0;
						}else {
							dir = 2;
						}
					}else if(board[nx][ny] == 4) {
						if(dir == 0) {
							dir = 3;
						}else if(dir == 1) {
							dir = 2;
						}else if(dir == 2) {
							dir = 0;
						}else {
							dir = 1;
						}
					}else {
						if(dir == 0) {
							dir = 2;
						}else if(dir == 1) {
							dir = 3;
						}else if(dir == 2) {
							dir = 0;
						}else {
							dir = 1;
						}
					}
					score++;
				}
				
				nx += dx[dir];
				ny += dy[dir];
			}
			
			//최대값 갱신
			result = Math.max(result, score);
		}
	}
}
