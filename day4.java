import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day4 {

    
    public static void main(String[] args) {
        File f = new File("input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
            int[] numberlist = convert_numbers(sc.nextLine().split(","));
            List<BingoBoard> bingoBoards = new ArrayList<>();
            sc.nextLine();

            String[] rawboard = new String[5];
            int counter = 0;
            int brdct = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Saving board " + brdct); brdct += 1;
                    bingoBoards.add(new BingoBoard(rawboard));
                    // System.out.println("Board added");
                    rawboard = new String[5];
                    counter = 0;
                } else {
                    // System.out.println("Adding line " + line + " at counter " + counter);
                    rawboard[counter] = line;
                    counter += 1;
                }
            }
            bingoBoards.add(new BingoBoard(rawboard));
            System.out.println("Saving board " + brdct);

            int final_score = play_game(numberlist, bingoBoards);
            System.out.println("Final score: " + final_score);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static int[] convert_numbers(String[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    public static int play_game(int[] numberlist, List<BingoBoard> bingoBoards) {
        int total_number_of_boards = bingoBoards.size();
        int boards_won = 0;

        for (int i = 0; i < numberlist.length; i++) {
            int x = numberlist[i];
            // System.out.println("")
            for (int b = 0; b < bingoBoards.size(); b++) {
                BingoBoard board = bingoBoards.get(b);
                int ret = board.mark_number(x);
                if (ret == 1) {
                    boards_won += 1;
                    if (boards_won == total_number_of_boards) {
                        int secret = board.compute_secret();
                        System.out.println("The last winner is board " + b);
                        System.out.println("The secret is " + secret);
                        System.out.println("The last number called was: " + x);
                        return secret * x;
                    }
                    
                }
            }
        }
        return -1;
    }
}

class BingoBoard {

    int[][] board;
    boolean[][] markers;
    int[] row_tracker;
    int[] col_tracker;
    
    public BingoBoard(String[] rawboard) {
        this.board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            // System.out.print(rawboard[i] + " , ");
            String[] s = rawboard[i].trim().replaceAll(" +", " ").split(" ");
            for (int j = 0; j < s.length; j++) {
                board[i][j] = Integer.parseInt(s[j]);
            }
        }
        this.markers = new boolean[5][5];
        this.col_tracker = new int[5];
        this.row_tracker = new int[5];
    }

    public int mark_number(int x) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.board[i][j] == x) {
                    this.markers[i][j] = true;
                    this.col_tracker[i] += 1;
                    this.row_tracker[j] += 1;
                    if (this.col_tracker[i] == 5 || this.row_tracker[j] == 5) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public int compute_secret() {
        int secret = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!this.markers[i][j]) {
                    // System.out.println("Adding marked number " + this.board[i][j]);
                    secret += this.board[i][j];
                }
            }
        }
        return secret;
    }

    
}
