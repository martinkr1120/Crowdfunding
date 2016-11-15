package neu.edu.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.Post;
@Repository
public class PostDAOImp implements PostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createPost(Post post) {
		sessionFactory.getCurrentSession().save(post);
	}

	@Override
	public Collection<Post> listPosts() {
		return sessionFactory.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public Post getPost(long Id) {
		return (Post)sessionFactory.getCurrentSession().load(Post.class, Id);

	}

	@Override
	public void updatePost(Post post) {
		sessionFactory.getCurrentSession().merge(post);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removePost(long Id) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(Id);
        Post post=(Post)session.load(Post.class, Id);
        System.out.println(post);
        if(post!=null){
        	session.delete(post);
        	session.flush();
        }

	}

	@Override
	public String getRecentPostVid() {
		String queryString = "SELECT videoLink FROM post WHERE post_id=(SELECT max(post_id) FROM post)";
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()){
		return (String) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		}else{
			return "";
		}
	}

	@Override
	public Collection<Post> listRecentPosts() {
		return sessionFactory.getCurrentSession().createQuery("from Post p Order by p.postId DESC").setMaxResults(10).list();
	}

	@Override
	public String getRecentTitle() {
		String queryString = "SELECT title FROM post WHERE post_id=(SELECT max(post_id) FROM post)";
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()){
		return (String) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		}else{
			return "No update";
		}
	}

	@Override
	public Date getRecentDate() {
		String queryString = "SELECT  postDate FROM post WHERE post_id=(SELECT max(post_id) FROM post)";
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()){
		return (Date) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public BigInteger getPostCount(long Id) {
		String queryString = "SELECT COUNT(*) FROM post where ID ="+Id;
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).list().isEmpty()){
		return (BigInteger) sessionFactory.getCurrentSession().createSQLQuery(queryString).list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public Collection<Post> listMyPosts(long Id) {
		String queryString = "from Post p where p.user.id="+Id;
		if(!sessionFactory.getCurrentSession().createQuery(queryString).list().isEmpty()){
		return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}else{
			return null;
		}
	}



	

}
