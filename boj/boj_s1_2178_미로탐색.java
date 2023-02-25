package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_s1_2178_미로탐색 {
    // 입력
    // bfs
    // 1이면서 방문한 적 없으면 큐에 넣는다.
    // N,M이면 탈출
    // 거리 배열 만들기

    static int N, M;
    static int[][] arr, dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int r = 0; r < N; r++) {
            String row = br.readLine();
            for (int c = 0; c < M; c++) {
                arr[r][c] = row.charAt(c)-'0';
            }
        }
        br.close();
        System.out.println(bfs());
    }

    static public int bfs() {
        dist = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                dist[r][c] = 987654321;
            }
        }
        dist[0][0] = 1;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});
        while (!queue.isEmpty()) {
            int[] rc = queue.poll();
            int r = rc[0];
            int c = rc[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 1 && dist[r][c]+1 < dist[nr][nc]) {
                    queue.offer(new int[] {nr, nc});
                    dist[nr][nc] = dist[r][c] + 1;
                }
            }
        }
        return dist[N-1][M-1];
    }
}
