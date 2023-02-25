package a20230224;

import java.io.*;
import java.util.*;

public class boj_g4_17281_야구 {
	
	static int N, maxScore;
	static int[][] playersScore;
	static int[] playerOrder;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		playersScore = new int[N][9];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++) {
				playersScore[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		visited = new boolean[9];
		playerOrder = new int[9];
		visited[0] = true;
		playerOrder[0] = 0;
		perm(1);
		System.out.println(maxScore);
	}
	
	// 순열 (4번 타자 제외)
	public static void perm(int depth) {
		// 기저 조건에서 게임 진행 후 최대값 갱신
		if(depth == 9) {
			maxScore = Math.max(maxScore, getScore());
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				playerOrder[depth] = i;
				perm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	// 게임 진행 후 점수 반환
	public static int getScore() {
		int score = 0;
		int nowPointer = 6; // 첫 이닝에 0번째가 4번째로 타석에 서야 하므로
		int nowPlayer;
		int outCnt;
		int[] locations;
		
		for (int i = 0; i < N; i++) {
			outCnt = 0; // 아웃카운트 초기화
			locations = new int[9]; // 주자들 초기화
			while(outCnt < 3) { // 아웃 카운트가 3이 되면 다음 이닝으로
				nowPlayer = playerOrder[nowPointer];
				if(playersScore[i][nowPlayer] == 0) { // 아웃카운트 세기
					outCnt++;
				} else {
					locations[nowPointer] += playersScore[i][nowPlayer]; // 출루 시키기
					for (int j = 0; j < 9; j++) {
						if(locations[j] != 0 && j != nowPointer) { // 본인 제외 출루한 선수 진루시키기
							locations[j] += playersScore[i][nowPlayer];
						}
						if (locations[j] >= 4) { // 4칸 이상 진루하면(홈으로 돌아오면) 득점
							score++;
							locations[j] = 0;
						}
					}
				}
				// 다음 타자
				nowPointer += 1; 
				nowPointer %= 9; 
			}
		}
		return score;
	}
}
