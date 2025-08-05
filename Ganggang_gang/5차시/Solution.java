package com.ssafy.hw.step4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SpiderWeb {
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int startX = 0, startY = 0;

    static int[] dx = {-1, -1, -1,  0, 0, 1, 1, 1};
    static int[] dy = {-1,  0,  1, -1, 1,-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    visited = new boolean[n][n];
                    int value = 0;
                    dfs(i, j);
                    
                    if (value > ans) {
                        startX = i;
                        startY = j;
                        ans = value;
                    }
                }
            }
        }
        
        System.out.println(ans);
        System.out.println(startX + "," + startY);
    }
    
    public static int dfs(int x, int y) {
        int obstacle = 0;
        visited[x][y] = true;
        for(int i=0; i<8; i++) {
            int px = x + dx[i];
            int py = y + dy[i];
            System.out.println(px + " " + py);
            while( px >= 0 && px < n && py >= 0 && py < n ) {
                System.out.println(px + " "+ py);
                if (map[px][py] == 0) {
                    visited[px][py] = true;
                    if (obstacle == 1) {obstacle = 0; }
                }
                else if (map[px][py] == 1 && obstacle == 0) {
                    obstacle = 1;
                }
                else if (map[px][py] == 1 && obstacle == 1){
                    break;
                }

                px += dx[i];
                py += dy[i];
            }
        }
        return 0;

    }
}
