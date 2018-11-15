package model.bo;

/****************** Commentaire d'une annonce ***************/

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACOMMENT_TABLE")
public class ACommentaire { 
	@Id
	@GeneratedValue
	int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "annonce_id", referencedColumnName = "id", nullable = false)
	private Annonce annonce_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Utilisateur user_id;
	
	String content;
	Date date;
	int state;
	
	public ACommentaire() {
		super();
	}
    
	public ACommentaire(Annonce annonce_id, Utilisateur user_id, String content) {
		super();
		this.annonce_id = annonce_id;
		this.user_id = user_id;
		this.content = content;
		this.state=0;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Annonce getAnnonce_id() {
		return annonce_id;
	}
	public void setAnnonce_id(Annonce annonce_id) {
		this.annonce_id = annonce_id;
	}
	public Utilisateur getUser_id() {
		return user_id;
	}
	public void setUser_id(Utilisateur user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
