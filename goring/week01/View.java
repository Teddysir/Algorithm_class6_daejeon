package week01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class View {
	public static void main(String args[]) throws Exception
    {
        //Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T;
        T=10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            int result = 0;
            for(int j=2; j<N-2; j++) {
                max = Math.max(arr[j-2], Math.max(arr[j-1], Math.max(arr[j+1], arr[j+2])));
                result += Math.max(arr[j] - max, 0);
            }
            
            StringBuffer sb = new StringBuffer();
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
            
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
    }
}
