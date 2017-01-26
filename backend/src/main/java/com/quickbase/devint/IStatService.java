package com.quickbase.devint;

import java.util.List;

/**
 * The IStateService interface provides methods for getting country population data from API and
 * other methods to perform operations on the data.
 */
public interface IStatService {

    /**
     * Returns an unordered list of countries and their populations in the form of a CountryDemographics object
     * from an API.
     *
     * @return - a list of CountryDemographics object.
     */
    List<CountryDemographics> getCountryPopulations();

    /**
     * Consumes two lists of CountryDemographics objects and returns an list of countries and their
     * populations in the form of a CountryDemographics object. This list is a merged list not containing duplicate
     * country name values
     *
     * @param sourceList
     * @param destinationList
     * @return - an unordered list of CountryDemographics objects.
     */
    List<CountryDemographics> mergeLists(List<CountryDemographics> sourceList,
                                         List<CountryDemographics> destinationList);

}
