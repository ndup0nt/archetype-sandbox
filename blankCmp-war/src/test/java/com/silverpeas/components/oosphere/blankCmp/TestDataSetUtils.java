package com.silverpeas.components.oosphere.blankCmp;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;

public class TestDataSetUtils {
    private static final FlatXmlDataSetBuilder DATASET_BUILDER = new FlatXmlDataSetBuilder();
    private static final ReplacementDataSet DATA_SET;
    private static final String DATASET_PATH = "/blankCmp-test-dataset.xml";
    
    public static final int A_BLANK_STUFF_ID = 100; 

    static{
        try {
            DATA_SET = new ReplacementDataSet(DATASET_BUILDER.build(
                    TestDataSetUtils.class.getResourceAsStream(
                            DATASET_PATH)));
        } catch (DataSetException e) {
            throw new AssertionError(e);
        }
        DATA_SET.addReplacementObject("[NULL]", null);
    }

    public static IDatabaseTester initDatabaseTester(DataSource dataSource) {
        IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        databaseTester.setDataSet(DATA_SET);
        return databaseTester;
    }
}
