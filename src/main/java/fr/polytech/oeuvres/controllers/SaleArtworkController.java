package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.SaleArtwork;
import fr.polytech.oeuvres.services.ArtworkStateDaoServices;
import fr.polytech.oeuvres.services.SaleArtworkDaoServices;

/**
 * This class represents a sale artwork controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
public class SaleArtworkController {

	/**
	 * The sale artwork DAO services.
	 */
	private final SaleArtworkDaoServices saleArtworkDaoServices;

	/**
	 * The artwork state DAO services.
	 */
	private final ArtworkStateDaoServices artworkStateDaoServices;

	/**
	 * Create a sale artwork controller.
	 */
	public SaleArtworkController() {
		this.saleArtworkDaoServices = new SaleArtworkDaoServices();
		this.artworkStateDaoServices = new ArtworkStateDaoServices();
	}

	/**
	 * Overview a sale artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/SaleArtworkController/overview", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("saleArtwork", this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		return new ModelAndView("pages/sale-artworks/overview");
	}

	/**
	 * List all sale artworks.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/SaleArtworkController/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("saleArtworks", this.saleArtworkDaoServices.getAll());

		return new ModelAndView("pages/sale-artworks/list");
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
	@RequestMapping(value = "/SaleArtworkController/add-form", method = RequestMethod.POST)
	public ModelAndView addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("artworkStates", this.artworkStateDaoServices.getAll());

		return new ModelAndView("pages/sale-artworks/add-form");
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
	@RequestMapping(value = "/SaleArtworkController/update-form", method = RequestMethod.POST)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("saleArtwork", this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("artworkStates", this.artworkStateDaoServices.getAll());

		return new ModelAndView("pages/sale-artworks/update-form");
	}

	/**
	 * Insert a sale artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/SaleArtworkController/insert", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final SaleArtwork saleArtwork = new SaleArtwork();
		saleArtwork.setTitle(request.getParameter("title"));
		saleArtwork.setState(this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("artworkStateId"))));
		saleArtwork.setPrice(Double.parseDouble(request.getParameter("price")));

		this.saleArtworkDaoServices.insert(saleArtwork);

		request.setAttribute("message", "The sale artwork was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a sale artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/SaleArtworkController/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final SaleArtwork saleArtwork = this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("id")));
		saleArtwork.setTitle(request.getParameter("title"));
		saleArtwork.setState(this.artworkStateDaoServices.get(Integer.parseInt(request.getParameter("artworkStateId"))));
		saleArtwork.setPrice(Double.parseDouble(request.getParameter("price")));

		this.saleArtworkDaoServices.update(saleArtwork);

		request.setAttribute("message", "The sale artwork was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a sale artwork.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/SaleArtworkController/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.saleArtworkDaoServices.delete(this.saleArtworkDaoServices.get(Integer.parseInt(request.getParameter("id"))));

		request.setAttribute("message", "The sale artwork was successfully deleted!");

		return new ModelAndView("index");
	}
}