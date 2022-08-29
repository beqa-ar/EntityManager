package dev.omedia.domains.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GloverQueryModel {
    private final Long id;
    private final String name;
}
