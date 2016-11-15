package neu.edu.project.domain;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "POST")
public class Post {
	@Id
	@Column(name = "POST_ID")
	@GeneratedValue
	private Long postId;

	@NotEmpty
	private String title;

	@Column(name="Description", columnDefinition="TEXT")
	private String Description;

	@DateTimeFormat(style = "S-")
	private Date postDate = new Date(new Date().getTime());

	@Transient
	private MultipartFile photo;

	@Lob
	private byte[] photoBytes;

	private String photoContentType;

	private String photoFilename;

	private String videoLink;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	private User user;

	public Long getPostId() {
		return postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;

		setPhotoContentType(photo.getContentType());
		setPhotoFilename(photo.getOriginalFilename());
		try {
			setPhotoBytes(photo.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

}
