import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class day3 {
    public static void main(String[] args) {
        try {
            
            
            File f = new File("input.txt");
            Scanner sc = new Scanner(f);
            List<String> inputs = new ArrayList<>();
            while (sc.hasNextLine()) {
                inputs.add(sc.nextLine());
            }
            sc.close();

            // Find oxygen generator rating 
            
            int[] oxy_tracker = new int[inputs.size()]; //arr_og having all zeros means that all are selected
            int pos = 0;
            boolean only_one_left = false;

            while(! only_one_left) {
                // Check the most common bit in pos
                int most_common_bit = most_common_bit(inputs, pos, oxy_tracker);
                // System.out.println("Most common bit at pos " + pos + " is  " + most_common_bit);

                // Remove all that have the common bit
                for (int i = 0; i <oxy_tracker.length; i++) {
                    int bit = Character.getNumericValue(inputs.get(i).charAt(pos));
                    if (bit != most_common_bit) {
                        if (oxy_tracker[i] != 1) {
                            // System.out.println("Removing, " + inputs.get(i));
                        }
                        oxy_tracker[i] = 1; // set arr_og to 1 to signify it has been removed
                    }
                }

                //Check the flag
                only_one_left = is_only_one_left(oxy_tracker);
                pos += 1;
            }

            int oxy_res = retrieve_result(oxy_tracker, inputs);
            System.out.println("Oxygen generator result: " + oxy_res);

            // Find C02 scrubber rating

            int[] co2_tracker = new int[inputs.size()]; //arr_og having all zeros means that all are selected
            pos = 0;
            only_one_left = false;

            while(! only_one_left) {
                // Check the most common bit in pos
                int least_common_bit = least_common_bit(inputs, pos, co2_tracker);
                // System.out.println("Least common bit at pos " + pos + " is  " + least_common_bit);

                // Remove all that have the common bit
                for (int i = 0; i <co2_tracker.length; i++) {
                    int bit = Character.getNumericValue(inputs.get(i).charAt(pos));
                    if (bit != least_common_bit) {
                        if (co2_tracker[i] != 1) {
                            // System.out.println("Removing, " + inputs.get(i));
                        }
                        co2_tracker[i] = 1; // set arr_og to 1 to signify it has been removed
                    }
                }

                //Check the flag
                only_one_left = is_only_one_left(co2_tracker);
                pos += 1;
            }

            int co2_res = retrieve_result(co2_tracker, inputs);
            System.out.println("C02 scrubber result: " + co2_res);

            System.out.println("Product: " + co2_res * oxy_res);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int most_common_bit(List<String> inputs, int pos, int[] arr_og) {
        int freq_0 = 0;
        int freq_1 = 0;
        for (int i = 0; i < inputs.size(); i++) {
            if (arr_og[i] == 0) {
                String s = inputs.get(i);
                int bit = Character.getNumericValue(s.charAt(pos));
                freq_0 = bit == 0 ? freq_0 + 1 : freq_0;
                freq_1 = bit == 1 ? freq_1 + 1 : freq_1;
            }
            
        }
    

        // Return most common bit
        if (freq_0 > freq_1) {
            return 0;
        } else {
            return 1;
        }

    }

    public static int least_common_bit(List<String> inputs, int pos, int[] arr_og) {
        int freq_0 = 0;
        int freq_1 = 0;
        for (int i = 0; i < inputs.size(); i++) {
            if (arr_og[i] == 0) {
                String s = inputs.get(i);
                int bit = Character.getNumericValue(s.charAt(pos));
                freq_0 = bit == 0 ? freq_0 + 1 : freq_0;
                freq_1 = bit == 1 ? freq_1 + 1 : freq_1;
            }
            
        }

        // Return most common bit
        if (freq_1 < freq_0) {
            return 1;
        } else {
            return 0;
        }

    }

    

    public static boolean is_only_one_left(int[] arr_og) {
        int sum = 0;
        for(int i = 0; i < arr_og.length; i++) {
            sum += arr_og[i];
        }
        if (sum == arr_og.length - 1) {
            // enough 1s (removals) have been made so that the sum is close to the length
            return true;
        }
        // still a lot of 0s, sum is less than length - 1
        return false;
    }

    public static void print(int[] arr_og) {
        for(int i = 0; i < arr_og.length; i++) {
            System.out.print(arr_og[i] + ", ");
        }
    }

    public static int retrieve_result(int[] oxy_tracker, List<String> inputs) {
        for (int i = 0; i < oxy_tracker.length; i++) {
            if (oxy_tracker[i] == 0) {
                return Integer.parseInt(inputs.get(i), 2);
            }
        }
        return -1;
    }
}