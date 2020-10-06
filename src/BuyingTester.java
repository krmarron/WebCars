import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Controller.BuyerHelper;
import Controller.LotDetailsHelper;
import Model.Buyer;
import Model.LotCar;
import Model.LotDetails;

public class BuyingTester {
	public static void main(String[] args) {
		Buyer lily = new Buyer("Lily");
		
		LotDetailsHelper ldh = new LotDetailsHelper();
		
		LotCar car1 = new LotCar("Volkswagen", "Beetle", "2005");
		LotCar car2 = new LotCar("Honda", "Civic", "2014");
		
		List<LotCar> lilysCars = new ArrayList<LotCar>();
		lilysCars.add(car1);
		lilysCars.add(car2);
		
		LotDetails lilysLot = new LotDetails("Lily's Lot", LocalDate.now(), lily);
		lilysLot.setLotOfCars(lilysCars);
		
		ldh.insertNewLotDetails(lilysLot);
		
		List<LotDetails> allLots = ldh.getLists();
		for(LotDetails a: allLots) {
			System.out.println(a.toString());
		}
	}
}
