import java.util.HashMap;
import java.util.Map;

public class Top150Mat {
    // Palindrome Number
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x!= 0)){
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber){
            revertedNumber = revertedNumber * 10 + (x % 10);
            x /= 10;
        }
        return (x == revertedNumber || x == revertedNumber /10);
    }

    // Plus One
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n -1; i >=0; --i ){
            if(digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i]++;
                return digits;
            }
        }
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }

    // Factorial Trailing Zeroes
    public int trailingZeroes(int n) {
        int zerocount = 0;
        long currentMultiple = 5;
        while ( n > 0){
            n /= 5;
            zerocount += n;
        }
        return zerocount;
    }

    // Sqrt(x)
    public int mySqrt(int x) {
        if(x < 2){
            return x;
        }
        long num;
        int pivot , left = 2, right = x/2;
        while(left <= right){
            pivot = left + (right - left)/2;
            num = (long) pivot * pivot;
            if(num > x){
                right = pivot -1;
            }else if(num < x){
                left = pivot +1;
            }else {
                return pivot;
            }
        }
        return right;
    }

    // Pow(x, n)
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0){
            x = 1/x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    // Max Points on a Line
    public int maxPoints(int[][] points) {
        int n = points.length;

        if (n == 1) {
            return 1;
        }
        int max = 0;
        for (int i=0; i < n; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j=i+1; j < n; j++) {
                double slope = calculateSlope(points[i], points[j]);
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                max = Math.max(max, map.get(slope));
            }
        }
        return max + 1;
    }

    private double calculateSlope(int[] p1, int[] p2) {
        int x1 = p1[0], x2 = p2[0];
        int y1 = p1[1], y2 = p2[1];

        if (x1 == x2) return Double.MAX_VALUE;
        if (y1 == y2) return 0;
        return (double) (y2 - y1) / (double) (x2 - x1);
    }
}
