package com.guestbook.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import com.guestbook.models.MessageModel;

public class DynamoImplementation {

	private AmazonDynamoDB dbobject;
	private DynamoDB dynamodb;
	private String tableName = "guestbook";

	

	public DynamoImplementation(){
		
		super();
		dbobject =  new AmazonDynamoDBClient(new ClasspathPropertiesFileCredentialsProvider());
		 dynamodb = new DynamoDB(dbobject);
		Region res = Region.getRegion(Regions.AP_SOUTHEAST_1);
		dbobject.setRegion(res);
		
		
		doesTableExist();
		
	}
	
	public void doesTableExist(){
		
		//Implement  Dynamo Db Table creation  code if table doesn't exist
		//If table exist then use the same table.
		
		
        
		
		
		
		
		
	}
	
	public String InsertMessages(MessageModel msgmodel){
		Date date = new Date();
		Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
		
		System.out.println("Record to be inserted is:  "+msgmodel.getSender()+"\t message is : "+msgmodel.getMessage()+"\t  Obj URL :\t" +msgmodel.getObjecturl());
		item1.put("sender", new AttributeValue().withS(msgmodel.getSender()));
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
		item1.put("post-time", new AttributeValue().withS(ft.format(date)));
		
		item1.put("message", new AttributeValue().withS(msgmodel.getMessage()));
		item1.put("objecturl", new AttributeValue().withS(msgmodel.getObjecturl()));

		PutItemRequest putItemRequest1 = new PutItemRequest().withTableName(tableName).withItem(item1);
		dbobject.putItem(putItemRequest1);
		
		return "success";
	}
	
	public List<MessageModel> retriveMessages(){
		List<MessageModel> messages = new ArrayList<MessageModel>();
		 
		  ScanResult DBresult = null;
			String post_time = "0";
			ScanRequest req = new ScanRequest();
			req.setTableName(tableName);
			if(DBresult != null){
	            req.setExclusiveStartKey(DBresult.getLastEvaluatedKey());
	 
	        }  
	               
	        DBresult = dbobject.scan(req);
	        List<Map<String, AttributeValue>> rows = DBresult.getItems();
	        MessageModel msgmodel;
	        AttributeValue sender, message,  objurl;
	        messages.clear();
	        for (Map<String, AttributeValue> map : rows) {
				try {
					msgmodel = new MessageModel();
					
					sender = map.get("sender");
					msgmodel.setSender(sender.getS());
					
					message = map.get("message");
					msgmodel.setMessage(message.getS());
					
					objurl = map.get("objecturl");
					msgmodel.setObjecturl(objurl.getS());
										
					messages.add(msgmodel);
				}
				catch (NumberFormatException e){
	                System.out.println(e.getMessage());
	            }
			}

		

        return messages;
	} 



}
