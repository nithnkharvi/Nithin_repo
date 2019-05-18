package com.omnicuris.ekart.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="OC_USER", uniqueConstraints= {@UniqueConstraint(columnNames={"USER_ID"})})
public class OcUser 
{
	@Column(name="USER_NAME", nullable=false, length=20)
	private String userName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="USER_ID", nullable=false, unique=true, length=10)
	private Integer userId;
	
	@Column(name="PASSWORD", nullable=false, length=20)
	private String password;
	
	@Column(name="EMAIL_ID", nullable=false, length=20)
	private String emailId;

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public Integer getUserId() 
	{
		return userId;
	}

	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getEmailId() 
	{
		return emailId;
	}

	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}
}
