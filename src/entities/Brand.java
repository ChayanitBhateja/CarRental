package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="tbl_vehicle_brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtbl_vehicle_brand")
	@PrimaryKeyJoinColumn
	int id;
	
	@Column(name="tbl_vehicle_brandname")
	String name;
	
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
}
