package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/SaleArtworkController")
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/overview?id=${id}", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		request.setAttribute("saleArtwork", this.saleArtworkDaoServices.get(id));

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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
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
	@RequestMapping(value = "/add-form", method = RequestMethod.GET)
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/update-form?id=${id}", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		request.setAttribute("saleArtwork", this.saleArtworkDaoServices.get(id));
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
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	 * @param id
	 *            The id.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/delete?id=${id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws Exception {
		this.saleArtworkDaoServices.delete(this.saleArtworkDaoServices.get(id));

		request.setAttribute("message", "The sale artwork was successfully deleted!");

		return new ModelAndView("index");
	}
}