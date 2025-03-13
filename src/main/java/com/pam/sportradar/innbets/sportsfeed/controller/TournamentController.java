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
@RequestMapping("/api/v1/tournaments")
@Tag(name = "Tournaments Controller", description = "Endpoints for managing sport tournaments")
public class TournamentController {
    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/tournament/{prefix}/{urn}/{id}/schedule", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get tournament schedule")
    public String getTournamentSchedule(@PathVariable String language, @PathVariable String prefix, @PathVariable String urn, @PathVariable String id) {
        return sportEventRestXMLService.getTournamentSchedule(language, prefix, urn, id);
    }

    @GetMapping(value = "/{language}/tournaments", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get all tournaments - WORKING")
    public String getTournaments(@PathVariable String language) {
        return sportEventRestXMLService.getTournaments(language);
    }

    @GetMapping(value = "/{language}/tournaments/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get tournament details - WORKING")
    public String getTournaments(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getTournamentDetail(language, prefix, type, id);
    }

    @GetMapping(value = "/{language}/tournaments/seasons/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get available seasons - WORKING")
    public String getAvailableSeasons(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getAvailableSeasons(language, prefix, type, id);
    }

    @GetMapping(value = "/{language}/sports/tournaments/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get available tournaments for a sport - WORKING")
    public String getAvailableSportTournaments(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getAvailableSportTournaments(language, prefix, type, id);
    }
}
