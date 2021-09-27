// Given a non-empty array of integers, every element appears 
// three times except for one, which appears exactly once. 
// Find that single one.
public class SingleNumber2 {
    //test
    public static void main (String[] args){
        int[] case1 = {2,2,3,2};
        int ans1 = 3;
        System.out.println("case1:" +  String.valueOf(singleNumber(case1) == ans1) );
        int[] case2 = {0,1,0,1,0,1,99};
        int ans2 = 99;
        System.out.println("case2:" +  String.valueOf(singleNumber(case2) == ans2) );
    }
    //solution
    // https://www.geeksforgeeks.org/find-the-element-that-appears-once/
    public static int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        int common_bit_mask;
        for(int i=0; i<nums.length; i++){
            twos = twos | (ones & nums[i]);
            ones = ones ^ nums[i];
            
            common_bit_mask = ~(ones & twos);
                
            ones &= common_bit_mask;
            twos &= common_bit_mask;
        }
        return ones;
    }
}
