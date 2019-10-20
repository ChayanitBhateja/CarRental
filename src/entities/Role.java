package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtbl_role")
	int id;
	
	@Column(name = "tbl_rolename")
	String rolename;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "tbl_role_reguser")
	User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User fkUser) {
		this.user = fkUser;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", user=" + user + "]";
	}

}
