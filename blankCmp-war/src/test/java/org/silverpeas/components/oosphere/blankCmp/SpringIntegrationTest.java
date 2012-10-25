package org.silverpeas.components.oosphere.blankCmp;

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

import org.silverpeas.components.oosphere.blankCmp.blankStuff.BlankStuff;
import org.silverpeas.components.oosphere.blankCmp.blankStuff.BlankStuffRepository;
import org.silverpeas.components.oosphere.blankCmp.rest.BlankCmpTestResources;


@ContextConfiguration(locations= "/"+BlankCmpTestResources.SPRING_CONTEXT)
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
