package fr.polytech.oeuvres.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.Purchase;
import fr.polytech.oeuvres.entities.pks.PurchasePK;
import fr.polytech.oeuvres.services.OwnerDaoServices;
import fr.polytech.oeuvres.services.PurchaseDaoServices;
import fr.polytech.oeuvres.services.PurchaseStatusDaoServices;
import fr.polytech.oeuvres.services.SaleArtworkDaoServices;

/**
 * This class represents a purchase controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class PurchaseController {

	/**
	 * The purchase DAO services.
	 */
	private final PurchaseDaoServices purchaseDaoServices;

	/**
	 * The owner DAO services.
	 */
	private final OwnerDaoServices ownerDaoServices;

	/**
	 * The sale artwork DAO services.
	 */
	private final SaleArtworkDaoServices saleArtworkDaoServices;

	/**
	 * The purchase status DAO services.
	 */
	private final PurchaseStatusDaoServices purchaseStatusDaoServices;

	/**
	 * Create a purchase controller.
	 */
	public PurchaseController() {
		this.purchaseDaoServices = new PurchaseDaoServices();
		this.ownerDaoServices = new OwnerDaoServices();
		this.saleArtworkDaoServices = new SaleArtworkDaoServices();
		this.purchaseStatusDaoServices = new PurchaseStatusDaoServices();
	}

	/**
	 * Overview a purchase.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchasePK purchasePK = new PurchasePK();
		purchasePK.setOwner(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("ownerId"))));
		purchasePK.setSaleArtwork(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("saleArtworkId"))));
		purchasePK.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("purchaseStatusId"))));

		request.setAttribute("purchase", this.purchaseDaoServices.get(purchasePK));

		return new ModelAndView("pages/purchases/overview");
	}

	/**
	 * List all purchases.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("purchases", this.purchaseDaoServices.getAll());

		return new ModelAndView("pages/purchases/list");
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
	@RequestMapping(value = "/LoanController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("owners", this.ownerDaoServices.getAll());
		request.setAttribute("saleArtworks", this.saleArtworkDaoServices.getAll());
		request.setAttribute("purchaseStatus", this.purchaseStatusDaoServices.getAll());

		return new ModelAndView("pages/purchases/add-form");
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
	@RequestMapping(value = "/LoanController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchasePK purchasePK = new PurchasePK();
		purchasePK.setOwner(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("ownerId"))));
		purchasePK.setSaleArtwork(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("saleArtworkId"))));
		purchasePK.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("purchaseStatusId"))));

		request.setAttribute("purchase", this.purchaseDaoServices.get(purchasePK));
		request.setAttribute("purchaseStatus", this.purchaseStatusDaoServices.getAll());

		return new ModelAndView("pages/purchases/update-form");
	}

	/**
	 * Insert a purchase.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Purchase purchase = new Purchase();
		purchase.setOwner(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("ownerId"))));
		purchase.setSaleArtwork(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("saleArtworkId"))));
		purchase.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("purchaseStatusId"))));
		purchase.setDate(LocalDate.parse(request.getParameter("purchaseDate")));

		this.purchaseDaoServices.insert(purchase);

		request.setAttribute("message", "The purchase was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a purchase.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchasePK purchasePK = new PurchasePK();
		purchasePK.setOwner(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("ownerId"))));
		purchasePK.setSaleArtwork(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("saleArtworkId"))));
		purchasePK.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("oldPurchaseStatusId"))));

		final Purchase purchase = this.purchaseDaoServices.get(purchasePK);

		this.purchaseDaoServices.delete(purchase);

		purchase.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("newPurchaseStatusId"))));
		purchase.setDate(LocalDate.parse(request.getParameter("purchaseDate")));

		this.purchaseDaoServices.insert(purchase);

		request.setAttribute("message", "The purchase was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a purchase.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/LoanController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PurchasePK purchasePK = new PurchasePK();
		purchasePK.setOwner(this.ownerDaoServices.get(Integer.parseInt(request.getParameter("ownerId"))));
		purchasePK.setSaleArtwork(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("saleArtworkId"))));
		purchasePK.setStatus(this.purchaseStatusDaoServices.get(Integer.parseInt(request.getParameter("purchaseStatusId"))));

		this.purchaseDaoServices.delete(this.purchaseDaoServices.get(purchasePK));

		request.setAttribute("message", "The purchase was successfully deleted!");

		return new ModelAndView("index");
	}
}