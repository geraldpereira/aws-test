package fr.byob.gae.server.rest.jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

import com.google.inject.servlet.GuiceFilter;

/**
 * https://github.com/sunnygleason/j4-minimal
 */
public class Launcher {

	private final Server server;

	public Launcher() throws Exception {
		server = new Server(10080);
		final Context root = new Context(server, "/", Context.SESSIONS);
		root.addEventListener(new GuiceServletConfig());
		root.addFilter(GuiceFilter.class, "/*", 0);

		root.addServlet(EmptyServlet.class, "/*");

		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}

	public static void main(final String[] args) throws Exception {
		final Launcher launcher = new Launcher();
		System.in.read();
		launcher.stop();
	}
}
