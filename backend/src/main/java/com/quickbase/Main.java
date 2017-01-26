package com.quickbase;

import com.quickbase.devint.ConcreteStatService;
import com.quickbase.devint.CountryDemographics;
import com.quickbase.devint.DBManager;
import com.quickbase.devint.DBManagerImpl;

import java.sql.Connection;
import java.util.List;

/**
 *  Main class for the application. This class is responsible for printing final merged list of country
 *  populations.
 */
public class Main {
    public static void main(String args[] ) {
        System.out.println("Starting the application");
        DBManager dbm = new DBManagerImpl();
        Connection connection = dbm.getConnection();
        if (null == connection) {
            System.out.println("Failed to set connection. App wil exit");
            System.exit(1);
        }
        // Call helper method to get country demographics
        getCountryDemographics(connection);
    }

    /**
     * Helper method to retrieve lists of CountryDemographics object from API and Database.
     * Merges both lists and returns a single list of CountryDemographics object. Note that
     * duplicate countryName value in two CountryDemographics objects are treated as one and
     * population attribute is chosen corresponding to value returned from database.
     *
     */
    private static void getCountryDemographics(final Connection connection) {

        ConcreteStatService concreteStatService = new ConcreteStatService();
        DBManagerImpl dbManager = new DBManagerImpl();

        List<CountryDemographics> outputFromAPI = concreteStatService.getCountryPopulations();
        List<CountryDemographics> resultFromDB = dbManager.getCountryPopulation(connection);

        //Merge both lists
        final List<CountryDemographics> finalCountryPopulationList = concreteStatService.mergeLists(outputFromAPI,
                resultFromDB);

        //Print resultFromDBs
        System.out.println("     Country\t  Population");
        int rowNum = 1;
        for (CountryDemographics countryDemographics : finalCountryPopulationList) {
            System.out.print(" " + rowNum + ".  ");
            String countryName = countryDemographics.getCountryName();
            Integer population = countryDemographics.getPopulation();
            System.out.println(countryName + "\t  " + population + "\n");
            rowNum++;
        }
        System.out.println("Size of list from Database: " + resultFromDB.size());
        System.out.println("Size of list from API: " + outputFromAPI.size());
        System.out.println("Size of final merged list: " + finalCountryPopulationList.size());

    }
}