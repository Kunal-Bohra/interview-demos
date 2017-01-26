package com.quickbase.devint;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class contains unit tests for CountryDemographics class.
 */
public class CountryDemographicsTest {
    private static CountryDemographics countryDemographics1;
    private static CountryDemographics countryDemographics2;
    private static CountryDemographics countryDemographicsWithNullPopulation;

    @BeforeClass
    public static void setUp() throws Exception {
        countryDemographics1 = new CountryDemographics.Builder()
                .countryName("Test Country 1")
                .population(500000)
                .build();

        countryDemographics2 = new CountryDemographics.Builder()
                .countryName("Test Country 2")
                .population(300000)
                .build();
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    /**
     * This test verifies that getCountryName method returns a valid country name.
     *
     * @throws Exception
     */
    @Test
    public void testGetCountryName() throws Exception {
        final String countryName1 = countryDemographics1.getCountryName();
        Assert.assertEquals(countryName1, "Test Country 1");
    }

    /**
     * This test verifies that getCountryName method raises an exception to retrieve .
     * @throws Exception
     */
    @Test
    public void testGetCountryNameNull() throws Exception {
        String countryName = null;
        try {
            countryName = countryDemographicsWithNullPopulation.getCountryName();
        } catch (NullPointerException npe) {
            Assert.assertEquals(countryName, null);
        }
    }

    /**
     * This test verifies that setPopulation method successfully sets a population value for a
     * give CounryDemographic object.
     * @throws Exception
     */
    @Test
    public void testSetCountryName() throws Exception {
        CountryDemographics countryDemographics = new CountryDemographics.Builder()
                .countryName("Test Name")
                .population(10000)
                .build();
        // Set a value to the name and verify
        Assert.assertEquals(countryDemographics.getCountryName(), "Test Name");
        // Now update the value
        countryDemographics.setCountryName("Updated Name");
        // Verify that the value has been changed
        Assert.assertEquals(countryDemographics.getCountryName(), "Updated Name");

    }

    /**
     * This test verifies that getPopulation method retrieves a valid population value which is not null.
     * @throws Exception
     */
    @Test
    public void testGetPopulation() throws Exception {
        final Integer population = countryDemographics1.getPopulation();
        Assert.assertEquals(population, new Integer(500000));
    }

    /**
     * This test verifies that setPopulation method successfully sets a population value for a
     * give CounryDemographic object.
     * @throws Exception
     */
    @Test
    public void testSetPopulation() throws Exception {
        CountryDemographics countryDemographics = new CountryDemographics.Builder()
                .countryName("Test Name")
                .population(10000)
                .build();
        // Set a value to the population and verify
        Assert.assertEquals(countryDemographics.getPopulation(), new Integer(10000));
        // Now update the value
        countryDemographics.setPopulation(20000);
        // Verify that the value has been changed
        Assert.assertEquals(countryDemographics.getPopulation(), new Integer(20000));
    }

    /**
     * This test verifies equality of two CountryDemographics objects.
     *
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {
        // Countries with same name should be equal.
        CountryDemographics countryDemographics = new CountryDemographics.Builder()
                .countryName("Test Country 1")
                .population(10000)
                .build();
        Assert.assertEquals(countryDemographics1, countryDemographics);
    }

    /**
     * This test verifies inequality of two CountryDemographics objects.
     *
     * @throws Exception
     */
    @Test
    public void testNotEquals() throws Exception {
        Assert.assertNotEquals(countryDemographics1, countryDemographics2);
    }
}