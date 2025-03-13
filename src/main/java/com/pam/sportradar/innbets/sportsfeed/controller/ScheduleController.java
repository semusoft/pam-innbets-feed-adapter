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
@RequestMapping("/api/v1/schedule")
@Tag(name = "Schedule Controller", description = "Endpoints for managing sports schedule")
public class ScheduleController {

    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/{date}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get sport event schedule - WORKING")
    public String getSchedule(@PathVariable String language, @PathVariable String date) {
        return sportEventRestXMLService.getSchedule(language, date);
    }

    @GetMapping(value = "/live", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get live schedule")
    public String getLiveSchedule() {
        return sportEventRestXMLService.getLiveSchedule();
    }

    @GetMapping(value = "/{language}/prematch", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get pre-match schedule")
    public String getPreMatchSchedule(@PathVariable String language) {
        return sportEventRestXMLService.getPreMatchSchedule(language);
    }
}
