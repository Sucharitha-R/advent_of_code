import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class template {
    
    public static void main(String[] args) {
        File f = new File("input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
