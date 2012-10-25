package org.silverpeas.components.oosphere.blankCmp.blankStuff;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import org.silverpeas.components.oosphere.blankCmp.JarIntegrationTest;
import org.silverpeas.components.oosphere.blankCmp.TestDataSetUtils;

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
