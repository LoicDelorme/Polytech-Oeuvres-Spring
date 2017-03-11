package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.LoanArtwork;
import fr.polytech.oeuvres.services.LoanArtworkDaoServices;

/**
 * This class represents a loan artwork controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class LoanArtworkController {

	/**
	 * The loan artwork DAO services.
	 */
	private final LoanArtworkDaoServices loanArtworkDaoServices;

	/**
	 * Create a loan artwork controller.
	 */
	public LoanArtworkController() {
		this.loanArtworkDaoServices = new LoanArtworkDaoServices();
	}

	/**
	 * Overview a loan artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("loanArtwork", this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/loan-artworks/overview");
	}

	/**
	 * List all loan artworks.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("loanArtworks", this.loanArtworkDaoServices.getAll());

		return new ModelAndView("pages/loan-artworks/list");
	}

	/**
	 * Add form.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("pages/loan-artworks/add-form");
	}

	/**
	 * Update form.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("loanArtwork", this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/loan-artworks/update-form");
	}

	/**
	 * Insert a loan artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final LoanArtwork loanArtwork = new LoanArtwork();
		loanArtwork.setTitle(request.getParameter("title"));
		loanArtwork.setDuration(Integer.parseInt(request.getParameter("duration")));

		this.loanArtworkDaoServices.insert(loanArtwork);

		request.setAttribute("message", "The loan artwork was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a loan artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final LoanArtwork loanArtwork = this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("id")));
		loanArtwork.setTitle(request.getParameter("title"));
		loanArtwork.setDuration(Integer.parseInt(request.getParameter("duration")));

		this.loanArtworkDaoServices.update(loanArtwork);

		request.setAttribute("message", "The loan artwork was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a loan artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanArtworkController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.loanArtworkDaoServices.delete(this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		request.setAttribute("message", "The loan artwork was successfully deleted!");

		return new ModelAndView("index");
	}
}