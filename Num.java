import java.util.*;
import java.util.Random;
public class Num{
    public static boolean checkifExisit(int n){
        int b[];
        b = new int[1000];
        int flag=0;
        for(int i=0;i<=100;i++){
            if(n==b[i]){
                flag=1;
            }
        }
        if(flag==0){
            return true;
        }
        else
            return false;
    }
    public static void main(String args[]){    
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        int a[];
        a = new int[1000];
        int b[];
        b = new int[1000];
        int range = s.nextInt();
        for(int i=0;i<=range;i++){
            a[i]=i;
        }
        for(int i=0;i<range;i++){
            int number = r.nextInt(range);
            if(checkifExisit(number)==true){
                b[i]=number;
            }
        }
        for(int i=0;i<=range;i++){
            System.out.println(b[i]);
        }
    }
}
/* 
int randomIndexToSwap = rand.nextInt(array.length);
int temp = array[randomIndexToSwap];
array[randomIndexToSwap] = array[i];
array[i] = temp;
    
*/