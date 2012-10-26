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
package org.silverpeas.components.oosphere.blankCmp;

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
