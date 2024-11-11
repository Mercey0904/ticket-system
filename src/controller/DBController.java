package controller;
import view.*;
import model.Transaction;
import model.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBController {
	Statement stmt;
	Statement stmt2;
	String sql = null;
	String sql2 = null;
	ResultSet result = null;
	ResultSet result2 = null;
	public DBController () throws Exception {
		//connect database
		String addr="jdbc:mysql://localhost:3306/assignment2_TangJiaHui";
		String user="root";
		String pword="";
		Connection connect = DriverManager.getConnection(addr, user, pword);
		if (connect !=null)
			System.out.println("successfully connected to the database");
		else
			System.out.println("error connecting to the database");
		//create a statement object
		stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		stmt2 = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}
	//auto generate id
	public int AutoGenerateId () throws Exception {
		int id = 0;
		sql = "select max(transactionid) as maxId from transactionReport";//no spacing
		result = stmt.executeQuery(sql);
		if (result.next()) {
			id = result.getInt("maxId");
			if (id == 0)
				id = 100; //set the initial id
			else
				id ++;
		}
		return id;
	}

	//insert to transactionReport on the database
	public boolean insertTransactionReport(String date, int id, int ticketQty, int totalPrice, int dd, int mm, int yy) throws Exception{

		boolean success = false;
		int row=0;

		sql=String.format("INSERT INTO `transactionReport`(`ticketDate`, `transactionId`, `noTicket`, `totalPrice`, `dd`, `mm`, `yy`) VALUES ('%s','%d','%d','%d','%d','%d','%d')",
				date, id, ticketQty, totalPrice, dd, mm, yy);
		int rowresult = stmt.executeUpdate(sql);

		//step 4 process result
		success = rowresult ==1;

		return success;
	}
	//insert to transaction on the database
	public boolean insertTransaction(String date, int id, String phoneNumber, int totalPrice) throws Exception{

		boolean success = false;
		int row=0;
		//execute a sql statement
		sql2=String.format("INSERT INTO `transaction`(`ticketDate`,`transactionId`,`memberPhoneNo`, `totalPrice`) VALUES ('%s','%d','%s','%d')",
				date,id, phoneNumber,totalPrice);
		row = stmt2.executeUpdate(sql2);
		if (row == 1)
			return true;
		else
			return false;	
	}
	//update transaction and transactionReport on the database
	public boolean updateRecord(String date, int updateId, int dd, int mm, int yy) throws Exception{
		boolean success = false;
		int row=0;
		int row2=0;
		sql = String.format("UPDATE `transactionReport` SET `ticketDate`='%s',`dd`='%d',`mm`='%d',`yy`='%d' WHERE `transactionId` = '%d' ",date, dd, mm, yy, updateId);
		sql2 = String.format("UPDATE `transaction` SET `ticketDate`='%s' WHERE `transactionId` = '%d' ",date, updateId );

		row = stmt.executeUpdate(sql);
		row2 = stmt.executeUpdate(sql2);
		System.out.println(row2);
		if (row == 1 & row2>0 )
			return true;
		else
			return false;	
	}

	//Get member phone number	
	public Member[] getMembers(int id) throws Exception {
		Member mem[] =null;

		ResultSet result = null;
		String phoneNumber="";
		String temp="";
		int row=0;
		String sql ="";
		sql="SELECT `memberPhoneNo` FROM `transaction` WHERE `transactionId`=" +id;
		int count=0, cnt=0;

		result = stmt2.executeQuery(sql);
		if (result.next()) {
			result.last();
			count = result.getRow();
			mem = new Member[count];
			result.beforeFirst();
		} else {
			return null;
		}

		while (result.next()) {
			phoneNumber = result.getString("memberPhoneNo");
			mem[cnt]= new Member("", phoneNumber);
			cnt++;
		}
		return mem;

	}
	//get member on id
	public Member [] getAllMemberOnId(int id) throws Exception {
		Member mem [] = null;
		ResultSet result = null;
		String phoneNum =null;
		int row=0;

		String sql ="SELECT `memberPhoneNo` FROM `transaction` WHERE `transactionId` =" +id;

		result = stmt.executeQuery(sql);
		if (result.next()) {
			result.last();
			row = result.getRow();
			mem = new Member[row];
			result.beforeFirst();
		} 
		row=0;
		while (result.next()) {
			phoneNum = result.getString("memberPhoneNo");
			mem[row] = new Member(null, phoneNum);
			row++;
		}
		return mem;
	}
	//method to retrieve all transactions based on the the given date
	public Transaction[] getAllTransactionFromDate (String date) throws Exception {
		ResultSet result =null;
		Transaction tran[]=null;
		Member member[] = null;
		int id=0, dd=0, mm=0, yy=0, row=0, tempDD=0, tempMM=0, tempYY=0, tickets=0, price=0;
		String date2= " ";
		String sql=null, sDate=null;
		dd=DateProcessing.getDD(date);
		mm=DateProcessing.getMM(date);
		yy=DateProcessing.getYY(date);
		System.out.println(dd);
		System.out.println(mm);
		System.out.println(yy);
		sql = "select * from transactionReport where (yy>" + yy + ")" + 
				" or (yy>= " + yy +" and mm>" + mm + ") or (yy>=" + yy +" and mm>=" + mm + " and dd>=" + dd +")";
		result = stmt2.executeQuery(sql);

		if (result.next()) {
			result.last();
			row = result.getRow();
			tran = new Transaction[row];
			result.beforeFirst();
		}
		row=0;
		while (result.next()) {
			id = result.getInt("transactionId");
			date2 = result.getString("ticketDate");
			tickets = result.getInt("noTicket");
			price = result.getInt("totalPrice");
			member = getAllMemberOnId (id);

			tran[row] = new Transaction(id, date2, tickets, member,price);
			row++;
		}
		return tran;
	}
	//insert member data
	public boolean insertNewMember(String name, String phoneNumber) throws Exception{

		boolean success = false;
		int row=0;
		//execute a sql statement
		sql=String.format("INSERT INTO `memberList`(`memberName`, `memberPhoneNo`) VALUES ('%s','%s')",
				name, phoneNumber);
		int rowresult = stmt.executeUpdate(sql);

		//step 4 process result
		success = rowresult ==1;

		return success;
	}
	//view member details phone number and name
	public String viewMemberDetails(String phoneNumber) throws Exception{
		String temp=" ";
		int row=1;
		//execute a sql statement
		String sql = String.format("SELECT `memberName` FROM `memberList` WHERE memberPhoneNo = '%s' ",phoneNumber);
		ResultSet result = stmt.executeQuery(sql);
		while(result.next()) {
			temp =  result.getString("memberName");
		}
		return temp;
	}
	//view member report based on phone number
	public Transaction[] viewMemberReport(String phoneNumber) throws Exception{

		Transaction[] trans = null;
		ResultSet result =null;
		Member member[] = null;
		int row=0;
		String sql = "SELECT `ticketDate`, `transactionId`, `totalPrice` FROM `transaction` WHERE `memberPhoneNo`= " + phoneNumber;
		result = stmt2.executeQuery(sql);

		if (result.next()) {
			result.last();
			row = result.getRow();
			trans = new Transaction[row];
			result.beforeFirst();
		}
		row=0;
		while (result.next()) {
			int id = result.getInt("transactionId");
			String date = result.getString("ticketDate");
			int price= result.getInt("totalPrice");
			trans[row] = new Transaction(id, date,0, null, price);

			row++;
		}
		return trans;

	}
}


