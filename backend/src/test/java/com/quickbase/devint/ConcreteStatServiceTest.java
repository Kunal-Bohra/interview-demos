package com.quickbase.devint;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains unit tests for ConcreteStatService class.
 */
public class ConcreteStatServiceTest {

    // Initialize the required objects and variables.
    private static ConcreteStatService concreteStatService = new ConcreteStatService();
    private static List<CountryDemographics> emptyListOfCD = new ArrayList<CountryDemographics>(); // Empty List
    private static List<CountryDemographics> sampleListOfCD1 = new ArrayList<CountryDemographics>();
    private static List<CountryDemographics> sampleListOfCD2 = new ArrayList<CountryDemographics>();
    private static List<CountryDemographics> sampleListOfCD3 = new ArrayList<CountryDemographics>();

    @BeforeClass
    public static void setUp() throws Exception {

        // Create a list of a few CountryDemographics objects
        sampleListOfCD1.add(new CountryDemographics.Builder().countryName("India").population(1182105000).build());
        sampleListOfCD1.add(new CountryDemographics.Builder().countryName("United Kingdom").population(62026962).build());
        sampleListOfCD1.add(new CountryDemographics.Builder().countryName("Chile").population(17094270).build());
        sampleListOfCD1.add(new CountryDemographics.Builder().countryName("Mali").population(15370000).build());
        sampleListOfCD1.add(new CountryDemographics.Builder().countryName("Greece").population(11305118).build());

        // Create a list of a few CountryDemographics objects with duplicates from list 1
        sampleListOfCD2.add(new CountryDemographics.Builder().countryName("India").population(1182105000).build());
        sampleListOfCD2.add(new CountryDemographics.Builder().countryName("United Kingdom").population(62026962).build());

        // Create a list of a few CountryDemographics objects with no duplicates from list 1 or list 2
        sampleListOfCD3.add(new CountryDemographics.Builder().countryName("U.S.A.").population(309349689).build());
        sampleListOfCD3.add(new CountryDemographics.Builder().countryName("Saint Vincent and the Grenadines")
                .population(109284).build());

    }

    @AfterClass
    public static void tearDown() throws Exception {
        // Clear all lists
        sampleListOfCD1.clear();
        sampleListOfCD2.clear();
        sampleListOfCD3.clear();
        emptyListOfCD.clear();
    }

    /**
     * This test verifies that getCountryPopulations method returns 28 objects in total.
     *
     * @throws Exception
     */
    @Test
    public void testGetCountryPopulationsFromAPI() throws Exception {
        final List<CountryDemographics> retrievedCD = concreteStatService.getCountryPopulations();
        Assert.assertEquals(retrievedCD.size(), 28);
    }

    /**
     * This test verifies that mergeLists method merges two list of CountryDemographics objects as per
     * requirement of no duplicates
     *
     * @throws Exception
     */
    @Test
    public void testMergeListsWithDuplicates() throws Exception {
        final List<CountryDemographics> mergedList = concreteStatService.mergeLists(sampleListOfCD1, sampleListOfCD2);
        // Verify that size of output merged list is 5 and same as list 1
        Assert.assertEquals(mergedList.size(), sampleListOfCD1.size());
    }

    /**
     * This test verifies that mergeLists method merges two list of CountryDemographics objects as per
     * requirement of no duplicates with one list being an empty list
     *
     * @throws Exception
     */
    @Test
    public void testMergeListsWithOneEmpty() throws Exception {
        final List<CountryDemographics> mergedList = concreteStatService.mergeLists(sampleListOfCD1, emptyListOfCD);
        // Verify that size of output merged list is 5 and same as list 1
        Assert.assertEquals(mergedList.size(), sampleListOfCD1.size());
    }

    /**
     * This test verifies that mergeLists method merges two list of CountryDemographics objects as per
     * requirement of no duplicates with one list being an empty list
     *
     * @throws Exception
     */
    @Test
    public void testMergeListsWithNoDuplicates() throws Exception {
        final List<CountryDemographics> mergedList = concreteStatService.mergeLists(sampleListOfCD1, sampleListOfCD3);
        // Verify that size of output merged list is 7
        Assert.assertEquals(mergedList.size(), 7);
    }

    /**
     * This test verifies that mergeLists method merges two empty lists as an empty lists.
     *
     * @throws Exception
     */
    @Test
    public void testMergeTwoEmptyLists() throws Exception {
        final List<CountryDemographics> mergedList = concreteStatService.mergeLists(emptyListOfCD, emptyListOfCD);
        // Verify that size of output merged list is 0
        Assert.assertEquals(mergedList.size(), 0);
    }


}