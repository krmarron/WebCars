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
 * Servlet implementation class createNewLotServlet
 */
@WebServlet("/createNewLotServlet")
public class createNewLotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createNewLotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LotCarHelper lch = new LotCarHelper();
		String lotName = request.getParameter("lotName");
		System.out.println("Lot Name: " + lotName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String buyerName = request.getParameter("buyerName");
		LocalDate ld;
		
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		String[] selectedCars = request.getParameterValues("allCarsToAdd");
		List<LotCar> selectedCarsInLot = new ArrayList<LotCar>();
		
		if(selectedCars != null && selectedCars.length > 0) {
			for(int i = 0; i < selectedCars.length; i++) {
				System.out.println(selectedCars[i]);
				LotCar c = lch.searchForCarById(Integer.parseInt(selectedCars[i]));
				selectedCarsInLot.add(c);
			}
		}
		Buyer buyer = new Buyer(buyerName);
		LotDetails sld = new LotDetails(lotName, ld, buyer);
		sld.setLotOfCars(selectedCarsInLot);
		LotDetailsHelper slh = new LotDetailsHelper();
		slh.insertNewLotDetails(sld);
		
		System.out.println("Success!");
		System.out.println(sld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllLotsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
