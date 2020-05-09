package com.nazmul.covid19.covid19graph.coronaservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.domain.Country;
import com.nazmul.covid19.covid19graph.domain.Day;

public class SingleCountry implements ICountry {

	private Optional<ApiCountry> apiCountry;
	private Optional<Country> country = Optional.empty();
	
	public SingleCountry(ApiCountry apiCountry) {
		super();
		this.apiCountry = Optional.ofNullable(apiCountry);
	}

	@Override
	public Optional<Country> toGraphModel() {
		this.apiCountry.ifPresent(c -> {

            List<Day> days = new ArrayList<>();
            if (c.getTimeline() != null) {
                days = c.getTimeline().stream()
                        .map(t -> new Day(
                                t.getDate(),
                                t.getConfirmed(),
                                t.getDeaths(),
                                t.getRecovered(),
                                t.getNew_confirmed(),
                                t.getNew_deaths(),
                                t.getNew_recovered()
                        ))
                        .collect(Collectors.toList());
            }
            
            country = Optional.of(
            			new Country.CountryBuilder()
            				.withIsoCode(c.getCode())
            				.withName(c.getName())
            				.withPopulatione(c.getPopulation())
            				.withTotalCases(c.getLatest_data().getConfirmed())
            				.withTotalDeaths(c.getLatest_data().getDeaths())
            				.withTotalRecovered(c.getLatest_data().getRecovered())
            				.withDays(days)
            				.build()
            		);
		});
		return this.country;
	}

}
