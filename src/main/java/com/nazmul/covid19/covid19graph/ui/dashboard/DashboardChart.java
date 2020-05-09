package com.nazmul.covid19.covid19graph.ui.dashboard;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Function;

import com.syndybat.chartjs.ChartJs;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DashboardChart extends VerticalLayout {

    public DashboardChart(ChartJs chart) {
    	setId("DashboardChart");
    	this.setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("chart");
        layout.setPadding(false);
        layout.add(wrapToDiv(chart));
        layout.setId("ChartVlayout");
        layout.setSizeFull();
        layout.setAlignItems(Alignment.AUTO);
        setAlignItems(Alignment.AUTO);
        add(layout);
    }
    
    private Div wrapToDiv(ChartJs chart) {
        Div div = new Div();
        div.add(chart);
        div.setSizeFull();
        return div;
    }

}