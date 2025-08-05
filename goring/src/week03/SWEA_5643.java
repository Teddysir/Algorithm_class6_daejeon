package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_5643 {
	static int N;
	static int M;
	static int[][] node;
	static boolean[] visited;
	static boolean[] visited2;
	static HashSet<Integer>[] result;
	static HashSet<Integer>[] result2;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			node = new int[N+1][N+1];
			
			StringTokenizer st;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				// 각 i에 아래 노드에 해당하면 1 저장
				node[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			visited = new boolean[N+1];
			visited2 = new boolean[N+1];
			result = new HashSet[N+1];
			result2 = new HashSet[N+1];
			for(int i=0; i<=N; i++) {
				result[i] = new HashSet<>();
				result2[i] = new HashSet<>();
			}
			
			for(int i=1; i<=N; i++) {
				findMinNode(i);
				findMaxNode(i);
			}
			
			
			int answer = 0;
			for(int i=1; i<=N; i++) {
				if(result[i].size() + result2[i].size() == N-1) {
					answer++;
				}
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}
	
	public static void findMinNode(int x) {
		if(visited[x]) return;
		
		for(int i=1; i<=N; i++) {
			if(node[x][i] == 1 && !result[x].contains(i)) {
				result[x].add(i);
				if(!visited[i]) {
					findMinNode(i);
				}
				result[x].addAll(result[i]);
			}
		}
		visited[x] = true;
	}
	
	public static void findMaxNode(int x) {
		if(visited2[x]) return;
		
		for(int i=1; i<=N; i++) {
			if(node[i][x] == 1 && !result2[x].contains(i)) {
				result2[x].add(i);
				if(!visited2[i]) {
					findMaxNode(i);
				}
				result2[x].addAll(result2[i]);
			}
		}
		visited2[x] = true;
	}
}
