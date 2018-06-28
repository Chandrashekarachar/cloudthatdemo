package com.guestbook.controllers;

import java.io.File;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3Implementation {

	private AmazonS3 s3;
	private String bucketName="devtestdemo";
	private String RegionName="ap-southeast-1" ;
	
	public  S3Implementation(){
		super();
		s3= new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		Region res = Region.getRegion(Regions.AP_SOUTHEAST_1);
	    s3.setRegion(res);
	    

		
	    if(s3.doesBucketExist(bucketName)){
			 System.out.println("S3 bucket already exits");
		 }
		 else{
			 s3.createBucket(bucketName);
			 System.out.println("S3 bucket created");
		 }

		
		
	}
	
	
	public String  uploadImage(String imagefilename, File imagefile) throws Exception {
		String url;
		int flag=0;
		S3Object object=null;
	
		//Hint: Implement code to put image into S3 bucket
		// Use  of putobject method
		s3.putObject(new PutObjectRequest(bucketName, imagefilename, imagefile)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		object=s3.getObject(new GetObjectRequest(bucketName,imagefilename));
		
		System.out.println(object);
		if(object.equals(null))
		{
			System.out.println("Image is not uploaded into S3 bucket");
			return null;
		}
		else
		{
			
			url = "https://s3-"+RegionName+".amazonaws.com/" + bucketName + "/" + imagefilename;

			return url;
		}
		
		
		
		
	}
	
}
