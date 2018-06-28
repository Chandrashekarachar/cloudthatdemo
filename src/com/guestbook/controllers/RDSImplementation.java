package com.guestbook.controllers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.rds.AmazonRDS;
import com.guestbook.models.MessageModel;

public class RDSImplementation {
	
private AmazonRDS rds;
	
	private Connection con=null;
	private Statement stmt = null;
	private ResultSet rs;
	String tablename;
	
	//Please Enter the following details
		private String endpoint = "<ENTER_YOUR_RDS_INSTANCE'S_END_POINT >";
		private String username="<ENTER_YOUR_DB_USERNAME>";
		private String password="<ENTER_YOUR_DB_PASSWORD>";
		private String database="<ENTER_YOUR_DATABASE_NAME>";
		
	
	int flag = 0 ;
	String url= "jdbc:mysql://"+endpoint+"/"+database;
	
	public  RDSImplementation() {
		super();
		
		//Hint: Implement Code for creation of  Guestbook table inside RDS Instance.
		
	}
	
	
	public String InsertMessages(MessageModel msgmodel){
		String sender = msgmodel.getSender();
		String msg = msgmodel.getMessage();
		String objurl = msgmodel.getObjecturl();
		System.out.println(" Record to be inserted is: "+sender+"\t message is : "+msg+"\t  Obj URL :\t" +objurl+"\n Please Implement the code in RDSImplementation.InserMessages method.");
		
		//Hint: Implement code for inserting data into the Guestbook table
		
		return "success";
	}
	
	public List<MessageModel> retriveMessages(){
		List<MessageModel> messages = new ArrayList<MessageModel>();
		 
		String sender=null,message=null,objurl=null,posttime=null;
	
		//Hint:Implement to get the data from the Guestbook Table

        return messages;
	} 
}
