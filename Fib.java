import java.util.Scanner;
public class Fib{
    public static int fibonacci(int n){
        int a = 0, b = 1, c;
        if (n == 0)
            return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        int n=obj.nextInt();
        System.out.println("Fibonacci series is: "); 
        for(int i=0;i<=n;i++){
            int a=fibonacci(i);
            System.out.println(a); 
        }
    }
}