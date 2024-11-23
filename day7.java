import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class day7 {
    
    public static void main(String[] args) {
        File f = new File("input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
            int[] nums = convert_to_numbers(sc.nextLine().split(","));
            Arrays.sort(nums);
            System.out.println("total length: " + nums.length);

            
            // Part 1 - median worked
            int median = nums[nums.length / 2];
            // System.out.println("Median: " + nums[nums.length / 2]);
            // int fuel = 0;
            // for (int i = 0; i < nums.length; i++ ){
            //     fuel += Math.abs(nums[i] - median);
            // }
            // System.out.println("Fuel: " + fuel);

            // Part 2 - brute-force but also make some optimal guesses
            int min_fuel = Integer.MAX_VALUE;
            int best_pos = -1;
            int start_pos = nums[0];
            int end_pos = nums[nums.length - 1];
            for (int pos = start_pos; pos <= end_pos; pos++) {
                int pos_fuel = 0;
                for (int i = 0; i < nums.length; i++) { 
                    int dist = Math.abs(nums[i] - pos);
                    // System.out.println("From " + nums[i] + " to " + pos + " a distance of " + dist + " costs " + fuel_cost(dist) );
                    pos_fuel += fuel_cost(dist);
                }
                if (pos_fuel < min_fuel) {
                    min_fuel = pos_fuel;
                    best_pos = pos;
                }
            }
            System.out.println("Min fuel (brutesearch): " + min_fuel);
            System.out.println("Best position (brutesearch): " + best_pos);
            System.out.println("Median: " + median);
            System.out.println("Mean: " + mean(nums));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int[] convert_to_numbers(String[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = Integer.parseInt(nums[i]);
        }
        return res;
    }

    public static int mean(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
        }
        return res / nums.length;
    }

    public static int fuel_cost(int distance) {
        // Sum of 1+2+3... up to distance
        return (distance * (distance + 1))/2;
    }


}
