import java.io.*;
import java.util.*;
 
public class SWEA_2112 {
 
    static int D, W, K, TC;
    static int[][] map;
    static int ans;
    static int[] A, B;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        TC = Integer.parseInt(br.readLine());
 
        for (int k = 1; k <= TC; k++) {
 
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[D][W];
 
            A = new int[W];
            B = new int[W];
            Arrays.fill(B, 1);
 
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            ans = K;
 
            make(0, 0);
 
            sb.append("#").append(k).append(" ").append(ans).append("\n");
        }
 
        System.out.println(sb);
 
    }
 
    static void make(int r, int cnt) {
        if (check()) {
            ans = Math.min(ans, cnt);
            return;
        }
 
        if (ans < cnt) {
            return;
        }
 
        if (r == D) {
            return;
        }
 
        make(r + 1, cnt);
        int[] tmp = map[r];
 
        map[r] = A;
        make(r + 1, cnt + 1);
 
        map[r] = B;
        make(r + 1, cnt + 1);
 
        map[r] = tmp;
    }
 
    static boolean check() {
        for (int c = 0; c < W; c++) {
            boolean flag = false;
            int cnt = 1;
 
            for (int r = 1; r < D; r++) {
                if (map[r][c] == map[r - 1][c]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if (cnt == K) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
 
}