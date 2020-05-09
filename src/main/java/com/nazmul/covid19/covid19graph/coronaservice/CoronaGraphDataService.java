package com.nazmul.covid19.covid19graph.coronaservice;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.Timeline;
import com.nazmul.covid19.covid19graph.domain.Country;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoronaGraphDataService implements CovidService {

    public static final String COVID_SERVICE_CACHE = "covid-service-cache";

    private final CoronaApiService webService;

    public CoronaGraphDataService(CoronaApiService webService) {
        this.webService = webService;
    }

    @Override
    @Cacheable(cacheNames = COVID_SERVICE_CACHE)
    public List<Country> findAll() {
        List<Country> countries = webService.countries().getData().stream()
                .map(c -> new SingleCountry(c).toGraphModel().get())
                .collect(Collectors.toList());
        
        List<Timeline> timeLine = webService.timeline().getData();
        countries.add(0, new GlobalCountry(ApiCountry.globalApicountry(), timeLine).toGraphModel().get());

        return countries;
    }

    @Override
    @Cacheable(cacheNames = COVID_SERVICE_CACHE)
    public Optional<Country> getById(String id) {
        if (Objects.equals(WORLD_ISO_CODE, id)) {
        	List<Timeline> timeLine = webService.timeline().getData();
            return new GlobalCountry(ApiCountry.globalApicountry(), timeLine).toGraphModel();
        } else {
            return new SingleCountry(webService.countries(id).getData()).toGraphModel();
        }
    }

    @Scheduled(cron = "${coronaapi.cache.evict.cron}")
    @CacheEvict(cacheNames = COVID_SERVICE_CACHE, allEntries = true)
    public void clearCache() {
    }

}