package com.in11minutes.springboot.myfirstwebapplication.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController 
{
	
	//http://localhost:8080/say-hello
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayhello()
	{
		return "Hello what are you learning Today!";
	}

	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayhelloHtml()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>MY first HTML page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("MY First HTML Page With Body");
		sb.append("</body>");
		sb.append("<html>");
		return sb.toString();
	}
	
	///src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	@RequestMapping("say-hello-jsp")
	public String sayhelloJsp()
	{
		return "sayHello";
	}

}
