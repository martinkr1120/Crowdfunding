package neu.edu.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import neu.edu.project.domain.Post;


public interface PostDAO {
	public void createPost(Post post);
	public Collection<Post> listPosts();
	public Post getPost(long Id);
	public void updatePost(Post post);
	public void removePost(long Id);
	public String getRecentPostVid();
	public Collection<Post> listRecentPosts();
	public String getRecentTitle();
	public Date getRecentDate();
	public BigInteger getPostCount(long Id);
	public Collection<Post> listMyPosts(long Id);

}
