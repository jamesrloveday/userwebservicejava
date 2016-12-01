/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.web.objects;

/**
 *
 * @author james
 */
public final class User {

	public final String id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String username;
	public final String phone;
	public final Address address;

	public User(String id, String firstName, String lastName, String email, String username, String phone, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

}
