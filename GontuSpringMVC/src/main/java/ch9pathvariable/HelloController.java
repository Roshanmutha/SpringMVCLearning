package ch9pathvariable;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value="/{countryName}", method = RequestMethod.GET)
	public ModelAndView helloWorld(@PathVariable ("countryName") String countryName) {
		System.out.println("helloWorld");
		//String name = pathVars.get("userName");
//		String country = pathVars.get("countryName");
		System.out.println("country: "+countryName);
		ModelAndView model = new ModelAndView("HelloPage");
		model.addObject("msg","hello "+3+ " You are from "+countryName);

		return model;
	}
	@RequestMapping("/")
	public String hello( Model model) {
		return "HelloPage";
	}
}