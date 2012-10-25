package org.silverpeas.components.oosphere.blankCmp.rest;

import javax.inject.Named;
import javax.inject.Singleton;

import com.silverpeas.web.TestResources;


@Named(TestResources.TEST_RESOURCES_NAME)
@Singleton
public class BlankCmpTestResources extends TestResources{
    public static final String SPRING_CONTEXT = "blankCmp-test-context.xml";
    public static final String REST_PACKAGE = "org.silverpeas.components.oosphere.blankCmp";
    public static final String COMPONENT_NAME = "blankCmp";
    public static final String EXISTING_COMPONENT_ID = "blankCmp1";
}
