import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2112{
    static int d, w, k;
    static int[][] arr;
    static int ans;
    static int[] a,b; //a는 0로 초기화, b는 1 로 초기화하는 배열


    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[d][w];
            for (int i = 0; i < d; i++) {
            	StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st2.nextToken());
                }
            }

            a = new int[w];
            b = new int[w];
            Arrays.fill(b, 1); //b1로 채워두기

            ans = k; //최댓값은 k개에 해당하기 때문에 k값으로 초기화

            make(0, 0);

            System.out.println("#" + tc + " " + ans);

        } 
    }

    private static void make(int r, int cnt) {
        if (check()) {
            ans = Math.min(ans, cnt);
            return; //약품 최솟값 리턴
        }
        //최솟값 수치를 넘어갈 경우 가지치기
        if (ans < cnt)
            return; 
        if (r == d) //행기준으로 초기화하는데 d값이 될경우 더 이상 갈곳이 없기 때문에 return
            return;

        //아무것도 적용 하지 않는 경우
        make(r + 1, cnt);
        int[] tmp = arr[r]; //형제 분기점을 위한 백업

        //a주입
        arr[r] = a;
        make(r + 1, cnt + 1);

        //b주입
        arr[r] = b;
        make(r + 1, cnt + 1);

        //윈상복구
        arr[r] = tmp;

    }

    private static boolean check() {
        //열부터 검사
        for (int c = 0; c < w; c++) {
            boolean flag = false;
            int cnt = 1;
            for (int r = 1; r < d; r++) {
                if (arr[r][c] == arr[r - 1][c])
                    cnt++;
                else
                    cnt = 1;

                if (cnt == k) {
                    flag = true;
                    break;
                }
            }

            if (!flag)
                return false;
        }
        return true;
    }


}
