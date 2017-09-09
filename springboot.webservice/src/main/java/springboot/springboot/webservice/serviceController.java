package springboot.springboot.webservice;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
