package model.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "EVENT_TABLE")
public class Evenement {
	@Id
	@GeneratedValue
	private int event_id;
    @Column(name="description", nullable=false, columnDefinition="longtext")
	private String description;
	private String title;
    private Date dateCreation;

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@OneToOne
	@JoinColumn(
	name="event_photo",
	referencedColumnName="id")
	//@OneToOne(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
	private PhotoEvent photo;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate date;

	private String location;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private Utilisateur user_id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "event_id"), inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	Set<Utilisateur> users_inter;

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public PhotoEvent getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoEvent photo) {
		this.photo = photo;
	}

	public Set<Utilisateur> getUsers_inter() {
		return users_inter;
	}

	public void setUsers_inter(Set<Utilisateur> users_inter) {
		this.users_inter = users_inter;
	}

	public Evenement(String description, String title, String location, Utilisateur user_id, LocalDate date) {
		// super();
		this.description = description;
		this.title = title;
		this.location = location;
		this.user_id = user_id;
		this.date = date;
		this.dateCreation=new Date();
	}

	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur getUser_id() {
		return user_id;
	}

	public void setUser_id(Utilisateur user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
