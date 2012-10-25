package org.silverpeas.components.oosphere.blankCmp.blankStuff;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.silverpeas.annotation.Authorized;
import com.silverpeas.web.RESTWebService;

@Service
@RequestScoped
@Path("blankCmp/blankStuff")
@Authorized
public class BlankStuffResource extends RESTWebService {
    @Inject private BlankStuffService blankStuffService;

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
		//TODO get component id as a path template parameter or from a properties file
		return "blankCmp1";
	}


}
