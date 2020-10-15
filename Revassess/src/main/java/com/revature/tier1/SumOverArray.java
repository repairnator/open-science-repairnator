package com.revature.tier1;

public class SumOverArray {
	
	public static int IterateAndSum(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];		
		}
		return sum;
	}
}

