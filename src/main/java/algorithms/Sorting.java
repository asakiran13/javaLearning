package algorithms;

public class Sorting {


	public static void countingSort(int[] arr, int k){
		int [] B,C;
		B = new int[arr.length];
		C = new int[k+1];

		//Counting frequency of each element
		for(int i = 0; i < arr.length; i++){
			C[arr[i]]++;
		}

		//cumulative function
		for(int i = 1; i <= k; i++){
			C[i] = C[i-1] + C[i];
		}

		for(int i = 0; i < arr.length; i++){
			B[C[arr[i]] - 1] = arr[i];
			C[arr[i]]--;
		}

		for(int i = 0; i < arr.length; i++){
			arr[i] = B[i];
		}

	}

	private static int getMaxInArray(int[] arr){
		int max = Integer.MIN_VALUE;
		for(int i=0; i < arr.length; i++){
			if(arr[i] > max) max = arr[i];
		}
		return max;
	}

	private static void radixCountingSort(int[] arr, int digit){

		int divisor = (int )Math.pow(10, digit);
		int[] B, C;
		int n = arr.length;
		B = new int[n];
		C = new int[10];

		for(int i = 0; i < n; i++){
			C[(arr[i]/divisor)%10]++;
		}

		for(int i = 1; i < 10; i++){
			C[i] = C[i] + C[i-1];
		}

		for(int i = n - 1; i >= 0; i--){
			B[C[(arr[i]/divisor)%10] - 1] = arr[i];
			C[(arr[i]/divisor)%10]--;
		}

		for(int i = 0; i < n; i++){
			arr[i] = B[i];
		}

		return;

	}

	//radix sort
	public static void radixSort(int[] arr){

		int max = getMaxInArray(arr);
		int m = 0;

		//counting the max no of digits
		while(max > 0){
			max = max/10; m++;
		}

		for(int i = 0; i < m ; i++){
			radixCountingSort(arr, i);
		}
	}

	private static void printArray(int[] arr){
		if(arr == null)return;
		System.out.println();
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	private static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return;
	}
	private static int getPartition(int[] arr, int start, int end){
		int pivot = arr[end];
		int j = start - 1;
		for(int i = start; i < end; i++){
			if(arr[i] <= pivot){
				j++;
				swap(arr, i, j);
			}
		}
		j++;
		swap(arr, j, end);
		return j;
	}

	private static void quickSortUtil(int[] arr, int start, int end){
		if(start >= end) return;
		int partition = getPartition(arr, start, end);
		quickSortUtil(arr, start, partition - 1);
		quickSortUtil(arr, partition + 1, end);
		return;
	}
	private static void quickSort(int[] arr){
		int len = arr.length;
		quickSortUtil(arr, 0, len - 1);
		return;
	}
	public static void main(String[] args){
		int[]arr = {12, 23, 456, 2, 923, 69};
		quickSort(arr);
		printArray(arr);

	}
}
