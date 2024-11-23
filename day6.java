import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day6 {
    
    public static void main(String[] args) {
        File file = new File("input.txt");
        Scanner sc;
        try {
            sc = new Scanner(file);
            List<Integer> fish = convert_to_list(sc.nextLine());
            int total_days = 256;

            // Initialize fishy_census
            Map<Integer, Long> fishy_census = new HashMap<>();
            for (int i = 0; i < 9; i ++) {
                fishy_census.put(i, 0l);
            }

            // Populate it with initial counts
            for (int f : fish) {
                fishy_census.put(f, fishy_census.get(f) + 1);
            }
            // see_fishy_census(fishy_census);

            // Let time run
            for (int day = 0; day < total_days; day ++) {
                System.out.println("Day: " + day);
                long to_spawn = fishy_census.get(0);
                // System.out.println("today will be spawned: " + to_spawn);

                for (int i = 0; i < 9; i ++) {
                    if (i == 6) {
                        fishy_census.put(i, to_spawn + fishy_census.get(i+1));
                    } else if (i == 8) {
                        fishy_census.put(i, to_spawn);
                    } else {
                        fishy_census.put(i, fishy_census.get(i+1));
                    }
                }
                // see_fishy_census(fishy_census);
            }

            // Calculate result
            long result = 0;
            for (int i = 0; i < 9; i ++) {
                result += fishy_census.get(i);
            }
            System.out.println(result);



            // for (int day = 0; day < total_days; day ++) {
            //     System.out.println("Day " + day);
            //     int new_fish_spawned = 0;

            //     // Update each fish
            //     for (int f = 0; f < fish.size(); f++) {
            //         int curr_fish = fish.get(f);
            //         if (curr_fish == 0) {
            //             fish.set(f, 6); // reset age to 6
            //             new_fish_spawned += 1;
            //         } else {
            //             fish.set(f, curr_fish - 1); // decrease age by 1
            //         }
            //     }

            //     // Spawn all the new fish
            //     for (int sp = 0; sp < new_fish_spawned; sp ++) {
            //         fish.add(8);
            //     }
            // }

            // System.out.println(fish.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> convert_to_list(String biglist) {
        List<Integer> res = new ArrayList<>();
        String[] rawnums = biglist.split(",");
        for (int i = 0; i < rawnums.length; i++) {
            res.add(Integer.parseInt(rawnums[i]));
        }
        return res;
    }

    public static void see_fishy_census(Map<Integer, Integer> fishyCensus) {
        for (int k : fishyCensus.keySet()) {
            System.out.println("Key " + k + "contains " + fishyCensus.get(k) + " values");
        }
    }
}
