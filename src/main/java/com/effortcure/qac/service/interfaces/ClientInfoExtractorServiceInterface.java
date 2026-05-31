package com.effortcure.qac.service.interfaces;

import com.effortcure.qac.dto.external.GeoLocationDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface ClientInfoExtractorServiceInterface {
    public String extractClientIp(HttpServletRequest request);

    public GeoLocationDTO getLocation(String ip);

    public String extractDeviceInfo(HttpServletRequest request);
}