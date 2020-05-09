package com.nazmul.covid19.covid19graph.domain;

import java.util.Collections;
import java.util.List;

public class Country {

    private String isoCode;

    private String name;

    private Double population;

    private Double totalCases;

    private Double totalDeaths;

    private Double totalRecovered;

    private List<Day> days;
    
    public static class CountryBuilder {
        private String isoCode;

        private String name;

        private Double population;

        private Double totalCases;

        private Double totalDeaths;

        private Double totalRecovered;

        private List<Day> days;
        
        public CountryBuilder() {}

        
        public CountryBuilder withIsoCode(String isoCode) {
        	this.isoCode = isoCode;
        	return this;
        }
        
        public CountryBuilder withName(String name) {
        	this.name = name;
        	return this;
        }
        
        public CountryBuilder withPopulatione(Double population) {
        	this.population = population;
        	return this;
        }
        
        public CountryBuilder withTotalCases(Double totalCases) {
        	this.totalCases = totalCases;
        	return this;
        }
        
        public CountryBuilder withTotalDeaths(Double totalDeaths) {
        	this.totalDeaths = totalDeaths;
        	return this;
        }
        
        public CountryBuilder withTotalRecovered(Double totalRecovered) {
        	this.totalRecovered = totalRecovered;
        	return this;
        }
        
        public CountryBuilder withDays(List<Day> days) {
        	this.days = days;
        	return this;
        }
        
        public Country build() {
        	Country c = new Country(this);
        	return c;
        }
        
    }

	private Country(CountryBuilder builder) {
		this.isoCode = builder.isoCode;
		this.name = builder.name;
		this.population = builder.population;
		this.totalCases = builder.totalCases;
		this.totalDeaths = builder.totalDeaths;
		this.totalRecovered = builder.totalRecovered;
		this.days = builder.days;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public String getName() {
		return name;
	}

	public Double getPopulation() {
		return population;
	}

	public Double getTotalCases() {
		return totalCases;
	}

	public Double getTotalDeaths() {
		return totalDeaths;
	}

	public Double getTotalRecovered() {
		return totalRecovered;
	}

	public List<Day> getDays() {
		return Collections.unmodifiableList(days);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		return true;
	}

}