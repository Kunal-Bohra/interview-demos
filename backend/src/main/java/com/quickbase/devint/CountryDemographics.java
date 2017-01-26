package com.quickbase.devint;


import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * Created by Owner on 1/24/2017.
 */
public final class CountryDemographics {

    @NotNull
    private String countryName;
    @NotNull
    private Integer population;

    private CountryDemographics(final Builder Builder) {
        countryName = Builder.countryName;
        population = Builder.population;
    }

    public CountryDemographics(String countryName, Integer population) {
        this.countryName = countryName;
        this.population = population;
    }

    public String getCountryName() {
        return countryName;
    }

    public CountryDemographics setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public CountryDemographics setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(countryName);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final CountryDemographics otherCountryDemographics = (CountryDemographics) other;
        return com.google.common.base.Objects.equal(countryName, otherCountryDemographics.countryName);
    }

    /**
     * Build an immutable instance of CountryDemographics.
     */
    public static class Builder {
        private String countryName;
        private Integer population;

        public Builder() {
        }

        public Builder(final CountryDemographics countryDemographics) {
            countryName(countryDemographics.countryName);
            population(countryDemographics.population);
        }

        public Builder countryName(final String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder population(final Integer population) {
            this.population = population;
            return this;
        }

        public CountryDemographics build() {
            return new CountryDemographics(this);
        }
    }
}
