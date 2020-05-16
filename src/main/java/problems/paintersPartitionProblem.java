package problems;

/*
We have to paint n boards of length {A1, A2…An}.
There are k painters available and each takes 1 unit time to paint 1 unit of board. The problem is to find the minimum time to get
this job done under the constraints that any painter will only paint continuous sections of boards.


Input : k = 2, A = {10, 10, 10, 10}
Output : 20.
Here we can divide the boards into 2
equal sized partitions, so each painter
gets 20 units of board and the total
time taken is 20.

Input : k = 2, A = {10, 20, 30, 40}
Output : 60.
Here we can divide first 3 boards for
one painter and the last board for
second painter.
 */

public class paintersPartitionProblem {

	private static int minimumTimeRecursion(int[] paintingTime, int noOfPainters, int index){

		int sum = 0, best = Integer.MAX_VALUE;
		if(noOfPainters == 1){
			for(int i = index; i<paintingTime.length; i++) sum += paintingTime[i];
			return sum;
		}

		for(int i = index; i < paintingTime.length - noOfPainters; i ++){
			sum += paintingTime[i];
			best = Math.min(best, Math.max(sum, minimumTimeRecursion(paintingTime, noOfPainters - 1, i+1)));

		}
		return best;

	}

//	private static int minimumPaintingTimeDP(int[] paintingTime, int noOfPainters){
//		int noOfBlocks = paintingTime.length;
//		int dp[][] = new int[noOfBlocks][noOfPainters + 1], sum = 0, best;
//		for(int i = 0; i < noOfBlocks; i++){
//			sum += paintingTime[i];
//			dp[i][1] = sum;
//		}
//		for(int j = 2; j <= noOfPainters; j++){
//			for(int i = 0; i < noOfBlocks; i++){
//				sum = 0;best = Integer.MAX_VALUE;
//				for(int k = j; k > j - )
//			}
//		}
//	}

	public static int getMinimumTime(int[] paintingTime, int noOfPainters){
		return minimumTimeRecursion(paintingTime, noOfPainters, 0);
	}
}
