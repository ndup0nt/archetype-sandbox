package org.silverpeas.components.oosphere.blankCmp.web.rest;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.silverpeas.components.oosphere.blankCmp.BlankCmpConstants;

@Provider
//MUST BE PUBLIC to be scanned by Jersey
public class BlankCmpObjectMapperProvider implements ContextResolver<ObjectMapper> {
    private static final ObjectMapper myObjectMapper = new ObjectMapper();

    static {
        myObjectMapper.getSerializationConfig().withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL); //do not serialize null properties
        myObjectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false); //use ISO 8601
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        if(type != null && type.getPackage() != null
                && type.getPackage().getName().startsWith(BlankCmpConstants.BASE_PACKAGE.getName())){
            return myObjectMapper;
        }
        return null;
    }
}
