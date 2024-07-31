package com.in28minutes.rest.webservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource=messageSource;
	}
	@GetMapping("hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
	}
	/*Method-1
	 @RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloWorld() {
		return "helloworld";
	}*/
	@GetMapping(path="/hello-world")
	public String helloworld() {
		return "Hello World";
	}
	//Returning Bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello Ramesh");
	}
	//Path Variable
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean("Hello "+name);
	}
	

}
