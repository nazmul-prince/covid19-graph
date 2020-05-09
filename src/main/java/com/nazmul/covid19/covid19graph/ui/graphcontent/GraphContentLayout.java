package com.nazmul.covid19.covid19graph.ui.graphcontent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.github.appreciated.css.grid.GridLayoutComponent;
import com.github.appreciated.css.grid.sizes.Flex;
import com.github.appreciated.css.grid.sizes.Length;
import com.github.appreciated.css.grid.sizes.MinMax;
import com.github.appreciated.css.grid.sizes.Repeat.RepeatMode;
import com.github.appreciated.layout.FlexibleGridLayout;
import com.github.appreciated.layout.FluentGridLayout;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.domain.Country;
import com.nazmul.covid19.covid19graph.domain.Day;
import com.nazmul.covid19.covid19graph.ui.dashboard.DashboardChart;
import com.nazmul.covid19.covid19graph.ui.dashboard.DashboardStats;
import com.syndybat.chartjs.ChartJs;
import com.syndybat.chartjs.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinRequest;

import be.ceau.chart.BarChart;
import be.ceau.chart.LineChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.data.LineData;
import be.ceau.chart.dataset.BarDataset;
import be.ceau.chart.dataset.LineDataset;
import be.ceau.chart.javascript.JavaScriptFunction;
import be.ceau.chart.options.BarOptions;
import be.ceau.chart.options.Legend;
import be.ceau.chart.options.LineOptions;
import be.ceau.chart.options.Title;
import be.ceau.chart.options.scales.BarScale;
import be.ceau.chart.options.scales.LinearScale;
import be.ceau.chart.options.scales.LinearScales;
import be.ceau.chart.options.scales.XAxis;
import be.ceau.chart.options.scales.YAxis;
import be.ceau.chart.options.ticks.LinearTicks;

public class GraphContentLayout extends VerticalLayout {

    private ComboBox<Country> countrySelector;
	private ICountryDataPresenter dataPresenter;

	public GraphContentLayout(ICountryDataPresenter dataPresenter) {
		this.dataPresenter = dataPresenter;
		
        countrySelector = new ComboBox<>();
        countrySelector.setWidth("100%");
        countrySelector.setItems(dataPresenter.getAllCountry());
        countrySelector.setItemLabelGenerator(Country::getName);
        countrySelector.setPlaceholder("Country");
        
        countrySelector.addValueChangeListener(event -> {
        	if(event.isFromClient()) {
	    		this.removeAll();
	    		addGraphContents(countrySelector.getValue().getIsoCode());
        	}
        });
        setSizeFull();
        String isoCode = dataPresenter.getIsoCode(getIP());
        addGraphContents(isoCode);
        countrySelector.setValue(dataPresenter.getCountry());
	}

    private String getIP() {
        String ip;

        if ((ip = VaadinRequest.getCurrent().getHeader("X-Forwarded-For")) == null) {
            if ((ip = VaadinRequest.getCurrent().getHeader("Via")) == null) {
                ip = VaadinRequest.getCurrent().getRemoteHost();
            }
        }

        return ip;
    }

	private void addGraphContents(String isoCode) {
		dataPresenter.initializeCountry(isoCode);
        DashboardStats s1 = new DashboardStats("Population",
        		dataPresenter.getValue(Country::getPopulation),
        		null,
        		"number-population");
        DashboardStats s2 = new DashboardStats("Cases",
        		dataPresenter.getValue(Country::getTotalCases),
        		dataPresenter.getValue(Country::getPopulation),
        		"number-cases");
        DashboardStats s3 = new DashboardStats("Deaths",
        		dataPresenter.getValue(Country::getTotalDeaths), 
        		dataPresenter.getValue(Country::getTotalCases), 
        		"number-deaths");
        DashboardStats s4 = new DashboardStats("Recovered",
        		dataPresenter.getValue(Country::getTotalRecovered), 
        		dataPresenter.getValue(Country::getTotalCases), 
        		"number-recovered");

//      
	    Component stackedBar = getStackedBarChart();
	    Component lineBar = getLineChart();
	
	    FluentGridLayout layout = new FluentGridLayout()
	              .withTemplateRows(new Flex(.1))
	              .withTemplateColumns(new Flex(1), new Flex(1), new Flex(1), new Flex(1))
	              .withRowAndColumn(s1, 1, 1)
	              .withRowAndColumn(s2, 1, 2)
	              .withRowAndColumn(s3, 1, 3)
	              .withRowAndColumn(s4, 1, 4);
	
	    layout.setWidth("100%");;
	      
	    HorizontalLayout h = new HorizontalLayout();
	    h.setSizeFull();
	      
	    h.add(lineBar, stackedBar);
	    h.setAlignItems(Alignment.AUTO);

        add(countrySelector, layout, h);
	}
//    private Div getStackedBarChart() {
    private DashboardChart getStackedBarChart() {
        BarScale scale = new BarScale()
                .addxAxes(new XAxis<LinearTicks>().setStacked(false))
                .addyAxes(new YAxis<LinearTicks>().setStacked(false));

        BarOptions options = new BarOptions()
        					.setResponsive(true)
        					.setScales(scale);
        
        BarDataset deathDataSet = new BarDataset().addBackgroundColor(Color.RED).setLabel("Death");
        deathDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewDeaths, 8)));
        
        BarDataset recoverDataSet = new BarDataset().addBackgroundColor(Color.GREEN_YELLOW).setLabel("Recovered");
        recoverDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewRecovered, 8)));
        
        BarDataset confirmedDataSet = new BarDataset().addBackgroundColor(Color.AQUA_MARINE).setLabel("Confirmed");
        confirmedDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewCases, 8)));

        BarData barData = new BarData()
			.setLabels(dataPresenter.getDays().getDates(Day::getFormattedDate, 8))
			.addDataset(deathDataSet)
			.addDataset(recoverDataSet)
			.addDataset(confirmedDataSet);

        BarChart barChart = new BarChart(barData, options).setVertical();

        ChartJs chart = new ChartJs(barChart.toJson());

        chart.addClickListener(new ComponentEventListener<ClickEvent>()
        {
            @Override
            public void onComponentEvent(ClickEvent clickEvent)
            {
                Notification.show(String.format("%s : %s : %s", clickEvent.getLabel(), clickEvent.getDatasetLabel(), clickEvent.getValue()),
                        3000, Notification.Position.TOP_CENTER);
            }
        });

//        return wrapToDiv(chart);
        return new DashboardChart(chart);
    }

    private Div wrapToDiv(ChartJs barChartJs)
    {
        Div div = new Div();
        div.add(barChartJs);
        div.setWidth("100%");
        return div;
    }


    private Div getBarChart(){
        BarDataset dataset = new BarDataset()
                .setLabel("sample chart")
                .setData(65, 59, 80, 81, 56, 55, 40)
                .addBackgroundColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.BLACK)
                .setBorderWidth(2);

        BarData data = new BarData()
                .addLabels("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
                .addDataset(dataset);

        JavaScriptFunction label = new JavaScriptFunction(
                "\"function(chart) {console.log('test legend');}\""
        );

        BarOptions barOptions = new BarOptions()
                .setResponsive(true)
                .setTitle(new Title().setText("test"))
                .setLegend(new Legend().setDisplay(true)
                        .setOnClick(label));

        return wrapToDiv(new ChartJs(new BarChart(data,barOptions).toJson()));
    }

//    private Div getLineChart() {
    private DashboardChart getLineChart(){

//        BarDataset deathDataSet = new BarDataset().addBackgroundColor(Color.RED).setLabel("Death");
        LineDataset deathDataSet = new LineDataset().setBorderColor(Color.RED).setLabel("Death");
        deathDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewDeaths, -8)));
        
//        BarDataset recoverDataSet = new BarDataset().addBackgroundColor(Color.GREEN_YELLOW).setLabel("Recovered");

        LineDataset recoverDataSet = new LineDataset().setBorderColor(Color.GREEN_YELLOW).setLabel("Recovered");
        recoverDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewRecovered, -8)));
        
//        BarDataset confirmedDataSet = new BarDataset().addBackgroundColor(Color.AQUA_MARINE).setLabel("Confirmed");

        LineDataset confirmedDataSet = new LineDataset().setBorderColor(Color.AQUA_MARINE).setLabel("Confirmed");
        confirmedDataSet.setData(dataPresenter.toArray(dataPresenter.getDays().getDates(Day::getNewCases, -8)));

//                .addDataset(new LineDataset().addBorderDash(2).setBorderColor(Color.RED).setLabel("Death").addData(22).addData(33).addData(200))
//                .addDataset(new LineDataset().addBorderDash(2).setBorderColor(Color.GREEN_YELLOW).setLabel("Recovered").addData(3).addData(55).addData(11))
//                .addDataset(new LineDataset().addBorderDash(2).setBorderColor(Color.AQUA_MARINE).setLabel("Confirmed").addData(22).addData(33).addData(76));

        LineData data = new LineData()
                .setLabels(dataPresenter.getDays().getDates(Day::getFormattedDate, -8))
                .addDataset(deathDataSet).addDataset(recoverDataSet).addDataset(confirmedDataSet);
        
        LineOptions barOptions = new LineOptions()
                .setResponsive(true)
                .setScales(new LinearScales());
        LineChart lineChart = new LineChart(data,barOptions);

        ChartJs chart = new ChartJs(lineChart.toJson());
//        return wrapToDiv(chart);
        return new DashboardChart(chart);
    }
    
    
}
