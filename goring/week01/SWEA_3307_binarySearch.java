package week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class SWEA_3307_binarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            // 최대 길이 수열의 길이?를 저장할 배열
            LinkedList<Integer> result = new LinkedList<>();
            // 서치 결과 인덱스
            int nowIndex;
            for(int i=0; i<N; i++) {
                // arr[i]가 result에 있는지 탐색 후 index값 반환
                nowIndex = Collections.binarySearch(result, arr[i]);
 
                // binarysearch는 검색한 배열에 해당 값이 존재하지 않는 경우
                // 해당 배열에 오름차순 정렬로 위치해야 할 -index-1 값을 반환
                 
                // binarysearch로 들어온 index(-index-1)를 실제 index로 변환하는 과정
                int point = (nowIndex+1)*-1;
                // point가 result의 모든 요소 중 가장 큰 값임
                if(point == result.size()) {
                    // result size가 1개 커짐
                    result.add(arr[i]);
                } 
                // 가장 최근의 요소가 piont보다 큰 값임
                // 그래서 원래는 넣을 필요 없지만
                // point 위치를 해당 요소로 덮어씌워야 다음 배열들이 얼마나 이어지는지 체크 가능
                // 그니까 결과적으로, 최장 부분 배열을 구하는 건 불가능, 길이를 구하는 건 가능
                else {
                    result.set(point, arr[i]);
                }
                 
            }
            sb.append("#").append(t).append(" ").append(result.size()).append("\n");
        }
        System.out.println(sb);
    }
}