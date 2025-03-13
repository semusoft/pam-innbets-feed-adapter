package com.pam.sportradar.innbets.sportsfeed.controller;

import com.pam.sportradar.innbets.sportsfeed.service.SportEventRestXMLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/markets")
@Tag(name = "Markets Controller", description = "Endpoints for managing sport markets")
public class MarketController {

    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get sport markets - WORKING")
    public String getSchedule(@PathVariable String language)  {
        return sportEventRestXMLService.getMarketDescription(language);
    }

    @GetMapping(value = "/void-reasons", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get void reasons - WORKING")
    public String getVoidReasons()  {
        return sportEventRestXMLService.getVoidReasons();
    }

    @GetMapping(value = "/bet-stop-reasons", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get bet stop reasons - WORKING")
    public String getBetStopReasons()  {
        return sportEventRestXMLService.getBetStopReasons();
    }
}
