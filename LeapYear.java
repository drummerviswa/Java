import java.util.Scanner;
public class LeapYear{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        int year=obj.nextInt();
        if(((year%100==0)&&(year%400==0))||(year%100!=0)&&(year%4==0)){
            System.out.println("Leap Year");
        }
        else{
            System.out.println("Not a Leap Year");
        }
    }
}