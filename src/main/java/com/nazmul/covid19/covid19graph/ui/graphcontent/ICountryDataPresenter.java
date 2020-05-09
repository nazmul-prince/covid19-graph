package com.nazmul.covid19.covid19graph.ui.graphcontent;

import java.util.List;

import com.nazmul.covid19.covid19graph.domain.Country;

public interface ICountryDataPresenter extends IGraphDataPresenter {

	public List<Country> getAllCountry();

	public void initializeCountry(String isoCode);
	
	public IDayDataPresenter getDays();
	
	public String getIsoCode(String ip);
	
	public Country getCountry();
}
