package dev.omedia.helpers;

import dev.omedia.exceptions.PageNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class Pager {

    public static <T> List<T> getPageContent(final int page, final int pageSize, List<T> content){
        double totalPages = Math.ceil(content.size() / (double) pageSize);
        if (totalPages < page) {
            throw new PageNotFoundException(page);
        }
        return content.stream()
                .skip((long) pageSize * page - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
