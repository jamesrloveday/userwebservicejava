/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.web.connector.impl;

import com.application.web.connector.UserConnector;
import com.application.web.objects.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author james
 *
 * Class that implements the UserConnector interface
 */
public final class UserConnectorImpl implements UserConnector {

	public MongoClient client;

	public UserConnectorImpl() {
	}

	public UserConnectorImpl(final MongoClient client) {
		this.client = client;
	}

	private MongoCollection<Document> usersCollection() {
		return getClient().getDatabase("user_store").getCollection("user");
	}

	@Override
	public String saveNew(User user) throws Exception {
		String json = getMapper().writeValueAsString(user);
		Document doc = Document.parse(json);
		usersCollection().insertOne(doc);
		return user.id;
	}

	@Override
	public User findSingleUser(User user) throws IOException {
		Document document = usersCollection()
				.find(eq("id", user.id)).first();
		User userFound = getMapper().readValue(document.toJson(), User.class);
		return userFound;
	}

	@Override
	public User findSingleUserByName(String firstName, String lastName) throws IOException {
		Document document = usersCollection()
				.find(and(eq("first_name", firstName), eq("last_name", lastName)))
				.first();
		User userFound = getMapper().readValue(document.toJson(), User.class);
		return userFound;
	}

	@Override
	public Long updateUser(User user) throws IOException {
		String json = getMapper().writeValueAsString(user);
		Document updatedUser = Document.parse(json);
		UpdateResult result = usersCollection().updateOne(eq("id", user.id), new Document("$set", updatedUser));
		return result.getModifiedCount();
	}

	@Override
	public void deleteUser(User user) throws IOException {
		usersCollection().deleteOne(eq("id", user.id));
	}

}
