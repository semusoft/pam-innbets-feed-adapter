package com.pam.sportradar.innbets.sportsfeed.controller;

import com.pam.sportradar.innbets.sportsfeed.service.SportEventRestXMLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fixtures")
@Tag(name = "Fixtures Controller", description = "Endpoints for managing sport events fixtures")
public class FixtureController {

    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/changes", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get fixture changes")
    public String getFixtureChanges(@PathVariable String language) {
        return sportEventRestXMLService.getFixtureChanges(language);
    }

    @GetMapping(value = "/venues/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get venue profile")
    public String getVenueProfile(@PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getVenueProfile(prefix, type, id);
    }
}
