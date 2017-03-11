package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.ArtworkState;
import fr.polytech.oeuvres.services.ArtworkStateDaoServices;

/**
 * This class represents an artwork state controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class ArtworkStateController {

	/**
	 * The artwork state DAO services.
	 */
	private final ArtworkStateDaoServices artworkStateDaoServices;

	/**
	 * Create an artwork state controller.
	 */
	public ArtworkStateController() {
		this.artworkStateDaoServices = new ArtworkStateDaoServices();
	}

	/**
	 * Overview an artwork state.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/ArtworkStateController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("artworkState", this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/artwork-states/overview");
	}

	/**
	 * List all artwork states.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/ArtworkStateController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("artworkStates", this.artworkStateDaoServices.getAll());

		return new ModelAndView("pages/artwork-states/list");
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
	@RequestMapping(value = "/ArtworkStateController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("pages/artwork-states/add-form");
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
	@RequestMapping(value = "/ArtworkStateController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("artworkState", this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/artwork-states/update-form");
	}

	/**
	 * Insert an artwork state.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/ArtworkStateController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ArtworkState artworkState = new ArtworkState();
		artworkState.setLabel(request.getParameter("label"));

		this.artworkStateDaoServices.insert(artworkState);

		request.setAttribute("message", "The artwork state was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update an artwork state.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/ArtworkStateController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ArtworkState artworkState = this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("id")));
		artworkState.setLabel(request.getParameter("label"));

		this.artworkStateDaoServices.update(artworkState);

		request.setAttribute("message", "The artwork state was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete an artwork state.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/ArtworkStateController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.artworkStateDaoServices.delete(this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		request.setAttribute("message", "The artwork state was successfully deleted!");

		return new ModelAndView("index");
	}
}