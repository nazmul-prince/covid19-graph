 
package com.nazmul.covid19.covid19graph.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Day {

    private LocalDate date;

    private Double cases;

    private Double deaths;

    private Double recovered;

    private Double newCases;

    private Double newDeaths;

    private Double newRecovered;

	public Day() {
		super();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getCases() {
		return cases;
	}

	public void setCases(Double cases) {
		this.cases = cases;
	}

	public Double getDeaths() {
		return deaths;
	}

	public void setDeaths(Double deaths) {
		this.deaths = deaths;
	}

	public Double getRecovered() {
		return recovered;
	}

	public void setRecovered(Double recovered) {
		this.recovered = recovered;
	}

	public Double getNewCases() {
		return newCases;
	}

	public void setNewCases(Double newCases) {
		this.newCases = newCases;
	}

	public Double getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(Double newDeaths) {
		this.newDeaths = newDeaths;
	}

	public Double getNewRecovered() {
		return newRecovered;
	}

	public Day(LocalDate date, Double cases, Double deaths, Double recovered, Double newCases, Double newDeaths,
			Double newRecovered) {
		super();
		this.date = date;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.newCases = newCases;
		this.newDeaths = newDeaths;
		this.newRecovered = newRecovered;
	}

	public void setNewRecovered(Double newRecovered) {
		this.newRecovered = newRecovered;
	}
	
	public String getFormattedDate() {
		return date.format(DateTimeFormatter.ofPattern("dd MMM"));
	}

}