package dev.omedia.domains.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserStatusQueryModel {
    private final Long id;
    private final String name;
}
