import java.util.Scanner;
public class LargestofThree{
    public static void main(String args[]){
        Scanner o1 = new Scanner(System.in);
        Scanner o2 = new Scanner(System.in);
        Scanner o3 = new Scanner(System.in);
        int a=o1.nextInt(),b=o2.nextInt(),c=o3.nextInt();
        if((a>b)&&(a>c)){
            System.out.println("A is Greater");
        }
        else if ((b>a)&&(b>c)){
            System.out.println("B is Greater");
        }
        else{
            System.out.println("C is Greater");
        }
    }
}