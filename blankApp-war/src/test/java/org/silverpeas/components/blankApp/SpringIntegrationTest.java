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
package org.silverpeas.components.blankApp;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.silverpeas.components.blankApp.blankStuff.BlankStuff;
import org.silverpeas.components.blankApp.blankStuff.BlankStuffRepository;
import org.silverpeas.components.blankApp.rest.BlankAppTestResources;


@ContextConfiguration(locations= "/"+BlankAppTestResources.SPRING_CONTEXT)
@DirtiesContext //this is a workaround for a bug with Eclipse JUnit runner
public abstract class SpringIntegrationTest extends AbstractJUnit4SpringContextTests {
    @Inject protected BlankStuffRepository blankStuffRepository;

    private final boolean initializeData;
    private IDatabaseTester databaseTester;
    protected JdbcTemplate jdbcTemplate;

	public SpringIntegrationTest(){
		this(false);
	}
	
	public SpringIntegrationTest(boolean initializeData) {
		super();
		this.initializeData = initializeData;
	}
	
	private void checkEmptyDatabase(){
    	assertEquals(0, blankStuffRepository.count());
	}
	
	@Before
    public void beforeTest() throws Exception{
		if(initializeData){
	    	//ensure database is empty before test
			checkEmptyDatabase();

			databaseTester.onSetup();
			
	    	BlankStuff myFirstBlankStuff = blankStuffRepository.findOne(100);
	    	checkNotNull(myFirstBlankStuff);
		}
    }

    @After
    public void afterTest() throws Exception{
    	if(initializeData){
    		databaseTester.onTearDown();
    	}
    }
        
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.databaseTester = TestDataSetUtils.initDatabaseTester(dataSource);
	}

}
