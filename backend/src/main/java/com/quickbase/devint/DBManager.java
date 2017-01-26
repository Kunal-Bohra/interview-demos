package com.quickbase.devint;

import java.sql.Connection;
import java.util.List;

/**
 * The DBManager interface provides data access methods for CountryDemographics.
 */
public interface DBManager {

    /**
     * Method to get a Connection object to connect to the SQLite database
     *
     * @return - an instance of Connection class.
     */
    Connection getConnection();

    /**
     * Returns an unordered list of countries and their populations in the form of a CountryDemographics object
     * from the database.
     *
     * @return - a list of CountryDemographics object.
     */
    List<CountryDemographics> getCountryPopulation();
}
