package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_g1_13460_구슬탈출2 {

    static int N, M;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int redR = -1, redC = -1, blueR = -1, blueC = -1;
        for (int r = 0; r < N; r++) {
            String row = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = row.charAt(c);
                if(map[r][c] == 'R'){
                    redR = r;
                    redC = c;
                    map[r][c] = '.';
                } else if(map[r][c] == 'B'){
                    blueR = r;
                    blueC = c;
                    map[r][c] = '.';
                }
            }
        }
        br.close();
        System.out.println(bfs(redR, redC, blueR, blueC, 0));
    }
    static public int bfs(int redR, int redC, int blueR, int blueC, int cnt) {
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[redR][redC][blueR][blueC] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {redR, redC, blueR, blueC, cnt+1});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            redR = now[0];
            redC = now[1];
            blueR = now[2];
            blueC = now[3];
            cnt = now[4];
            if (cnt > 10) { // cnt : 이번에 이동할 횟수 (이전에 이동한 횟수 X)
                break;
            }
//            System.out.println(redR + " " + redC + " " + blueR + " " + blueC);
//            System.out.println(cnt);
            for (int d = 0; d < 4; d++) {
                // 파란 공 이동
                int nBlueR = blueR;
                int nBlueC = blueC;
                int bMove = 0;
                boolean blueInO = false;
                while (map[nBlueR+dr[d]][nBlueC+dc[d]] != '#'){
                    nBlueR += dr[d];
                    nBlueC += dc[d];
                    bMove++;
                    if(map[nBlueR][nBlueC] == 'O'){
                        blueInO = true;
                        break;
                    }
                }
                // 빨간 공 이동
                int nRedR = redR;
                int nRedC = redC;
                int rMove = 0;
                boolean redInO = false;
                while (map[nRedR+dr[d]][nRedC+dc[d]] != '#'){
                    nRedR += dr[d];
                    nRedC += dc[d];
                    rMove++;
                    if (map[nRedR][nRedC] == 'O'){
                        redInO = true;
                        break;
                    }
                }

                if (blueInO) { // 파란 공 들어가면 continue
                    continue;
                } else if (redInO) { // 빨간 공 들어가면 끝
                    return cnt;
                } else if (redR == nRedR && redC == nRedC && blueR == nBlueR && blueC == nBlueC) { // 둘다 이전과 위치 같으면 continue
                    continue;
                }

                // 둘의 위치가 같으면 위치 조정
                if (nRedR == nBlueR && nRedC == nBlueC) {
                    if (rMove > bMove) {
                        nRedR = nBlueR - dr[d];
                        nRedC = nBlueC - dc[d];
                    } else {
                        nBlueR = nRedR - dr[d];
                        nBlueC = nRedC - dc[d];
                    }
                }

                // 방문한 적 없으면 큐에 추가
                if (!visited[nRedR][nRedC][nBlueR][nBlueC]) {
                    visited[nRedR][nRedC][nBlueR][nBlueC] = true;
                    q.offer(new int[] {nRedR, nRedC, nBlueR, nBlueC, cnt+1});
                }
            }
        }
        return -1;
    }

}
