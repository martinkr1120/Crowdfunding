package neu.edu.project.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.project.dao.PostDAO;
import neu.edu.project.domain.Post;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDAO;

	@Override
	@Transactional
	public void createPost(Post post) {
		postDAO.createPost(post);
	}

	@Override
	@Transactional
	public Collection<Post> listPosts() {
		return postDAO.listPosts();
	}

	@Override
	@Transactional
	public Post getPost(long Id) {
		return postDAO.getPost(Id);
	}

	@Override
	@Transactional
	public void updatePost(Post post) {
		postDAO.updatePost(post);
	}

	@Override
	@Transactional
	public void removePost(long Id) {
        postDAO.removePost(Id);
	}

	@Override
	@Transactional
	public String getRecentPostVid() {
		return postDAO.getRecentPostVid();
	}

	@Override
	@Transactional
	public Collection<Post> listRecentPosts() {
		return postDAO.listRecentPosts();
	}

	@Override
	@Transactional
	public String getRecentTitle() {
		return postDAO.getRecentTitle();
	}

	@Override
	@Transactional
	public Date getRecentDate() {
		return postDAO.getRecentDate();
	}

	@Override
	@Transactional
	public BigInteger getPostCount(long Id) {
		return postDAO.getPostCount(Id);
	}

	@Override
	@Transactional
	public Collection<Post> listMyPosts(long Id) {
		return postDAO.listMyPosts(Id);
	}

}
