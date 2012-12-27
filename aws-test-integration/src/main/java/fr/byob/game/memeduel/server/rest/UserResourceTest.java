package fr.byob.game.memeduel.server.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import fr.byob.game.memeduel.domain.User;

public class UserResourceTest extends AbstractResourceTest {



	@Test
	public void testUser() {
		final User user = new User(LOGIN, PASSWORD, "test@gmail.com");

		User returnedUser = webResource.path("/user/add").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(User.class, user);
		Assert.assertEquals(user, returnedUser);

		user.setEmail("test2@gmail.com");
		user.setPassword("password2");
		returnedUser = webResource.path("/user/update").header("authorization", computeAuthHeader(LOGIN, "password")).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(User.class, user);

		Assert.assertEquals(user, returnedUser);

		returnedUser = webResource.path("/user/get/" + LOGIN).header("authorization", computeAuthHeader(LOGIN, "password2")).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(User.class);

		Assert.assertEquals(user, returnedUser);

		webResource.path("/user/delete/" + LOGIN).header("authorization", computeAuthHeader(LOGIN, "password2")).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();
	}


}
