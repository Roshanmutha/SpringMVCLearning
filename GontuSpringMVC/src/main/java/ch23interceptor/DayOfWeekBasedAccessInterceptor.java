package ch23interceptor;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DayOfWeekBasedAccessInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("handler.toString() - "+handler.toString());
        System.out.println("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
		Calendar cal = Calendar.getInstance(); 
			
			int dayOfWeek = cal.get(cal.DAY_OF_WEEK);  //getting the day on which request is made
			System.out.println("DayOfWeekBasedAccessInterceptor - preHandle - "+cal.DAY_OF_WEEK);
			if(dayOfWeek == 1) { // 1 means Sunday, 2 means Monday....7 means Saturday
				//response.sendRedirect("/springmvc/redirect");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("redirect.jsp");
//				dispatcher.forward(request, response);
				response.getWriter().write("The Website is closed on Sunday; please try accessing it " + "on any other week day!!");
				
				return false;
			}
			return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		System.out.println("Request URL::" + request.getRequestURL().toString()
                + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
        //we can add attributes in the modelAndView and use that in the view page
    }
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		System.out.println("Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
		System.out.println("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
