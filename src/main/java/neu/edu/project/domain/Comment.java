package neu.edu.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="COMMENT")
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long Id;
	
	@Column(name="CONTENT",columnDefinition="TEXT")
	private String Content; 
	
	@DateTimeFormat(style = "S-")
	private Date CommentDate = new Date(new Date().getTime());
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_ID")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Post_ID")
	private Post post;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Date getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(Date commentDate) {
		CommentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	

}
