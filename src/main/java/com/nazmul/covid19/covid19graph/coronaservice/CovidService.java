package com.nazmul.covid19.covid19graph.coronaservice;

import java.util.List;
import java.util.Optional;

import com.nazmul.covid19.covid19graph.domain.Country;

public interface CovidService {
	public String WORLD_ISO_CODE = "global";
    List<Country> findAll();

    Optional<Country> getById(String id);

}
