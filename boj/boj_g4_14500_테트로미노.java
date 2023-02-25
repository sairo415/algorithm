package a20230225;

import java.io.*;
import java.util.*;

public class boj_g4_14500_테트로미노 {
	
	static int N, M, max;
	static int[][] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		getMax();
		System.out.println(max);
	}

	// 탐색하기
	public static void getMax() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				max = Math.max(max, getSquare(r, c));
				max = Math.max(max, getL(r, c));
				max = Math.max(max, getStick(r, c));
				max = Math.max(max, getT(r, c));
				max = Math.max(max, getZ(r, c));
			}
		}
	}

	// Z 모양
	public static int getZ(int r, int c) {
		
		int max = 0;
		int sum = arr[r][c];
		
		// 우하
		if(c+2 < M && r+1 < N) {
			sum += arr[r][c+1];
			sum += arr[r+1][c+1];
			sum += arr[r+1][c+2];
		}
		max = Math.max(max, sum);
		
		// 좌하
		sum = arr[r][c];
		if(c-2 >= 0 && r+1 < N) {
			sum += arr[r][c-1];
			sum += arr[r+1][c-1];
			sum += arr[r+1][c-2];
		}
		max = Math.max(max, sum);
		
		// 하우
		sum = arr[r][c];
		if(c+1 < M && r+2 < N) {
			sum += arr[r+1][c];
			sum += arr[r+1][c+1];
			sum += arr[r+2][c+1];
		}
		max = Math.max(max, sum);
		
		// 하좌
		sum = arr[r][c];
		if(c-1 >= 0 && r+2 < N) {
			sum += arr[r+1][c];
			sum += arr[r+1][c-1];
			sum += arr[r+2][c-1];
		}
		max = Math.max(max, sum);
		
		
		return max; // 네 가지 중 큰 값 반환
	}

	// T 모양
	public static int getT(int r, int c) {
		
		int max = 0;
		int sum = arr[r][c];
		
		// 상
		if(r-2 >= 0 && c-1 >= 0) {
			sum += arr[r-2][c];
			sum += arr[r-1][c];
			sum += arr[r-1][c-1];
		}
		max = Math.max(max, sum);
		
		// 하
		sum = arr[r][c];
		if(r+2 < N && c+1 < M) {
			sum += arr[r+2][c];
			sum += arr[r+1][c];
			sum += arr[r+1][c+1];
		}
		max = Math.max(max, sum);

		// 좌
		sum = arr[r][c];
		if(r+1 < N && c-2 >= 0) {
			sum += arr[r][c-2];
			sum += arr[r][c-1];
			sum += arr[r+1][c-1];
		}
		max = Math.max(max, sum);
		
		// 우
		sum = arr[r][c];
		if(r-1 >= 0 && c+2 < M) {
			sum += arr[r][c+1];
			sum += arr[r][c+2];
			sum += arr[r-1][c+1];
		}
		max = Math.max(max, sum);
		
		return max; // 네 방향 중 큰 값 반환
	}

	// L 모양
	public static int getL(int r, int c) {
		
		int max = 0;
		int sum = arr[r][c];
		
		// 우우하
		if(r+1 < N && c+2 < M) {
			sum += arr[r][c+1];
			sum += arr[r][c+2];
			sum += arr[r+1][c+2];
		}
		max = Math.max(max, sum);
		
		// 우하하
		sum = arr[r][c];
		if(r+2 < N && c+1 < M) {
			sum += arr[r][c+1];
			sum += arr[r+1][c+1];
			sum += arr[r+2][c+1];
		}
		max = Math.max(max, sum);

		// 하하좌
		sum = arr[r][c];
		if(r+2 < N && c-1 >= 0) {
			sum += arr[r+1][c];
			sum += arr[r+2][c];
			sum += arr[r+2][c-1];
		}
		max = Math.max(max, sum);
		
		// 하좌좌
		sum = arr[r][c];
		if(r+1 < N && c-2 >= 0) {
			sum += arr[r+1][c];
			sum += arr[r+1][c-1];
			sum += arr[r+1][c-2];
		}
		max = Math.max(max, sum);
		
		// 좌좌상
		sum = arr[r][c];
		if(r-1 >= 0 && c-2 >= 0) {
			sum += arr[r][c-1];
			sum += arr[r][c-2];
			sum += arr[r-1][c-2];
		}
		max = Math.max(max, sum);
		
		// 좌상상
		sum = arr[r][c];
		if(r-2 >= 0 && c-1 >= 0) {
			sum += arr[r][c-1];
			sum += arr[r-1][c-1];
			sum += arr[r-2][c-1];
		}
		max = Math.max(max, sum);
		
		// 상상우
		sum = arr[r][c];
		if(r-2 >= 0 && c+1 < M) {
			sum += arr[r-1][c];
			sum += arr[r-2][c];
			sum += arr[r-2][c+1];
		}
		max = Math.max(max, sum);
		
		// 상우우
		sum = arr[r][c];
		if(r-1 >= 0 && c+2 < M) {
			sum += arr[r-1][c];
			sum += arr[r-1][c+1];
			sum += arr[r-1][c+2];
		}
		max = Math.max(max, sum);
		
		return Math.max(max, sum); // 8방향 중 큰 값 반환
	}
	
	// 막대 모양
	public static int getStick(int r, int c) {
		
		int max = arr[r][c];
		int sum = arr[r][c];
		
		for (int i = 1; i <= 3; i++) {
			if(c+3 < M) { // 우방향
				sum += arr[r][c+i];
			}
			if(r+3 < N) { // 하방향
				max += arr[r+i][c];
			}
		}
		return Math.max(max, sum); // 우방향, 하방향 중 큰 값 반환
	}
	
	// 사각형 모양
	public static int getSquare(int r, int c) {
		
		int max = arr[r][c];
		int sum = arr[r][c];
		
		if(r+1 < N && c+1 < M) {
			sum += arr[r][c+1];
			sum += arr[r+1][c];
			sum += arr[r+1][c+1];
		}
		if(r+1 < N && c-1 >= 0) {
			max += arr[r+1][c];
			max += arr[r+1][c-1];
			max += arr[r][c-1];
		}
		return Math.max(max, sum); // 우방향, 하방향 중 큰 값 반환
	}
}
