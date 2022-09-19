package a0801;

import java.io.*;

public class Solution_d3_1289_원재의메모리복구하기 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_d3_1289.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			String N = in.readLine();
			char[] c = N.toCharArray();
			int sum = c[0] - '0';

			for (int i = 1; i < c.length; i++) {
				if (c[i - 1] != c[i]) {
					sum++;
				}
			}

			System.out.println("#" + tc + " " + sum);
		}

		in.close();
	}
}
