package fr.polytech.oeuvres.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class represents a home controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * Create a home controller.
	 */
	public HomeController() {
	}

	/**
	 * Root page.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}

	/**
	 * Index page.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}

	/**
	 * Error page.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @param exception
	 *            The exception.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {
		final Writer writer = new StringWriter();
		exception.printStackTrace(new PrintWriter(writer));

		request.setAttribute("message", writer.toString());

		return new ModelAndView("error");
	}
}