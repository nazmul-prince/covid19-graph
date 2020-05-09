package com.nazmul.covid19.covid19graph.coronaservice;

public class CountryGenerator {
	private CoronaApiService webService;

	private CountryGenerator(CoronaApiService webService) {
		this.webService = webService;
	}
	
	public static CountryGenerator generate(CoronaApiService service) {
		return new CountryGenerator(service);
	}
}
