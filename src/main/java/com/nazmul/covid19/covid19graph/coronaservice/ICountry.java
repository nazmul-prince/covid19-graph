package com.nazmul.covid19.covid19graph.coronaservice;

import java.util.Optional;

import com.nazmul.covid19.covid19graph.domain.Country;

public interface ICountry {
	public Optional<Country> toGraphModel();
}
