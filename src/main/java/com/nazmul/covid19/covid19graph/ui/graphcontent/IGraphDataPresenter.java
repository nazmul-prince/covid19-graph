package com.nazmul.covid19.covid19graph.ui.graphcontent;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.domain.Country;

public interface IGraphDataPresenter {

	public Double getValue(Function<Country, Double> f);
	public int[] toArray(List<Double> counts);
}
