package com.nazmul.covid19.covid19graph.ui.dashboard;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DashboardStats extends VerticalLayout {

    public DashboardStats(String description, double value, Double total, String cssClass) {
        Div descriptionDiv = new Div(new Text(description));
        descriptionDiv.addClassName("stat-description");

        String percentageText = "";
        if (total != null && total != 0) {
            double percentage = (double) value / total * 100;
            String percentageStr = new DecimalFormat("#.##").format(percentage);
            percentageText = "(" + percentageStr + "%)";
        }

        Div valueDiv = new Div(new Text(NumberFormat.getInstance().format(value)));
        valueDiv.addClassName(cssClass);
        valueDiv.addClassName("stat-value");

        Div percentageDiv = new Div(new Text(percentageText));
        percentageDiv.addClassName(cssClass);
        percentageDiv.addClassName("stat-percentage");

        HorizontalLayout numbersLayout = new HorizontalLayout(
                valueDiv,
                percentageDiv
        );
        numbersLayout.setSpacing(false);

        add(
                descriptionDiv,
                numbersLayout
        );
        setSpacing(false);
        setSizeUndefined();
    }

}