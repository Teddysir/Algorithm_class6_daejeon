import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int n,m, cnt, ans;
	static boolean[][] arr;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			ans = 0;
			sb.append("#").append(t).append(" ");
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			arr = new boolean[n+1][n+1];
			
			for(int i=0; i<m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[b][a] = true;
			}
			
			for(int i =1; i<n+1; i++) {
				cnt = 0;
				long_dfs(i,new boolean[n+1]); //현재 노드보다 긴 사람 검색
				short_dfs(i,new boolean[n+1]); //현재 노드보다 짧은 사람 검색
				if(cnt == n-1) ans++;				
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}
	
	public static void long_dfs(int start, boolean[] visited) {
		visited[start] = true;
		for(int i=1;i<n+1;i++) {
			if(arr[start][i] == true && !visited[i]) {
				cnt++;
				long_dfs(i,visited);
			}
		}
	}
	
	public static void short_dfs(int start, boolean[] visited) {
		visited[start] = true;
		for(int i=1;i<n+1;i++) {
			if(arr[i][start] == true && !visited[i]) {
				cnt++;
				short_dfs(i,visited);
			}
		}
	}

}
