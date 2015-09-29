package ch23interceptor;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentAdmissionController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		//binder.setDisallowedFields(new String[] {"studentMobile"});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy****MM****dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, "studentName", new StudentNameEditor());
	}

	@RequestMapping(value="/admissionForm23.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		System.out.println("admissionForm23.html");
		ModelAndView model1 = new ModelAndView("AdmissionForm23");
		return model1;
	}
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("ch23 home.html");
		ModelAndView model1 = new ModelAndView("home");
		return model1;
	}
	@RequestMapping(value="/redirect", method = RequestMethod.GET)
	public ModelAndView redirect() {
		System.out.println("redirect.jsp");
		ModelAndView model1 = new ModelAndView("redirect");
		return model1;
	}
	@ModelAttribute
    public void addingCommonObjects(Model model1) {
		
		model1.addAttribute("headerMessage", "Gontu College of Engineering, India");
	}

	@RequestMapping(value="/submitAdmissionForm23.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student1") Student student, BindingResult result) {
		System.out.println("chapter 23");
		 if (result.hasErrors()) {
			 	
				ModelAndView model1 = new ModelAndView("AdmissionForm23");
				return model1;
		 }

		ModelAndView model1 = new ModelAndView("AdmissionSuccess23");
		return model1;
	}
	
}

