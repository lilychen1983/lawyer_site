package com.law.fileupload;

public class User {
	 
    private String username;  
    private String nickname;  
    private String password;  
    private String email;  
    /*==四个属性的getter()、setter()略==*/  
    public User() {}  
    public User(String username, String nickname, String password, String email) {  
        this.setUsername(username);  
        this.nickname = nickname;  
        this.password = password;  
        this.email = email;  
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}  
}
