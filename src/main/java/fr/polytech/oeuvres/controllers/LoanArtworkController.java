package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/LoanArtworkController")
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		request.setAttribute("loanArtwork", this.loanArtworkDaoServices.get(id));

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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
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
	@RequestMapping(value = "/add-form", method = RequestMethod.GET)
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/update-form", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		request.setAttribute("loanArtwork", this.loanArtworkDaoServices.get(id));

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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		this.loanArtworkDaoServices.delete(this.loanArtworkDaoServices.get(id));

		request.setAttribute("message", "The loan artwork was successfully deleted!");

		return new ModelAndView("index");
	}
}