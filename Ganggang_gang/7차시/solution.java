import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adj = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            visited = new boolean[N + 1];
            int groups = 0;

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    groups++;
                }
            }
            System.out.println("#" + tc + " " + groups);
        }
        br.close();
    }

    static void dfs(int node) {
        visited[node] = true;
        for (int next : adj[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
