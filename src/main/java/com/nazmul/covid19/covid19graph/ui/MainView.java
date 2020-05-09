package com.nazmul.covid19.covid19graph.ui;

import java.util.Objects;

import javax.annotation.PostConstruct;

import com.nazmul.covid19.covid19graph.coronaservice.CoronaApiService;
import com.nazmul.covid19.covid19graph.coronaservice.CoronaGraphDataService;
import com.nazmul.covid19.covid19graph.coronaservice.GeoIpService;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.ApiCountry;
import com.nazmul.covid19.covid19graph.coronaservice.apimodel.DataWrapper;
import com.nazmul.covid19.covid19graph.ui.graphcontent.GraphContentLayout;
import com.nazmul.covid19.covid19graph.ui.graphcontent.GraphPresenterImpl;
import com.nazmul.covid19.covid19graph.ui.graphcontent.ICountryDataPresenter;
import com.nazmul.covid19.covid19graph.ui.graphcontent.IGraphDataPresenter;
import com.syndybat.chartjs.ChartJs;
import com.syndybat.chartjs.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

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
import be.ceau.chart.options.scales.GridLines;
import be.ceau.chart.options.scales.LinearScale;
import be.ceau.chart.options.scales.XAxis;
import be.ceau.chart.options.scales.YAxis;
import be.ceau.chart.options.ticks.LinearTicks;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@PageTitle("Covid19 graph")
@StyleSheet("mainView.css")
@StyleSheet("charts.css")
@Route("")
public class MainView extends VerticalLayout  {
	
    public MainView(GeoIpService geoIpService, CoronaGraphDataService service) {
		String c = service.getById("BD").get().getName();
        Image icon = new Image("images/corona.png", "Icon");
        icon.addClassName("icon");
        HorizontalLayout title = new HorizontalLayout(
                new H1("Covid-19 Dashboard"),
                icon
        );
//        title.setSizeFull();
        title.addClassName("title");
        title.setVerticalComponentAlignment(Alignment.END, icon);
        
        //Create presenter
        ICountryDataPresenter dataPresenter = new GraphPresenterImpl(geoIpService, service);
        //Generate formlayout
        GraphContentLayout contentLayout = new GraphContentLayout(dataPresenter);
        
        Image vaadinImage = new Image("images/ardites.png", "Ardites logo");

        HorizontalLayout footer = new HorizontalLayout(
                new Text("Powered by"),
                vaadinImage,
                new Anchor("https://github.com/", "Browse the source code.")
        );
        
//        footer.setSizeFull();
        footer.addClassName("footer");
        footer.setMargin(true);
        footer.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        add(
                title,
                contentLayout,
                footer
        );
    }
}
