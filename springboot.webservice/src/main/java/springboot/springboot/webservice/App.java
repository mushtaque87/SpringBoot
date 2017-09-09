package springboot.springboot.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 *
 */


@SpringBootApplication
public class App 
{
	@RequestMapping("/")
	@ResponseBody
	String home()
	{
		return "Bitch Please!!!";
	}
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(App.class, args);

    }
}
