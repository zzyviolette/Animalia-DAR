package model.bo;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POST_TABLE")
public class Annonce {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private Utilisateur user_id;

	@Column(name = "category", nullable = false, columnDefinition = "text")
	private String category;

	@OneToOne
	@JoinColumn(name = "post_photo", referencedColumnName = "id")
	private Photo photo;

	@OneToMany(mappedBy = "annonce_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ACommentaire> comments;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "favoris", joinColumns = @JoinColumn(name = "annonce_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	Set<Utilisateur> users_favoris;

	@Column(name = "content", nullable = false, columnDefinition = "longtext")
	private String content;
	private String title;
	private Date date;
	private String location;

	public Annonce(String content, String title, String location, Utilisateur user, String category, Photo photo) {
		// super();
		this.content = content;
		this.title = title;
		this.location = location;
		this.user_id = user;
		this.category = category;
		this.photo = photo;
		this.comments = new ArrayList();
		this.date = new Date();
	}

	public Annonce() {
		super();
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Utilisateur getUser_id() {
		return user_id;
	}

	public Set<Utilisateur> getUsers_favoris() {
		return users_favoris;
	}

	public void setUsers_favoris(Set<Utilisateur> users_favoris) {
		this.users_favoris = users_favoris;
	}

	public List<ACommentaire> getComments() {
		if(comments==null)
			comments=new ArrayList();
		return comments;
	}

	public void setComments(List<ACommentaire> comments) {
		this.comments = comments;
	}

	public String getCategory() {
		return category;
	}

	public void setId_category(String category) {
		this.category = category;
	}

	public Utilisateur getUser() {
		return user_id;
	}

	public void setUser_id(Utilisateur user_id) {
		this.user_id = user_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}