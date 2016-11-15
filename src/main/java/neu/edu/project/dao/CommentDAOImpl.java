package neu.edu.project.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.Comment;
@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);		
	}

	@Override
	public Collection<Comment> listComments() {
		if(!sessionFactory.getCurrentSession().createQuery("from comment").list().isEmpty()){
		return sessionFactory.getCurrentSession().createQuery("from comment").list();
		}else{
			return null;
		}
	}

	@Override
	public Comment getComment(long Id) {
		return (Comment)sessionFactory.getCurrentSession().load(Comment.class, Id);
	}

	@Override
	public void updateComment(Comment comment) {
		sessionFactory.getCurrentSession().merge(comment);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public void removeComment(long Id) {
		Session session = this.sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.load(Comment.class, Id);
		if (comment != null) {
			session.delete(comment);
		}		
	}

	@Override
	public Collection<Comment> listComments(long postId) {
		String queryString = "SELECT * FROM Patreon.COMMENT where post_id ="+postId;
		if(!sessionFactory.getCurrentSession().createSQLQuery(queryString).addEntity(Comment.class).list().isEmpty()){
		return sessionFactory.getCurrentSession().createSQLQuery(queryString).addEntity(Comment.class).list();
		}else{
			return null;
		}
	
	}
}