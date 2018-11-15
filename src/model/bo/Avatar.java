package model.bo;

import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "AVATAR")
public class Avatar {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NAME")
	private String name;

	@Lob
	@Column(name = "IMAGE", nullable = false, columnDefinition = "mediumblob")
	private byte[] image;
	
	@Column(name = "URL", nullable = false, columnDefinition = "longtext")
	String base64Image;

	public Avatar() {
		super();
	}

	public Avatar(String name, byte[] image) {
		super();
		System.out.println(name+"***"+image);
		this.name = name;
		this.image = image;
		base64Image = Base64.getEncoder().encodeToString(image);

	}

	public String getBase64Image() {
		return base64Image;
	}
	
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}