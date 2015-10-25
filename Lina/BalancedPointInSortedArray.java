 find a balance point in an array, array不是sorted的，里面可能有负数。
解法也是从两边往中间搞，用两个数组left和right存原数组从左向右和从右向左的元素和。然后按index来比较left和right两个数组里的元素，有相等的就是balance point.

 Find the balance point in an array. (The index where the   sum of the elements to the left it is the same as the sum of the elements to the right of it.) 

The partial sum of the array A is

[A[0], A[0]+A[1], A[0]+A[1]+A[2], …, A[0]+A[1]+A[2]+…+A[n-1]]
example:

A=[5,2,3,1,4,6]
partial sum = [5,7,10,11,15,21]
With this array you can compute sumleft[i]=partial_sum[i-1] and sumright[i]=partial_sum[n-1]-partial_sum[i]


1 1+2 1+2+3 1+2+3+4
1+2+3+4 2+3+4 3+4 4

public int findBalance (int[] nums) {
	if (nums == null || nums.length == 0) {
		return -1;
	}
	int[] leftSum = new int[nums.length];
	leftSum[0] = nums[0];
	for (int i = 1; i < nums.length; i++) {
		leftSum[i] = leftSum[i - 1] + nums[i];
	}
	for (int i = 1; i < leftSum.length - 1; i++) {
		if (leftSum[i - 1] == leftSum[leftSum.length - 1] - leftSum[i]) {
			result.add(i);
		}
	}
	return result;
}
