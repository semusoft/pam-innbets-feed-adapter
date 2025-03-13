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
@RequestMapping("/api/v1/sports")
@Tag(name = "Sports Controller", description = "Endpoints for managing sport")
public class SportController {
    @Autowired
    private SportEventRestXMLService sportEventRestXMLService;

    @GetMapping(value = "/{language}/sports", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Get all sports - WORKING")
    public String getAllSports(@PathVariable String language) {
        return sportEventRestXMLService.getAllSports(language);
    }

}
