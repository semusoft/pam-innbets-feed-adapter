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
@RequestMapping("/api/v1/events")
@Tag(name = "Events Controller", description = "Endpoints for managing sports events")
public class EventController {

    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/{prefix}/{type}/{id}/summary", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get sport event summary - WORKING")
    public String getEventSummary(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getSportEventSummary(language, prefix, type, id);
    }

    @GetMapping(value = "/{language}/{prefix}/{type}/{id}/fixture", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get sport event fixture - WORKING")
    public String getFixture(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getFixture(language, prefix, type, id);
    }
}

