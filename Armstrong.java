import java.util.Scanner;
import java.lang.Math;
public class Armstrong{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        int r,i=0;
        double sum=0;
        int a[];
        a = new int[5];
        int n=obj.nextInt();
        int temp=n;
        while(n!=0){    
            r=n%10;
            a[i]=r;
            n=n/10;
            i++;
        }
        for(int j=0;j<=i;j++){
            sum=sum+Math.pow(a[j],i);
        }
        if(temp==sum){
            System.out.println("Armstrong");
        }
        else{
            System.out.println("Not a Armstrong");
        }
    }
}