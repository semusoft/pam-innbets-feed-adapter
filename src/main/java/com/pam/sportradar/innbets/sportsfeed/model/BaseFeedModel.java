package com.pam.sportradar.innbets.sportsfeed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFeedModel {
    private String prefix;
    private String type;
    private long id;
    private String betradarId;
}
