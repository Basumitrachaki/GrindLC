import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Top150DP {
    // Climbing Stairs
    public int climbStairs(int n) {
        if(n ==1)
            return 1;
        int[] dp = new int[n+1];
        dp[1] =1;
        dp[2] = 2;
        for(int i =3; i<=n; i++){
            dp[i] = dp [i-1] + dp [i-2];
        }
        return dp[n];
    }

    // House Robber
    public int rob(int[] nums) {
        int len = nums.length;
        if(len ==0)
            return 0;

        int[] maxRobbedAmount = new int[len +1];
        maxRobbedAmount[len] = 0;
        maxRobbedAmount[len - 1] = nums[len - 1];
        for(int i= len -2; i >=0; --i){
            maxRobbedAmount[i] = Math.max(maxRobbedAmount[i+1], maxRobbedAmount[i+2]+ nums[i]);
        }
        return maxRobbedAmount[0];
    }
    // Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i =1; i<= s.length(); i++){
            for(int j =0; j< i; j++){
                if(dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
    return dp[s.length()];
    }
   // Coin Change
   public int coinChange(int[] coins, int amount) {
       int max = amount + 1;
       int[] dp = new int[amount + 1];
       Arrays.fill(dp, max);
       dp[0] = 0;
       for (int i = 1; i <= amount; i++) {
           for (int j = 0; j < coins.length; j++) {
               if (coins[j] <= i) {
                   dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
               }
           }
       }
       return dp[amount] > amount ? -1 : dp[amount];
   }

   // Longest Increasing Subsequence
   public int lengthOfLIS(int[] nums) {
        int [] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i =1; i< nums.length; i++){
            for(int j =0; j< i; j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int longest = 0;
        for(int c : dp){
            longest = Math.max(longest, c);
        }
return longest;
   }
}
