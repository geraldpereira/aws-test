package fr.byob.aws.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.google.inject.Singleton;

import fr.byob.aws.commons.guice.InjectLogger;

/**
 * 
 * Filter that adds Cross-origin resource sharing headers 
 * 
 * More information http://en.wikipedia.org/wiki/Cross-origin_resource_sharing  
 * 
 * @author gpereira
 *
 */
@Singleton
public class CORSHeadersFilter implements Filter {

	@InjectLogger
	private Logger log;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletResponse httpReponse = (HttpServletResponse) response;
		log.info("Reponse headers added !");
		httpReponse.setHeader("Access-Control-Allow-Origin", "*"); // TODO limiter a localhost et a l'url de l'appli cliente
		httpReponse.setHeader("Access-Control-Allow-Methods","PUT, GET, POST, DELETE, OPTIONS");
		httpReponse.setHeader("Access-Control-Allow-Headers",
				"authorization,content-type");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
