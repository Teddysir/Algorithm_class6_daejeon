import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5650 {
	static int[][] map;
	static int n, ans;
	static int[] dx = { -1, 1, 0, 0};
	static int[] dy = { 0,0,-1,1};
	
	static int[][] block = {
			{0,1,2,3},
			//상하좌우 만났을때 바뀌는 방향
			{1,3,0,2}, //1번 블록
			{3,0,1,2}, //2번 블록
			{2,0,3,1}, //3번 블록
			{1,2,3,0}, //4번 블록
			{1,0,3,2} //5번 블록
	};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Tc = Integer.parseInt(br.readLine().trim());
		for(int t = 1; t <= Tc; t++) {
			n = Integer.parseInt(br.readLine().trim());
			ans = 0;
			map = new int[n+2][n+2];
			for(int i=1; i<=n ;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int j =1; j<=n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//5번 블록과 테두리는 일직선으로 돌아가기 때문에 테두리도 5번블록으로 하면 편하다
			for(int i=0; i<=n ;i++) {
				map[i][0] = map[i][n+1] = map[0][i] = map[n+1][i] = 5;
			}
			
			for(int i =1;i<=n; i++) {
				for(int j=1; j <=n; j++) {
					if(map[i][j] == 0) {
						for(int d=0; d<4; d++) {
							move(i,j,d);
						}
					}
				}
			}
			System.out.println("#" + t + " "  + ans);
		}
	}
	
	public static void move(int sx, int sy, int d) {
		int x = sx;
		int y = sy;
		int score = 0;
		
		while(true) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if ((nx == sx && ny == sy) || (map[nx][ny] == -1)) {
				if (ans < score) ans = score;
				return;
			}
			if(map[nx][ny] >0) {
				if (map[nx][ny] < 6 ) {
					int change_num = map[nx][ny];
					d = block[change_num][d];
					score++;
				}
				else {
					//웜홀 케이스
					shoot : for (int i =1; i<=n; i++) {
						for(int j=1; j<=n; j++) {
							if ((nx != i || ny != j) && map[i][j] == map[nx][ny]) {
								nx = i;
								ny = j;
								break shoot; //찾으면 2중 포문 벗어나
							}
						}
					}
				}
			}
			x = nx;
			y = ny;
		}
	}

}
