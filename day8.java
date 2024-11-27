import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day8 {
    
    public static void main(String[] args) {
        File f = new File("test_input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
            int sum = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split("\\|")[1].strip().split(" ");
                String[] signals = line.split("\\|")[0].strip().split(" ");
                signals = mysort(signals);
                
                // Create a mapping from signal wire string to output number
                Map<String, Integer> decoding = new HashMap<>();

                // Analyse all 10 unique signals 
                // First do the obvious ones
                for(int i = 0; i < signals.length; i++) {
                    switch (signals[i].length()) {
                        case 2:
                            // length two means the number being shown is 1
                            decoding.put(signals[i], 1);
                            System.out.println("String " + signals[i] + " maps to value " + 1);
                            break;
                        case 3:
                            // length three means the number being shown is 7
                            decoding.put(signals[i], 7);
                            System.out.println("String " + signals[i] + " maps to value " + 7);
                            break;
                        case 4:
                            // length four means the number being shown is 4
                            decoding.put(signals[i], 4);
                            System.out.println("String " + signals[i] + " maps to value " + 4);
                            break;
                        case 7:
                            // length seven means the number being shown is 8
                            decoding.put(signals[i], 8);
                            System.out.println("String " + signals[i] + " maps to value " + 8);
                            break;
                    }
                }
                // Length 5 and containing the code 1 means it's 3
                for(int i = 0; i < signals.length; i++) {
                    if (signals[i].length() == 5 && signals[i].contains(signals[1])) {
                        decoding.put(signals[i], 3);
                        System.out.println("String " + signals[i] + " maps to value " + 3);
                    }
                }
                // // Length 6 and not containing the code 1 means it's 6
                String decoding_6 = null;
                for(int i = 0; i < signals.length; i++) {
                    if (signals[i].length() == 6 && !signals[i].contains(signals[1])) {
                        decoding.put(signals[i], 6);
                        System.out.println("String " + signals[i] + " maps to value " + 6);
                        decoding_6 = signals[i];
                    }
                }
                // Length 5 and being one char diff from 6 means it's 5
                 for(int i = 0; i < signals.length; i++) {
                    if (signals[i].length() == 5 && oneDiff(signals[i], decoding_6)) {
                        decoding.put(signals[i], 5);
                        System.out.println("String " + signals[i] + " maps to value " + 5);
                    }
                }
                // Length 5 any remaining means it's 2
                for (int i = 0; i < signals.length; i++) {
                    if (signals[i].length() == 5 && !decoding.containsKey(signals[i])) {
                        decoding.put(signals[i], 2);
                        System.out.println("String " + signals[i] + " maps to value " + 2);
                    }
                }
                // // Length 6 and containing code 3 means it's 9
                // for (int i = 0; i < signals.length; i++) {
                //     if (signals[i].length() == 6 && signals[i].contains(signals[3])) {
                //         idx_map[9] = signals[i];
                //         string_decoded[i] = 1;
                //     }
                // }
                // // Length 6 any remaining means it's 0
                // for (int i = 0; i < signals.length; i++) {
                //     if (signals[i].length() == 6 && string_decoded[i] == 0) {
                //         idx_map[0] = signals[i];
                //         string_decoded[i] = 1;
                //     }
                // }
                
                // // Use mapping to decode values
                // String res = "";
                // for (int i = 0; i < values.length; i++) {
                //     for (int s = 0; s < signals.length; s++) {
                //         if (signals[s] == values[i]) {
                //             res += s;
                //         }
                //     }
                // }
                // System.out.println("Decoded: " + res);
                // // Add values to sum
                // sum += Integer.parseInt(res);
                
            }
            System.out.println("Sum: " + sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean check_validity(String[] values) {
        boolean simple = false;
        for (int i = 0; i < values.length; i++) {
            int len = values[i].length();
            if (len == 1 || len == 4 || len == 7 || len == 8) {
                simple = true;
            }
        }
        return simple;
    }

    public static String[] mysort(String[] values) {
        for (int s = 0; s < values.length; s++) {
            String input = values[s];
            char[] charArray = input.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            values[s] = sortedString;
        }
        return values;
        
    }

    public static boolean oneDiff(String from, String s) {
        if (from.length()!=s.length()) return false;
        int differences = 0;
        for (int charIndex = 0;charIndex<from.length();charIndex++) {
            if (from.charAt(charIndex)!=s.charAt(charIndex)) differences++;
            if(differences > 1) break;
        }
        return (differences == 1);
    }
}
