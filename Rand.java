import java.util.Random;
import java.util.Scanner;
public class Rand{
    public static void main(String[] args) {
        Scanner object = new Scanner(System.in);
        Random r = new Random();
        int a[];
        a = new int[1000];
        System.out.println("Enter the value of n: ");
        int n =  object.nextInt();
        for(int i=0;i<n;i++){
            a[i] = i+1;
        }
        for (int i = 0; i < n; i++) {
			int num = r.nextInt(n);
			int temp = a[num];
			a[num] = a[i];
			a[i] = temp;
		}
        for(int i=0;i<n;i++) {
           System.out.println(a[i]); 
        }

    }
}
