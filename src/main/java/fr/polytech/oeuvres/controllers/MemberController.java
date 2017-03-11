package fr.polytech.oeuvres.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.Member;
import fr.polytech.oeuvres.services.MemberDaoServices;

/**
 * This class represents a member controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
@RequestMapping("/MemberController")
public class MemberController {

	/**
	 * The member DAO services.
	 */
	private final MemberDaoServices memberDaoServices;

	/**
	 * Create a member controller.
	 */
	public MemberController() {
		this.memberDaoServices = new MemberDaoServices();
	}

	/**
	 * Overview a member.
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
		request.setAttribute("member", this.memberDaoServices.get(id));

		return new ModelAndView("pages/members/overview");
	}

	/**
	 * List all members.
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
		request.setAttribute("members", this.memberDaoServices.getAll());

		return new ModelAndView("pages/members/list");
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
		return new ModelAndView("pages/members/add-form");
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
		request.setAttribute("member", this.memberDaoServices.get(id));

		return new ModelAndView("pages/members/update-form");
	}

	/**
	 * Insert a member.
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
		final Member member = new Member();
		member.setLastname(request.getParameter("lastname"));
		member.setFirstname(request.getParameter("firstname"));
		member.setAddress(request.getParameter("address"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setCity(request.getParameter("city"));

		this.memberDaoServices.insert(member);

		request.setAttribute("message", "The member was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a member.
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
		final Member member = this.memberDaoServices.get(Integer.parseInt(request.getParameter("id")));
		member.setLastname(request.getParameter("lastname"));
		member.setFirstname(request.getParameter("firstname"));
		member.setAddress(request.getParameter("address"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setCity(request.getParameter("city"));

		this.memberDaoServices.update(member);

		request.setAttribute("message", "The member was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a member.
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
		this.memberDaoServices.delete(this.memberDaoServices.get(id));

		request.setAttribute("message", "The member was successfully deleted!");

		return new ModelAndView("index");
	}
}