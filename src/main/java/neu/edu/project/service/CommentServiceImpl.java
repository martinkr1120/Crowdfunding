package neu.edu.project.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.project.dao.CommentDAO;
import neu.edu.project.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDAO;

	@Override
	@Transactional
	public void createComment(Comment comment) {
        commentDAO.createComment(comment);
	}

	@Override
	@Transactional
	public Collection<Comment> listComments() {
		return commentDAO.listComments();
	}

	@Override
	@Transactional
	public Comment getComment(long Id) {
		return commentDAO.getComment(Id);
	}

	@Override
	@Transactional
	public void updateComment(Comment comment) {
       commentDAO.updateComment(comment);
	}

	@Override
	@Transactional
	public void removeComment(long Id) {
      commentDAO.removeComment(Id);
	}

	@Override
	@Transactional
	public Collection<Comment> listComments(long postId) {
		return commentDAO.listComments(postId);
	}

}
