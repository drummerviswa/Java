
import java.sql.*;

import java.util.*;

import java.lang.String;

class Daisy

{

	Scanner sc = new Scanner(System.in);

	public static void Select(String id)

	{

		try

		{

			// String id;

			// System.out.println("enter subject id:");

			// id=sc.next();

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

			System.out.println("\n");

			// mark

			String queryy = "SELECT * FROM mark WHERE Subcode= ?";

			// Creating the PreparedStatement object

			PreparedStatement pstm = con.prepareStatement(queryy);

			pstm.setString(1, id);

			ResultSet sm = pstm.executeQuery();

			System.out.println("Markid" + "   " + "RegNo" + "     " + "Subcode" + "   " + "Subtype" + "    " + " Att1"
					+ "   " + "Att2" + "  " + "Att3"

					+ "  " + "TotAtt" + "  " + "Assess1" + "  " + "Assess2" + "  " + "TotAssess" + "   " + "Endsem"
					+ "   " + "Total" + "    " + "Grade");

			while (sm.next())

				System.out.println(sm.getInt(1) + "     " + sm.getInt(2) + "   " + sm.getString(3) + "    "
						+ sm.getString(4) + "      " + sm.getInt(5) + "      "

						+ sm.getInt(6) + "    " + sm.getInt(7) + "     " + sm.getInt(8) + "      " + sm.getInt(9)
						+ "      " + sm.getInt(10) +

						"        " + sm.getInt(11) + "         " + sm.getInt(12) + "        " + sm.getInt(13)
						+ "       " + sm.getString(14));

			System.out.println("\n");

			// subject

			String quer = "SELECT * FROM subject WHERE Subcode= ?";

			// Creating the PreparedStatement object

			PreparedStatement pst = con.prepareStatement(quer);

			pst.setString(1, id);

			ResultSet ss = pst.executeQuery();

			System.out.println("Subcode" + "    " + "Subname" + "   " + "Subtype" + "     " + "Sem");

			while (ss.next())

				System.out.println(ss.getString(1) + "     " + ss.getString(2) + "      " + ss.getString(3) + "      "
						+ ss.getInt(4));

			System.out.println("\n");

			con.close();

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

	}

	public static void Select_Student(String id)

	{

		try

		{

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

			String query = "SELECT * FROM student WHERE RegNo= ?";

			// Creating the PreparedStatement object

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			System.out.println("\n");

			System.out.println("RegNo" + "      " + "Name" + "      " + "Age" + "       " + "Degree" +

					"       " + "Mode of Degree" + "       " + "Branch" + "               " + "Department" + "         "
					+ "Year of Joining");

			while (rs.next())

				System.out.println(rs.getInt(1) + "    " + rs.getString(2) + "      " + rs.getInt(3) + "       "
						+ rs.getString(4) +

						"       " + rs.getString(5) + "              " + rs.getString(6) + "      " + rs.getString(7)
						+ "        " + rs.getString(8));

			System.out.println("\n");

			// mark

			String queryy = "SELECT * FROM mark WHERE RegNo= ?";

			// Creating the PreparedStatement object

			PreparedStatement pstm = con.prepareStatement(queryy);

			pstm.setString(1, id);

			ResultSet sm = pstm.executeQuery();

			System.out.println("Markid" + "   " + "RegNo" + "     " + "Subcode" + "   " + "Subtype" + "    " + " Att1"
					+ "   " + "Att2" + "  " + "Att3"

					+ "  " + "TotAtt" + "  " + "Assess1" + "  " + "Assess2" + "  " + "TotAssess" + "   " + "Endsem"
					+ "   " + "Total" + "    " + "Grade");

			while (sm.next())

				System.out.println(sm.getInt(1) + "     " + sm.getInt(2) + "   " + sm.getString(3) + "    "
						+ sm.getString(4) + "      " + sm.getInt(5) + "      "

						+ sm.getInt(6) + "    " + sm.getInt(7) + "     " + sm.getInt(8) + "      " + sm.getInt(9)
						+ "      " + sm.getInt(10) +

						"        " + sm.getInt(11) + "         " + sm.getInt(12) + "        " + sm.getInt(13)
						+ "       " + sm.getString(14));

			System.out.println("\n");

			con.close();

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

	}

	public static String grade(float Total)

	{

		String Grade = "UA";

		if (Total >= 95)

		{
			Grade = "O+";

		}

		else if (Total >= 85)

		{
			Grade = "A+";

		}

		else if (Total >= 75)

		{
			Grade = "A";

		}

		else if (Total >= 65)

		{
			Grade = "B+";

		}

		else if (Total >= 55)

		{
			Grade = "B";

		}

		else if (Total >= 50)

		{
			Grade = "C";

		}

		else

		{
			Grade = "RA";

		}

		return Grade;

	}

	public static void Insert_Mark()

	{

		int Markid = 0, RegNo, Att1, Att2, Att3, TotAtt, Assess1, Assess2, Endsem;

		float Total, Internal, i, j;

		String Subcode = null, Subtype, Grade = "UA";

		Scanner sc = new Scanner(System.in);

		try

		{

			System.out.println("Enter  Markid:\n");

			Markid = sc.nextInt();

			System.out.println("Enter  Register number:\n");

			RegNo = sc.nextInt();

			System.out.println("Enter  Subcode:\n");

			Subcode = sc.next();

			System.out.println("Enter  Subtype:\n");

			Subtype = sc.next();

			System.out.println("Enter  Attendance 1:\n");

			Att1 = sc.nextInt();

			System.out.println("Enter Attendance 2:\n");

			Att2 = sc.nextInt();

			System.out.println("Enter Attendance 3:\n");

			Att3 = sc.nextInt();

			TotAtt = (Att1 + Att2 + Att3) / 3;

			System.out.println("Enter Assessment 1 mark out of 50:\n");

			Assess1 = sc.nextInt();

			System.out.println("Enter Assessment 2 mark out of 50:\n");

			Assess2 = sc.nextInt();

			System.out.println("Enter Endsem mark:\n");

			Endsem = sc.nextInt();

			i = Assess1 + Assess2;

			// System.out.println(i);

			j = i / 100;

			// System.out.println(j);

			Internal = j * 40;

			// System.out.println(Internal);

			float k = Endsem * 60;

			// System.out.println(k);

			float l = k / 100;

			// System.out.println(l);

			Total = l + Internal;

			// System.out.println(Total);

			Grade = grade(Total);

			// System.out.println(Grade);

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

			String sql = " insert into mark (Markid, RegNo, Subcode, Subtype, Att1, Att2, Att3, TotAtt, Assess1, Assess2, Internal, Endsem, Total, Grade)"

					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(sql);

			preparedStmt.setInt(1, Markid);

			preparedStmt.setInt(2, RegNo);

			preparedStmt.setString(3, Subcode);

			preparedStmt.setString(4, Subtype);

			preparedStmt.setInt(5, Att1);

			preparedStmt.setInt(6, Att2);

			preparedStmt.setInt(7, Att3);

			preparedStmt.setInt(8, TotAtt);

			preparedStmt.setInt(9, Assess1);

			preparedStmt.setInt(10, Assess2);

			preparedStmt.setFloat(11, Internal);

			preparedStmt.setInt(12, Endsem);

			preparedStmt.setFloat(13, Total);

			preparedStmt.setString(14, Grade);

			preparedStmt.execute();

			con.close();

		}

		catch (Exception e)

		{

			System.err.println("Got an exception!");

			e.printStackTrace();

			System.out.println(e);

		}

		sc.close();

	}

	public static void subcode()

	{

		Scanner sc = new Scanner(System.in);

		String id1;

		int id2;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

			String query = "UPDATE  mark SET Subcode= ? WHERE Markid= ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			System.out.println("Enter  new subcode:");

			id1 = sc.next();

			pstmt.setString(1, id1);

			System.out.println("Enter mark id:");

			id2 = sc.nextInt();

			pstmt.setInt(2, id2);

			int count = pstmt.executeUpdate();

			if (count > 0)

			{

				System.out.println("-----UPDATED SUCCESSFULLY-----");

			}

			else

			{

				System.out.println("-----UPDATION FAILED-----");

			}

			con.close();

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

		// sc.close();

	}

	public static void Update()

	{

		Scanner sc = new Scanner(System.in);

		// String id;

		int selection;

		String obj, id3;
		try

		{

			// Class.forName("com.mysql.cj.jdbc.Driver");

			// Connection con=DriverManager.getConnection(
			// "jdbc:mysql://localhost:3306/portal","root","");

			int id4;

			do {

				System.out.println("\n1. UPDATE SUBJECT CODE:");

				System.out.println("\n2. UPDATE SUBJECT TYPE:");

				System.out.println("\n3. UPDATE ATTENDANCE 1:");

				System.out.println("\n4. UPDATE ATTENDANCE 2:");

				System.out.println("\n5. UPDATE ATTENDANCE 3:");

				System.out.println("\n6. UPDATE ASSESSMENT 1 MARK:");

				System.out.println("\n7. UPDATE ASSESSMENT 2 MARK:");

				System.out.println("\n8. UPDATE ENDSEM MARK:");

				System.out.println("\n9. Quit");

				System.out.println("\nenter option:");

				do {

					selection = sc.nextInt();

					if (selection == 1)

					{

						// Scanner sc=new Scanner(System.in);

						String id1;

						int id2;

						try {

							Class.forName("com.mysql.cj.jdbc.Driver");

							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root",
									"");

							String query = "UPDATE  mark SET Subcode= ? WHERE Markid= ?";

							PreparedStatement pstmt = con.prepareStatement(query);

							System.out.println("Enter  new subcode:");

							id1 = sc.next();

							pstmt.setString(1, id1);

							System.out.println("Enter mark id:");

							id2 = sc.nextInt();

							pstmt.setInt(2, id2);

							int count = pstmt.executeUpdate();

							if (count > 0)

							{

								System.out.println("-----UPDATED SUCCESSFULLY-----");

							}

							else

							{

								System.out.println("-----UPDATION FAILED-----");

							}

							con.close();

						}

						catch (Exception e)

						{

							System.out.println(e);

						}

						break;

					}

					else if (selection == 2) {

						Class.forName("com.mysql.cj.jdbc.Driver");

						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

						String query = "UPDATE  mark SET Subtype=? WHERE Markid= ?";

						PreparedStatement pstmt = conn.prepareStatement(query);

						System.out.println("Enter mark id:");

						id3 = sc.next();

						pstmt.setString(1, id3);

						System.out.println("Enter mark id:");

						id4 = sc.nextInt();

						pstmt.setInt(1, id4);

						conn.close();

						break;

					}

					else if (selection == 3) {

						Delete();

						break;

					}

					else {

						System.out.println("4");

					}

					// System.out.println("enter option");

				} while (selection != 6);

				System.out.println("Would you like to continue (update menu)?(yes/no)");

				obj = sc.next();

			} while (obj.equals("yes"));

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

		sc.close();

	}

	public static void Delete()

	{

		Scanner sc = new Scanner(System.in);

		int del;

		System.out.println("Enter mark id:");

		del = sc.nextInt();

		try

		{

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal", "root", "");

			String query = "DELETE  FROM mark WHERE Markid= ?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, del);

			System.out.println("-------DELETED SUCCESSFULLY--------");

			pstmt.execute();

			con.close();

		}

		catch (Exception e)

		{

			System.out.println(e);

		}

		sc.close();

	}

	public static void main(String[] arguments) {

		// String staffid[]= {"java24","dbms24"} ;

		// String staffPassword[]= {"java","dbms"};

//    String studentid[]= {"2024001","2024002"};

		// String studentPassword[]= {"001d","002j"};

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your userid: ");

		String userName = sc.next();

		System.out.println("Enter your password: ");

		String passWord = sc.next();

		String obj;
		if ((userName.equals("XC5253") && passWord.equals("java"))
				|| (userName.equals("XC5454") && userName.equals("dbms"))) {

			int selection;

			do {

				System.out.println("\n1. SELECT");

				System.out.println("\n2. INSERT");

				System.out.println("\n3. DELETE");

				System.out.println("\n4. UPDATE");

				System.out.println("\n5. Quit");

				System.out.println("\nENTER  CHOICE:");

				do {

					selection = sc.nextInt();

					if (selection == 1) {

						Select(userName);

						break;

					}

					else if (selection == 2) {

						Insert_Mark();

						break;

					}

					else if (selection == 3) {

						Delete();

						break;

					}

					else if (selection == 4) {

						Update();

						break;

					}

					else {

						System.out.println("4");

					}

					// System.out.println("enter option");

				}

				while (selection != 6);

				System.out.println("Would you like to continue (main menu)?(yes/no)");

				obj = sc.nextLine();

			} while (obj.equals("yes"));

			// sc.close();

		}

		else if ((userName.equals("2024001") && passWord.equals("001d"))
				|| (userName.equals("2024002") && userName.equals("002j"))) {

			String reply;

			int selection;

			do {

				System.out.println("\n1. View student details and mark");

				System.out.println("\n2. Quit");

				System.out.println("\n Enter option:");

				do {

					selection = sc.nextInt();

					if (selection == 1) {

						Select_Student(userName);

						break;

					}

				}

				while (selection != 2);

				System.out.println("\nWould you like to continue?(yes/no)");

				reply = sc.next();

			} while (reply.equals("yes"));

			// sc.close();

		}

		else

		{

			System.out.println("INVALID USERNAME AND PASSWORD");

		}

		sc.close();

	}

}