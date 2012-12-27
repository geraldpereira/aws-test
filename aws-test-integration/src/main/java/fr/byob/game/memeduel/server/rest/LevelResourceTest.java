package fr.byob.game.memeduel.server.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import fr.byob.game.memeduel.domain.Level;
import fr.byob.game.memeduel.domain.User;


public class LevelResourceTest extends AbstractResourceTest {



	@SuppressWarnings("deprecation")
	@Test
	public void testLevel() {

		final User user = new User(LOGIN, PASSWORD, "test@gmail.com");

		try {
			final User returnedUser = webResource.path("/user/add").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(User.class, user);
			Assert.assertEquals(user, returnedUser);

			final Level level1 = new Level(LOGIN, "level1", "level1");
			final Level level1Bck = webResource.path("/level/add").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Level.class, level1);
			Assert.assertNotNull(level1Bck.getId());

			final Level level2 = new Level(LOGIN, "level2", "level2");
			final Level level2Bck = webResource.path("/level/add").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Level.class, level2);
			Assert.assertNotNull(level2Bck.getId());

			final Level level3 = new Level(LOGIN, "level3", "level3");
			final Level level3Bck = webResource.path("/level/add").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Level.class, level3);
			Assert.assertNotNull(level3Bck.getId());

			final Level[] expected = new Level[] { level3Bck, level2Bck };
			final Level[] levels = webResource.path("/level/owned/" + LOGIN + "/2/0").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(Level[].class);
			Assert.assertEquals(expected, levels);

			final Level[] expected2 = new Level[] { level3Bck, level2Bck, level1Bck };
			final Level[] levels2 = webResource.path("/level/latest/10/0").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(Level[].class);
			Assert.assertEquals(expected2, levels2);


			level1Bck.setContent("level1edit");
			level1Bck.setTitle("level1edit");
			final Level level1Edit = webResource.path("/level/update").header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Level.class, level1Bck);
			Assert.assertEquals("level1edit", level1Edit.getContent());
			Assert.assertEquals("level1edit", level1Edit.getTitle());

			webResource.path("/level/delete/" + level1Bck.getId()).header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();
			webResource.path("/level/delete/" + level2Bck.getId()).header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();
			webResource.path("/level/delete/" + level3Bck.getId()).header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();

		} finally {
			webResource.path("/user/delete/" + LOGIN).header("authorization", computeAuthHeader(LOGIN, PASSWORD)).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();
		}

	}

}
