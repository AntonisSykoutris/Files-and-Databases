rs = st.executeQuery("select * from sys.employees where EID = " + id);

ResultSet rs = st.executeQuery("SELECT EID FROM sys.employees WHERE name = '"+emp.getName() +"' AND Iban = '"+ emp.getIban() +"';");
st.execute("INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('" +Mark.getName() +"', " +Mark.getIs_Married()+", "+Mark.getNumber_Of_Children()+",'"+Mark.getPosition()+"','"+Mark.getDepartment()+
"', '"+dtf.format(Mark.getDrafted())+"', '"+Mark.getAdress()+"','"+Mark.getMobile()+"','"+Mark.getIban()+"','"+Mark.getBank()+"',"+Mark.Salary+", "+ Mark.fam_bonus + "," + Mark.time_bonus + "," + Mark.Final_Salary + ")");

st.execute("UPDATE sys.employees SET "+col +" = '"+ value +  "' where EID = " + id);

st.execute("UPDATE sys.employees SET "+col +" = '"+ value +  "' where EID = " + id);

st.execute("UPDATE sys.employees SET "+col +" = "+ value +  "," + "Salary = " +  Mark.getSalary() + ", fam_bonus = " + Mark.getFam_bonus() + ", time_bonus = " + Mark.getTime_bonus() +", final_salary = " + sal  +  " where EID = " + id);

st.execute("UPDATE sys.employees SET " + "Salary = " +  Mark.getSalary() + ", fam_bonus = " + Mark.getFam_bonus() + ", time_bonus = " + Mark.getTime_bonus() +", final_salary = " + sal  +  " where EID = " + id);

rs = st.executeQuery("SELECT * FROM sys.employees where Position = '" + str +"'");

rs = st.executeQuery("SELECT * FROM sys.employees where Position = '" + str +"'");

rs = st.executeQuery("SELECT * FROM sys.employees where Is_Married = 1");


