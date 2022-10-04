import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Table;

public class Payment_Records {

	public static void show_payment(String first, String second) throws SQLException {
		String leftAlignFormat = "| %-4d | %-14s | %-12f|%n";
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		
		rs = st.executeQuery("SELECT * FROM sys.payment_records WHERE Payment_Date BETWEEN '"+first +"' AND '"+second+"';");
		System.out.format("+------+----------------+-------------+%n");
		System.out.format("| EID  |  Payment_Date  |    Amount   |%n");
		System.out.format("+------+----------------+-------------+%n");
		while(rs.next()) {
			System.out.format(leftAlignFormat, rs.getInt("EID") ,rs.getString("Payment_Date"), rs.getFloat("Amount"));
		}
		System.out.format("+------+----------------+-------------+%n");
		
	}
	
	public static void stats_per_position(String pos) throws SQLException {
		float min = -1, max = -1, avg = -1;
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		ResultSet rs2;
		ResultSet rs3;
		
		rs = st.executeQuery("select MIN(final_salary) from sys.employees WHERE Position = '" +pos +"';");
		if(rs.next())
			min = rs.getFloat(1);
		
		rs2 = st.executeQuery("select MAX(final_salary) from sys.employees WHERE Position = '" +pos +"'");
		if(rs2.next())
			max = rs2.getFloat(1);
		
		rs3 = st.executeQuery("select AVG(final_salary) from sys.employees WHERE Position = '" +pos +"'");
		if(rs3.next())
			avg = rs3.getFloat(1);
		System.out.println("-----------------------------------------------");
		System.out.println("O elaxistos misthos gia enan " +pos +" einai: " +min);
		System.out.println("O megistos misthos gia enan " +pos +" einai: " +max);
		System.out.println("O mesos misthos gia enan " +pos +" einai: " +avg);
		System.out.println("-----------------------------------------------");
	}
	
	public static void ypsos_misthodosias(String pos) throws SQLException {
		float sum = -1f;
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		
		rs = st.executeQuery("select SUM(final_salary) from sys.employees WHERE Position = '" +pos +"';");
		if(rs.next())
			sum = rs.getFloat(1);
		
		System.out.println("---------------------------------------------------------");
		System.out.println("To synoliko ypsos misthodosias gia enan " +pos +" einai: " +sum);
		System.out.println("---------------------------------------------------------");

	}
	
	
}
