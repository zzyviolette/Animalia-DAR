package model.bo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE_TABLE")
public class Message {

		@Id @GeneratedValue
		private Long id;

	    private String content;
	    private int state;
		private Date date;
	    private int id_user_departure;
	    private int id_user_destination;

		public Message(String content,int state, Date date, int id_user_departure,int id_user_destination) {
			//super();
			this.content = content;
			this.state = state;
			this.date = date;
			this.id_user_departure = id_user_departure;
			this.id_user_destination = id_user_destination;
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
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
		public int getId_user_departure() {
			return id_user_departure;
		}
		public void setId_user_departure(int id_user_departure) {
			this.id_user_departure = id_user_departure;
		}
		public int getId_user_destination() {
			return id_user_destination;
		}
		public void setId_user_destination(int id_user_destination) {
			this.id_user_destination = id_user_destination;
		}
	
	    


}
