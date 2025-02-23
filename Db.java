import java.sql.*;
import java.util.Scanner;

public class Db {
	public static String grading(int t) {
		String g = "";
		if (t > 91 && t <= 100)
			g = "O";
		else if (t > 81 && t <= 90)
			g = "A+";
		else if (t > 71 && t <= 80)
			g = "A";
		else if (t > 61 && t <= 70)
			g = "B+";
		else if (t > 51 && t <= 60)
			g = "B";
		else if (t > 51 && t <= 60)
			g = "C";
		return g;

	}

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			int option, regno, so;
			String Name=null,DOB=null,Department=null,Branch=null,Degree=null,DMode=null,YOJ=null,Sub_Code=null,Sub_Name=null,Sub_Type=null;
			int attend_1, attend_2, attend_3, Assess_1, Assess_2, Assess_3, Max_Attend_1, Max_Attend_2, Max_Attend_3,Sem,
					Total_Attend, Total_Internal, End_Sem, Total;
			do {
				System.out.print(
						"Welcome to the Mini Sems Project\n1.Enter results for a student\n2.Update results for a student\n3.View the results for a student\n4.Delete the results for a student\n5.Create a student\n6.View all Students\n7.Update Student Details\n8.Create a Subject\n9.Delete a Subject\n10.List of Subjects\n11.Exit\nEnter an option below: \n");
				option = scan.nextInt();
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sems", "root", "");
				Statement s = con.createStatement();
				Statement s1 = con.createStatement();
				Statement s2 = con.createStatement();
				ResultSet student_register = s.executeQuery("select * from student_register");
				int[] temprno = new int[50000];
				String[] subjectslist = new String[50000];
				String sj = "", grade;
				int i = 0, k = 0;
				switch (option) {
					case 1:
						System.out.println("Enter the register no: ");
						regno = scan.nextInt();
						i = 0;
						while (student_register.next()) {
							temprno[i] = student_register.getInt(1);
							i++;
						}
						boolean student_exist = false;
						for (int j = 0; j <= i; j++) {
							if (regno == temprno[j]) {
								System.out.println("Stduent Exist");
								ResultSet name = s.executeQuery("select * from student_register where Reg_no=" + regno);
								while (name.next())
									System.out.println("Hello!! " + name.getString(2));
								System.out.print(
										"Subjects list:\n1.Java Theory\n2.DBMS Theory\n3.Theory of Computation\n4.Computer Networks\n5.Probabilty and Statistics\n6.R Programming Lab\n7.Java Lab\n8.DBMS Lab\n");
								System.out.println("Enter the subject: ");
								so = scan.nextInt();
								ResultSet subjects = s.executeQuery("select * from subjects");
								switch (so) {
									case 1:
										while (subjects.next()) {
											// TODO- Not entering inside the if statement
											// Comparing Sub name is not possible with == symbol, only possible with .equals() kaththuko da
											if ("JAVA AND INTERNET PROGRAMMING".equals(subjects.getString(2))) {
												sj = subjects.getString(1);
											}
											break;
										}
										break;
									case 2:
										while (subjects.next()) {
											if ("DATABASE MANAGEMENT SYSTEMS".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 3:
										while (subjects.next()) {
											if ("THEORY OF COMPUTATION".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 4:
										while (subjects.next()) {
											if ("COMPUTER NETWORKS".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 5:
										while (subjects.next()) {
											if ("PROBABILITY AND STATISTICS".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 6:
										while (subjects.next()) {
											if ("Computational Laboratory using R".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 7:
										while (subjects.next()) {
											if ("Java and Internet Programming Laboratory"
													.equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									case 8:
										while (subjects.next()) {
											if ("Database Management System Laboratory".equals(subjects.getString(2)))
												sj = subjects.getString(1);
										}
										break;
									default:
										System.out.println("Error");
										break;
								}
								System.out.println("Enter Attendance 1: ");
								attend_1 = scan.nextInt();
								System.out.println("Enter Attendance 2: ");
								attend_2 = scan.nextInt();
								System.out.println("Enter Attendance 3: ");
								attend_3 = scan.nextInt();
								System.out.println("Enter Assessment 1 marks: ");
								Assess_1 = scan.nextInt();
								System.out.println("Enter Assessment 2 marks: ");
								Assess_2 = scan.nextInt();
								System.out.println("Enter Assessment 3 marks: ");
								Assess_3 = scan.nextInt();
								System.out.println("Enter Total class hours - 1: ");
								Max_Attend_1 = scan.nextInt();
								System.out.println("Enter Total class hours - 2: ");
								Max_Attend_2 = scan.nextInt();
								System.out.println("Enter Total class hours - 3: ");
								Max_Attend_3 = scan.nextInt();
								Total_Attend = ((attend_1 / Max_Attend_1) + (attend_2 / Max_Attend_2)
										+ (attend_3 / Max_Attend_3)) / 3;
								System.out.println("Enter Total Internal marks: ");
								Total_Internal = scan.nextInt();
								System.out.println("Enter End-Sem marks: ");
								End_Sem = scan.nextInt();
								Total = (int) (End_Sem * 0.6) + Total_Internal;
								grade = grading(Total);
								String insert_marks_query = "Insert into marks(`Regno`, `Sub_code`, `Attend_1`, `Attend_2`, `Attend_3`, `Assess_1`, `Assess_2`, `Assess_3`, `Max_Att_1`, `Max_Att_2`, `Max_Att_3`, `Total_Attend`, `Total_Internal`, `End_Sem`, `Total`, `Grade`)"
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								PreparedStatement insert_Marks = con.prepareStatement(insert_marks_query);
								insert_Marks.setInt(1, regno);
								insert_Marks.setString(2, sj);
								insert_Marks.setInt(3, attend_1);
								insert_Marks.setInt(4, attend_2);
								insert_Marks.setInt(5, attend_3);
								insert_Marks.setInt(6, Assess_1);
								insert_Marks.setInt(7, Assess_2);
								insert_Marks.setInt(8, Assess_3);
								insert_Marks.setInt(9, Max_Attend_1);
								insert_Marks.setInt(10, Max_Attend_2);
								insert_Marks.setInt(11, Max_Attend_3);
								insert_Marks.setInt(12, Total_Attend);
								insert_Marks.setInt(13, Total_Internal);
								insert_Marks.setInt(14, End_Sem);
								insert_Marks.setInt(15, Total);
								insert_Marks.setString(16, grade);
								insert_Marks.execute();
								System.out.println("Inserted successfully!!");
								student_exist=true;
							}
						}
						if (!student_exist)
							System.out.print("Register Number not found!!!\n\n\n");
						break;
					case 2:
						System.out.println("Enter Register no: ");
						regno = scan.nextInt();
						i = 0;
						while (student_register.next()) {
							temprno[i] = student_register.getInt(1);
							i++;
						}
						boolean student_exist2 = false;
						for (int j = 0; j <= i; j++) {
							if (regno == temprno[j]) {
								System.out.println("Student Exist\n");
								ResultSet name = s.executeQuery("select * from student_register where Reg_no=" + regno);
								while (name.next()) {
									System.out.println("Hello!!" + name.getString(2));
									System.out.print(
											"Subjects list:\n1.Java Theory\n2.DBMS Theory\n3.Theory of Computation\n4.Computer Networks\n5.Probabilty and Statistics\n6.R Programming Lab\n7.Java Lab\n8.DBMS Lab\n");
									System.out.println("Enter the subject you want to update values for: ");
									so = scan.nextInt();
									ResultSet subjects = s.executeQuery("select * from subjects");
									switch (so) {
										case 1:
											while (subjects.next()) {
												if ("JAVA AND INTERNET PROGRAMMING".equals(subjects.getString(2))) {
													sj = subjects.getString(1);
												}
												break;
											}
											break;
										case 2:
											while (subjects.next()) {
												if ("Database Management System".equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 3:
											while (subjects.next()) {
												if ("Theory of Computation".equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 4:
											while (subjects.next()) {
												if ("Computer Networks".equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 5:
											while (subjects.next()) {
												if ("Probabilty and Statistics".equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 6:
											while (subjects.next()) {
												if ("Computational Laboratory using R".equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 7:
											while (subjects.next()) {
												if ("Java and Internet Programming Laboratory"
														.equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										case 8:
											while (subjects.next()) {
												if ("Database Management System Laboratory"
														.equals(subjects.getString(2)))
													sj = subjects.getString(1);
											}
											break;
										default:
											break;
									}
									ResultSet particularStudentmarks = s.executeQuery("Select * from marks where Regno=" + regno);
									particularStudentmarks.next();
									System.out.println("Enter Assessment 1 marks(" + particularStudentmarks.getString(7) + "): ");
									Assess_1 = scan.nextInt();
									 System.out.println(
									 		"Enter Assessment 2 marks(" + particularStudentmarks.getInt(8) + "): ");
									 Assess_2 = scan.nextInt();
									 System.out.println(
									 		"Enter Assessment 3 marks(" + particularStudentmarks.getInt(9) + "): ");
									 Assess_3 = scan.nextInt();
									 System.out.println(
									 		"Enter Total Internal marks(" + particularStudentmarks.getInt(14) + "): ");
									 Total_Internal = scan.nextInt();
									 System.out
									 		.println("Enter End-Sem marks(" + particularStudentmarks.getInt(15) + "): ");
									 End_Sem = scan.nextInt();
									 Total = (int) (End_Sem * 0.6) + Total_Internal;
									 grade = grading(Total);
									 String update_marks_query = "Update marks set Assess_1=?,Assess_2=?,Assess_3=?,Total_Internal=?,End_Sem=?,Total=?,Grade=?";
									 PreparedStatement update_Marks = con.prepareStatement(update_marks_query);
									 update_Marks.setInt(1, Assess_1);
									 update_Marks.setInt(2, Assess_2);
									 update_Marks.setInt(3, Assess_3);
									 update_Marks.setInt(4, Total_Internal);
									 update_Marks.setInt(5, End_Sem);
									 update_Marks.setInt(6, Total);
									 update_Marks.setString(7, grade);
									 update_Marks.execute();
									 System.out.println("Update successfully!!");
									 break;
									}
									student_exist2=true;
							}
						}
						if (!student_exist2)
							System.out.print("Register Number not found!!!\n\n\n");
						break;
					case 3:
						System.out.println("Enter Register no: ");
						regno = scan.nextInt();
						i = 0;
						while (student_register.next()) {
							temprno[i] = student_register.getInt(1);
							i++;
						}
						boolean student_exist3 =false;
						for (int j = 0; j <= i; j++) {
							if (regno == temprno[j]) {
								ResultSet name_marks = s1.executeQuery("select * from student_register where Reg_no=" + regno);
								ResultSet marks = s.executeQuery("select * from marks where Regno="+regno);
								while (name_marks.next()) {
									System.out.println("Results of: "+name_marks.getString(2));
								}
								while(marks.next()){
									System.out.println();
									ResultSet subject_mark = s1.executeQuery("Select * from subjects where Sub_Code='"+marks.getString(3)+"'");
									while(subject_mark.next())
									System.out.println("Subject: "+subject_mark.getString(2));
									System.out.println("Subject Code: "+marks.getString(3));
									System.out.println();
									System.out.println("	Assess-1: "+marks.getString(7));
									System.out.println("	Assess-2: "+marks.getString(8));
									System.out.println("	Assess-3: "+marks.getString(9));
									System.out.println("	Internal: "+marks.getString(14));
									System.out.println("	End Semester: "+marks.getString(15));
									System.out.println("	Total: "+marks.getString(16));
									System.out.println("	Grade: "+marks.getString(17));
									System.out.println();
									System.out.println();
								}
								student_exist3=true;

							}
						}
						if (!student_exist3)
							System.out.print("Register Number not found!!!\n\n\n");
						break;
					case 4:
					System.out.println("Enter Register no: ");
					regno = scan.nextInt();
					i = 0;
					while (student_register.next()) {
						temprno[i] = student_register.getInt(1);
						i++;
					}
					boolean student_exist4=false;
					for (int j = 0; j <= i; j++) {
						if (regno == temprno[j]) {
							ResultSet name_marks = s1.executeQuery("select * from student_register where Reg_no=" + regno);
							ResultSet marks = s.executeQuery("select * from marks where Regno="+regno);
							ResultSet mark = s2.executeQuery("select * from marks where Regno="+regno);
							while (name_marks.next()) {
								System.out.println("Results of: "+name_marks.getString(2));
							}
							while(marks.next()){
								System.out.println();
								ResultSet subject_mark = s1.executeQuery("Select * from subjects where Sub_Code='"+marks.getString(3)+"'");
								System.out.println("	Mark ID: "+marks.getString(1));
								while(subject_mark.next())
								System.out.println("	Subject: "+subject_mark.getString(2));
								System.out.println();
								System.out.println("	Assess-1: "+marks.getString(7));
								System.out.println("	Assess-2: "+marks.getString(8));
								System.out.println("	Assess-3: "+marks.getString(9));
								System.out.println("	Internal: "+marks.getString(14));
								System.out.println("	End Semester: "+marks.getString(15));
								System.out.println("	Total: "+marks.getString(16));
								System.out.println("	Grade: "+marks.getString(17));
								System.out.println();
								System.out.println();
							}
							System.out.println("Enter the Mark ID you want to Delete Marks: ");
							String mid = scan.next();
							while(mark.next()){
								if(mid.equals(mark.getString(1))){
									PreparedStatement delete_Marks = con.prepareStatement("DELETE FROM marks WHERE Marks_ID="+mid);
									delete_Marks.execute();
									System.out.println("Deleted successfully!!");
								}
							}
							student_exist4=true;
							}
						}
						if (!student_exist4)
							System.out.print("Register Number not found!!!\n\n\n");
						break;
					case 5:
						System.out.println("Enter the Register Number: ");
						regno=scan.nextInt();
						i = 0;
						while (student_register.next()) {
							temprno[i] = student_register.getInt(1);
							i++;
						}
						boolean student_exist5 =false;
						for (int j = 0; j <= i; j++) {
							if (regno == temprno[j]) {
								System.out.println("Register number already found");
								student_exist5=true;
							}
						}
						if (!student_exist5){
							System.out.println("Enter Name: ");
							Name=scan.next();
							System.out.println("Enter Date of Birth in YYYY-MM-DD: ");
							DOB=scan.next();
							System.out.println("Enter Department: ");
							Department=scan.next();
							System.out.println("Enter Branch: ");
							Branch=scan.next();
							System.out.println("Enter Degree: ");
							Degree=scan.next();
							int mt;
							do{
								System.out.print("1.Full Time\n2.Part Time\n3.Distance Education\n4.Exit\nEnter Mode of Study: ");
								mt=scan.nextInt();
								switch(mt){
									case 1:
										DMode="Full Time";
										break;
									case 2:
										DMode="Part Time";
										break;
									case 3:
										DMode="Distance Education";
										break;
									default:
										System.out.println("Invalid, Please Enter correct option");
										break;
								}
							}while(mt==4);
							System.out.println("Enter Year of Joining in YYYY: ");
							YOJ=scan.next();
							PreparedStatement insert_student = con.prepareStatement("Insert into student_register(`Reg_no`, `Name`, `DOB`, `Department`, `Branch`, `Degree`, `D_Mode`, `Year_of_Joining`) Values(?,?,?,?,?,?,?,?)");
							insert_student.setInt(1, regno);
							insert_student.setString(2, Name);
							insert_student.setString(3, DOB);
							insert_student.setString(4, Department);
							insert_student.setString(5, Branch);
							insert_student.setString(6, Degree);
							insert_student.setString(7, DMode);
							insert_student.setString(8,YOJ);
							insert_student.execute();
							System.out.println("Inserted Successfully!!");
						}
						break;
					case 6:
						ResultSet studentsList = s.executeQuery("select * from student_register");
						while (studentsList.next()) {
							regno=studentsList.getInt(1);
							Name=studentsList.getString(2);
							DOB=studentsList.getString(3);
							Department=studentsList.getString(4);
							Branch=studentsList.getString(5);
							Degree=studentsList.getString(6);
							DMode=studentsList.getString(7);
							YOJ=studentsList.getString(8);
							System.out.println("Register Number: "+regno);
							System.out.println("Name: "+Name);
							System.out.println("Date of Birth: "+DOB);
							System.out.println("Department: "+Department);
							System.out.println("Branch: "+Branch);
							System.out.println("Degree: "+Degree);
							System.out.println("Mode of Study: "+DMode);
							System.out.println("Year of Joining: "+YOJ);
							System.out.println();
							System.out.println();
						}
						break;
					case 7:
						System.out.println("Enter the Register Number: ");
						regno=scan.nextInt();
						i = 0;
						while (student_register.next()) {
							temprno[i] = student_register.getInt(1);
							i++;
						}
						boolean student_exist6 =false;
						for (int j = 0; j <= i; j++) {
							if (regno == temprno[j]) {
								ResultSet updateStudents = s.executeQuery("select * from student_register where Reg_no="+regno);
								while (updateStudents.next()) {
									Name=updateStudents.getString(2);
									DOB=updateStudents.getString(3);
									Department=updateStudents.getString(4);
									Branch=updateStudents.getString(5);
									Degree=updateStudents.getString(6);
									DMode=updateStudents.getString(7);
									YOJ=updateStudents.getString(8);
									System.out.println("Update any changes: ");
									System.out.println("Enter Name("+updateStudents.getString(2)+"): ");
									Name=scan.next();
									System.out.println("Enter DOB("+updateStudents.getString(3)+"): ");
									DOB=scan.next();
									System.out.println("Enter Department("+updateStudents.getString(4)+"): ");
									Department=scan.next();
									System.out.println("Enter Branch("+updateStudents.getString(5)+"): ");
									Branch=scan.next();
									System.out.println("Enter Degree("+updateStudents.getString(6)+"): ");
									Degree=scan.next();
									System.out.println("Enter Mode of Study("+updateStudents.getString(7)+"): ");
									int mt;
									do{
										System.out.print("1.Full Time\n2.Part Time\n3.Distance Education\n4.Exit\nEnter Mode of Study: ");
										mt=scan.nextInt();
										switch(mt){
											case 1:
											DMode="Full Time";
											break;
											case 2:
											DMode="Part Time";
											break;
											case 3:
											DMode="Distance Education";
											break;
											default:
											System.out.println("Invalid, Please Enter correct option");
											break;
										}
									}while(mt==4);
									System.out.println("Enter Mode of Year of Joining("+updateStudents.getString(8)+"): ");
									YOJ=scan.next();
								}
								PreparedStatement upStudents = con.prepareStatement("Update student_register SET Name=?,DOB=?,Department=?,Branch=?,Degree=?,D_Mode=?,Year_of_Joining=? where Reg_no="+regno);
								upStudents.setString(1, Name);
								upStudents.setString(2, DOB);
								upStudents.setString(3, Department);
								upStudents.setString(4, Branch);
								upStudents.setString(5, Degree);
								upStudents.setString(6, DMode);
								upStudents.setString(7, YOJ);
								upStudents.execute();
								System.out.println("Updated Successfully!!");
								student_exist6=true;
							}
							}
						if(!student_exist6){
							System.out.println("Register Number not found!!\n\n");
						}
						break;
					// case 8:
					// 	System.out.println("Enter the Register Number: ");
					// 	regno=scan.nextInt();
					// 	i = 0;
					// 	while (student_register.next()) {
					// 		temprno[i] = student_register.getInt(1);
					// 		i++;
					// 	}
					// 	boolean student_exist7 =false;
					// 	for (int j = 0; j <= i; j++) {
					// 		if (regno == temprno[j]) {
					// 			PreparedStatement delete_Students = con.prepareStatement("DELETE FROM student_register WHERE Reg_no="+regno);
					// 			delete_Students.execute();
					// 			System.out.println("Deleted Successfully!!");
					// 			student_exist7=true;
					// 		}
					// 	}
					// 	if(!student_exist7){
					// 		System.out.println("Register Number not found!!\n\n");
					// 	}
					// 	break;
					case 8:
						ResultSet subject= s.executeQuery("Select * from subjects");
						System.out.println("Enter Subject Code: ");
						Sub_Code=scan.next();
						i = 0;
						while (subject.next()) {
							subjectslist[i] = subject.getString(1);
							i++;
						}
						boolean student_exist8 =false;
						for (int j = 0; j <= i; j++) {
							if (Sub_Code == subjectslist[j]) {
								System.out.println("Subject Already Exist");
								student_exist8=true;
							}
						}
						if(!student_exist8){
							System.out.println("Enter Subject Name: ");
							Sub_Name=scan.next();
							System.out.println("Enter Semester: ");
							Sem=scan.nextInt();
							System.out.println("Enter Subject Type: ");
							Sub_Type=scan.next();
							PreparedStatement insert_subject = con.prepareStatement("Insert into Subjects values(?,?,?,?)");
							insert_subject.setString(1, Sub_Code);
							insert_subject.setString(2, Sub_Name);
							insert_subject.setInt(3, Sem);
							insert_subject.setString(4, Sub_Type);
							insert_subject.execute();
							System.out.println("Inserted Successfully!!!");
						}
						break;
					case 9:
						ResultSet subject1= s.executeQuery("Select * from subjects");
						Sub_Code=scan.next();
						i = 0;
						while (subject1.next()) {
							subjectslist[i] = subject1.getString(1);
							i++;
						}
						boolean student_exist9 =false;
						for (int j = 0; j <= i; j++) {
							if (Sub_Code == subjectslist[j]) {
								PreparedStatement delete_sub = con.prepareStatement("Delete from subjects where Sub_Code="+Sub_Code);
								delete_sub.execute();
								student_exist8=true;
							}
						}
						if(!student_exist9){
							System.out.println("Subject Code Doesn't not exist");
						}
						break;
					case 10:
						boolean student_exist10=false;
						ResultSet subject2= s.executeQuery("Select * from subjects");
						while(subject2.next()){
								System.out.println();
								System.out.println("	Subject Code: "+subject2.getString(1));
								System.out.println("	Subject Name: "+subject2.getString(2));
								System.out.println("	Semester: "+subject2.getString(3));
								System.out.println("	Course Type: "+subject2.getString(4));
								System.out.println();
							}
						if(!student_exist10){
							System.out.println("Subject Code Doesn't not exist");
						}
						break;
					default:
						System.out.print("Invalid Entry, Please enter a valid option\n");
				}
				con.close();
			} while (option != 11);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}
}
