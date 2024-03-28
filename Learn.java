package com.annauniv.msc;

import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.Date;

public class Learn{
	private static void lar(int a,int b,int c) {
		if(a>b&&a>c)
			System.out.println("A is greater");
		else if(b>c)
			System.out.println("B is greater");
		else
			System.out.println("C is greater");
	}
	private static void binaryToDecimal(String b) {
		int d = Integer.parseInt(b,2);
		System.out.println(d);
	}
	private static void decimalToBinary(int d) {
		System.out.println(Integer.toBinaryString(d));
	}
	private static void oddOrEven(int n) {
		if (n%2==0) {
			System.out.println("Even");
		}
		else
			System.out.println("Odd");
	}
	private static void sumOfDigits(int n) {
		int sum=0;
		while(n!=0) {
			int r=n%10;
			n/=10;
			sum=sum+r;
		}
		System.out.println("Sum of Digits: "+sum);
	}
	private static void sumofnNos(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			sum+=i;
		}
		System.out.println("Sum of Natural nos: "+sum);
	}
	private static void multiplybyFactor(int n) {
		n=n<<1;
		System.out.println("Result: "+n);
	}
	private static boolean primeorNot(int n) {
		if(n<=1)
			return false;
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	private static void primetill(int n) {
		System.out.print("Prime numbers: ");
		for(int i=0;i<=n;i++) {
			if(primeorNot(i))
			System.out.print(i+" ");
		}
	}
	private static boolean isAmstrong(int n) {
		int temp=n,i=0,r,sum=0;
		while(temp!=0) {
			temp/=10;
			i++;
		}
		temp=n;
		while(temp!=0) {
			r=temp%10;
			sum+=(int)(Math.pow(r, i));
			temp/=10;
		}
		return sum==n;
	}
	private static void dbselect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
			String[] xyz = new String[5];
			int i=0;
			if(con!=null) {
				Statement s = con.createStatement();
				ResultSet result = s.executeQuery("SELECT * FROM students");
				while(result.next()) {
					xyz[i]=result.getString(3);
					i++;
				}
				for(i=0;i<xyz.length;i++) {
					System.out.println("Name: "+xyz[i]);
				}
			}
		}
		catch(SQLException e) {
			System.out.println("Error: "+e);
		}
	}
	private static void dbinsert() {
		try {
			String regno,dept,name,branch;
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter regno: ");
			regno=scan.next();
			System.out.println("Enter dept: ");
			dept=scan.next();
			System.out.println("Enter name: ");
			name=scan.next();
			System.out.println("Enter branch: ");
			branch=scan.next();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
			if(con!=null) {
				String query = "INSERT INTO students(regno,dept,name,branch) values(?,?,?,?)";
				PreparedStatement forinsert = con.prepareStatement(query);
				forinsert.setString(1, regno);
				forinsert.setString(2, dept);
				forinsert.setString(3, name);
				forinsert.setString(4, branch);
				forinsert.execute();
			}
		}catch(SQLException e){
			System.out.println("Error: "+e);
		}
	}
	private static void dbupdate() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
			if(con!=null) {
				Scanner scan = new Scanner(System.in);
				String regno,dept,name,branch;
				System.out.println("Enter register no: ");
				regno = scan.next();
				System.out.println("Enter Department: ");
				dept = scan.next();
				System.out.println("Enter Name: ");
				name = scan.next();
				System.out.println("Enter Branch: ");
				branch = scan.next();
				PreparedStatement forupdate = con.prepareStatement("UPDATE students SET dept=?,name=?,branch=? WHERE regno="+regno);
				forupdate.setString(1, dept);
				forupdate.setString(2, name);
				forupdate.setString(3, branch);
				forupdate.execute();
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e);
		}
	}
	private static void dbdelete() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
			if(con!=null) {
				Scanner scan = new Scanner(System.in);
				String regno;
				System.out.println("Enter register no: ");
				regno = scan.next();
				PreparedStatement fordelete = con.prepareStatement("DELETE from students WHERE regno="+regno);
				fordelete.execute();
				System.out.println("Deleted da paiyaa!!!!");
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e);
		}
	}
	private static void ageCalc() {
		Date dob = new Date(2004,10,30);
		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int dobyear = dob.getYear();
		int age = currentyear-  dobyear;
		System.out.println("Age: "+age);
	}
	//Oru function create pandrom getdata nu
	//Andha function Oru integer double indexed array ah return pannum
	//Which is [] []
	private static int[][] getdata(int row,int col) {
		int[][] temp = new int[10][10];
		Scanner scan = new Scanner(System.in);
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.println("Enter value for ["+i+"]["+j+"]: ");
				temp[i][j]=scan.nextInt();
			}
		}
		return temp;
	}
	private static void matrixmul() {
		int a[][]=getdata(3, 3);    
		int b[][]=getdata(3,3);      
		int c[][]=new int[3][3];    
		for(int i=0;i<3;i++){    
			for(int j=0;j<3;j++){    
				c[i][j]=0;      
				for(int k=0;k<3;k++){      
					c[i][j]+=a[i][k]*b[k][j];      
				}  
			System.out.print(c[i][j]+" ");
			}
		System.out.println();
		}
	}
	private static int GCD(int x, int y){ 
        if (y == 0) 
            return x; 
        return GCD(y, x % y); 
    }
	private static int lcm(int x,int y) {
        return (x / GCD(x, y)) * y;
	}
	private static void LeapYear(int year) {
		if ((year % 400 == 0)|| ((year % 4 == 0) && (year % 100 != 0))) {
			System.out.println(year + " : Leap Year");
	    }
		else {
	        System.out.println(year + " : Non - Leap Year");
		}
	}
	private static int factorial(int n) {
		if(n==0)
			return 1;
		return n*factorial(n-1);
	}
	private static int fib(int n) {
		if (n==0 || n==1)
			return 1;
		return fib(n-1)+fib(n-2);
	}
	private static void primid(int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<=i;j++)
				System.out.print("* ");
			System.out.println();
		}

	}
	private static void linearSearch(int a[],int target) {
		int location=0;
		boolean flag=false;
		for(int i=0;i<a.length;i++) {
			if(a[i]==target) {
				flag=true;
				location= i;
				break;
			}
		}
		if(flag)
			System.out.println("Target found at "+location);
		else
			System.out.println("Target not found");

	}
	private static void bubbleSort(int a[]) {
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a.length-i-1;j++) {
				if(a[j]>a[j+1]) {
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		System.out.print("Sorted Array: ");
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
	}
	private static void binarySearch(int a[],int target) {
		int low=0,high=a.length-1,loc=0;
		boolean flag=false;
		while(low<=high) {
			int mid=(low+high)/2;
			if(a[mid]==target) {
				flag=true;
				loc=mid;
				break;
			}
			else if(a[mid]<target) {
				low=mid+1;
			}
			else {
				high=mid-1;
			}
		}
		if(flag)
			System.out.println("Target found at "+loc);
		else
			System.out.println("Target not found");
	}
	private static void transpose(int a[][]) {
		int b[][] = new int[100][100];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				b[i][j]=a[j][i];
			}
		}
		System.out.println("Transpose: ");
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
//		lar(5,9,7);
//		binaryToDecimal("10101");
//		decimalToBinary(8);
//		sumOfDigits(123);
//		System.out.println(result);
//		sumofnNos(5);
//		multiplybyFactor(5);
//		System.out.println(primeorNot(9)?"Prime":"Not Prime");
//		primetill(50);
//		System.out.println(isAmstrong(2)?"Armstrong":"Not Armstrong");
//		dbselect();
//		dbinsert();
//		dbupdate();
//		dbdelete();
//		ageCalc();
//		matrixmul();
//		System.out.println(GCD(6,4));
//		System.out.println(lcm(4,6));
//		LeapYear(2004);
//		System.out.println(factorial(5));
//		for(int i=0;i<5;i++)
//			System.out.println(fib(i));
//		primid(5);
//		int a[] = {1,5,18,36,51,70,88,95,500,3600};
//		linearSearch(a, 6);
//		bubbleSort(a);
//		binarySearch(a, 70);
//		int a[][] = { { 1, 1, 1, 1 }, 
//                { 2, 2, 2, 2 }, 
//                { 3, 3, 3, 3 }, 
//                { 4, 4, 4, 4 } };
//		transpose(a);
		for(int a=1;a<3;a+=3)
			System.out.println(--a);
	}
}