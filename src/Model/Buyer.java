package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buyer")
public class Buyer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BUYER_ID")
	private int id;
	@Column(name="BUYER_NAME")
	private String buyerName;

	public Buyer() {
		super();
	}
	
	public Buyer(int id, String buyerName) {
		super();
		this.id = id;
		this.buyerName = buyerName;
	}
	
	public Buyer(String buyerName) {
		super();
		this.buyerName = buyerName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
	@Override
	public String toString() {
		return "Buyer [id=" + id + ", buyerName=" + buyerName + "]";
	}
}
