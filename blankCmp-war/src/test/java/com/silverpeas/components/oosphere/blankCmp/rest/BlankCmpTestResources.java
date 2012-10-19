package com.silverpeas.components.oosphere.blankCmp.rest;

import javax.inject.Named;
import javax.inject.Singleton;

import com.silverpeas.web.TestResources;


@Named(TestResources.TEST_RESOURCES_NAME)
@Singleton
public class BlankCmpTestResources extends TestResources{
    public static final String SPRING_CONTEXT = "blankCmp-test-context.xml";
    public static final String REST_PACKAGE = "com.silverpeas.components.oosphere.blankCmp.blankStuff";
    public static final String COMPONENT_NAME = "blankCmp";
		
	public String[] myExistingComponentInstances() {
        return new String[]{"blabla1"};
    }

}
