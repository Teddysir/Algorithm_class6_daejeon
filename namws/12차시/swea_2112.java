import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int K;
	static int result;
	static boolean[][] board;
	static boolean isFound;
	static List<Integer> rows;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			
			// 0, 1 테이블이라 그냥 boolean으로 관리
			board = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					if(st.nextToken().equals("1")) board[i][j] = true;
				}
			}
			
			result = Integer.MAX_VALUE;
			rows = new ArrayList<Integer>();
			if(checkBoard(board)) { // 기존 배열이 강도 테스트 통과하면 그대로 종료
				result = 0;
			}else {
				for(int i = 1; i <= N; i++) { // 약품 개수를 1개에서 N개까지 늘려가며 검사 진행
					isFound = false;
					makeCombination(i);
					if(isFound) break; // 하나라도 찾으면 더 이상 진행할 필요가 없음
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	// 조합을 만들어서 약품에 쓰일 행을 고름
	static void makeCombination(int limit) {
		// next permutation 방식을 활용해 조합을 구성 <- 이게 성능이나 코드로 보나 깔끔할 것 같았음
		int[] comb = new int[N];
		for(int i = 0; i < limit; i++) comb[N-1-i] = 1;
		
		addDrug(comb);
		
		while(!isFound) { // 하나라도 찾으면 더 이상 진행할 필요가 없음
			int stand = N-2;
			while(comb[stand] >= comb[stand+1]) {
				stand--;
				if(stand < 0) return; // 모든 조합을 만들었으면 종료
			}
			
			int target = 0;
			for(int i = N-1; i > stand; i--) {
				if(comb[stand] < comb[i]) {
					target = i;
					break;
				}
			}
			int temp = comb[stand]; comb[stand] = comb[target]; comb[target] = temp;
			
			int s = stand + 1; int e = N-1;
			while(s < e) {
				temp = comb[s]; comb[s] = comb[e]; comb[e] = temp;
				s++; e--;
			}
			
			addDrug(comb); // 만든 조합으로 약품을 넣고 검사를 해봄
		}
	}
	
	static void addDrug(int[] comb) {
		// 원본 배열 복사
		boolean[][] now = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				now[i][j] = board[i][j];
			}
		}
		
		rows.clear();
		for(int i = 0; i < N; i++) {
			if(comb[i] == 1) rows.add(i);
		}
		
		// 복사한 배열과 약품을 넣을 행을 넘기고, 각각 T와 F로 변경 시키고 마지막에 검사를 진행
		dfs(now, rows, 0, true);
		if(isFound) return;
		dfs(now, rows, 0, false);
	}
	
	static void dfs(boolean[][] board, List<Integer> rows, int index, boolean change) {
		if(isFound) return; // 검사 중간에 하나라도 찾으면 더 검사할 필요 없음
		
		if(index >= rows.size()) { // 끝까지 도달시 검사 진행
			if(checkBoard(board)) {
				result = rows.size();
				isFound = true;
				return;
			}else {
				return;
			}
		}
		
		// 현재 약품을 넣을 행을 수정
		int i = rows.get(index);
		for(int j = 0; j < M; j++) {
			board[i][j] = change;
		}
		
		// 다음 인덱스 호출
		dfs(board, rows, index + 1, true);
		dfs(board, rows, index + 1, false);
	}
	
	// 강도 테스트 통과를 확인하는 함수
	static boolean checkBoard(boolean[][] board) {
		for(int j = 0; j < M; j++) {
			boolean stand = board[0][j];
			int maxCnt = 0; int cnt = 1;
			// 열 마다 최대 강도 검사
			for(int i = 1; i < N; i++) {
				if(board[i][j] == stand) cnt++;
				else {
					maxCnt = Math.max(maxCnt, cnt);
					cnt = 1;
					stand = board[i][j];
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
			
			if(maxCnt < K) return false; // 하나의 열이라도 통과 못하면 false
		}
		return true;
	}
}
