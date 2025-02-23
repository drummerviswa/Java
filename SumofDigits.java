import java.util.Scanner;
public class SumofDigits{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        int r,sum=0;    
        int n=obj.nextInt();
        while(n>0){    
            r=n%10;
            sum=sum+r;    
            n=n/10;    
        }       
        System.out.println("The sum is "+sum); 
    }
}