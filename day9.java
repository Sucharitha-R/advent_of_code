import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day9 {
    
    public static void main(String[] args) {
        File f = new File("input.txt");
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
            boolean[][] isLowPoint = new boolean[charsperline][linecount];

            // Populate grid with integers and set all 'isLowPoint' entries to true
            Scanner scnew = new Scanner(f);
            int linenumber = 0;
            while(scnew.hasNextLine()){
                String l = scnew.nextLine();
                for (int i = 0; i < l.length(); i++) {
                    int x = Character.getNumericValue(l.charAt(i));
                    data[i][linenumber] = x;
                    isLowPoint[i][linenumber] = true;
                }
                linenumber ++;
            }

            // Check for each point if it's a low point
            for (int i = 0; i < charsperline; i++) {
                for (int j = 0; j < linecount; j++) {
                    // System.out.println(data[i][j]);
                    if ( i+1 < charsperline && data[i+1][j] <= data[i][j]) {
                        isLowPoint[i][j] = false;
                    }
                    if ( i-1 >= 0 && data[i-1][j] <= data[i][j]) {
                        isLowPoint[i][j] = false;
                    }
                    if ( j+1 < linecount && data[i][j+1] <= data[i][j]) {
                        isLowPoint[i][j] = false;
                    }
                    if ( j-1 >=0 && data[i][j-1] <= data[i][j]) {
                        isLowPoint[i][j] = false;
                    }
                    
                    // System.out.println("Point " + data[i][j] + " is set to " + isLowPoint[i][j]);
                    
                }
            }


            // Count low points
            int count = 0;
            int risk_level = 0;
            for(int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (isLowPoint[i][j]) {
                        count += 1;
                        risk_level += (data[i][j] + 1);
                    }
                }
            }
            System.out.println("Count: " + count);
            System.out.println("Risk level: " + risk_level);
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
