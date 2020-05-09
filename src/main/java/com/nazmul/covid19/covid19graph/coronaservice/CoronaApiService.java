package com.nazmul.covid19.covid19graph.coronaservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.DataWrapper;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.Timeline;

@FeignClient(name = "coronaapi", url = "${coronaapi.url}")
public interface CoronaApiService {

    @RequestMapping(value = "/countries")
    DataWrapper<List<ApiCountry>> countries();

    @RequestMapping(value = "/countries/{code}")
    DataWrapper<ApiCountry> countries(@PathVariable(value = "code") String code);

    @RequestMapping(value = "timeline")
    DataWrapper<List<Timeline>> timeline();

}
