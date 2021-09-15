package com.midorlo.k9.web.rest;

import com.midorlo.k9.configuration.core.CoreConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * Utility class for creating <a href="http://tools.ietf.org/html/rfc5988">RFC 5988</a> compliant Pagination Headers.
 */
public final class RestUtilities {

    private static final Logger log = LoggerFactory.getLogger(RestUtilities.class);

    private RestUtilities() {
    }

    /**
     * <p>createAlert.</p>
     *
     * @param applicationName a {@link String} object.
     * @param message         a {@link String} object.
     * @param param           a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("K9-" + applicationName + "-alert", message);
        try {
            headers.add("K9-" + applicationName + "-params", URLEncoder.encode(param,
                                                                               StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            // StandardCharsets are supported by every Java implementation so this exception will never happen
        }
        return headers;
    }

    /**
     * <p>createEntityCreationAlert.</p>
     *
     * @param applicationName   a {@link String} object.
     * @param enableTranslation a Boolean.
     * @param entityName        a {@link String} object.
     * @param param             a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityCreationAlert(String applicationName,
                                                        boolean enableTranslation,
                                                        String entityName,
                                                        String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".created"
                                           : "A new " + entityName + " is created with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createEntityUpdateAlert.</p>
     *
     * @param applicationName   a {@link String} object.
     * @param enableTranslation a Boolean.
     * @param entityName        a {@link String} object.
     * @param param             a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityUpdateAlert(String applicationName,
                                                      boolean enableTranslation,
                                                      String entityName,
                                                      String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".updated"
                                           : "A " + entityName + " is updated with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createEntityDeletionAlert.</p>
     *
     * @param applicationName   a {@link String} object.
     * @param enableTranslation a Boolean.
     * @param entityName        a {@link String} object.
     * @param param             a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityDeletionAlert(String applicationName,
                                                        boolean enableTranslation,
                                                        String entityName,
                                                        String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".deleted"
                                           : "A " + entityName + " is deleted with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createFailureAlert.</p>
     *
     * @param applicationName   a {@link String} object.
     * @param enableTranslation a Boolean.
     * @param entityName        a {@link String} object.
     * @param errorKey          a {@link String} object.
     * @param defaultMessage    a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createFailureAlert(String applicationName,
                                                 boolean enableTranslation,
                                                 String entityName,
                                                 String errorKey,
                                                 String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);

        String message = enableTranslation ? "error." + errorKey : defaultMessage;

        HttpHeaders headers = new HttpHeaders();
        headers.add("K9-" + applicationName + "-error", message);
        headers.add("K9-" + applicationName + "-params", entityName);
        return headers;
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
        return MessageFormat.format(CoreConstants.HEADER_LINK_FORMAT, preparePageUri(uriBuilder, pageNumber,
                                                                                     pageSize), relType);
    }

    private static String preparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder.replaceQueryParam("page", Integer.toString(pageNumber))
                         .replaceQueryParam("size", Integer.toString(pageSize))
                         .toUriString()
                         .replace(",", "%2C")
                         .replace(";", "%3B");
    }
}
