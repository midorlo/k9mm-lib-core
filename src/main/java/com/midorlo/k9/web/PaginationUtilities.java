package com.midorlo.k9.web;

import com.midorlo.k9.configuration.core.CoreConstants;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;

/**
 * Utility class for creating <a href="http://tools.ietf.org/html/rfc5988">RFC 5988</a> compliant Pagination Headers.
 */
public final class PaginationUtilities {

    private PaginationUtilities() {
    }

    /**
     * Generate pagination headers for a Spring Data {@link org.springframework.data.domain.Page} object.
     *
     * @param uriBuilder The URI builder.
     * @param page       The page.
     * @param <T>        The type of object.
     * @return http header.
     */
    public static <T> HttpHeaders generatePaginationHttpHeaders(UriComponentsBuilder uriBuilder, Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CoreConstants.HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        int           pageNumber = page.getNumber();
        int           pageSize   = page.getSize();
        StringBuilder link       = new StringBuilder();
        if (pageNumber < page.getTotalPages() - 1) {
            link.append(prepareLink(uriBuilder, pageNumber + 1, pageSize, "next"))
                .append(",");
        }
        if (pageNumber > 0) {
            link.append(prepareLink(uriBuilder, pageNumber - 1, pageSize, "prev"))
                .append(",");
        }
        link.append(prepareLink(uriBuilder, page.getTotalPages() - 1, pageSize, "last"))
            .append(",")
            .append(prepareLink(uriBuilder, 0, pageSize, "first"));
        headers.add(HttpHeaders.LINK, link.toString());
        return headers;
    }

    private static String prepareLink(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize, String relType) {
        return MessageFormat.format(CoreConstants.HEADER_LINK_FORMAT, preparePageUri(uriBuilder, pageNumber, pageSize), relType);
    }

    private static String preparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder.replaceQueryParam("page", Integer.toString(pageNumber))
                         .replaceQueryParam("size", Integer.toString(pageSize))
                         .toUriString()
                         .replace(",", "%2C")
                         .replace(";", "%3B");
    }
}
