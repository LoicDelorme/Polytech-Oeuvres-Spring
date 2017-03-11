package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.Owner;
import fr.polytech.oeuvres.services.OwnerDaoServices;

/**
 * This class represents an owner controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class OwnerController {

	/**
	 * The owner DAO services.
	 */
	private final OwnerDaoServices ownerDaoServices;

	/**
	 * Create an owner controller.
	 */
	public OwnerController() {
		this.ownerDaoServices = new OwnerDaoServices();
	}

	/**
	 * Overview a owner.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/OwnerController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("owner", this.ownerDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/owners/overview");
	}

	/**
	 * List all owners.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/OwnerController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("owners", this.ownerDaoServices.getAll());

		return new ModelAndView("pages/owners/list");
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
	@RequestMapping(value = "/OwnerController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("pages/owners/add-form");
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
	@RequestMapping(value = "/OwnerController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("owner", this.ownerDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/owners/update-form");
	}

	/**
	 * Insert a owner.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/OwnerController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Owner owner = new Owner();
		owner.setLastname(request.getParameter("lastname"));
		owner.setFirstname(request.getParameter("firstname"));

		this.ownerDaoServices.insert(owner);

		request.setAttribute("message", "The owner was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a owner.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/OwnerController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Owner owner = this.ownerDaoServices.get(Integer.parseInt(request.getParameter("id")));
		owner.setLastname(request.getParameter("lastname"));
		owner.setFirstname(request.getParameter("firstname"));

		this.ownerDaoServices.update(owner);

		request.setAttribute("message", "The owner was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a owner.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/OwnerController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.ownerDaoServices.delete(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		request.setAttribute("message", "The owner was successfully deleted!");

		return new ModelAndView("index");
	}
}