import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day5 {
    
    public static void main(String[] args) {
        File f = new File("test_input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);

            List<Line> lines = new ArrayList<Line>();
            int max_x = -1;
            int max_y = -1;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] points = line.split(" -> ");
                
                Line newline = new Line(points);
                // Add new line
                lines.add(newline);
                // System.out.println("Line added" + newline.x1 + " " + newline.y1 +  " " + newline.x2 + " " +  newline.y2);

                // Update the maxes
                max_x = newline.x1 > max_x ? newline.x1 : max_x;
                max_x = newline.x2 > max_x ? newline.x2 : max_x;
                max_y = newline.y1 > max_y ? newline.y1 : max_y;
                max_y = newline.y2 > max_y ? newline.y2 : max_y;
                
                

            }

            // System.out.println("Max x: " + max_x);
            // System.out.println("Max y: " + max_y);

            int result_count = 0;
            for (int x = 0; x <= max_x; x++) {
                for (int y = 0; y <= max_y; y++) {
                    int number_of_lines_passing = 0;
                    for (Line l : lines) {
                        if (l.goesThroughPoint(x, y)) {
                            number_of_lines_passing += 1;
                            if (number_of_lines_passing == 2) {
                                result_count += 1;
                            }
                        }
                    }
                }
            }
            System.out.println("Result: " + result_count);

            // System.out.println("A: " + Line.isBetween(1, 3, 10));
            // System.out.println("A: " + Line.isBetween(1, 10, 3));
            // System.out.println("A: " + Line.isBetween(3, 1, 10));
            // System.out.println("A: " + Line.isBetween(3, 10, 1));
            // Line myLine = lines.get(0); // 1,1 -> 5,5
            // System.out.println("vertical? " + myLine.isVertical());
            // System.out.println("A: " + myLine.goesThroughPoint(3, 3));
            // System.out.println("A: " + myLine.goesThroughPoint(1, 5));
            // System.out.println("A: " + myLine.goesThroughPoint(5, 1));
            // System.out.println("A: " + myLine.goesThroughPoint(6, 6));
            // System.out.println("A: " + myLine.goesThroughPoint(3, 4));





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void print_array(String[] arr) {
        for (int i = 0 ; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    
}

class Line {
    int x1;
    int x2;
    int y1;
    int y2;

    public Line(String[] points) {
        this.x1 = Integer.parseInt(points[0].split(",")[0]);
        this.y1 = Integer.parseInt(points[0].split(",")[1]);
        this.x2 = Integer.parseInt(points[1].split(",")[0]);
        this.y2 = Integer.parseInt(points[1].split(",")[1]);
        
    }

    public boolean isValid() {
        // Check validity of lines
        if (this.x1 != this.x2 && this.y1 != this.y2) {
            return false;
        }
        return true;
    }

    public int isVertical() {
        if (this.x1 == this.x2 && this.y1 != this.y2) {
            return 0; // vertical
        }
        if (this.y1 == this.y2 && this.x1 != this.x2) {
            return 1; // horizontal
        }
        // System.out.println("eccomi?");
        return -1; // diagonal
    }

    public boolean goesThroughPoint(int x, int y) {
        if (this.isVertical() == 1 && y == this.y1 && isBetween(x, this.x1, this.x2)) {
            return true;
        } 
        if (this.isVertical() == 0 && x == this.x1 && isBetween(y, this.y1, this.y2)) {
            return true;
        }
        if (this.isVertical() == -1 && isBetween(y, this.y1, this.y2) && isBetween(x, this.x1, this.x2)) {
            // System.out.println("ecco");
            if (Math.abs(x - this.x1) == Math.abs(y - this.y1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBetween(int x, int x1, int x2) {
        if (x1 >= x && x2 <= x) {
            return true;
        }
        if (x1 <= x && x2 >= x) {
            return true;
        }
        return false;
    }
}
