package model.bo;

/*******************Photo des evenements****************/


import java.util.Base64;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "PHOTOEvent_TABLE")
public class PhotoEvent {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "base64Image", nullable = false, columnDefinition = "longtext")

	private String base64Image;

	public PhotoEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhotoEvent(byte[] image) {
		super();
		base64Image = Base64.getEncoder().encodeToString(image);

	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
