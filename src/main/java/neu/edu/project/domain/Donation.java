package neu.edu.project.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DONATION")
public class Donation {

	@Id
	@Column(name = "DONATION_ID")
	@GeneratedValue
	private long DonaID;
	@Column(name = "AMOUNT")
	private Double Amount;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Creator_ID")
	private User Creator;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Patron_ID")
	private User Patron;

	public long getDonaID() {
		return DonaID;
	}

	public void setDonaID(long donaID) {
		DonaID = donaID;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public User getCreator() {
		return Creator;
	}

	public void setCreator(User creator) {
		Creator = creator;
	}

	public User getPatron() {
		return Patron;
	}

	public void setPatron(User patron) {
		Patron = patron;
	}

}
