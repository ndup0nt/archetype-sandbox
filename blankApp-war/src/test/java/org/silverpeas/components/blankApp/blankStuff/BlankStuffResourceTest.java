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

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import org.silverpeas.components.blankApp.TestDataSetUtils;
import org.silverpeas.components.blankApp.rest.BlankAppRESTTest;
import com.sun.jersey.api.client.ClientResponse;


public class BlankStuffResourceTest extends BlankAppRESTTest {

	@Override
	protected String aSecuredResourceURI() {
		return "blankApp/blankStuff";
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
        ClientResponse response = post(blankStuff, "blankApp/blankStuff");
        assertCreated(response);
        
        String newId = response.getEntity(String.class);
        assertTrue(StringUtils.isNumeric(newId));
        assertThat(response.getLocation().toString(), endsWith("blankApp/blankStuff/"+newId));
    }

    @Test
    public void getAll() {
		BlankStuff[] res = getAt("blankApp/blankStuff", BlankStuff[].class);
		assertEquals(TestDataSetUtils.A_BLANK_STUFF_ID, (int)res[0].getId());
    }

}
