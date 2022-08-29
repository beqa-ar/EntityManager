package dev.omedia.domains.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestaurantQueryModel {
    private final Long id;
    private final String name;
}
