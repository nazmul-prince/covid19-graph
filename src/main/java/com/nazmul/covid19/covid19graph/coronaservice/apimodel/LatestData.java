package com.nazmul.covid19.covid19graph.coronaservice.apimodel;

public class LatestData {

    private Double deaths;
    private Double confirmed;
    public Double getDeaths() {
		return deaths;
	}
	public void setDeaths(Double deaths) {
		this.deaths = deaths;
	}
	public Double getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Double confirmed) {
		this.confirmed = confirmed;
	}
	public Double getRecovered() {
		return recovered;
	}
	public void setRecovered(Double recovered) {
		this.recovered = recovered;
	}
	private Double recovered;

}
