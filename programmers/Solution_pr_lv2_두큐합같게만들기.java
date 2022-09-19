package a0919;

import java.util.*;

public class Solution_pr_lv2_두큐합같게만들기 {

	public static void main(String[] args) {

		// 학재님 코드 한 번 보기
		// 배열과 포인터로 풀려면 인덱스와 사이즈로 제대로 포인터 짚어줘야 함. 상황 나눠서
		// 배열 길이 넘어갔으면 반대 큐를 가리키면 됐구나 ㅜㅜ
		int[] queue1 = { 1, 1 };
		int[] queue2 = { 1, 5 };

		System.out.println(solution(queue1, queue2));
	}

	// 큐1 합
	// 큐2 합
	// 두 큐의 합 / 2 가 되게
	// 차를 구한다?
	// 큰 쪽에서 pop하는 방식?
	// 원소 보면서 순서대로?
	// 같아질 때까지 하되
	// 큐*2의 길이만큼해서 안 되면 break;
	// 포인터로 풀고 싶었는데 1번 틀림...새로 들어온 원소를 포인터로 잡을 수 없어서 그런 것 같다.
	// 그냥 무난하게 큐로 풀자.

	public static int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		int len = queue1.length;

		long sum1 = 0;
		ArrayDeque<Integer> q1 = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			sum1 += queue1[i];
			q1.offer(queue1[i]);
		}

		long sum2 = 0;
		ArrayDeque<Integer> q2 = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			sum2 += queue2[i];
			q2.offer(queue2[i]);
		}

		long goal = (sum1 + sum2) / 2;
		if ((sum1 + sum2) % 2 == 1) {
			return -1;
		}

		while (answer < 3 * len) {
			if (sum1 == goal) {
				return answer;
			}
			if (sum1 > sum2) {
				int n = q1.poll();
				q2.offer(n);
				sum1 -= n;
				sum2 += n;
			} else if (sum1 < sum2) {
				int n = q2.poll();
				q1.offer(n);
				sum1 += n;
				sum2 -= n;
			}
			answer++;
		}
		return -1;
	}
}
