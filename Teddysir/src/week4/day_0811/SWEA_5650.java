import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5650 {
    static int[][] map;
    static int size, ans, T;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int[][] block = { { 0, 1, 2, 3 }, // -> 기본 블럭 0 번 초기화
            { 1, 3, 0, 2 }, { 3, 0, 1, 2 }, { 2, 0, 3, 1 }, { 1, 2, 3, 0 }, { 1, 0, 3, 2 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());

        for (int k = 1; k <= T; k++) {

            size = Integer.parseInt(br.readLine().trim());

            map = new int[size + 2][size + 2]; // 상하좌우 한 칸씩 더 만들어서 이거를벽으로 만드네

            for (int i = 1; i <= size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 1; j <= size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i <= size + 1; i++) {
                map[i][0] = map[0][i] = map[size + 1][i] = map[i][size + 1] = 5; // 둘러싼 벽을 5로
            }
            ans = 0;

            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    if (map[i][j] == 0) {
                        for (int go = 0; go < 4; go++) {
                            solution(i, j, go);
                        }
                    }
                }
            }

            sb.append("#").append(k).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void solution(int startR, int startC, int d) {

        int r = startR;
        int c = startC;
        int score = 0;

        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (map[nr][nc] == -1 || (nr == startR && nc == startC)) {
                ans = Math.max(ans, score);
                return;
            }

            if (map[nr][nc] > 0) {
                if (map[nr][nc] < 6) {
                    int blockNum = map[nr][nc];
                    d = block[blockNum][d];
                    score++;
                } else {
                    out: for (int i = 1; i <= size; i++) {
                        for (int j = 1; j <= size; j++) {
                            if ((nr != i || nc != j) && map[i][j] == map[nr][nc]) {
                                nr = i;
                                nc = j;
                                break out;
                            }
                        }
                    }
                }
            }
            r = nr;
            c = nc;
        }

    }
}