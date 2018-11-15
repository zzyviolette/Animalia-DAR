package model.vo;

import model.bo.Utilisateur;

public class UtilisateurVo {

	/*Les donnees pour modifier un profil utilisateur */
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String numberphone;
	private String about;
	private String occupation;
	private String interest;
	private String avatar;

	public UtilisateurVo() {
		super();
	}
	
	public UtilisateurVo(Utilisateur user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.numberphone = user.getNumberphone();
		this.about = user.getAbout();
		this.occupation = user.getOccupation();
		this.interest = user.getInterest();
		if(user.getAvatar()!=null)
		this.avatar = user.getAvatar().getBase64Image();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumberphone() {
		return numberphone;
	}

	public void setNumberphone(String numberphone) {
		this.numberphone = numberphone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
