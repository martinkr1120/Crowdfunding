package neu.edu.project.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import neu.edu.project.domain.Donation;
import neu.edu.project.domain.User;

public interface DonationService {
	public void createDonation(Donation donation);
	public Collection<Donation> listDonations();
	public Donation getDonation(long Id);
	public void updateDonation(Donation post);
	public void removeDonation(long Id);
	public Double getRecSum(long Id);
	public Double getDonSum(long Id);
    public BigInteger getDonNum(long Id);
    public Collection<User> listPatrons(long CreatorId);




}
