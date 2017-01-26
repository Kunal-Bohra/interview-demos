package com.quickbase.devint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ConcreteStatService class contains utility methods to operate on CountryDemographics objects and
 * return meaningful information to caller methods.
 */
public class ConcreteStatService implements IStatService {

	@Override
    /**
     * Returns an unordered list of countries and their populations in the form of a CountryDemographics object
     * from an API.
     * @return - a list of CountryDemographics object.
     */
    public List<CountryDemographics> GetCountryPopulations() {
        List<CountryDemographics> output = new ArrayList<CountryDemographics>();

        // Pretend this calls a REST API somewhere
        output.add(new CountryDemographics.Builder().countryName("India").population(1182105000).build());
        output.add(new CountryDemographics.Builder().countryName("United Kingdom").population(62026962).build());
        output.add(new CountryDemographics.Builder().countryName("Chile").population(17094270).build());
        output.add(new CountryDemographics.Builder().countryName("Mali").population(15370000).build());
        output.add(new CountryDemographics.Builder().countryName("Greece").population(11305118).build());
        output.add(new CountryDemographics.Builder().countryName("Armenia").population(3249482).build());
        output.add(new CountryDemographics.Builder().countryName("Slovenia").population(2046976).build());
        output.add(new CountryDemographics.Builder().countryName("Bhutan").population(695822).build());
        output.add(new CountryDemographics.Builder().countryName("Aruba (Netherlands)").population(101484).build());
        output.add(new CountryDemographics.Builder().countryName("Maldives").population(319738).build());
        output.add(new CountryDemographics.Builder().countryName("Mayotte (France)").population(202000).build());
        output.add(new CountryDemographics.Builder().countryName("Vietnam").population(86932500).build());
        output.add(new CountryDemographics.Builder().countryName("Germany").population(81802257).build());
        output.add(new CountryDemographics.Builder().countryName("Botswana").population(2029307).build());
        output.add(new CountryDemographics.Builder().countryName("Togo").population(6191155).build());
        output.add(new CountryDemographics.Builder().countryName("Luxembourg").population(502066).build());
        output.add(new CountryDemographics.Builder().countryName("U.S. Virgin Islands (US)").population(106267).build());
        output.add(new CountryDemographics.Builder().countryName("Belarus").population(9480178).build());
        output.add(new CountryDemographics.Builder().countryName("Myanmar").population(59780000).build());
        output.add(new CountryDemographics.Builder().countryName("Mauritania").population(3217383).build());
        output.add(new CountryDemographics.Builder().countryName("Malaysia").population(28334135).build());
        output.add(new CountryDemographics.Builder().countryName("Dominican Republic").population(9884371).build());
        output.add(new CountryDemographics.Builder().countryName("New Caledonia (France)").population(248000).build());
        output.add(new CountryDemographics.Builder().countryName("Slovakia").population(5424925).build());
        output.add(new CountryDemographics.Builder().countryName("Kyrgyzstan").population(5418300).build());
        output.add(new CountryDemographics.Builder().countryName("Lithuania").population(3329039).build());
        output.add(new CountryDemographics.Builder().countryName("U.S.A.").population(309349689).build());
        output.add(new CountryDemographics.Builder().countryName("Saint Vincent and the Grenadines").population(109284)
                .build());

		return output;
	}

    @Override
    /**
     * Consumes two lists of CountryDemographics objects and returns an unordered list of countries and their
     * populations in the form of a CountryDemographics object. This list is a merged list not containing duplicate
     * country name values
     *
     * @param sourceList
     * @param destinationList
     * @return - an unordered list of CountryDemographics objects.
     */
    public List<CountryDemographics> mergeLists(List<CountryDemographics> sourceList,
                                                List<CountryDemographics> destinationList) {
        List<CountryDemographics> finalCountryPopulationList = new ArrayList<CountryDemographics>();

        // Create a Hashmap to store all values.
        final Map<String, CountryDemographics> mergedListMap = new HashMap<String, CountryDemographics>();

        // Add the values from source list to the hashmap.
        for (final CountryDemographics sourceCD : sourceList) {
            mergedListMap.put(sourceCD.getCountryName().toLowerCase(), sourceCD);
        }

        // Add the values from destination list to the hashmap. Values with same countryName are overwritten.
        // Hence, the values from database or destination list are final values stored.
        for (final CountryDemographics destinationCD : destinationList) {
            mergedListMap.put(destinationCD.getCountryName().toLowerCase(), destinationCD);
        }

        // Return a new list of merged CountryDemographics object
        finalCountryPopulationList = new ArrayList<CountryDemographics>(mergedListMap.values());
        return finalCountryPopulationList;
    }

}
