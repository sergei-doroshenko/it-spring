package org.training.issuetracker.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	@Autowired
	private MessageProvider messageProvider;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("title", messageProvider.getTitle());
		model.addAttribute("message", messageProvider.getHelloMessage());
		return "hello";
	}
	
}
