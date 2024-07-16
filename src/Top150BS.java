public class Top150BS {

    // Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int pivot, left =0, right = nums.length -1;
        while (left <= right){
            pivot = left + (right -left)/2;
            if (nums[pivot] == target){
                return pivot;
            }if(nums[pivot] >= target){
                right = pivot -1;
            }else {
                left = pivot -1;
            }
        }
        return left;
    }

    // Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length * matrix[0].length == 0){
            return false;
        }
        int row = 0, col = matrix[0].length -1;
        while (row < matrix.length && col >=0){
            if(matrix[row][col] == target){
                return true;
            }if(matrix[row][col] > target){
                col --;
            }else {
                row ++;
            }
        }
        return false;
    }

    // Find Peak Element
    public int findPeakElement(int[] nums) {
        int l =0, r = nums.length -1;
        while (l < r){
            int mid = (l+r)/2;
            if(nums[mid] > nums[mid+1]){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    // Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = start + (end -start)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[start]) {
                if(target >= nums[start] && target < nums[mid]) end = mid -1;
                else start = mid + 1;
            } else {
                if(target <=nums[end] && target > nums[mid]) start = mid + 1;
                else end = mid -1;
            }
        }
        return -1;
    }

    // Find First and Last Position of Element in Sorted Array
    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }
                    // Search on the left side for the bound.
                    end = mid - 1;
                } else {
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }
                    // Search on the right side for the bound.
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }
    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = this.findBound(nums, target, true);
        if (firstOccurrence == -1) {
            return new int[] { -1, -1 };
        }
        int lastOccurrence = this.findBound(nums, target, false);
        return new int[] { firstOccurrence, lastOccurrence };
    }

    // Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        if(nums.length ==1) return nums[0];
        int left = 0; int right  = nums.length -1;
        if(nums[right] > nums[left]){
            return nums[0];
        }
        while(right>= left){
            int mid = left + (right - left)/2 ;
            if(nums[mid] > nums[mid+1]){
                return nums[mid+1];
            }
            if(nums[mid -1] > nums[mid]){
                return nums[mid];
            }
            if(nums[mid] > nums[0]){
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return -1;
    }

    /// Find Minimum in Rotated Sorted Array II

    // Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // to ensure m<=n
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j-1] > nums1[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && nums1[i-1] > nums2[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = nums2[j-1]; }
                else if (j == 0) { maxLeft = nums1[i-1]; }
                else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = nums2[j]; }
                else if (j == n) { minRight = nums1[i]; }
                else { minRight = Math.min(nums2[j], nums1[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
