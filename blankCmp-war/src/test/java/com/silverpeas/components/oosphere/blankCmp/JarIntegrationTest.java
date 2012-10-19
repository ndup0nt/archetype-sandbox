package com.silverpeas.components.oosphere.blankCmp;


/**
 * All integration tests (even for JAR module classes) are in WAR for dependency reasons.
 * 
 */
public abstract class JarIntegrationTest extends SpringIntegrationTest {

	public JarIntegrationTest() {
		super();
	}

	/**
	 * @param initializeData
	 */
	public JarIntegrationTest(boolean initializeData) {
		super(initializeData);
	}

}
