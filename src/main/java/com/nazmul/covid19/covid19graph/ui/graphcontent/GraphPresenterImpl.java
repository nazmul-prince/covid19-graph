package com.nazmul.covid19.covid19graph.ui.graphcontent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.nazmul.covid19.covid19graph.coronaservice.CoronaGraphDataService;
import com.nazmul.covid19.covid19graph.coronaservice.CovidService;
import com.nazmul.covid19.covid19graph.coronaservice.GeoIpService;
import com.nazmul.covid19.covid19graph.domain.Country;
import com.nazmul.covid19.covid19graph.domain.Day;

public class GraphPresenterImpl implements ICountryDataPresenter, IDayDataPresenter {

	private CovidService covidService;
	private GeoIpService geoIpService;
	
	private Optional<Country> country;
	private Optional<List<Day>> probableDays;
//	private List<R> dates = new ArrayList<R>();

	public GraphPresenterImpl(GeoIpService geoIpService, CoronaGraphDataService service) {
		this.geoIpService = geoIpService;
		this.covidService = service;
	}
	
	public List<Country> getAllCountry() {
		return covidService.findAll();
	}

	@Override
	public void initializeCountry(String isoCode) {
		country = covidService.getById(isoCode);
	}
	
	public Country getCountry() {
		return country.orElse(null);
	}
	
	public Double getValue(Function<Country, Double> f) {
		return country.map(f).get();
	}

//	@Override
	public <R>List<R> getDates(Function<Day, R> f, int numOfDays) {
		Stream<Day> s = probableDays.orElse(new ArrayList<>())
				.stream();
		
		if(numOfDays > 0) {
			s = s.limit(numOfDays);
		}
				;
		
		return s.sorted((d1, d2) -> d1.getDate().compareTo(d2.getDate()))
				.map(f)
				.collect(Collectors.toList());
	}

	@Override
	public IDayDataPresenter getDays() {
		probableDays = country.map(Country::getDays);
		return this;
	}

	@Override
	public int[] toArray(List<Double> counts) {
		return counts.stream().mapToInt(d -> d.intValue()).toArray();
	}

	@Override
	public String getIsoCode(String ip) {
		return geoIpService.getIsoCode(ip);
	}


}
