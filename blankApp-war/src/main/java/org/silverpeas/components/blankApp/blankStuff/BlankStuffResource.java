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
package org.silverpeas.components.blankApp.blankStuff;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.silverpeas.annotation.RequestScoped;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.silverpeas.annotation.Authorized;
import com.silverpeas.web.RESTWebService;

@Service
@RequestScoped
@Path("blankApp/blankStuff")
@Authorized
public class BlankStuffResource extends RESTWebService {
    @Inject private BlankStuffService blankStuffService;
    @Value("#{blankAppGlobalProperties.getString('rest.componentId')}") private String componentId;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBlankStuff(final BlankStuff entity){
    	final BlankStuff newBlankStuff = blankStuffService.createNewBlankStuff(entity);
    	URI newURI = getUriInfo().getRequestUriBuilder().path(newBlankStuff.getId().toString()).build();
        return Response.created(newURI).entity(newBlankStuff.getId()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
    	List<BlankStuff> res = blankStuffService.getAllBlankStuffs();
    	return Response.ok(res).build();
    }

	@Override
    //user authorization will be checked among this component
	public String getComponentId() {
		return componentId;
	}


}
