import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int arr[][];
	static int n;
	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <=TC; t++ ) {
			int ans = 0;
			int pos = 0;
			int cango = 0;
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
						cango = cango(i, j, 1);
						if (cango > ans) {
						    ans = cango;
						    pos = arr[i][j];
						}
						else if (cango == ans && arr[i][j] < pos) {
						    pos = arr[i][j];
						}

				}
			}
			sb.append("#").append(t).append(" ").append(pos).append(" ").append(ans).append("\n");
			
			
		}
		System.out.println(sb);

	}
	
	public static int cango(int x, int y, int cnt) {
		boolean flag = false;
		int[] dx = {0,0,-1,1};
		int[] dy = {1,-1,0,0};
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < n && ny >= 0 && ny <n) {
				if(arr[x][y] + 1 == arr[nx][ny]) {
					return cango(nx, ny, cnt+1);
				}
			}
		}
		
		return cnt;
	}

}
