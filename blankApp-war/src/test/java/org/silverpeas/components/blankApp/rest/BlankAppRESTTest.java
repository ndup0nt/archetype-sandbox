/**
 * Copyright (C) 2000 - 2012 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.components.blankApp.rest;

import static org.silverpeas.components.blankApp.rest.BlankAppTestResources.*;
import static com.silverpeas.util.StringUtil.isDefined;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.dbunit.IDatabaseTester;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import org.silverpeas.components.blankApp.TestDataSetUtils;
import com.silverpeas.web.RESTWebServiceTest;
import com.silverpeas.web.WebResourceTesting;
import com.stratelia.webactiv.beans.admin.UserDetail;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * Convenient superclass for REST test
 * 
 */
public abstract class BlankAppRESTTest extends
		RESTWebServiceTest<BlankAppTestResources> {
	private IDatabaseTester databaseTester;
	private String sessionKey;

    public BlankAppRESTTest() {
		super(REST_PACKAGE, SPRING_CONTEXT);
	}

	@Before
	public void prepareTestResources() throws Exception {
		ApplicationContext applicationContext = getTestResources()
				.getApplicationContext();

		databaseTester = TestDataSetUtils.initDatabaseTester(applicationContext
				.getBean(DataSource.class));
		databaseTester.onSetup();

		UserDetail user = aUser();
		sessionKey = authenticate(user);
	}

	protected Statement query(String sql) {
		Statement stmt = null;
		try {
			Connection conn = databaseTester.getConnection().getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException ex) {
			close(stmt);
			throw new AssertionError(ex);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
		return stmt;
	}

	protected void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				throw new AssertionError(ex);
			}
		}
	}

	@After
	public void afterTest() throws Exception {
		databaseTester.onTearDown();
	}
	
	public final String getSessionKey() {
		return sessionKey;
	}

    @Override
	public final String[] getExistingComponentInstances() {
		return new String[]{getTestResources().componentId};
	}

	protected ClientResponse post(final Object entity, String uri) {
		return post(entity, uri, getSessionKey());
	}

	private ClientResponse post(final Object entity, String uri, 
			String withSessionKey) {
		String thePath = uri;
		WebResource resource = resource();
		WebResource.Builder resourcePoster = resource.path(thePath)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON);
		if (isDefined(withSessionKey)) {
			resourcePoster = resourcePoster.header(WebResourceTesting.HTTP_SESSIONKEY,
					withSessionKey);
		}
		return resourcePoster.post(ClientResponse.class, entity);
	}

	protected <T> ClientResponse put(final T entity, String atURI,
			MediaType mediaType) {
		return put(entity, atURI, getSessionKey(), mediaType);
	}

	protected <T> ClientResponse put(final T entity, String atURI) {
		return put(entity, atURI, getSessionKey(),
				MediaType.APPLICATION_JSON_TYPE);
	}

	private ClientResponse put(final Object entity, String atURI,
			String withSessionKey, MediaType mediaType) {
		String thePath = atURI;
		WebResource resource = resource();
		WebResource.Builder resourcePutter = resource.path(thePath)
				.accept(MediaType.APPLICATION_JSON).type(mediaType);
		if (isDefined(withSessionKey)) {
			resourcePutter = resourcePutter.header(WebResourceTesting.HTTP_SESSIONKEY,
					withSessionKey);
		}
		return resourcePutter.put(ClientResponse.class, entity);
	}

	protected <T> T getAt(String uri, Class<T> c) {
		String thePath = uri;
		WebResource resource = resource();
		return resource.path(thePath).header(WebResourceTesting.HTTP_SESSIONKEY, getSessionKey())
				.accept(MediaType.APPLICATION_JSON).get(c);
	}

	protected void assertCreated(ClientResponse response) {
		assertStatus(response, Response.Status.CREATED);
	}

	protected void assertUpdated(ClientResponse response) {
		assertStatus(response, Response.Status.NO_CONTENT);
	}

	protected void assertStatus(ClientResponse response, Response.Status status) {
		if (status.getStatusCode() != response.getStatus()) {
			Assert.fail("expected status: " + status.getStatusCode()
					+ ", actual: " + response.getStatus() + ". Response: "
					+ response.getEntity(String.class));
		}
	}
	
	// security checks

	protected abstract String aSecuredResourceURI();
	protected abstract String aSecuredResourceMethod();

	@Test
	public final void accessAResourceByANonAuthenticatedUser() {
		Builder builder = resource().path(aSecuredResourceURI()).accept(MediaType.APPLICATION_JSON);
		if("POST".equals(aSecuredResourceMethod())){
			builder = builder.entity("{}");
		}
		ClientResponse response = builder.method(aSecuredResourceMethod(),ClientResponse.class);
		assertStatus(response, Status.UNAUTHORIZED);
	}

	@Test
	public final void accessAResourceWithAnExpiredSession() {
		Builder builder = resource().path(aSecuredResourceURI())
				.header(WebResourceTesting.HTTP_SESSIONKEY, UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON);
		if("POST".equals(aSecuredResourceMethod())){
			builder = builder.entity("{}");
		}
		ClientResponse response = builder.method(aSecuredResourceMethod(),ClientResponse.class);
		assertStatus(response, Status.UNAUTHORIZED);
	}

	@Test
	public final void accessAResourceByAnUnauthorizedUser() {
		denieAuthorizationToUsers();
		Builder builder = resource().path(aSecuredResourceURI()).header(WebResourceTesting.HTTP_SESSIONKEY, getSessionKey())
				.accept(MediaType.APPLICATION_JSON);
		if("POST".equals(aSecuredResourceMethod())){
			builder = builder.entity("{}");
		}
		ClientResponse response = builder.method(aSecuredResourceMethod(),ClientResponse.class);
		assertStatus(response, Status.FORBIDDEN);
	}
	
	public final String anUnexistingResourceURI() {
		return aSecuredResourceURI() + "/anUnexistingResourceURI";
	}

	@Test
	public void gettingAnUnexistingResource() {
		ClientResponse response = getAt(anUnexistingResourceURI(), ClientResponse.class);
		assertStatus(response, Status.NOT_FOUND);
	}

}
