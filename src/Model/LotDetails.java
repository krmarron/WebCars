package Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lot_details")
public class LotDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LOT_ID")
	private int id;
	@Column(name="LOT_NAME")
	private String lotName;
	@Column(name="TRIP_DATE")
	private LocalDate tripDate;
	@ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn(name="BUYER_ID")
	private Buyer buyer;
	@OneToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
			@JoinTable(name="CARS_ON_LOT", joinColumns= {@JoinColumn(name="LOT_ID", referencedColumnName="LOT_ID")},
			inverseJoinColumns= {@JoinColumn(name="CAR_ID",referencedColumnName="ID", unique=true)})
	private List<LotCar> lotOfCars;
	
	public LotDetails() {
		super();
	}
	
	public LotDetails(int id, String lotName, LocalDate tripDate, Buyer buyer, List<LotCar> lotOfCars) {
		super();
		this.id = id;
		this.lotName = lotName;
		this.tripDate = tripDate;
		this.buyer = buyer;
		this.lotOfCars = lotOfCars;
	}
	
	public LotDetails(String lotName, LocalDate tripDate, Buyer buyer, List<LotCar> lotOfCars) {
		super();
		this.lotName = lotName;
		this.tripDate = tripDate;
		this.buyer = buyer;
		this.lotOfCars = lotOfCars;
	}
	
	public LotDetails(String lotName, LocalDate tripDate, Buyer buyer) {
		super();
		this.lotName = lotName;
		this.tripDate = tripDate;
		this.buyer = buyer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public LocalDate getTripDate() {
		return tripDate;
	}
	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public List<LotCar> getLotOfCars() {
		return lotOfCars;
	}
	public void setLotOfCars(List<LotCar> lotOfCars) {
		this.lotOfCars = lotOfCars;
	}
	@Override
	public String toString() {
		return "LotDetails [id=" + id + ", lotName=" + lotName + ", tripDate=" + tripDate + ", buyer=" + buyer
				+ ", lotOfCars=" + lotOfCars + "]";
	}
	
	
}
