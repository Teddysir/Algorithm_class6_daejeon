import java.io.*;
import java.util.*;

public class BOJ_17471 {
    static int n;
    static int[] prr; //사람 수 저장
    static List<Integer>[] g;
    static boolean[] pick_A; // A 그룹
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        prr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) prr[i] = Integer.parseInt(st.nextToken());

        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int k = 0; k < cnt; k++) {
                int v = Integer.parseInt(st.nextToken());
                g[i].add(v);
                g[v].add(i);
            }
        }

        pick_A = new boolean[n + 1];
        dfsChoose(1, 0);
        
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfsChoose(int pos, int cntA) {
        if (pos == n + 1) {
            if (cntA == 0 || cntA == n) return; // 공집합/전체집합은 무시
            
            //연결 체크
            if (connected(true, cntA) && connected(false, n - cntA)) {
                int diff = Math.abs(sum(true) - sum(false));
                ans = Math.min(ans, diff);
            }
            return;
        }
        // A에 포함
        pick_A[pos] = true;
        dfsChoose(pos + 1, cntA + 1);
        // A에 미포함
        pick_A[pos] = false;
        dfsChoose(pos + 1, cntA);
    }

    //서로 연결하고 있는지 검사
    static boolean connected(boolean group, int size) {
        if (size == 0) return false;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        // 시작 노드 찾기
        for (int i = 1; i <= n; i++) {
            if (pick_A[i] == group) {
                q.add(i);
                visited[i] = true;
                break;
            }
        }
        int seen = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            seen++;
            for (int next : g[curr]) {
                if (!visited[next] && pick_A[next] == group) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return seen == size; //BFS 탐색을 하고 나서 지나간 노드가 SIZE랑 다르면 인접하지 않은 배열임을 의미
    }

    static int sum(boolean group) { //true 이면 A 형 집합만 합, false 이면 B 형 집합만 합
        int s = 0;
        for (int i = 1; i <= n; i++)  {
            if (pick_A[i] == group) s += prr[i];
        }
        return s;
    }
}
