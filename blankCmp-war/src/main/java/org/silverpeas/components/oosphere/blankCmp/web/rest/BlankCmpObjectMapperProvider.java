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
