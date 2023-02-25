package a20230223;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_s2_14889_스타트와링크 {
	
	static int N, min = 987654321;
	static int[][] arr;
	static boolean[] teamA;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		teamA = new boolean[N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		comb(0, 0);
		System.out.println(min);
		
	}
	
	// 조합
	public static void comb(int depth, int start) {
		
		if(depth == N/2) {
			// 팀 능력치 차이로 최소값 갱신
			int teamPowerDiff = getTeamPowerDiff();
			min = Math.min(min, teamPowerDiff);
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (!teamA[i]) {
				teamA[i] = true;
				comb(depth+1, i+1);
				teamA[i] = false;
			}
		}
	}
	
	// 팀 능력치 차이 구하기
	public static int getTeamPowerDiff() {
		int teamAPower = 0;
		int teamBPower = 0;
		for (int i = 0; i < N; i++) {
			if(teamA[i]) {
				for (int j = i; j < N; j++) {
					if (teamA[j]) {
						teamAPower += arr[i][j];
						teamAPower += arr[j][i];
					}
				}
			} else {
				for (int j = i; j < N; j++) {
					if(!teamA[j]) {
						teamBPower += arr[i][j];
						teamBPower += arr[j][i];
					}
				}
			}
		}
		return Math.abs(teamAPower - teamBPower);
	}

}
