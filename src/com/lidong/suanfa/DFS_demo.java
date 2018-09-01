package com.lidong.suanfa;

/**
 * 
 * @author lidong 深度优先搜索
 *
 */

public class DFS_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getN2(6));
	}

	private static int getN(int n) {
		int aim = 0;
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else if (n % 2 == 0) {
			aim = getN(n - 1) * getN(n - 2);
		} else if (n % 2 == 1) {
			aim = getN(n - 1) + getN(n - 2);
		}
		return aim;
	}

	private static int getN2(int n) {
		int cun1 = 1;
		int cun2 = 2;
		if (n == 1) {
			return cun1;
		} else if (n == 2) {
			return cun2;
		} else {
			for (int i = 3; i <= n; i++) {
				if (i % 2 == 1) {
					cun1 = cun1 + cun2;
				} else {
					cun2 = cun1 * cun2;
				}
			}

			return cun1 > cun2 ? cun1 : cun2;
		}
	}
}
