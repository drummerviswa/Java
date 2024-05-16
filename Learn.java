package com.annauniv.msc;

import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.Date;

public class Learn {
	public static void lar(int a, int b, int c) {
		if (a > b && a > c)
			System.out.println("A is greater");
		else if (b > c)
			System.out.println("B is greater");
		else
			System.out.println("C is greater");
	}

	public static void binaryToDecimal(String b) {
		int d = Integer.parseInt(b, 2);
		System.out.println(d);
	}

	public static void decimalToBinary(int d) {
		System.out.println(Integer.toBinaryString(d));
	}

	public static void oddOrEven(int n) {
		if (n % 2 == 0) {
			System.out.println("Even");
		} else
			System.out.println("Odd");
	}

	public static void sumOfDigits(int n) {
		int sum = 0;
		while (n != 0) {
			int r = n % 10;
			n /= 10;
			sum = sum + r;
		}
		System.out.println("Sum of Digits: " + sum);
	}

	public static void sumofnNos(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		System.out.println("Sum of Natural nos: " + sum);
	}

	public static void multiplybyFactor(int n) {
		n = n << 1;
		System.out.println("Result: " + n);
	}

	public static boolean primeorNot(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void primetill(int n) {
		System.out.print("Prime numbers: ");
		for (int i = 0; i <= n; i++) {
			if (primeorNot(i))
				System.out.print(i + " ");
		}
	}

	public static boolean isAmstrong(int n) {
		int temp = n, i = 0, r, sum = 0;
		while (temp != 0) {
			temp /= 10;
			i++;
		}
		temp = n;
		while (temp != 0) {
			r = temp % 10;
			sum += (int) (Math.pow(r, i));
			temp /= 10;
		}
		return sum == n;
	}

	public static void dbselect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			String[] xyz = new String[5];
			int i = 0;
			if (con != null) {
				Statement s = con.createStatement();
				ResultSet result = s.executeQuery("SELECT * FROM students");
				while (result.next()) {
					xyz[i] = result.getString(3);
					i++;
				}
				for (i = 0; i < xyz.length; i++) {
					System.out.println("Name: " + xyz[i]);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	public static void dbinsert() {
		try {
			String regno, dept, name, branch;
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter regno: ");
			regno = scan.next();
			System.out.println("Enter dept: ");
			dept = scan.next();
			System.out.println("Enter name: ");
			name = scan.next();
			System.out.println("Enter branch: ");
			branch = scan.next();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				String query = "INSERT INTO students(regno,dept,name,branch) values(?,?,?,?)";
				PreparedStatement forinsert = con.prepareStatement(query);
				forinsert.setString(1, regno);
				forinsert.setString(2, dept);
				forinsert.setString(3, name);
				forinsert.setString(4, branch);
				forinsert.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	public static void dbupdate() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				Scanner scan = new Scanner(System.in);
				String regno, dept, name, branch;
				System.out.println("Enter register no: ");
				regno = scan.next();
				System.out.println("Enter Department: ");
				dept = scan.next();
				System.out.println("Enter Name: ");
				name = scan.next();
				System.out.println("Enter Branch: ");
				branch = scan.next();
				PreparedStatement forupdate = con
						.prepareStatement("UPDATE students SET dept=?,name=?,branch=? WHERE regno=" + regno);
				forupdate.setString(1, dept);
				forupdate.setString(2, name);
				forupdate.setString(3, branch);
				forupdate.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	public static void dbdelete() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				Scanner scan = new Scanner(System.in);
				String regno;
				System.out.println("Enter register no: ");
				regno = scan.next();
				PreparedStatement fordelete = con.prepareStatement("DELETE from students WHERE regno=" + regno);
				fordelete.execute();
				System.out.println("Deleted da paiyaa!!!!");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}
/*
	public static void ageCalc() {
		Date dob = new Date(2004, 10, 30);
		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int dobyear = dob.getYear();
		int age = currentyear - dobyear;
		System.out.println("Age: " + age);
	}
*/
	// Oru function create pandrom getdata nu
	// Andha function Oru integer double indexed array ah return pannum
	// Which is [] []
	public static int[][] getdata(int row, int col) {
		int[][] temp = new int[10][10];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.println("Enter value for [" + i + "][" + j + "]: ");
				temp[i][j] = scan.nextInt();
			}
		}
		return temp;
	}

	public static void matrixmul() {
		int a[][] = getdata(3, 3);
		int b[][] = getdata(3, 3);
		int c[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				c[i][j] = 0;
				for (int k = 0; k < 3; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int GCD(int x, int y) {
		if (y == 0)
			return x;
		return GCD(y, x % y);
	}

	public static int lcm(int x, int y) {
		return (x / GCD(x, y)) * y;
	}

	public static void LeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			System.out.println(year + " : Leap Year");
		} else {
			System.out.println(year + " : Non - Leap Year");
		}
	}

	public static int factorial(int n) {
		if (n == 0)
			return 1;
		return n * factorial(n - 1);
	}

	public static int fib(int n) {
		if (n == 0 || n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}

	public static void lprimid(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++)
				System.out.print("* ");
			System.out.println();
		}

	}
	public static void rprimid(int n){
		for(int i=n;i>=1;i--){
			for(int j=1;j<i;j++){
				System.out.print(" ");
			}
			for(int j=0;j<=n-i;j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public static void primid(int n){
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
			for (int j = 1; j <= i; j++) {
                System.out.print("*" + " ");
            }
			System.out.println();
		}
	}
	public static void hollowsq(int n){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0 || i == n - 1|| j == n - 1) {
                    System.out.print("*");
                }
				else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
	}
	public static void pascal(int n){
		for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
				System.out.print(" ");
            }
			int x = 1;
            for (int k = 1; k <= i; k++) {
                System.out.print(x + " ");
                x = x * (i - k) / k;
            }
            System.out.println();
        }
	}
	public static void linearSearch(int a[], int target) {
		int location = 0;
		boolean flag = false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == target) {
				flag = true;
				location = i;
				break;
			}
		}
		if (flag)
			System.out.println("Target found at " + location);
		else
			System.out.println("Target not found");

	}

	public static void bubbleSort(int a[]) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		System.out.print("Sorted Array: ");
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	public static void binarySearch(int a[], int target) {
		int low = 0, high = a.length - 1, loc = 0;
		boolean flag = false;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == target) {
				flag = true;
				loc = mid;
				break;
			} else if (a[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		if (flag)
			System.out.println("Target found at " + loc);
		else
			System.out.println("Target not found");
	}

	public static void transpose(int a[][]) {
		int b[][] = new int[100][100];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				b[i][j] = a[j][i];
			}
		}
		System.out.println("Transpose: ");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}
	public static boolean isKrishna(int n){
		int temp=n;
		int sum=0;
		while(temp!=0){
			int rem=temp%10;
			sum += factorial(rem);
			temp/=10;
		}
		if(sum==n)
			return true;
		else
			return false;
	}
	public static boolean palindromeNo(int n){
		int temp=n,sum=0;
		while(temp!=0){
			int rem=temp%10;
			sum=(sum*10)+rem;
			temp/=10;
		}
		return sum==n;
	}
	public static boolean palindrome(String s){
		int len = s.length();
		String temp="";
		for(int i=len-1;i>=0;i--){
			temp+=s.charAt(i);
		}
		return temp.equals(s);
	}
	public static void main(String[] args) {
		// lar(5,9,7);
		// binaryToDecimal("10101");
		// decimalToBinary(8);
		// sumOfDigits(123);
		// System.out.println(result);
		// sumofnNos(5);
		// multiplybyFactor(5);
		// System.out.println(primeorNot(9)?"Prime":"Not Prime");
		// primetill(50);
		// System.out.println(isAmstrong(2)?"Armstrong":"Not Armstrong");
		// dbselect();
		// dbinsert();
		// dbupdate();
		// dbdelete();
		// ageCalc();
		// matrixmul();
		// System.out.println(GCD(6,4));
		// System.out.println(lcm(4,6));
		// LeapYear(2004);
		// System.out.println(factorial(5));
		// for(int i=0;i<5;i++)
		// System.out.println(fib(i));
		// lprimid(5);
		// rprimid(5);
		// primid(5);
		// hollowsq(5);
		// int a[] = {1,5,18,36,51,70,88,95,500,3600};
		// linearSearch(a, 6);
		// bubbleSort(a);
		// binarySearch(a, 70);
		// int a[][] = { { 1, 1, 1, 1 },
		// { 2, 2, 2, 2 },
		// { 3, 3, 3, 3 },
		// { 4, 4, 4, 4 } };
		// transpose(a);
		// for (int a = 1; a < 3; a += 3)
		// 	System.out.println(--a);
		// if(isKrishna(145))
		// 	System.out.println("Yes");
		// else
		// 	System.out.println("No");
		// ArrayList<Integer> a = new ArrayList<Integer>();
		// ArrayList<String> names = new ArrayList<String>();
		// names.add("Viswa");
		// names.add("Nishanth");
		// names.add("Pugalenthi");
		// names.add("Hemamalini");
		// Collections.sort(names);
		// System.out.println(names);
		// if(palindromeNo(1515223))
		// 	System.out.println("Yes");
		// if(palindrome("malayalam"))
		// 	System.out.println("yes");
		package com.annauniv.msc;

import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.Date;

public class Learn {
	private static void lar(int a, int b, int c) {
		if (a > b && a > c)
			System.out.println("A is greater");
		else if (b > c)
			System.out.println("B is greater");
		else
			System.out.println("C is greater");
	}

	private static void binaryToDecimal(String b) {
		int d = Integer.parseInt(b, 2);
		System.out.println(d);
	}

	private static void decimalToBinary(int d) {
		System.out.println(Integer.toBinaryString(d));
	}

	private static void oddOrEven(int n) {
		if (n % 2 == 0) {
			System.out.println("Even");
		} else
			System.out.println("Odd");
	}

	private static void sumOfDigits(int n) {
		int sum = 0;
		while (n != 0) {
			int r = n % 10;
			n /= 10;
			sum = sum + r;
		}
		System.out.println("Sum of Digits: " + sum);
	}

	private static void sumofnNos(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		System.out.println("Sum of Natural nos: " + sum);
	}

	private static void multiplybyFactor(int n) {
		n = n << 1;
		System.out.println("Result: " + n);
	}

	private static boolean primeorNot(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	private static void primetill(int n) {
		System.out.print("Prime numbers: ");
		for (int i = 0; i <= n; i++) {
			if (primeorNot(i))
				System.out.print(i + " ");
		}
	}

	private static boolean isAmstrong(int n) {
		int temp = n, i = 0, r, sum = 0;
		while (temp != 0) {
			temp /= 10;
			i++;
		}
		temp = n;
		while (temp != 0) {
			r = temp % 10;
			sum += (int) (Math.pow(r, i));
			temp /= 10;
		}
		return sum == n;
	}

	private static void dbselect() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			String[] xyz = new String[5];
			int i = 0;
			if (con != null) {
				Statement s = con.createStatement();
				ResultSet result = s.executeQuery("SELECT * FROM students");
				while (result.next()) {
					xyz[i] = result.getString(3);
					i++;
				}
				for (i = 0; i < xyz.length; i++) {
					System.out.println("Name: " + xyz[i]);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	private static void dbinsert() {
		try {
			String regno, dept, name, branch;
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter regno: ");
			regno = scan.next();
			System.out.println("Enter dept: ");
			dept = scan.next();
			System.out.println("Enter name: ");
			name = scan.next();
			System.out.println("Enter branch: ");
			branch = scan.next();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				String query = "INSERT INTO students(regno,dept,name,branch) values(?,?,?,?)";
				PreparedStatement forinsert = con.prepareStatement(query);
				forinsert.setString(1, regno);
				forinsert.setString(2, dept);
				forinsert.setString(3, name);
				forinsert.setString(4, branch);
				forinsert.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	private static void dbupdate() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				Scanner scan = new Scanner(System.in);
				String regno, dept, name, branch;
				System.out.println("Enter register no: ");
				regno = scan.next();
				System.out.println("Enter Department: ");
				dept = scan.next();
				System.out.println("Enter Name: ");
				name = scan.next();
				System.out.println("Enter Branch: ");
				branch = scan.next();
				PreparedStatement forupdate = con
						.prepareStatement("UPDATE students SET dept=?,name=?,branch=? WHERE regno=" + regno);
				forupdate.setString(1, dept);
				forupdate.setString(2, name);
				forupdate.setString(3, branch);
				forupdate.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}

	private static void dbdelete() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
			if (con != null) {
				Scanner scan = new Scanner(System.in);
				String regno;
				System.out.println("Enter register no: ");
				regno = scan.next();
				PreparedStatement fordelete = con.prepareStatement("DELETE from students WHERE regno=" + regno);
				fordelete.execute();
				System.out.println("Deleted da paiyaa!!!!");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}
/*
	private static void ageCalc() {
		Date dob = new Date(2004, 10, 30);
		int currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int dobyear = dob.getYear();
		int age = currentyear - dobyear;
		System.out.println("Age: " + age);
	}
*/
	// Oru function create pandrom getdata nu
	// Andha function Oru integer double indexed array ah return pannum
	// Which is [] []
	private static int[][] getdata(int row, int col) {
		int[][] temp = new int[10][10];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.println("Enter value for [" + i + "][" + j + "]: ");
				temp[i][j] = scan.nextInt();
			}
		}
		return temp;
	}

	private static void matrixmul() {
		int a[][] = getdata(3, 3);
		int b[][] = getdata(3, 3);
		int c[][] = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				c[i][j] = 0;
				for (int k = 0; k < 3; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int GCD(int x, int y) {
		if (y == 0)
			return x;
		return GCD(y, x % y);
	}

	private static int lcm(int x, int y) {
		return (x / GCD(x, y)) * y;
	}

	private static void LeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			System.out.println(year + " : Leap Year");
		} else {
			System.out.println(year + " : Non - Leap Year");
		}
	}

	private static int factorial(int n) {
		if (n == 0)
			return 1;
		return n * factorial(n - 1);
	}

	private static int fib(int n) {
		if (n == 0 || n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}

	private static void primid(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++)
				System.out.print("* ");
			System.out.println();
		}

	}

	private static void linearSearch(int a[], int target) {
		int location = 0;
		boolean flag = false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == target) {
				flag = true;
				location = i;
				break;
			}
		}
		if (flag)
			System.out.println("Target found at " + location);
		else
			System.out.println("Target not found");

	}

	private static void bubbleSort(int a[]) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		System.out.print("Sorted Array: ");
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	private static void binarySearch(int a[], int target) {
		int low = 0, high = a.length - 1, loc = 0;
		boolean flag = false;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == target) {
				flag = true;
				loc = mid;
				break;
			} else if (a[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		if (flag)
			System.out.println("Target found at " + loc);
		else
			System.out.println("Target not found");
	}

	private static void transpose(int a[][]) {
		int b[][] = new int[100][100];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				b[i][j] = a[j][i];
			}
		}
		System.out.println("Transpose: ");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// lar(5,9,7);
		// binaryToDecimal("10101");
		// decimalToBinary(8);
		// sumOfDigits(123);
		// System.out.println(result);
		// sumofnNos(5);
		// multiplybyFactor(5);
		// System.out.println(primeorNot(9)?"Prime":"Not Prime");
		// primetill(50);
		// System.out.println(isAmstrong(2)?"Armstrong":"Not Armstrong");
		// dbselect();
		// dbinsert();
		// dbupdate();
		// dbdelete();
		// ageCalc();
		// matrixmul();
		// System.out.println(GCD(6,4));
		// System.out.println(lcm(4,6));
		// LeapYear(2004);
		// System.out.println(factorial(5));
		// for(int i=0;i<5;i++)
		// System.out.println(fib(i));
		// primid(5);
		// int a[] = {1,5,18,36,51,70,88,95,500,3600};
		// linearSearch(a, 6);
		// bubbleSort(a);
		// binarySearch(a, 70);
		// int a[][] = { { 1, 1, 1, 1 },
		// { 2, 2, 2, 2 },
		// { 3, 3, 3, 3 },
		// { 4, 4, 4, 4 } };
		// transpose(a);
		for (int a = 1; a < 3; a += 3)
			System.out.println(--a);
			1. Hello World

			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Hello World</title>
			</head>
			<body>
				<h1>Hello World</h1>
			</body>
			</html>
			
			2. Current Date
			
			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Current Date</title>
			</head>
			<body>
				<h1>Date: <%= new java.util.Date() %></h1>
			</body>
			</html>
			
			3. Current Time
			
			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Current Time</title>
			</head>
			<body>
				<h1>Time: <%= new java.text.SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()) %></h1>
			</body>
			</html>
			
			
			4. Calculate Age
			
			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Calculate Age</title>
			</head>
			<body>
			<form action="age.jsp" method="post">
					<label>DOB</label>
					<input type="date" name="dob" />
					<input type="submit" value="submit">
				</form>
				<% 
					String dobStr = request.getParameter("dob");
					if (dobStr != null && !dobStr.isEmpty()) {
						try {
							java.util.Date birthDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
							java.util.Date now = new java.util.Date();
			
							long diff = now.getTime() - birthDate.getTime();
							long seconds = diff / 1000;
							long minutes = seconds / 60;
							long hours = minutes / 60;
							long days = hours / 24;
							long years = days / 365;
							long months = (long) (years * 12 + ((now.getMonth() + 1) - (birthDate.getMonth() + 1)));
							days = (long) (diff / (1000 * 60 * 60 * 24) % 30.4375);
			
							%>
							<h1>Age: <%= years %> years, <%= months %> months, <%= days %> days, <%= hours %> hours, <%= minutes %> minutes, <%= seconds %> seconds.</h1>
							<% 
						} catch (java.text.ParseException e) {
							out.println("Error parsing date: " + e.getMessage());
						}
					}
				%>
			</body>
			</html>
			
			5. Login
			
			login.jsp
			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Login</title>
			</head>
			<body>
			<form action="lCheck.jsp" method="post">
					<label for="username">Username:</label><br>
					<input type="text" id="username" name="username" required="required"><br>
					<label for="password">Password:</label><br>
					<input type="password" id="password" name="password" required="required"><br><br>
					<input type="submit" value="Login">
				</form>
			</body>
			</html>
			
			lCheck.jsp
			<%@ page language="java" contentType="text/html; charset=UTF-8"
				pageEncoding="UTF-8"%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Login Check</title>
			</head>
			<body>
				<% 
					String username = request.getParameter("username").trim();
					String password = request.getParameter("password").trim();
			
				   boolean bFlab =false;
				   if(username!=null&&username!=""&&"Viswa".equalsIgnoreCase(username)&&password!=null&&password!=""&&"GoodBye@123".equals(password)){
					   bFlab=true;
				   }
				%>
				<%
				if(bFlab){
				%>
				<h1 style="color:green">Login Successful</h1>
				<p style="color:green">Username: <%= username %></p>
				<%}else{ %>
				<h1 style="color:red">Login Failed</h1>
				<p style="color:red">Username: <%= username %></p>
				<%} %>
			</body>
			</html>
	}
}
	}
}
