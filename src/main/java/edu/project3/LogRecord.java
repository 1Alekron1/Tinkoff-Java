package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogRecord {

    private static final String LOG_PATTERN =
        "(\\S+) - (\\S+) \\[(.*?)\\] \"(.*?)\" (\\d+) (\\d+) \"(.*?)\" \"(.*?)\"";
    public static final int GROUP_3 = 3;
    public static final int GROUP_4 = 4;
    public static final int GROUP_5 = 5;
    public static final int GROUP_6 = 6;
    public static final int GROUP_7 = 7;
    private String remoteAddr;
    private String remoteUser;
    private OffsetDateTime timeLocal;
    private String request;
    private int status;
    private long bodyBytesSent;
    private String httpReferer;
    private String httpUserAgent;

    public LogRecord() {
    }

    public LogRecord(
        String remoteAddr, String remoteUser, OffsetDateTime timeLocal, String request,
        int status, long bodyBytesSent, String httpReferer
    ) {
        this.remoteAddr = remoteAddr;
        this.remoteUser = remoteUser;
        this.timeLocal = timeLocal;
        this.request = request;
        this.status = status;
        this.bodyBytesSent = bodyBytesSent;
        this.httpReferer = httpReferer;
    }

    public static List<LogRecord> parseLines(List<String> lines) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);

        return lines.stream()
            .map(pattern::matcher)
            .filter(Matcher::matches)
            .map(matcher -> new LogRecord(
                matcher.group(1),
                matcher.group(2),
                OffsetDateTime.parse(matcher.group(GROUP_3)),
                matcher.group(GROUP_4),
                Integer.parseInt(matcher.group(GROUP_5)),
                Long.parseLong(matcher.group(GROUP_6)),
                matcher.group(GROUP_7)
            ))
            .collect(Collectors.toList());
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public OffsetDateTime getTimeLocal() {
        return timeLocal;
    }

    public void setTimeLocal(OffsetDateTime timeLocal) {
        this.timeLocal = timeLocal;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getBodyBytesSent() {
        return bodyBytesSent;
    }

    public void setBodyBytesSent(long bodyBytesSent) {
        this.bodyBytesSent = bodyBytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public void setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }
}


