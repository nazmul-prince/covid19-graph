package com.nazmul.covid19.covid19graph.coronaservice.apimodel;

import java.util.List;

public class ApiCountry {

    private Coordinates coordinates;
    private String name;
    private String code;
    private Double population;
    private LatestData latest_data;
    private List<Timeline> timeline;
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getPopulation() {
		return population;
	}
	public void setPopulation(Double population) {
		this.population = population;
	}
	public LatestData getLatest_data() {
		return latest_data;
	}
	public void setLatest_data(LatestData latest_data) {
		this.latest_data = latest_data;
	}
	public List<Timeline> getTimeline() {
		return timeline;
	}
	public void setTimeline(List<Timeline> timeline) {
		this.timeline = timeline;
	}
	
	public static ApiCountry globalApicountry() {
        ApiCountry world = new ApiCountry();
        world.setCode("global");
        world.setName("Global");
        world.setPopulation(7800000000d);
        
        return world;
	}

}
