package neu.edu.project.service;

import java.util.Collection;

import neu.edu.project.domain.Comment;

public interface CommentService {

	public void createComment(Comment comment);
	public Collection<Comment> listComments();
	public Comment getComment(long Id);
	public void updateComment(Comment comment);
	public void removeComment(long Id);
	public Collection<Comment> listComments(long postId);

}
