package com.quickbase.devint;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains tests for DBManagerImpl class.
 */
public class DBManagerImplTest {
    private static DBManagerImpl dbManager;
    private static Connection connection;
    private static List<CountryDemographics> sampleListOfCD = new ArrayList<CountryDemographics>();

    @BeforeClass
    public static void setUp() throws Exception {
        dbManager = new DBManagerImpl();
        connection = dbManager.getConnection();
        // Create a list of a few CountryDemographics objects
        sampleListOfCD.add(new CountryDemographics.Builder().countryName("India").population(1182105000).build());
        sampleListOfCD.add(new CountryDemographics.Builder().countryName("United Kingdom").population(62026962).build());
        sampleListOfCD.add(new CountryDemographics.Builder().countryName("Chile").population(17094270).build());
        sampleListOfCD.add(new CountryDemographics.Builder().countryName("Mali").population(15370000).build());
        sampleListOfCD.add(new CountryDemographics.Builder().countryName("Greece").population(11305118).build());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        connection.close();
        sampleListOfCD.clear();
    }

    /**
     * This test tests basic retrieval of CountryDemographics objects from database.
     *
     * @throws Exception
     */
    @Test
    public void getCountryPopulationTestBasic() throws Exception {
        final List<CountryDemographics> listFromDB = dbManager.getCountryPopulation();
        Assert.assertEquals(listFromDB.size(), 16);
    }



}