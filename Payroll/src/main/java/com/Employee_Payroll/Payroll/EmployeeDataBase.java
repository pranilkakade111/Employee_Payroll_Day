package com.Employee_Payroll.Payroll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDataBase 
{
	static Scanner sc= new Scanner(System.in);
	static boolean exit=false;
	static Connection connection;
	static Statement statement;
	static String jdbcUrl="jdbc:mysql://localhost:3306/employee?useSSL=false";
	static String username="root";
	static String password="root";
	public static Connection getConnection() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			System.out.println("Connecting to database "+jdbcUrl);
			connection=DriverManager.getConnection(jdbcUrl,username,password);
			System.out.println("done!!!");


		}
		catch(ClassNotFoundException | SQLException  e)
		{
			e.printStackTrace();
		}

		return connection;
	}
	public static List<Employee> display_EmployeeDetails() throws SQLException
	{ 


		List<Employee> Employe = new ArrayList<>();
		try 
		{
			connection=getConnection();
			statement= connection.createStatement();
			ResultSet result = statement.executeQuery("select * from employee_system");
			while(result.next())
			{
				Employee e= new Employee();
				e.setId(result.getInt(1));
				e.setName(result.getString(2));
				e.setGender(result.getString(3));
				e.setSalary(result.getDouble(4));
				e.setAdress(result.getString(5));
				e.setPhone_no(result.getString(6));
				e.setDate_join(LocalDate.parse(result.getString(7)));
				Employe.add(e);

			}
			connection.close();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}


		return Employe;
	}

	public static List<Salary> getSalaryDetails() throws SQLException
	{
		List<Salary> salarylist =new ArrayList<>();

		try {
			connection=getConnection();
			statement= connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * from earning");
			while(result.next())
			{
				Salary sal = new Salary();
				sal.setId(result.getInt(1));
				sal.setBasic_Pay(result.getDouble(2));
				sal.setDeduction(result.getDouble(3));
				sal.setTaxPay(result.getDouble(4));
				sal.setTax(result.getDouble(5));
				sal.setE_id(result.getInt(6));
				salarylist.add(sal);
			}
			connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return salarylist;

	}

	public static void main(String[] args)
	{

	}

}
