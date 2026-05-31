package com.effortcure.qac.service.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.effortcure.qac.dto.external.GeoLocationDTO;
import com.effortcure.qac.service.interfaces.ClientInfoExtractorServiceInterface;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Primary
public class ClientInfoExtractorService implements ClientInfoExtractorServiceInterface {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String extractClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty()) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public GeoLocationDTO getLocation(String ip) {
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            GeoLocationDTO local = new GeoLocationDTO();
            local.setCountry("Local");
            local.setCity("Local");
            return local;
        }
        String url = "http://ip-api.com/json/" + ip;
        return restTemplate.getForObject(url, GeoLocationDTO.class);
    }

    @Override
    public String extractDeviceInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return (userAgent == null) ? "Unknown Device" : userAgent;
    }
}
