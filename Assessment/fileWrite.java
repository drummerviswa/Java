
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileWrite {
    public static void main(String[] args) {
        File file = null;
        Scanner scan = null;
        FileWriter writer = null;
        String input="";
        try {
            file = new File("D:\\Programming\\Java\\Assessment\\sample.txt");
            writer = new FileWriter(file,true);
            scan = new Scanner(System.in);
            if(file.exists()){
                while(scan.hasNext()){
                    input = scan.nextLine();
                    if(input.equals("-1"))
                    break;
                    input+="\n";
                    writer.write(input);
                }
            }else{
                System.out.println("File not exist");
            }
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }finally{
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
