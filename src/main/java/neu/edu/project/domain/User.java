package neu.edu.project.domain;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name = "UserName", unique = true, nullable = false)
	private String userName;

	@NotEmpty
	@Column(name = "Password", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "First_Name", nullable = false)
	private String firstName;
	@NotEmpty
	@Column(name = "Last_Name", nullable = false)
	private String lastName;
	@Column(name = "Email", nullable = false)
	@NotEmpty
	@Email
	private String email;
	@Column(name = "Role", nullable = false)
	@NotEmpty
	private String Role;
	@Column(name = "Enabled", nullable = false)
	private boolean enabled = true;
	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	private List<Post> postList;

//	public List<Post> getPostList() {
//		return postList;
//	}
//
//	public void setPostList(List<Post> postList) {
//		this.postList = postList;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = true;
	}

	@Transient
	private MultipartFile photo;

	@Lob
	private byte[] photoBytes;

	private String photoContentType;

	private String photoFilename;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public static AtomicLong getIdsequence() {
		return idSequence;
	}

	public Long assignId() {
		this.id = idSequence.incrementAndGet();
		return id;
	}

	private static final AtomicLong idSequence = new AtomicLong();
}
