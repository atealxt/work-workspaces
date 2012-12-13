package org.ywq.entity;

/**
 * User entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905951597256738790L;
	private Integer uid;
	private String uname;
	private Integer uage;
	private String usex;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public User(Integer uid, String uname, Integer uage, String usex) {
		this.uid = uid;
		this.uname = uname;
		this.uage = uage;
		this.usex = usex;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getUage() {
		return this.uage;
	}

	public void setUage(Integer uage) {
		this.uage = uage;
	}

	public String getUsex() {
		return this.usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

}