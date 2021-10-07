package io.gsyx.feed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedConfig {
    @SerializedName("cacheUrl")
    public String cacheUrl;
    @SerializedName("siteTitle")
    public String siteTitle;
    @SerializedName("cacheMaxDays")
    public int cacheMaxDays;
    @SerializedName("sources")
    public List<Sources> sources;

    public static class Sources {
        public String href;

        @Override
        public String toString() {
            return "Sources{" +
                    "href='" + href + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FeedConfig{" +
                "cacheUrl='" + cacheUrl + '\'' +
                ", siteTitle='" + siteTitle + '\'' +
                ", cacheMaxDays=" + cacheMaxDays +
                ", sources=" + sources +
                '}';
    }
}
