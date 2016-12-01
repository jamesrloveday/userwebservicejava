/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.web.connector;

import com.application.web.objects.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import java.io.IOException;

/**
 *
 * @author james
 *
 * Interface for the User database
 */
public interface UserConnector {

	default MongoClient getClient() {
		return new MongoClient("localhost", 27017);
	}

	default ObjectMapper getMapper() {
		return new ObjectMapper();
	}

	/**
	 * Method saveNewUser
	 *
	 * Saves a new user in the store
	 *
	 * @param user
	 * @return the id of the user as a string
	 * @throws Exception
	 */
	String saveNew(User user) throws Exception;

	/**
	 * Method findSingleUser
	 *
	 * Finds and returns a single user in the database
	 *
	 * @param user
	 * @return A User
	 * @throws IOException
	 */
	User findSingleUser(User user) throws IOException;

	/**
	 * Method findSingleUserByName
	 *
	 * Finds and returns a single user accessing the first name and last name of the user object
	 *
	 * @param user
	 * @return a User
	 * @throws IOException
	 */
	User findSingleUserByName(String firstName, String lastName) throws IOException;

	/**
	 * Method updateUser
	 *
	 * Updates a single user in the store
	 *
	 * @param user
	 * @return A long
	 * @throws IOException
	 */
	Long updateUser(User user) throws IOException;

	/**
	 * Method deleteUser
	 *
	 * Deletes a single user in the store through accessing the user id
	 *
	 * @param user
	 * @throws IOException
	 */
	void deleteUser(User user) throws IOException;

}
