import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
	static DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
	
	private String name;
	private int Is_Married;
	private int Number_Of_Children;
	private String Position;
	private String Department;
	private Date Drafted;
	private String Adress;
	private String Mobile;
	private String Iban;
	private String Bank;
	private float Salary;
	private float Final_Salary;
	private float fam_bonus;
	private float time_bonus;
	private static float Bonus = 0.15f;
	private static float fam_bonus_multiplier = 0.05f;
	private static float Salary_mdy = 800f;
	private static float Salary_mdp = 3500f;
	
	public Employee( String name, int Is_Married, int Number_Of_Children, String Position, String Department, Date Drafted, String Adress, String Mobile, String Iban, String Bank) throws SQLException {
		this.name = name;
		this.Is_Married = Is_Married;
		this.Number_Of_Children = Number_Of_Children;
		this.Position = Position;
		this.Department = Department;
		this.Drafted = Drafted;
		this.Adress = Adress;
		this.Mobile = Mobile;
		this.Iban = Iban;
		this.Bank = Bank;
		
		if((Position.compareTo("mdy") == 0)){	
		this.Salary =  Employee.Salary_mdy;
		
		}else{
			this.Salary =  Employee.Salary_mdp;
		}
		
		this.time_bonus = calculate_time_bonus(this);
		this.fam_bonus = calculate_fam_bonus(this);
		
		if((Position.compareTo("mdy") == 0))
			this.Final_Salary = this.time_bonus + this.fam_bonus + Employee.Salary_mdy;
		else
			this.Final_Salary = this.time_bonus + this.fam_bonus + Employee.Salary_mdp;
		
		
		add_To_Database(this);
	}
	
	
	public Employee( String name, int Is_Married, int Number_Of_Children, String Position, String Department, Date Drafted, String Adress, String Mobile, String Iban, String Bank , float Salary) throws SQLException {
		this.name = name;
		this.Is_Married = Is_Married;
		this.Number_Of_Children = Number_Of_Children;
		this.Position = Position;
		this.Department = Department;
		this.Drafted = Drafted;
		this.Adress = Adress;
		this.Mobile = Mobile;
		this.Iban = Iban;
		this.Bank = Bank;
		this.Salary = Salary;
		
		this.time_bonus = calculate_time_bonus(this);
		this.fam_bonus = calculate_fam_bonus(this);
		
		this.Final_Salary = this.time_bonus + this.fam_bonus + this.Salary;
	
		
		add_To_Database(this);
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	
	public static float calculate_fam_bonus(Employee Mark) {
		
		float Salary = Mark.Salary;
		float Bonus = 0;
		
		if(Mark.getIs_Married() == 1) {
			Bonus = Salary*Employee.getFam_bonus_multiplier()*(float)(Mark.getNumber_Of_Children() + 1);
		}
		
		return Bonus;
	}
	
	
	public static float calculate_time_bonus(Employee Mark) {
		float Salary = 0f;
		Date curDate = new Date();
		long ms = curDate.getTime() - Mark.getDrafted().getTime();
		long years = (long) (ms * 0.00000000000316);
		
		if(Mark.Position.compareTo("mdy")== 0)
		{
			if(years > 1)
				Salary = getBonus()*(years - 1)*getSalary_mdy();
		}
		else if(Mark.Position.compareTo("mdp")== 0)
		{
			if(years > 1)
				Salary =  getBonus()*(years - 1)*getSalary_mdp();
		}	
		return Salary;
	}
	
	
	
	
	public void add_To_Database(Employee Mark) throws SQLException {
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		st.execute("INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('" +Mark.getName() +"', " +Mark.getIs_Married()+", "+Mark.getNumber_Of_Children()+",'"+Mark.getPosition()+"','"+Mark.getDepartment()+
				"', '"+dtf.format(Mark.getDrafted())+"', '"+Mark.getAdress()+"','"+Mark.getMobile()+"','"+Mark.getIban()+"','"+Mark.getBank()+"',"+Mark.Salary+", "+ Mark.fam_bonus + "," + Mark.time_bonus + "," + Mark.Final_Salary + ")");
	}
	
	
	public String ask_Database(int id,String col) throws SQLException {
		String result = null;
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		
		rs = st.executeQuery("select "+ col +" from sys.employees where EID=" + id);
		
		while(rs.next()) {
		result = rs.getString(col);
		}
		
		return result;
	}
	
	
	
	
	public static void change_S_From_Database(int id,String col, String value) throws SQLException {
	
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();	
		st.execute("UPDATE sys.employees SET "+col +" = '"+ value +  "' where EID = " + id);
	
	}
	
	
	public void change_I_From_Database(int id,String col, int value) throws SQLException, ParseException {
		
		float sal;
		
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();	
		ResultSet rs;
		Employee Mark = new Employee();
		
		st.execute("UPDATE sys.employees SET "+col +" = '"+ value +  "' where EID = " + id);
		
		rs = st.executeQuery("select * from sys.employees where EID = " + id);
		
		while(rs.next()) {
			
			Mark.setName(rs.getString("Name"));
			Mark.setIs_Married(rs.getInt("Is_Married"));
			Mark.setNumber_Of_Children(rs.getInt("Number_Of_Children"));
			Mark.setPosition(rs.getString("Position"));
			Mark.setDepartment(rs.getString("Department"));
			Mark.setAdress(rs.getString("Adress"));
			String date=rs.getString("Drafted");
			Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date); 
			Mark.setDrafted(date1);
			Mark.setMobile(rs.getString("Mobile"));
			Mark.setIban(rs.getString("Iban"));
			Mark.setBank(rs.getString("Bank"));
			Mark.setSalary(rs.getFloat("Salary"));
			Mark.setFam_bonus(rs.getFloat("fam_bonus"));
			Mark.setTime_bonus(rs.getFloat("time_bonus"));
			System.out.println(Mark.getSalary());
		}
	
		
		sal = calculate_fam_bonus(Mark) + calculate_time_bonus(Mark) + Mark.getSalary();
		Mark.setFam_bonus(calculate_fam_bonus(Mark));
		Mark.setTime_bonus(calculate_time_bonus(Mark));
		System.out.println("i will take:" + sal);
		
		st.execute("UPDATE sys.employees SET "+col +" = "+ value +  "," + "Salary = " +  Mark.getSalary() + ", fam_bonus = " + Mark.getFam_bonus() + ", time_bonus = " + Mark.getTime_bonus() +", final_salary = " + sal  +  " where EID = " + id);
		
	}
	
	
	public static void change_F_From_Database(int id,String col, float value) throws SQLException, ParseException {
		float sal;
		
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();	
		ResultSet rs;
		Employee Mark = new Employee();
		
		st.execute("UPDATE sys.employees SET "+col +" = '"+ value +  "' where EID = " + id);
		
		rs = st.executeQuery("select * from sys.employees where EID = " + id);
		
		while(rs.next()) {
			
			Mark.setName(rs.getString("Name"));
			Mark.setIs_Married(rs.getInt("Is_Married"));
			Mark.setNumber_Of_Children(rs.getInt("Number_Of_Children"));
			Mark.setPosition(rs.getString("Position"));
			Mark.setDepartment(rs.getString("Department"));
			Mark.setAdress(rs.getString("Adress"));
			String date=rs.getString("Drafted");
			Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date); 
			Mark.setDrafted(date1);
			Mark.setMobile(rs.getString("Mobile"));
			Mark.setIban(rs.getString("Iban"));
			Mark.setBank(rs.getString("Bank"));
			Mark.setSalary(rs.getFloat("Salary"));
			Mark.setFam_bonus(rs.getFloat("fam_bonus"));
			Mark.setTime_bonus(rs.getFloat("time_bonus"));
		}
	
		
		sal = calculate_fam_bonus(Mark) + calculate_time_bonus(Mark) + Mark.getSalary();
		Mark.setFam_bonus(calculate_fam_bonus(Mark));
		Mark.setTime_bonus(calculate_time_bonus(Mark));

		
		st.execute("UPDATE sys.employees SET "+col +" = "+ value +  "," + "Salary = " +  Mark.getSalary() + ", fam_bonus = " + Mark.getFam_bonus() + ", time_bonus = " + Mark.getTime_bonus() +", final_salary = " + sal  +  " where EID = " + id);
	}
	
	
	public static void change_B_From_Database(int id) throws SQLException, ParseException {
		float sal;
		
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();	
		ResultSet rs;
		Employee Mark = new Employee();

		
		rs = st.executeQuery("select * from sys.employees where EID = " + id);
		
		while(rs.next()) {
			
			Mark.setName(rs.getString("Name"));
			Mark.setIs_Married(rs.getInt("Is_Married"));
			Mark.setNumber_Of_Children(rs.getInt("Number_Of_Children"));
			Mark.setPosition(rs.getString("Position"));
			Mark.setDepartment(rs.getString("Department"));
			Mark.setAdress(rs.getString("Adress"));
			String date=rs.getString("Drafted");
			Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date); 
			Mark.setDrafted(date1);
			Mark.setMobile(rs.getString("Mobile"));
			Mark.setIban(rs.getString("Iban"));
			Mark.setBank(rs.getString("Bank"));
			Mark.setSalary(rs.getFloat("Salary"));
			Mark.setFam_bonus(rs.getFloat("fam_bonus"));
			Mark.setTime_bonus(rs.getFloat("time_bonus"));
		}
	
		
		sal = calculate_fam_bonus(Mark) + calculate_time_bonus(Mark) + Mark.getSalary();
		Mark.setFam_bonus(calculate_fam_bonus(Mark));
		Mark.setTime_bonus(calculate_time_bonus(Mark));

		
		st.execute("UPDATE sys.employees SET " + "Salary = " +  Mark.getSalary() + ", fam_bonus = " + Mark.getFam_bonus() + ", time_bonus = " + Mark.getTime_bonus() +", final_salary = " + sal  +  " where EID = " + id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIs_Married() {
		return Is_Married;
	}

	public void setIs_Married(int is_Married) {
		Is_Married = is_Married;
	}

	public int getNumber_Of_Children() {
		return Number_Of_Children;
	}

	public void setNumber_Of_Children(int number_Of_Children) {
		Number_Of_Children = number_Of_Children;
	}

	public String getPosition() {
		return this.Position;
	}

	public void setPosition(String position) {
		this.Position = position;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public Date getDrafted() {
		return Drafted;
	}

	public void setDrafted(Date drafted) {
		Drafted = drafted;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getIban() {
		return Iban;
	}

	public void setIban(String iban) {
		Iban = iban;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public float getSalary() {
		return Salary;
	}

	public void setSalary(float salary) {
		Salary = salary;
	}

	public static float getBonus() {
		return Bonus;
	}

	public static void setBonus(float bonus) {
		Bonus = bonus;
	}

	public static float getSalary_mdy() {
		return Salary_mdy;
	}

	public static void setSalary_mdy(float salary_mdy) {
		Salary_mdy = salary_mdy;
	}

	public static float getSalary_mdp() {
		return Salary_mdp;
	}

	public static void setSalary_mdp(float salary_mdp) {
		Salary_mdp = salary_mdp;
	}


	public float getFinal_Salary() {
		return Final_Salary;
	}


	public void setFinal_Salary(float final_Salary) {
		Final_Salary = final_Salary;
	}


	public float getTime_bonus() {
		return this.time_bonus;
	}


	public void setTime_bonus(float time_bonus) {
		this.time_bonus = time_bonus;
	}


	public float getFam_bonus() {
		return this.fam_bonus;
	}


	public void setFam_bonus(float fam_bonus) {
		this.fam_bonus = fam_bonus;
	}


	public static float getFam_bonus_multiplier() {
		return fam_bonus_multiplier;
	}


	public static void setFam_bonus_multiplier(float fam_bonus_multiplier) {
		Employee.fam_bonus_multiplier = fam_bonus_multiplier;
	}


	public static void Re_calculate(String str) throws SQLException, ParseException {
		
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		
		if((str.compareTo("mdy") == 0)) {
			rs = st.executeQuery("SELECT * FROM sys.employees where Position = '" + str +"'");
			while(rs.next()) {
				int id = rs.getInt("EID");
				change_F_From_Database(id,"Salary",getSalary_mdy());
			}		
			
		}
		else if((str.compareTo("mdp") == 0)) {

			rs = st.executeQuery("SELECT * FROM sys.employees where Position = '" + str +"'");
			while(rs.next()) {
				int id = rs.getInt("EID");
				change_F_From_Database(id,"Salary",getSalary_mdp());
			}		
		
		}
		else {
			
			rs = st.executeQuery("SELECT * FROM sys.employees where Is_Married = 1");
			while(rs.next()) {
				int id = rs.getInt("EID");
				change_B_From_Database(id);
			}
		}
		
	}
	
}
