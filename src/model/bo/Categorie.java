package model.bo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY_TABLE")
public class Categorie {

		@Id @GeneratedValue
		private int id;
		private String type;
		  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_category",cascade = CascadeType.ALL)
  	    private List<Annonce> annonces ;

		
		public Categorie(String type) {
			super();
			this.type = type;
		}
		public Categorie() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public void setAnnonces(List<Annonce> annonces) {
			this.annonces= annonces;
		}
		public List<Annonce> getAnnonces() {
			return annonces;
		}
		
}
