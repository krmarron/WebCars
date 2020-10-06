package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Buyer;
import Model.LotCar;
import Model.LotDetails;

/**
 * Servlet implementation class editLotDetailsServlet
 */
@WebServlet("/editLotDetailsServlet")
public class editLotDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editLotDetailsServlet() {
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
		LotCarHelper lch = new LotCarHelper();
		BuyerHelper bh = new BuyerHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		LotDetails lotToUpdate = dao.searchForLotDetailsById(tempId);
		
		String newLotName = request.getParameter("lotName");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String buyerName = request.getParameter("buyerName");
		
		Buyer newBuyer = bh.findBuyer(buyerName);
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
			
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		try {
			String[] selectedCars = request.getParameterValues("allCarsToAdd");
			List<LotCar> selectedCarsInLot = new ArrayList<LotCar>();
			
			for (int i = 0; i < selectedCars.length; i++) {
				System.out.println(selectedCars[i]);
				LotCar c = lch.searchForCarById(Integer.parseInt(selectedCars[i]));
				selectedCarsInLot.add(c);
			}
			lotToUpdate.setLotOfCars(selectedCarsInLot);
		} catch(NullPointerException ex) {
			List<LotCar> selectedCarsInLot = new ArrayList<LotCar>();
			lotToUpdate.setLotOfCars(selectedCarsInLot);
		}
		
		lotToUpdate.setLotName(newLotName);
		lotToUpdate.setTripDate(ld);
		lotToUpdate.setBuyer(newBuyer);
		
		dao.updateLot(lotToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllLotsServlet").forward(request, response);
	}

}
