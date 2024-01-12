package org.ibs;

import org.junit.jupiter.api.Test;
import org.ibs.basetests.JDBCManager;
import java.sql.*;



public class AppTest extends JDBCManager{

    @Test
    public void testCase1() throws SQLException {
        getStatement();
        insertInvalidValues(5,null,null,12);
        deleteById(5);
    }

    @Test
    public void testCase2() throws SQLException {
        getStatement();
        insertInvalidValues(1,"Яблоко", "FRUIT", 0);
        deleteById(5);
    }
    @Test
    public void testCase3() throws SQLException {
        getStatement();
        insertInvalidValues(1, "Банан", "FRUIT", 12);
        deleteById(1);
    }

    @Test
    public void testCase4() throws SQLException {
        getStatement();
        insertInvalidValues(1, "Банан", null, 0);
        deleteById(1);
    }

    @Test
    public void testCase5() throws SQLException {
        getStatement();
        insertInvalidValues(5, "Банан", null, 0);
        deleteById(5);
    }

    @Test
    public void testCase6() throws SQLException {
        getStatement();
        insertInvalidValues(5, "Банан", "FRUIT", 12);
        deleteById(5);
    }
    @Test
    public void testCase7() throws SQLException {
        getStatement();
        insertInvalidValues(5, null, "FRUIT", 0);
        deleteById(5);
    }
    @Test
    public void testCase8() throws SQLException {
        getStatement();
        insertInvalidValues(1, null, null, 12);
        deleteById(1);
    }
    @Test
    public void testCase9() throws SQLException {
        getStatement();
        insertIntoFood(5, "Банан", "FRUIT", 0);
        deleteById(5);
    }
}
