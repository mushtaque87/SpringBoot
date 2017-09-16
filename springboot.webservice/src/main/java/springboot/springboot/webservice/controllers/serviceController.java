package springboot.springboot.webservice.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;

@Controller

public class serviceController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	    public @ResponseBody String login(@RequestParam(value="name", required=false, defaultValue="user") String name) {
	        return new login().login(name);
	    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public @ResponseBody String logout(@RequestParam(value="name", required=false, defaultValue="user") String name) {
	        return new login().logout(name);
	    }
	
	 
}
