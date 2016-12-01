/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.web.user;

import com.application.web.connector.UserConnector;
import com.application.web.connector.impl.UserConnectorImpl;
import com.application.web.objects.Address;
import com.application.web.objects.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * Class for the web service to handle users
 */
@WebService(serviceName = "userservice")
@Stateless()
public class UserService {

	private static final Logger LOG = Logger.getLogger(UserService.class.getName());

	/**
	 * Web service operation to add a new user
	 *
	 * @param user
	 * @return id the string representing the user id
	 */
	@WebMethod(operationName = "addNew")
	public String addNew(@WebParam(name = "user") User user) {
		final UserConnector userConnector = new UserConnectorImpl();
		try {
			return userConnector.saveNew(user);
		} catch (Exception ex) {
			return "0";
		}
	}

	/**
	 * Web service operation to get a user
	 *
	 * @param firstName
	 * @param lastName
	 * @return User
	 */
	@WebMethod(operationName = "getUser")
	public User getUser(@WebParam(name = "firstName") final String firstName,
			@WebParam(name = "lastName") final String lastName) {
		User user;
		final UserConnector userConnector = new UserConnectorImpl();
		try {
			user = userConnector.findSingleUserByName(firstName, lastName);
		} catch (Exception ex) {
			Address address = new Address("", "", "", "", "", "", "", "");
			user = new User("", "", "", "", "", "", address);
			LOG.log(Level.SEVERE, "User not found", ex);
		}
		return user;
	}

	/**
	 * Web service operation to update a user
	 *
	 * @param user
	 * @return long the number of users updated.
	 */
	@WebMethod(operationName = "updateUser")
	public Long updateUser(@WebParam(name = "user") User user) {
		Long updateCount;
		final UserConnector userConnector = new UserConnectorImpl();
		try {
			updateCount = userConnector.updateUser(user);
		} catch (IOException ex) {
			updateCount = 0L;
			LOG.log(Level.SEVERE, "User not updated", ex);
		}
		return updateCount;
	}

	/**
	 * Web service operation to delete a user
	 *
	 * @param user
	 */
	@WebMethod(operationName = "delete")
	public void deleteUser(@WebParam(name = "user") User user) {
		final UserConnector userConnector = new UserConnectorImpl();
		try {
			userConnector.deleteUser(user);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "User not deleted, not in storage", ex);
		}
	}
}
