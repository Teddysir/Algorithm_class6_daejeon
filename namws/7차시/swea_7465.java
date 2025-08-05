import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[] arr;
	static int[] rank; //자신이 속한 그룹의 노드 개수를 저장
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			rank = new int[n];
			
			for(int i = 0; i < n; i++) {
				arr[i] = i; //각자 자신의 그룹 대표로 설정
				rank[i] = 1; //처음엔 각 그룹 별 개수가 1
			}
			
			for(int i = 0; i < m; i++) { //무방향 그래프 입력 시 union 작업 수행
				st = new StringTokenizer(br.readLine());
				
				int node1 = Integer.parseInt(st.nextToken()) - 1;
				int node2 = Integer.parseInt(st.nextToken()) - 1;
				
				union(node1, node2);
			}
			
			Set<Integer> result = new HashSet<Integer>();
			for(int i = 0; i < n; i++) {
				result.add(find(i)); // 대표 노드들을 결과에 포함
			}
			
			sb.append("#").append(testCase).append(" ").append(result.size()).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void union(int node1, int node2) {
		int stand1 = find(node1);
		int stand2 = find(node2);
		
		if(stand1 == stand2) return; // 같은 집합이면 pass
		
		if(rank[stand1] < rank[stand2]) { // 더 작은 rank를 가진 집합을 더 큰 집합에 속하게 함 => 나중에 각 node의 대표를 update할 때 좀 더 효율적으로 하기 위함
			arr[stand1] = stand2;
			rank[stand2] += rank[stand1];
		} else {
			arr[stand2] = stand1;
			rank[stand1] += rank[stand2]; 
		}
	}
	
	public static int find(int node) {
		if(arr[node] == node) return node; // 자신이 대표라면 그대로 반환
		else { // 아니라면 최상단 대표 노드를 찾아 저장 후 반환
			arr[node] = find(arr[node]);
			return arr[node];
		}
	}
}
