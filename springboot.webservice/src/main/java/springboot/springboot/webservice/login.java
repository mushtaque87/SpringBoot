package springboot.springboot.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class login {
	
	public String login(String username)
	{
	if(username.equals("mushtaque"))
	{
		return "Welcome";
	}
	else{
		return "Bhag Bh**dike";
	}
   }
	
	public String logout(String username)
	{
	if(username.equals("mushtaque"))
	{
		return "Bye Mushtaque";
	}
	else{
		return "Bhag Bh**dike";
	}
   }
}
