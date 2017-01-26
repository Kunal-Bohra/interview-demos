package com.quickbase.devint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This DBManager implementation provides a connection to the SQLite database containing population data.
 *
 * Created by ckeswani on 9/16/15.
 */
public class DBManagerImpl implements DBManager {

    private static String driverName = "org.sqlite.JDBC";
    private static String databaseURL = "jdbc:sqlite:resources/data/citystatecountry.db";
    private static String sumPopulationQuery = "SELECT\n" +
            "  Country.CountryName,\n" +
            "  SUM(City.Population) AS population\n" +
            "FROM City\n" +
            "  INNER JOIN State ON City.StateId = State.StateId\n" +
            "  INNER JOIN Country ON State.CountryId = Country.CountryId\n" +
            "GROUP BY State.CountryId\n" +
            "ORDER BY Country.CountryId;";

    /**
     * Connects to the database and returns Connection class instance.
     *
     * @return - Connection instance
     */
    public Connection getConnection() {

        Connection c = null;
        try {
            Class.forName(driverName);
            c = DriverManager.getConnection(databaseURL);
            System.out.println("Opened the database connection successfully.\n");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Could not load the driver class.\n");
        } catch (SQLException sqle) {
            System.out.println("Sql exception:" + sqle.getStackTrace());
        }
        return c;
    }

    /**
     * Returns an unordered list of countries and their populations in the form of a CountryDemographics object
     * from the database.
     * @param connection - the database connection
     * @return - a list of CountryDemographics object.
     */
    public List<CountryDemographics> getCountryPopulation(final Connection connection) {
        List<CountryDemographics> countryPopulationList = new ArrayList<CountryDemographics>();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = sumPopulationQuery;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String countryName = rs.getString("CountryName");
                Integer population = rs.getInt("population");

                // Build the current countryDemographics object and add it to the list.
                CountryDemographics countryDemographics = new CountryDemographics.Builder()
                        .countryName(countryName)
                        .population(population)
                        .build();
                countryPopulationList.add(countryDemographics);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("sql exception:" + e.getMessage());
        }
        return countryPopulationList;
    }
}
