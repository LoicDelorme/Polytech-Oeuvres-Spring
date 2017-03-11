package fr.polytech.oeuvres.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.polytech.oeuvres.entities.Loan;
import fr.polytech.oeuvres.entities.pks.LoanPK;
import fr.polytech.oeuvres.services.LoanArtworkDaoServices;
import fr.polytech.oeuvres.services.LoanDaoServices;
import fr.polytech.oeuvres.services.MemberDaoServices;

/**
 * This class represents a loan controller.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
@Controller
@RequestMapping("/LoanController")
public class LoanController {

	/**
	 * The loan DAO services.
	 */
	private final LoanDaoServices loanDaoServices;

	/**
	 * The member DAO services.
	 */
	private final MemberDaoServices memberDaoServices;

	/**
	 * The loan artwork DAO services.
	 */
	private final LoanArtworkDaoServices loanArtworkDaoServices;

	/**
	 * Create a loan controller.
	 */
	public LoanController() {
		this.loanDaoServices = new LoanDaoServices();
		this.memberDaoServices = new MemberDaoServices();
		this.loanArtworkDaoServices = new LoanArtworkDaoServices();
	}

	/**
	 * Overview a loan.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @param memberId
	 *            The member id.
	 * @param loanArtworkId
	 *            The loan artwork id.
	 * @param loanDate
	 *            The loan date.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/overview?memberId=${memberId}&loanArtworkId=${loanArtworkId}&loanDate=${loanDate}", method = RequestMethod.GET)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "memberId") int memberId, @RequestParam(value = "loanArtworkId") int loanArtworkId, @RequestParam(value = "loanDate") String loanDate) throws Exception {
		final LoanPK loanPK = new LoanPK();
		loanPK.setMember(this.memberDaoServices.get(memberId));
		loanPK.setLoanArtwork(this.loanArtworkDaoServices.get(loanArtworkId));
		loanPK.setDate(LocalDate.parse(loanDate));

		request.setAttribute("loan", this.loanDaoServices.get(loanPK));

		return new ModelAndView("pages/loans/overview");
	}

	/**
	 * List all loans.
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
		request.setAttribute("loans", this.loanDaoServices.getAll());

		return new ModelAndView("pages/loans/list");
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
		request.setAttribute("members", this.memberDaoServices.getAll());
		request.setAttribute("loanArtworks", this.loanArtworkDaoServices.getAll());

		return new ModelAndView("pages/loans/add-form");
	}

	/**
	 * Update form.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @param memberId
	 *            The member id.
	 * @param loanArtworkId
	 *            The loan artwork id.
	 * @param loanDate
	 *            The loan date.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/update-form?memberId=${memberId}&loanArtworkId=${loanArtworkId}&loanDate=${loanDate}", method = RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "memberId") int memberId, @RequestParam(value = "loanArtworkId") int loanArtworkId, @RequestParam(value = "loanDate") String loanDate) throws Exception {
		final LoanPK loanPK = new LoanPK();
		loanPK.setMember(this.memberDaoServices.get(memberId));
		loanPK.setLoanArtwork(this.loanArtworkDaoServices.get(loanArtworkId));
		loanPK.setDate(LocalDate.parse(loanDate));

		request.setAttribute("loan", this.loanDaoServices.get(loanPK));

		return new ModelAndView("pages/loans/update-form");
	}

	/**
	 * Insert a loan.
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
		final Loan loan = new Loan();
		loan.setMember(this.memberDaoServices.get(Integer.parseInt(request.getParameter("memberId"))));
		loan.setLoanArtwork(this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("loanArtworkId"))));
		loan.setDate(LocalDate.parse(request.getParameter("loanDate")));
		loan.setDuration(Integer.parseInt(request.getParameter("duration")));

		this.loanDaoServices.insert(loan);

		request.setAttribute("message", "The loan was successfully added!");

		return new ModelAndView("index");
	}

	/**
	 * Update a loan.
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
		final LoanPK loanPK = new LoanPK();
		loanPK.setMember(this.memberDaoServices.get(Integer.parseInt(request.getParameter("memberId"))));
		loanPK.setLoanArtwork(this.loanArtworkDaoServices.get(Integer.parseInt(request.getParameter("loanArtworkId"))));
		loanPK.setDate(LocalDate.parse(request.getParameter("loanDate")));

		final Loan loan = this.loanDaoServices.get(loanPK);
		loan.setDuration(Integer.parseInt(request.getParameter("duration")));

		this.loanDaoServices.update(loan);

		request.setAttribute("message", "The loan was successfully updated!");

		return new ModelAndView("index");
	}

	/**
	 * Delete a loan.
	 * 
	 * @param request
	 *            The request.
	 * @param response
	 *            The response.
	 * @param memberId
	 *            The member id.
	 * @param loanArtworkId
	 *            The loan artwork id.
	 * @param loanDate
	 *            The loan date.
	 * @return The corresponding JSP page.
	 * @throws Exception
	 *             If an error occurs.
	 */
	@RequestMapping(value = "/delete?memberId=${memberId}&loanArtworkId=${loanArtworkId}&loanDate=${loanDate}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "memberId") int memberId, @RequestParam(value = "loanArtworkId") int loanArtworkId, @RequestParam(value = "loanDate") String loanDate) throws Exception {
		final LoanPK loanPK = new LoanPK();
		loanPK.setMember(this.memberDaoServices.get(memberId));
		loanPK.setLoanArtwork(this.loanArtworkDaoServices.get(loanArtworkId));
		loanPK.setDate(LocalDate.parse(loanDate));

		this.loanDaoServices.delete(this.loanDaoServices.get(loanPK));

		request.setAttribute("message", "The loan was successfully deleted!");

		return new ModelAndView("index");
	}
}