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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import org.silverpeas.components.blankApp.JarIntegrationTest;
import org.silverpeas.components.blankApp.TestDataSetUtils;

public class BlankStuffRepositoryTest extends JarIntegrationTest {
	
    public BlankStuffRepositoryTest() {
		super(true);
	}

	@Test
    public void testNominalCRUD() {
    	long initialCount = blankStuffRepository.count();
    	//READ
        BlankStuff existingObject = blankStuffRepository.findOne(TestDataSetUtils.A_BLANK_STUFF_ID);
        assertEquals("My blank stuff", existingObject.getLabel());
    	
    	//CREATE
    	BlankStuff newBlankStuff = new BlankStuff();
    	newBlankStuff.setLabel("Initial label");
        blankStuffRepository.saveAndFlush(newBlankStuff);
        assertThat(blankStuffRepository.count(), equalTo(initialCount+1));

        //READ
        Integer createdId = newBlankStuff.getId();
        assertNotNull(createdId);

        BlankStuff newBlankStuff2 = blankStuffRepository.findOne(createdId);
        assertNotSame(newBlankStuff, newBlankStuff2); //check that object is a plain new instantiated object loaded from database (not coming from any ORM cache)
        assertEquals(newBlankStuff, newBlankStuff2);
        assertEquals("Initial label", newBlankStuff2.getLabel());

        //UPDATE
        newBlankStuff2.setLabel("New label");
        blankStuffRepository.saveAndFlush(newBlankStuff2);
        
        newBlankStuff2 = blankStuffRepository.findOne(createdId);
        assertEquals("New label", newBlankStuff2.getLabel());
        
        //DELETE
        blankStuffRepository.delete(createdId);
        assertThat(blankStuffRepository.count(), equalTo(initialCount));
    }

}
