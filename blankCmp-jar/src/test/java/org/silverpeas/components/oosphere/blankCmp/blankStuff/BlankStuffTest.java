package org.silverpeas.components.oosphere.blankCmp.blankStuff;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class BlankStuffTest {

	@Test
	public void testGetSetLabel() {
		BlankStuff aBlankStuff = new BlankStuff();
        assertThat(aBlankStuff.getLabel(), nullValue());
        aBlankStuff.setLabel("John");
        assertThat(aBlankStuff.getLabel(), equalTo("John") );
	}
	
    @Test
    public void testEqualsAndHashCode(){
        EqualsVerifier.forClass(BlankStuff.class).verify();
        BlankStuff o1 = new BlankStuff();
        BlankStuff o2 = new BlankStuff();

        assertEquals(o1,o2);
        assertEquals(o1.hashCode(),o2.hashCode());
        
        o2.setLabel("New label for o2");
        assertFalse(o1.equals(o2));
        assertFalse(o1.hashCode() == o2.hashCode());
    }

}
