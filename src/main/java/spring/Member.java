package spring;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name = "member2")
public class Member {

	@Id
	private Long id;
	@Column(name = "email")
	private String email;
	@JsonIgnore
	private String password;
	private String name;
	//@JsonFormat(pattern = "yyyyMMddHHmmss")
	@JsonFormat(shape= JsonFormat.Shape.STRING)
	private LocalDateTime registerDateTime;
	public boolean matchPassword(String password){
		return this.password.equals(password);
	}
	public Member(String email, String password, 
			String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
	}
	public Member(){
		this("null","null","null",null);
	}
	void setId(Long id) {
		this.id = id;
	}
	void setRegdate(LocalDateTime time){this.registerDateTime = time;}
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	public void changePassword(String oldPassword, String newPassword) {
		System.out.print("\n"+password);
		System.out.printf(" %s %s\n",oldPassword,newPassword);
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;

	}

}
