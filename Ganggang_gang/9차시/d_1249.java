import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class pair {
    int x, y;
    public pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class d_1249 {
    static final int INF = 100_000_000;
    // 상, 하, 좌, 우
    static int[] dx = { 0,  0, -1, 1 };
    static int[] dy = { 1, -1,  0, 0 };
    
    static int n;
    static int[][] arr, dist;
    static boolean[][] visited;
    static Queue<pair> q = new LinkedList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= Tc; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            arr      = new int[n][n];
            dist     = new int[n][n];
            visited  = new boolean[n][n];
            q.clear();
            
            for (int i = 0; i < n; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < n; j++) {
                    arr[i][j]  = line.charAt(j) - '0';
                    dist[i][j] = INF;
                }
            }
            
            dist[0][0] = 0;
            visited[0][0] = true;
            q.offer(new pair(0, 0));
            
            spfa();
            
            System.out.println("#" + tc + " " + dist[n-1][n-1]);
        }
    }
    
    static void spfa() {
        while (!q.isEmpty()) {
            pair cur = q.poll();
            int x = cur.x, y = cur.y;
            visited[x][y] = false;
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                
                int cost = dist[x][y] + arr[nx][ny];
                if (cost < dist[nx][ny]) {
                    dist[nx][ny] = cost;
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new pair(nx, ny));
                    }
                }
            }
        }
    }
}