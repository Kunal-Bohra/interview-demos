package com.quickbase;

import com.quickbase.devint.*;

import java.sql.Connection;
import java.util.List;

/**
 *  Main class for the application. This class is responsible for printing final merged list of country
 *  populations.
 */
public class Main {
    public static void main(String args[] ) {
        System.out.println("Starting the application");
        System.out.print("Getting DB Connection...");

        Main main = new Main();
        DBManager dbm = new DBManagerImpl();
        Connection c = dbm.getConnection();
        if (null == c ) {
            System.out.println("Failed to set connection. App wil exit");
            System.exit(1);
        }
        // Call helper method to get country demographics
        main.getCountryDemographics(dbm);
    }

    /**
     * Helper method to retrieve lists of CountryDemographics object from API and Database.
     * Merges both lists and returns a single list of CountryDemographics object. Note that
     * duplicate countryName value in two CountryDemographics objects are treated as one and
     * population attribute is chosen corresponding to value returned from database.
     *
     * @param dbManager - Instance of DBManager class to call database connection method.
     */
    private void getCountryDemographics(final DBManager dbManager) {

        IStatService iStatService = new ConcreteStatService();

        List<CountryDemographics> outputFromAPI = iStatService.GetCountryPopulations();
        List<CountryDemographics> resultFromDB = dbManager.getCountryPopulation();

        //Merge both lists
        final List<CountryDemographics> finalCountryPopulationList = iStatService.mergeLists(outputFromAPI, resultFromDB);

        //Print resultFromDBs
        for (CountryDemographics countryDemographics : finalCountryPopulationList) {
            String countryName = countryDemographics.getCountryName();
            Integer population = countryDemographics.getPopulation();
            System.out.println("Country: " + countryName + "\tPopulation: " + population);
        }
        System.out.println("Size of list from Database: " + resultFromDB.size());
        System.out.println("Size of list from API: " + outputFromAPI.size());
        System.out.println("Size of final merged list: " + finalCountryPopulationList.size());

    }
}