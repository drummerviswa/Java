import java.sql.*;
import java.util.*;

public class Practice {
    
	public static void binaryToDecimal(String b) {
		int d = Integer.parseInt(b, 2);
		System.out.println(d);
	}

	public static void decimalToBinary(int d) {
		System.out.println(Integer.toBinaryString(d));
	}
	public static void main(String[] args) {
		binaryToDecimal("10101");
		decimalToBinary(8);
	}
}
