import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	static DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
	static Date curr = new Date();

	/*-------------------HELPING FUNCTIONS-------------------*/

	public static void Initialize() {

	}

	public static String scanString() {
Scanner scanner = new Scanner(System.in);
			String string = scanner.nextLine();
			return string;
		}
	

	public static int scanInt() {
Scanner scanner = new Scanner(System.in);
			int i = scanner.nextInt();
			return i;
		
	}

	public static float scanFloat() {
		Scanner scanner = new Scanner(System.in);
			float f = scanner.nextFloat();
			return f;
		}
	

	public static int scanFamily() {
		int is_married;
		Scanner scanner = new Scanner(System.in);
			String fam = scanner.nextLine();

			if (fam.compareTo("married") == 0)
				is_married = 1;
			else
				is_married = 0;
		
		return is_married;
	}
	
	
	@SuppressWarnings("deprecation")
	private static Date scanDate() {
		Date date = null;
		String str = null;
		Scanner scanner = new Scanner(System.in);
		
		str = scanner.nextLine();
		
		date = new Date(str);
		
		System.out.println(date);
		
		return date;
	}

	public static void hireEmployee(int i) throws SQLException {
		if (i == 1) {
			System.out.println(
					"Parakalw plhktrologiste ta akoloutha: onoma, oik. katastash(married / single) , arithmos.paidiwn, tmhma , dieuthinsi, kinhto,Iban,trapeza:");
			Employee Mark = new Employee(scanString() , scanFamily(), scanInt(), "mdy",scanString(),curr,scanString(),scanString(),scanString(),scanString());
		} else if (i == 2) {
			System.out.println(
					"Parakalw plhktrologiste ta akoloutha: onoma, oik. katastash(married / single) , arithmos.paidiwn, tmhma , dieuthinsi, kinhto,Iban,trapeza:");
			Employee Mark = new Employee(scanString() , scanFamily(), scanInt(), "mdp",scanString(),curr,scanString(),scanString(),scanString(),scanString());
		} else if (i == 3) {
			System.out.println(
					"Parakalw plhktrologiste ta akoloutha: onoma, oik. katastash(married / single) , arithmos.paidiwn, tmhma , dieuthinsi, kinhto,Iban,trapeza kai mistho:");
			Employee Mark = new Employee(scanString() , scanFamily(), scanInt(), "sdy",scanString(),curr,scanString(),scanString(),scanString(),scanString(),scanFloat());
		} else if (i == 4) {
			System.out.println(
					"Parakalw plhktrologiste ta akoloutha: onoma, oik. katastash(married / single) , arithmos.paidiwn, tmhma , dieuthinsi, kinhto,Iban,trapeza kai mistho:");
			Employee Mark = new Employee(scanString() , scanFamily(), scanInt(), "sdp",scanString(),curr,scanString(),scanString(),scanString(),scanString(),scanFloat());
		}

	}

	public static Employee find_him(int id) throws SQLException, ParseException {
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		ResultSet rs;
		Employee Mark = new Employee();

		rs = st.executeQuery("select * from sys.employees where EID = " + id);

		while (rs.next()) {

			Mark.setName(rs.getString("Name"));
			Mark.setIs_Married(rs.getInt("Is_Married"));
			Mark.setNumber_Of_Children(rs.getInt("Number_Of_Children"));
			Mark.setPosition(rs.getString("Position"));
			Mark.setDepartment(rs.getString("Department"));
			Mark.setAdress(rs.getString("Adress"));
			String date = rs.getString("Drafted");
			Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date);
			Mark.setDrafted(date1);
			Mark.setMobile(rs.getString("Mobile"));
			Mark.setIban(rs.getString("Iban"));
			Mark.setBank(rs.getString("Bank"));
			Mark.setSalary(rs.getFloat("Salary"));
			Mark.setFam_bonus(rs.getFloat("fam_bonus"));
			Mark.setTime_bonus(rs.getFloat("time_bonus"));
			Mark.setFinal_Salary(rs.getFloat("final_salary"));
		}

		return Mark;
	}

	private static void update_employee_field() throws SQLException, ParseException {
		int id, choice, num;
		String value;
		Employee Mark = new Employee();

		System.out.println("------------------------------");
		System.out.println("Plhktrologhste to ID toy ypallhlou pou thelete na kanete thn allagh");

		id = scanInt();

		while (Mark.ask_Database(id, "Name") == null) {
			System.out.println("Employee ID not found, try again or press 0 to exit");
			id = scanInt();
			if (id == 0)
				return;
		}

		System.out.println("------------------------------");
		System.out.println("Ti allagh thelete na kanete?");
		System.out.println("------------------------------");

		System.out.println("1.Allagh oikogeniakhs katastashs");
		System.out.println("2.Allagh arithmou paidiwn");
		System.out.println("3.Allagh tmhmatos");
		System.out.println("4.Allagh dieythinshs");
		System.out.println("5.Allagh arithmou thlefwnou");
		System.out.println("6.Allagh IBAN");
		System.out.println("7.Allagh trapezas");
		choice = scanInt();
		while (true) {

			switch (choice) {

			case 1:
				System.out.println("Eisagete kainouria oikogeniakh katastash");
				num = scanFamily();
				Mark.change_I_From_Database(id, "Is_Married", num);
				return;
			case 2:
				System.out.println("Eisagete neo arithmo paidiwn");
				num = scanInt();
				Mark.change_I_From_Database(id, "Number_Of_Children", num);
				return;
			case 3:
				System.out.println("Eisagete to onoma tou tmhmatos");
				value = scanString();
				Mark.change_S_From_Database(id, "Department", value);
				return;
			case 4:
				System.out.println("Eisagete thn dieuthinsh");
				value = scanString();
				Mark.change_S_From_Database(id, "Adress", value);
				return;
			case 5:
				System.out.println("Eisagete ton arithmo thlefwnoy");
				value = scanString();
				Mark.change_S_From_Database(id, "Mobile", value);
				return;
			case 6:
				System.out.println("Eisagete to IBAN");
				value = scanString();
				Mark.change_S_From_Database(id, "Iban", value);
				return;
			case 7:
				System.out.println("Eisagete to onoma ths trapezas");
				value = scanString();
				Mark.change_S_From_Database(id, "Bank", value);
				return;

			default:
				System.out.println("O arithmos pou plhktrologisate den antistoixei se epilogh prospathiste xana");
				return;
			}
		}

	}

	private static void update_wage_allowance() throws SQLException, ParseException {
		int choice;
		float new_Salary_mdy,new_Salary_mdp,bonus;
		System.out.println("------------------------------");
		System.out.println("Ti allagh thelete na kanete?");
		System.out.println("------------------------------");

		System.out.println("1.Allagh vasikou misthou monimou dioikitikou ypallhlou");
		System.out.println("2.Allagh vasikou misthou monimou didaktikou melous");
		System.out.println("3.Allagh epidomatwn");

		choice = scanInt();
		
		while (true) {
			
			switch (choice) {
			case 1:
				System.out.println("Eisagete ton neo vasiko mistho enos monimou dioikitikou ypallhlou");
				new_Salary_mdy = scanFloat();
				
				if(new_Salary_mdy < Employee.getSalary_mdy()) {
					System.out.println("Den ginetai na kanete meiwseis kathikia ths kuvernhshs");
					return;
				}
				else {
					Employee.setSalary_mdy(new_Salary_mdy);
					Employee.Re_calculate("mdy");
				}		
				
				return;
			case 2:
				System.out.println("Eisagete ton neo vasiko mistho enos monimou didaktikou melous");
				new_Salary_mdp = scanFloat();
				
				if(new_Salary_mdp < Employee.getSalary_mdp()) {
					System.out.println("Den ginetai na kanete meiwseis kathikia ths kuvernhshs");
					return;
				}
				else {
					Employee.setSalary_mdp(new_Salary_mdp);
					Employee.Re_calculate("mdp");
				}	
				return;

			case 3:
				System.out.println("Eisagete to neo vasiko epidoma gamou/paidiou");
				bonus = scanFloat();
				
				if(bonus < Employee.getFam_bonus_multiplier()) {
					System.out.println("Den ginetai na kanete meiwseis kathikia ths kuvernhshs");
					return;
				}
				else {
					Employee.setFam_bonus_multiplier(bonus);
					Employee.Re_calculate("bonus");
				}	
				return;

			default:
				System.out.println("O arithmos pou plhktrologisate den antistoixei se epilogh prospathiste xana");
				break;
			}
		}

	}

	private static void retire_fire_employee() throws SQLException, ParseException {

		int choice = 0, id = 0,day;
		Employee Mark = new Employee();
		Calendar calendar = Calendar.getInstance();

		System.out.println("------------------------------");
		System.out.println("Ti tha thelete na kanete?");
		System.out.println("------------------------------");

		System.out.println("1.Apolysh");
		System.out.println("2.Syntaxiodothsh");

		choice = scanInt();

		while (true) {

			switch (choice) {
			case 1:
				System.out.println("-------------------------------------");
				System.out.println("Parakalw eisagete to ID toy ypallhlou");
				System.out.println("-------------------------------------");
				id = scanInt();

				while (Mark.ask_Database(id, "Name") == null) {
					System.out.println("La8os ypallhlos.Pathste to 0 ean 8elete na kanete exit");
					id = scanInt();
					if (id == 0)
						return;
				}

				Mark = find_him(id);
				calendar.setTime(curr);
				day = calendar.get(Calendar.DAY_OF_MONTH);
				
				if(day != Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("You can only harm at the end of the month");
				}else {
				
				
				
				if ((Mark.getPosition().compareTo("FIRED") != 0) && (Mark.getPosition().compareTo("RETIRED") != 0)) {

					Mark.change_S_From_Database(id, "Position", "FIRED");

				} else if (Mark.getPosition().compareTo("FIRED") == 0) {
					System.out.println("Exei hdh fugei mhn ton plhgwnetai kai allo");
				} else {
					System.out.println("Ti na diwkseis re linatsa exei fugei apo ton kosmo");
				}
				}
				return;
			case 2:
				System.out.println("-------------------------------------");
				System.out.println("Parakalw eisagete to ID toy ypallhlou");
				System.out.println("-------------------------------------");

				id = scanInt();

				while (Mark.ask_Database(id, "Name") == null) {
					System.out.println("Employee ID not found, try again or press 0 to exit");
					id = scanInt();
					if (id == 0)
						return;
				}

				Mark = find_him(id);
				calendar.setTime(curr);
				day = calendar.get(Calendar.DAY_OF_MONTH);
				
				if(day != Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("You can only harm at the end of the month");
				}else {
				
				if ((Mark.getPosition().compareTo("RETIRED") != 0) && (Mark.getPosition().compareTo("FIRED") != 0)) {

					Mark.change_S_From_Database(id, "Position", "RETIRED");

				} else if (Mark.getPosition().compareTo("RETIRED") == 0) {
					System.out.println("Einai poly megalos kartharmata trapezites");
				} else {
					System.out.println("Esas perimene exei fugei kairo twra");
				}
			}
				return;
			default:
				System.out.println("O arithmos pou plhktrologisate den antistoixei se epilogh prospathiste xana");
				return;
			}
		}

	}

	private static void promote_employee() throws SQLException, ParseException {
		int id,day;
		float time_bonus,fam_bonus,final_Salary;
		Employee Mark = new Employee();
		Calendar calendar = Calendar.getInstance();
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Parakalw eisagete to ID toy ypallhlou pou thelete na dwsete proagwgh");
		System.out.println("---------------------------------------------------------------------");

		id = scanInt();

		while (Mark.ask_Database(id, "Name") == null) {
			System.out.println("La8os ypallhlos.Pathste to 0 ean 8elete na kanete exit");
			id = scanInt();
			if (id == 0)
				return;
		}

		Mark = find_him(id);
		calendar.setTime(curr);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		
		if(day != 1) {
		System.out.println("Borei na ginei  proagwgh mono sthn 1 tou mhna enw twra einai:"+ day);	
		}
		else {
				
				if (Mark.getPosition().compareTo("sdy") == 0) {
					
					Mark.setPosition("mdy");
					
					time_bonus = Mark.calculate_time_bonus(Mark);
					fam_bonus = Mark.calculate_fam_bonus(Mark);
					final_Salary= time_bonus + fam_bonus + Employee.getSalary_mdy();
					
					Mark.change_S_From_Database(id, "Position", "mdy");
					Mark.change_F_From_Database(id, "Salary", Employee.getSalary_mdy());
					Mark.change_F_From_Database(id, "fam_bonus", fam_bonus);
					Mark.change_F_From_Database(id, "time_bonus", time_bonus);
					Mark.change_F_From_Database(id, "final_Salary", final_Salary);
					
				}
				else if (Mark.getPosition().compareTo(" sdy") == 0)
				{
										
					Mark.setPosition("mdy");
					
					time_bonus = Mark.calculate_time_bonus(Mark);
					fam_bonus = Mark.calculate_fam_bonus(Mark);
					final_Salary= time_bonus + fam_bonus + Employee.getSalary_mdy();
					
					Mark.change_S_From_Database(id, "Position", "mdy");
					Mark.change_F_From_Database(id, "Salary", Employee.getSalary_mdy());
					Mark.change_F_From_Database(id, "fam_bonus", fam_bonus);
					Mark.change_F_From_Database(id, "time_bonus", time_bonus);
					Mark.change_F_From_Database(id, "final_Salary", final_Salary);
					
				}
				else if (Mark.getPosition().compareTo("sdp") == 0)
				{					Mark.setPosition("mdp");
					
					time_bonus = Mark.calculate_time_bonus(Mark);
					fam_bonus = Mark.calculate_fam_bonus(Mark);
					final_Salary= time_bonus + fam_bonus + Employee.getSalary_mdp();
					
					Mark.change_S_From_Database(id, "Position", "mdp");
					Mark.change_F_From_Database(id, "Salary", Employee.getSalary_mdp());
					Mark.change_F_From_Database(id, "fam_bonus", fam_bonus);
					Mark.change_F_From_Database(id, "time_bonus", time_bonus);
					Mark.change_F_From_Database(id, "final_Salary", final_Salary);;
				}
				else if (Mark.getPosition().compareTo(" sdp") == 0)
				{					Mark.setPosition("mdp");
					
					time_bonus = Mark.calculate_time_bonus(Mark);
					fam_bonus = Mark.calculate_fam_bonus(Mark);
					final_Salary= time_bonus + fam_bonus + Employee.getSalary_mdp();
					
					Mark.change_S_From_Database(id, "Position", "mdp");
					Mark.change_F_From_Database(id, "Salary", Employee.getSalary_mdp());
					Mark.change_F_From_Database(id, "fam_bonus", fam_bonus);
					Mark.change_F_From_Database(id, "time_bonus", time_bonus);
					Mark.change_F_From_Database(id, "final_Salary", final_Salary);;
				}
				else {
					System.out.println("I cannot do that");
				}
		    }
	}

	private static void katavolh_misthodosias() throws SQLException, ParseException {
		int id = 0,day;
		
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(curr);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(day != Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH)) {
			System.out.println("Mporeite na kanete tis plhromes mono thn 1h mera tou mhna");
			return;
		}
	
		Connector connector = new Connector();
		Connection con = connector.create_connection();
		Statement st = con.createStatement();
		Statement st2 = con.createStatement();
		ResultSet rs;
		
		rs = st.executeQuery("SELECT EID FROM sys.employees WHERE Position != 'FIRED' AND Position != 'RETIRED'");
		
		while(rs.next()) {
			Employee Mark = new Employee();
			Mark = find_him(rs.getInt("EID"));
			st2.execute("INSERT INTO sys.payment_records (EID, Payment_date, Amount) VALUES (" +rs.getInt("EID") +", '" +dtf.format(curr) +"', " +Mark.getFinal_Salary() + ");");
		}
		
		
		
	}
	
	
	private static void queries() throws SQLException, ParseException {
		int choice, id = -1;

		System.out.println("------------------------------");
		System.out.println("Ti tha thelete na mathete?");
		System.out.println("------------------------------");

		System.out.println("1.Katastash misthodosias ana kathgoria prosopikou kai gia sygkekrimeno xroniko diasthma");
		System.out.println("2.Megistos, elaxistos kai mesos misthos ana kathgoria proswpikou");
		System.out.println("3.Mesh ayxhsh misthwn kai epidomatwn ana xronikh periodo");
		System.out.println("4.Stoixeia kai misthodosia sygkekrimenoy ypallhlou");
		System.out.println("5.Synoliko ypsos misthodosias ana kathgoria proswpikou");
		
		choice = scanInt();
		
		while (true) {
			
			switch (choice) {
			case 1:
				System.out.println("Peite to xroniko diasthma.Prwta dwste mou thn 1 hmeromhnia(yyyy/mm/dd).");
				String first = scanString();
				System.out.println("Dwste parakalw thn deuterh hmeromhnia(yyyy/mm/dd).");
				String second = scanString();
				
				Payment_Records.show_payment(first,second);
				
				return;
			case 2:
				Payment_Records.stats_per_position("mdy");
				Payment_Records.stats_per_position("mdp");
				Payment_Records.stats_per_position("sdy");
				Payment_Records.stats_per_position("sdp");
				return;
			case 3:
				return;
			case 4:
				System.out.println("please type the id of the employee");
				id = scanInt();
				Employee Mark = find_him(id);
				System.out.println("name: "+Mark.getName());
				System.out.println("isMarried: "+Mark.getIs_Married());
				System.out.println("Number of Children: "+Mark.getNumber_Of_Children());
				System.out.println("Position: "+Mark.getPosition());
				System.out.println("Department: "+Mark.getDepartment());
				System.out.println("Drafted: "+Mark.getDrafted());
				System.out.println("Adress: "+Mark.getAdress());
				System.out.println("Mobile: "+Mark.getMobile());
				System.out.println("IBAN: "+Mark.getIban());
				System.out.println("Bank: "+Mark.getBank());
				System.out.println("Salary: "+Mark.getFinal_Salary());
				return;
			case 5:
				Payment_Records.ypsos_misthodosias("mdy");
				Payment_Records.ypsos_misthodosias("mdp");
				Payment_Records.ypsos_misthodosias("sdy");
				Payment_Records.ypsos_misthodosias("sdp");
				return;
			default:
				System.out.println("O arithmos pou plhktrologisate den antistoixei se epilogh prospathiste xana");
				break;
			}

		}
	}


	public static void main(String[] args) throws SQLException, ParseException {
		int in = 0;
		Initialize();

		System.out.println("---------------------------------------");
		System.out.println("Welcome to data base management system.");
		System.out.println("       Ti thelete na kanete?");

		while (true) {
			System.out.println("--/--/--/--/--/--/--/--/--/--/--/--/--/--/");
			System.out.println("1.Proslhpsh monimou dioikitkou ypallhlou");
			System.out.println("2.Proslhpsh monimou melos didaktikou prosopikou");
			System.out.println("3.Synapsh symvashs neou ypallhlou");
			System.out.println("4.Synapsh symvashs neou didaktikou melous");
			System.out.println("5.Allagh stoixeiwn ypallhlou/didaktikou melous");
			System.out.println("6.Metavolh vasikwn misthwn/epidomatwn");
			System.out.println("7.Apolhsh/syntaxiodothsh monimou ypallhlou");
			System.out.println("8.Proagogh symvasiouxou se monimo");
			System.out.println("9.Katavolh misthodosias");
			System.out.println("10.Database querries");
			System.out.println("11.Exit");

			in = scanInt();

			switch (in) {
			case 1:
				hireEmployee(1);
				break;
			case 2:
				hireEmployee(2);
				break;
			case 3:
				hireEmployee(3);
				break;
			case 4:
				hireEmployee(4);
				break;
			case 5:
				update_employee_field();
				break;
			case 6:
				update_wage_allowance();
				break;
			case 7:
				retire_fire_employee();
				break;
			case 8:
				promote_employee();
				break;
			case 9:
				katavolh_misthodosias();
				break;
			case 10:
				queries();
				break;
			case 11:
				System.out.println("Thanks for playing...");
				return;
			default:
				break;
			}

		}

	}

}
