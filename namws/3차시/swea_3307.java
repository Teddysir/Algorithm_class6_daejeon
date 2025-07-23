import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer> result = new ArrayList<>(); // LIS를 찾아 저장할 list 
			
			for(int i = 0; i < N; i++) {
				
				int findedIndex = Collections.binarySearch(result, arr[i]);
				
				if(findedIndex >= 0) { // list내에 동일한 값이 있는 경우
					result.add(findedIndex, arr[i]);
				}else { // list 내에 동일한 값이 없는 경우
					
					int insertPos = (findedIndex + 1) * -1;
					if(insertPos == result.size()) {
						result.add(arr[i]); // 최대값 보다 크면 add
					}else {
						result.set(insertPos, arr[i]); // 수열의 동일한 위치의 item을 더 낮은 수로 update할 수 있으므로 update
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(result.size()).append("\n");
		}
		System.out.print(sb);
	}
}
