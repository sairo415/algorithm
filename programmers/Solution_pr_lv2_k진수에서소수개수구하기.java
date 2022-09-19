package a0919;

import java.util.StringTokenizer;

public class Solution_pr_lv2_k진수에서소수개수구하기 {

	public static void main(String[] args) throws Exception {

		int n = 437674;
//		int n = 110011;
		int k = 3;
//		int k = 10;

		System.out.println(solution(n, k));
		// 3
		// 2
	}

	public static int solution(int n, int k) {
		int answer = 0;

		// k진수로 바꾸기 : k로 나눈 나머지들
		// 내장 함수 있음. Integer.toString(n, k);
		StringBuffer sb = new StringBuffer();
		while (n > 0) {
			sb.append(n % k);
			n /= k;
		}
		sb.reverse();

		// 0으로 구분 후 소수 판별
		StringTokenizer st = new StringTokenizer(sb.toString(), "0");
		at : while (st.hasMoreTokens()) {
			long num = Long.parseLong(st.nextToken());
			if(num < 2) continue;
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if(num % i == 0) {
					continue at;
				}
			}
			answer++;
		}
		return answer;
	}
}
