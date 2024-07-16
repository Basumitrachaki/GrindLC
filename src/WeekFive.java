import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeekFive {

    // Search in Rotated Sorted Array .. binary search
    public int search(int[] nums, int target) {
        int start = 0; int end = nums.length -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[start]) {
                if(target >=nums[start] && target < nums[mid]){
                    end = mid -1;
                }else start = mid + 1;
            }
            else {
                if(target <= nums[end] && target > nums[mid]){
                    start = mid + 1;
                }else{
                    end = mid -1;
                }
            }
        }
        return -1;
    }

    // Combination Sum
    private void backtrack(int remain, LinkedList<Integer> comb, int start, int[] candidates, List<List<Integer>>results){
        if(remain == 0){
            results.add(new ArrayList<>(comb));
        }
    }
}
