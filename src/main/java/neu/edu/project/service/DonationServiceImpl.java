package neu.edu.project.service;

import java.math.BigInteger;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import neu.edu.project.dao.DonationDAO;
import neu.edu.project.domain.Donation;
import neu.edu.project.domain.User;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	@Qualifier("donationDAOImpl")
	private DonationDAO donationDAO;

	@Override
	@Transactional
	public void createDonation(Donation donation) {
     donationDAO.createDonation(donation);
	}

	@Override
	@Transactional
	public Collection<Donation> listDonations() {
		return donationDAO.listDonations();
	}

	@Override
	@Transactional
	public Donation getDonation(long Id) {
		return donationDAO.getDonation(Id);
	}

	@Override
	@Transactional
	public void updateDonation(Donation donation) {
        donationDAO.updateDonation(donation);
	}

	@Override
	@Transactional
	public void removeDonation(long Id) {
        donationDAO.removeDonation(Id);
	}

	@Override
	@Transactional
	public Double getRecSum(long Id) {
		return donationDAO.getRecSum(Id);
	}

	@Override
	@Transactional
	public Double getDonSum(long Id) {
		return donationDAO.getDonSum(Id);
	}

	@Override
	@Transactional
	public BigInteger getDonNum(long Id) {
		return donationDAO.getDonNum(Id);
	}

	@Override
	@Transactional
	public Collection<User> listPatrons(long CreatorId) {
		return donationDAO.listPatrons(CreatorId);
	}

}
