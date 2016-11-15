package neu.edu.project.dao;

import java.math.BigInteger;
import java.util.Collection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.Donation;
import neu.edu.project.domain.User;

@Repository
public class DonationDAOImpl implements DonationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createDonation(Donation donation) {
		sessionFactory.getCurrentSession().save(donation);
	}

	@Override
	public Collection<Donation> listDonations() {

		return sessionFactory.getCurrentSession().createQuery("from user").list();
	}

	@Override
	public Donation getDonation(long Id) {
		return (Donation) sessionFactory.getCurrentSession().load(Donation.class, Id);
	}

	@Override
	public void updateDonation(Donation donation) {
		sessionFactory.getCurrentSession().merge(donation);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeDonation(long Id) {
		Session session = this.sessionFactory.getCurrentSession();
		Donation donation = (Donation) session.load(Donation.class, Id);
		if (donation != null) {
			session.delete(donation);
		}
	}

	@Override
	public Double getRecSum(long Id) {
		String queryString = "select sum(AMOUNT) from DONATION where Creator_ID = " + Id;
		if (!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()) {
			return (Double) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		} else {
			return 0.0;
		}
	}

	@Override
	public Double getDonSum(long Id) {
		String queryString = "select sum(AMOUNT) from DONATION where Patron_ID = " + Id;
		if (!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()) {
			return (Double) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		} else {
			return 0.0;
		}
	}

	@Override
	public BigInteger getDonNum(long Id) {
		String queryString = "SELECT COUNT(distinct Patron_ID) FROM donation where Creator_ID=" + Id;
		if (!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()) {
			return (BigInteger) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public Collection<User> listPatrons(long CreatorId) {
		String queryString = "select * from user where ID in (select Patron_ID from donation where Creator_ID="
				+ CreatorId + ")";
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).addEntity(User.class).list().isEmpty()){
			return sessionFactory.getCurrentSession().createSQLQuery(queryString).addEntity(User.class).list();
		}else{
			return null;
		}
		
	}

}
