package org.silverpeas.components.oosphere.blankCmp.blankStuff;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import org.silverpeas.components.oosphere.blankCmp.TestDataSetUtils;
import org.silverpeas.components.oosphere.blankCmp.blankStuff.BlankStuff;
import org.silverpeas.components.oosphere.blankCmp.rest.BlankCmpRESTTest;
import com.sun.jersey.api.client.ClientResponse;


public class BlankStuffResourceTest extends BlankCmpRESTTest {

	@Override
	protected String aSecuredResourceURI() {
		return "blankCmp/blankStuff";
	}

	@Override
	protected String aSecuredResourceMethod() {
		return "GET";
	}
	
    @Test
    public void postBlankStuff() {
    	//setup
    	BlankStuff blankStuff = new BlankStuff();
    	blankStuff.setLabel("sdfsdf");

        //exercise and verify
        ClientResponse response = post(blankStuff, "blankCmp/blankStuff");
        assertCreated(response);
        
        String newId = response.getEntity(String.class);
        assertTrue(StringUtils.isNumeric(newId));
        assertThat(response.getLocation().toString(), endsWith("blankCmp/blankStuff/"+newId));
    }

    @Test
    public void getAll() {
		BlankStuff[] res = getAt("blankCmp/blankStuff", BlankStuff[].class);
		assertEquals(TestDataSetUtils.A_BLANK_STUFF_ID, (int)res[0].getId());
    }

}
