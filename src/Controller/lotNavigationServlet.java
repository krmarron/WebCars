package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.LotDetails;

/**
 * Servlet implementation class lotNavigationServlet
 */
@WebServlet("/lotnavigationServlet")
public class lotNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lotNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LotDetailsHelper dao = new LotDetailsHelper();
		String act = request.getParameter("doThisToLot");
		
		if(act == null) {
			getServletContext().getRequestDispatcher("/viewAllLotsServlet").forward(request, response);
			
		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				LotDetails lotToDelete = dao.searchForLotDetailsById(tempId);
				dao.deleteLot(lotToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllLotsServlet").forward(request, response);
			}
		} else if(act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				LotDetails lotToEdit = dao.searchForLotDetailsById(tempId);
				
				request.setAttribute("lotToEdit", lotToEdit);
				
				request.setAttribute("month", lotToEdit.getTripDate().getMonthValue());
				request.setAttribute("date", lotToEdit.getTripDate().getDayOfMonth());
				request.setAttribute("year", lotToEdit.getTripDate().getYear());
				
				LotCarHelper daoForCars = new LotCarHelper();
				
				request.setAttribute("allCars", daoForCars.showAllCars());
				
				if(daoForCars.showAllCars().isEmpty()) {
					request.setAttribute("allCars", " ");
					
				}
				getServletContext().getRequestDispatcher("/edit-lot.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllLotsServlet").forward(request, response);
			}
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/new-lot.jsp").forward(request, response);
		}
	}

}
