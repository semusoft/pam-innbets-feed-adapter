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
@RequestMapping("/api/v1/competitors")
@Tag(name = "Competitors Controller", description = "Endpoints for managing sport competitors")
public class CompetitorController {

    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/{prefix}/{type}/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get competitor profile - WORKING")
    public String getCompetitorProfile(@PathVariable String language, @PathVariable String prefix, @PathVariable String type, @PathVariable String id) {
        return sportEventRestXMLService.getCompetitorProfile(language, prefix, type, id);
    }
}
