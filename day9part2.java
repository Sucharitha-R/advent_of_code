import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class day9part2 {
    
    public static void main(String[] args) {
        File f = new File("test_input.txt");
        Scanner sc;
        try {

            // First, obtain the dimensions of the grid
            sc = new Scanner(f);
            int linecount = 1;
            int charsperline  = sc.nextLine().length();
            while (sc.hasNextLine()) {
                sc.nextLine();
                linecount ++;
            }
            System.out.println(linecount + " lines");
            System.out.println("Each line has " + charsperline);
            sc.close();

            // Initialise grid
            int[][] data = new int[charsperline][linecount];
            

            // Populate grid with integers
            Scanner scnew = new Scanner(f);
            int linenumber = 0;
            while(scnew.hasNextLine()){
                String l = scnew.nextLine();
                for (int i = 0; i < l.length(); i++) {
                    int x = Character.getNumericValue(l.charAt(i));
                    data[i][linenumber] = x;
                    // visited[i][linenumber] = false;
                }
                linenumber ++;
            }
            scnew.close();

            // Track which points have been visited (i.e. already counted in a basin)
            boolean[][] visited = new boolean[charsperline][linecount];
            isValid iv = new isValid(linecount, charsperline);

            // Track the sizes of the basins found
            List<Integer> basin_sizes = new ArrayList<>();

            // Loop over all points
            for (int i = 0; i < charsperline; i++) {
                for (int j = 0; j < linecount; j++) {
                    
                    // If the point has already been visited: continue
                    if (visited[i][j]) {
                        continue;
                    }

                    // Explore the basin - construct a queue!
                    Queue<Position> q = new LinkedBlockingQueue<Position>();
                    q.add(new Position(i, j));
                    
                    // Remember to track basin size
                    int basin_size = 0;
                    System.out.println("==== New Basin =====");

                    while (! q.isEmpty()) {

                        Position curr = q.poll();
                    
                        // Check if it meets the requirements
                        if (iv.checkValidity(curr.i, curr.j)) {
                            // Mark it as visited
                            visited[curr.i][curr.j] = true;
                            if (data[curr.i][curr.j]  != 9) {
                                // Add it to the basin
                                basin_size += 1;
                                System.out.println("Basin contains element " + data[curr.i][curr.j] + " at coordinates " + curr.i + "," + curr.j);
                                
                                // Add its neighbours to the queue
                                if (iv.checkValidity(curr.i+1, curr.j) && !visited[curr.i+1][curr.j]) {
                                    q.add(new Position(curr.i+1, curr.j));
                                }
                                if (iv.checkValidity(curr.i-1, curr.j) && !visited[curr.i-1][curr.j]) {
                                    q.add(new Position(curr.i-1, curr.j));
                                }
                                if (iv.checkValidity(curr.i, curr.j+1) && !visited[curr.i][curr.j+1]) {
                                    q.add(new Position(curr.i, curr.j+1));
                                }
                                if (iv.checkValidity(curr.i, curr.j-1) && !visited[curr.i][curr.j-1]) {
                                    q.add(new Position(curr.i, curr.j-1));
                                }
                                
                            }
                        }
                        
                    }

                    System.out.println("====Basin size: " + basin_size + " =======");
                    basin_sizes.add(basin_size);
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Position {
    int i;
    int j;
    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class isValid {
    int linecount; int charsperline;

    public isValid(int linecount, int charsperline) {
        this.linecount = linecount;
        this.charsperline = charsperline;
    }

    public boolean checkValidity(int i, int j) {
        return i >= 0 && i < this.charsperline && j >= 0 && j < this.linecount;
    }
}