package com.nazmul.covid19.covid19graph.coronaservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.Timeline;
import com.nazmul.covid19.covid19graph.domain.Country;
import com.nazmul.covid19.covid19graph.domain.Country.CountryBuilder;
import com.nazmul.covid19.covid19graph.domain.Day;

public class GlobalCountry implements ICountry {
	private Optional<List<Timeline>> timelines;
	private Optional<ApiCountry> apiCountry;
	private Optional<Country> country = Optional.empty();
	
	public GlobalCountry(ApiCountry apiCountry, List<Timeline> timelines) {
		super();
		this.apiCountry = Optional.ofNullable(apiCountry);
		this.timelines = Optional.ofNullable(timelines);
	}

	@Override
	public Optional<Country> toGraphModel() {
		CountryBuilder cb = new Country.CountryBuilder()
							.withDays(new ArrayList<>());
		
		this.apiCountry.ifPresent(c -> {

            timelines.ifPresent(tl -> {
            	List<Day> days = tl.stream()
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
            	cb.withDays(days);
            	
            	Timeline lastTimeLine = tl.get(0);
            	cb.withTotalCases(lastTimeLine.getConfirmed())
            		.withTotalDeaths(lastTimeLine.getDeaths())
            		.withTotalRecovered(lastTimeLine.getRecovered());
            });

            country = Optional.of(
	            		cb
	            		.withIsoCode(c.getCode())
	            		.withName(c.getName())
	            		.withPopulatione(c.getPopulation())
	            		.build()
            );
		});
		
		return this.country;
	}
}
