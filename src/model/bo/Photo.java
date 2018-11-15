package model.bo;

/*******************Photo des annonces***********************/

import java.util.Base64;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "PHOTO_TABLE")
public class Photo {

	@Id
	@GeneratedValue
	private int id;
	
    @Column(name="base64Image", nullable=false, columnDefinition="longtext")

	private String base64Image;
	
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photo(byte[] image) {
		super();
		base64Image = Base64.getEncoder().encodeToString(image);

	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

}
