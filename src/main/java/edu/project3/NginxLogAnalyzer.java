package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NginxLogAnalyzer {

    private static final String LOG_ENTRY_REGEX =
        "^(?<remoteAddr>[^\\s]+) - (?<remoteUser>[^\\s]+) \\[(?<timeLocal>[^\\]]+)] \"(?<request>[^\"]+)\" "
            + "(?<status>\\d+) (?<bodyBytesSent>\\d+) \"(?<httpReferer>[^\"]+)\" \"(?<httpUserAgent>[^\"]+)\"$";

    private final static Logger LOGGER = LogManager.getLogger();
    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(LOG_ENTRY_REGEX);

    private String path;
    private String from;
    private String to;
    private String format;

    public NginxLogAnalyzer(String path, String from, String to, String format) {
        this.path = path;
        this.from = from;
        this.to = to;
        this.format = format;
    }

    public static void test(String[] args) {
        Options options = new Options();
        String pathOption = "path";
        options.addOption(pathOption, true, "Путь к лог-файлам");
        String fromOption = "from";
        options.addOption(fromOption, true, "Начальная дата");
        String optOption = "to";
        options.addOption(optOption, true, "Конечная дата");
        String formatOption = "format";
        options.addOption(formatOption, true, "Формат вывода");

        // Парсинг аргументов
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String path = cmd.getOptionValue(pathOption);
            String from = cmd.getOptionValue(fromOption);
            String to = cmd.getOptionValue(optOption);
            String format = cmd.getOptionValue(formatOption);

            NginxLogAnalyzer logAnalyzer = new NginxLogAnalyzer(path, from, to, format);
            logAnalyzer.analyzeLogs(path);
        } catch (ParseException e) {
        }
    }

    public void analyzeLogs(String path) {
        List<LogRecord> logRecords = readAndTransformLogs(path);
        LogReport logReport = LogReport.generateReport(logRecords);

        printReport(logReport);
    }

    private static List<LogRecord> readAndTransformLogs(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            return lines.stream()
                .map(NginxLogAnalyzer::parseLogEntry)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        } catch (IOException e) {
        }
        return null;
    }

    private static LogRecord parseLogEntry(String logEntry) {
        Matcher matcher = LOG_ENTRY_PATTERN.matcher(logEntry);
        if (matcher.matches()) {
            String remoteAddr = matcher.group("remoteAddr");
            String remoteUser = matcher.group("remoteUser");
            OffsetDateTime timeLocal = OffsetDateTime.parse(matcher.group("timeLocal"));
            String request = matcher.group("request");
            int status = Integer.parseInt(matcher.group("status"));
            int bodyBytesSent = Integer.parseInt(matcher.group("bodyBytesSent"));
            String httpReferer = matcher.group("httpReferer");

            return new LogRecord(
                remoteAddr,
                remoteUser,
                timeLocal,
                request,
                status,
                bodyBytesSent,
                httpReferer
            );
        } else {
            System.err.println("Invalid log entry: " + logEntry);
            return null;
        }
    }

    private void printReport(LogReport logReport) {
        LOGGER.info(logReport);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
