package springboot.springboot.webservice.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springboot.springboot.database.ContentValues;
import springboot.springboot.database.DatabaseConnection;
import springboot.springboot.database.DatabaseManager;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
 

@RestController
public class serviceController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
		
	    public @ResponseBody String login(@RequestParam(value="name", required=false, defaultValue="user") String name) {
	        return new login().login(name);
	    }
	
	@RequestMapping(value = "/insertStudent", method = RequestMethod.POST)
	 public @ResponseBody  springboot.springboot.models.Details insertDetails(@RequestBody springboot.springboot.models.Details details) {
		 System.out.println(details.getName());
		 //System.out.println(details.getGender());
//		 	details.setGender("Female");
//		 	details.setName("Fahemid");
//		 	details.setGrade("First Grade");
		 	
		 	DatabaseConnection dbconnect = new DatabaseConnection();
		 	DatabaseManager manager = DatabaseManager.getInstance();
		 	
		 	ContentValues content = new ContentValues();
		 	
		 	content.put("name", details.getName());
		 	content.put("rollno", details.getName());
		 	content.put("grade", details.getGrade());
		 	content.put("rollno", details.getRollno());
		 	
		 	manager.insert("student", content);
	        return details;
		 	
	      
	    }
	
	
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
	 public @ResponseBody springboot.springboot.models.Details deleteDetails(@RequestBody springboot.springboot.models.Details details) {
		 System.out.println(details.getName());
		 //System.out.println(details.getGender());
//		 	details.setGender("Female");
//		 	details.setName("Fahemid");
//		 	details.setGrade("First Grade");
		 	
		 	DatabaseConnection dbconnect = new DatabaseConnection();
		 	DatabaseManager manager = DatabaseManager.getInstance();
		 	
		 	ContentValues content = new ContentValues();
		 	
		 	content.put("name", details.getName());
		 	content.put("rollno", details.getName());
		 	content.put("grade", details.getGrade());
		 	content.put("rollno", details.getRollno());
		 	
		 	manager.insert("student", content);
	        return details;
		 	
	      
	    }
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public @ResponseBody String logout(@RequestParam(value="name", required=false, defaultValue="user") String name) {
	        return new login().logout(name);
	    }
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public @ResponseBody String profile(@RequestParam(value="name", required=false, defaultValue="user") String name) {
        return new login().profile(name);
    }

	
}
