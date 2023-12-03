package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    public static final int STATUS_OK = 200;

    private HackerNews() {
    }

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static long[] hackerNewsTopStories() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_OK) {
                String[] ids = response.body().replaceAll("[\\[\\]\"]", "").split(",");
                return convertToLongArray(ids);
            }

        } catch (Exception e) {
        }

        return new long[0];
    }

    public static String news(long id) {
        try {
            String itemUrl = String.format(ITEM_URL_FORMAT, id);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(itemUrl))
                .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_OK) {
                String titleRegex = "\"title\":\"(.*?)\"";
                Pattern pattern = Pattern.compile(titleRegex);
                Matcher matcher = pattern.matcher(response.body());

                if (matcher.find()) {
                    return matcher.group(1);
                }
            }

        } catch (Exception e) {
        }

        return "";
    }

    private static long[] convertToLongArray(String[] ids) {
        long[] result = new long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            result[i] = Long.parseLong(ids[i].trim());
        }
        return result;
    }
}
