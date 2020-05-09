package com.nazmul.covid19.covid19graph.coronaservice.apimodel;

import java.time.LocalDate;

public class Timeline {

    private LocalDate date;
    private Double deaths;
    private Double confirmed;
    private Double recovered;
    private Double new_confirmed;
    private Double new_recovered;
    private Double new_deaths;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
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
	public Double getNew_confirmed() {
		return new_confirmed;
	}
	public void setNew_confirmed(Double new_confirmed) {
		this.new_confirmed = new_confirmed;
	}
	public Double getNew_recovered() {
		return new_recovered;
	}
	public void setNew_recovered(Double new_recovered) {
		this.new_recovered = new_recovered;
	}
	public Double getNew_deaths() {
		return new_deaths;
	}
	public void setNew_deaths(Double new_deaths) {
		this.new_deaths = new_deaths;
	}

}
