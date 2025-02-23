import java.util.Random;
import java.util.Scanner;
public class Ran{
    public static void main(String args[]){    
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        int range = s.nextInt();
        int number = r.nextInt(range);
        System.out.println(number); 
    }
}