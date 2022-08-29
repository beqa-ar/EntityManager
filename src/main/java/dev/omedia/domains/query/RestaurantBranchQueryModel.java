package dev.omedia.domains.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestaurantBranchQueryModel {
    private final Long id;
    private final Long restaurantId;
}
