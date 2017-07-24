package com.bing.ddup.model;

public class User {
	private Integer id;
	private String account;
	private String name;
	private String password;
	private Boolean isDel;
	private String powers;

	private String username;
	private String salt;

	public User() {
		super();
	}

	public User(Integer id, String account, String name, String password,
			Boolean isDel, String powers) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.password = password;
		this.isDel = isDel;
		this.powers = powers;
	}

    /**
     * 辅助方法返回username+salt
     * @return username+salt
     */
	public String getCredentialsSalt() {
	    return username+salt;
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		this.powers = powers;
	}

	public Boolean getDel() {
		return isDel;
	}

	public void setDel(Boolean del) {
		isDel = del;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
