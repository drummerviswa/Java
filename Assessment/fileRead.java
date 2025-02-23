import java.io.*;
import java.util.Scanner;

public class fileRead {
    public static void main(String[] args) {
        File file = null;
        Scanner scan = null;
        try {
            file = new File("D:\\Programming\\Java\\Assessment\\sample.txt");
            if(file.exists()){
                scan = new Scanner(file);
                if(!scan.hasNextLine()){
                    System.out.println("File is empty");
                }else{
                    while(scan.hasNextLine()){
                        System.out.println(scan.nextLine());
                    }
                }
            }else{
                System.out.println("File not exist");
            }
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }
    }
}
