package com.nazmul.covid19.covid19graph.coronaservice;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ThemeResolver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GeoIpService {

	private static final Logger LOGGER = Logger.getLogger(GeoIpService.class.getName());
    public static final String WORLD_ISO_CODE = "global";

    private DatabaseReader dbReader;

    public GeoIpService() {
        try {
            File file = new ClassPathResource("GeoLite2-City.mmdb").getFile();
            dbReader = new DatabaseReader.Builder(file).build();
        } catch (IOException e) {
        	LOGGER.log(Level.SEVERE, "Geolite file not found", e.getMessage());
        }
    }

    public String getIsoCode(String ip) {
        String isoCode = WORLD_ISO_CODE;

        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            CountryResponse country = dbReader.country(inetAddress);
            if (country != null) {
                isoCode = country.getCountry().getIsoCode();
            }

        } catch (GeoIp2Exception ignored) {

        } catch (IOException e) {
        	LOGGER.log(Level.SEVERE, "Error getting ISO code");
        }

        return isoCode;
    }

}