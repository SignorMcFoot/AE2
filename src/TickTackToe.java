import java.util.Arrays;
import java.util.Scanner;

public class TickTackToe {
    public static char[][] init(int rows, int columns){
        return new char[rows][columns];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] k = init(3,3);
        boolean result = true;
        while(result){

        }
        System.out.println(Arrays.deepToString(k).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}
