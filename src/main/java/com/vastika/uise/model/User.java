package com.vastika.uise.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "user_name")
	@NotEmpty
	@Size(min = 3, max = 6, message = "username must be between 3 to 6 digits.")
	private String userName;
	
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;
	
	@Email
	private String email;
	
	@Column(name = "mobile_no")
	@Max(value = 999, message = "mobileNo should not be more than 3 digits")
	private long mobileNo;
	private String gender;
	private String hobbies;
	
	@NotNull
	private String nationality;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String comments;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
//	@Past(message = "date of birth must be in past.")
	private Date dob;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Role role;
	
	@Column(name = "image_url")
	private String imageUrl;

}
