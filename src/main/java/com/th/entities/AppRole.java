package com.th.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppRole {
@Id
private String roleName;

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public AppRole() {
	// TODO Auto-generated constructor stub
}

public AppRole(String roleName) {
	this.roleName = roleName;
}





}
