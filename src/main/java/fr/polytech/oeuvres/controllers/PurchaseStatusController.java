package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.PurchaseStatus;
import fr.polytech.oeuvres.services.PurchaseStatusDaoServices;

/**
 * This class represents a purchase status controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class PurchaseStatusController {

	/**
	 * The purchase status DAO services.
	 */
	private final PurchaseStatusDaoServices purchaseStatusDaoServices;

	/**
	 * Create a purchase status controller.
	 */
	public PurchaseStatusController() {
		this.purchaseStatusDaoServices = new PurchaseStatusDaoServices();
	}

	/**
	 * Overview a purchase status.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/PurchaseStatusController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("purchaseStatus", this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/purchase-status/overview");
	}

	/**
	 * List all purchase status.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/PurchaseStatusController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("purchaseStatus", this.purchaseStatusDaoServices.getAll());

		return new ModelAndView("pages/purchase-status/list");
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
	@RequestMapping(value = "/PurchaseStatusController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("pages/purchase-status/add-form");
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
	@RequestMapping(value = "/PurchaseStatusController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("purchaseStatus", this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/purchase-status/update-form");
	}

	/**
	 * Insert a purchase status.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/PurchaseStatusController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchaseStatus purchaseStatus = new PurchaseStatus();
		purchaseStatus.setLabel(request.getParameter("label"));

		this.purchaseStatusDaoServices.insert(purchaseStatus);

		request.setAttribute("message", "The purchase status was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a purchase status.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/PurchaseStatusController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchaseStatus purchaseStatus = this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("id")));
		purchaseStatus.setLabel(request.getParameter("label"));

		this.purchaseStatusDaoServices.update(purchaseStatus);

		request.setAttribute("message", "The purchase status was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a purchase status.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/PurchaseStatusController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.purchaseStatusDaoServices.delete(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		request.setAttribute("message", "The purchase status was successfully deleted!");

		return new ModelAndView("index");
	}
}