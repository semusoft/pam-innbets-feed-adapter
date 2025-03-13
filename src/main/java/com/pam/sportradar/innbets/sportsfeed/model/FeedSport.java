package com.pam.sportradar.innbets.sportsfeed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedSport extends BaseFeedModel {
    private String name;
    private List<FeedCategory> categories;
}
