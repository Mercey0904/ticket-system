//validation compares back to the database
/*public class Validation {

}*/
package controller;
import view.*;
import view.controller.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Validation {
	Statement stmt;
	String sql = null;
	ResultSet result = null;

	String phoneNumber;
	public Validation () throws Exception {
		//connect database
		String addr="jdbc:mysql://localhost:3306/assignment2_TangJiaHui";
		String user="root";
		String pword="";
		Connection connect = DriverManager.getConnection(addr, user, pword);
		if (connect !=null)
			System.out.println("successfully connected to the validation database");
		else
			System.out.println("error connecting to the validation database");
		//create a statement object
		stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}
	//check if member only bought one ticket
	public boolean isOneTicket(int id, String phoneNumber) throws Exception{
		sql = String.format("SELECT `transactionId`, `memberPhoneNo` FROM `transaction` WHERE transactionId = %d AND memberPhoneNo = '%s'",id,phoneNumber);
		result = stmt.executeQuery(sql);
		if (result.next()) 
			return false;
		return true;
	}
	//check if id exist
	public boolean isExistId(int updateId) throws Exception{
		sql = "select * from transactionReport where transactionId=" + updateId;
		result = stmt.executeQuery(sql);
		if (result.next()) 
			return true;
		return false;
	}
	//check if phone number exist
	public boolean isPhoneNumberExist(String phoneNumber) throws Exception{
		sql = "select * from memberList where memberPhoneNo=" + phoneNumber;
		result = stmt.executeQuery(sql);
		if (result.next()) 
			return false;
		return true;
	}
	// check if date exist
	public boolean isDateExist(String date) throws Exception{
		sql = "select * from transactionReport where ticketDate=" + date;
		result = stmt.executeQuery(sql);
		if (result.next()) 
			return false;
		return true;
	}

}
