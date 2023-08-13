package com.aryan.web.dao;

import com.aryan.web.model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class StudentDao {
	
	
	public Student getStudent(int rollno) {
		System.out.println("Roll : "+rollno);
		String class_name= "org.postgresql.Driver";
		String url="jdbc:postgresql://localhost:5432/aryan";
		String name="postgres";
		String pass="aryan111";
		
		Student student = new Student();

		 Properties dbProperties = new Properties();
		 
	      try {
	    	  String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	    	  String appConfigPath = rootPath+ "/com/aryan/web/dao/db.properties";
	    	  System.out.println(appConfigPath);
	    	  String configFile = "C:\\Coding Software\\Apache Software Foundation\\Tomcat 9.0\\wtpwebapps\\ServletJDBC\\WEB-INF\\classes\\com\\aryan\\web\\dao\\db.properties";
	    	  
	    	  File file = new File("C:\\Coding Software\\Apache Software Foundation\\Tomcat 9.0\\wtpwebapps\\ServletJDBC\\WEB-INF\\classes\\com\\aryan\\web\\dao");
	    	  for(String fileNames : file.list()) System.out.println(fileNames);
	    	  
	    	  dbProperties.load(new FileInputStream(configFile));
	         
	         
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      }
		
		
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(dbProperties.getProperty("db.url"),dbProperties.getProperty("db.name"),dbProperties.getProperty("db.pass"));
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student where rollno = "+rollno);
			
			if(rs.next()) {
				student.setRollno(rs.getInt("rollno"));
				student.setMarks(rs.getInt("marks"));
				student.setName(rs.getString("name"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return student;
	}
	
}
