package io.gsyx.feed.model;

import java.util.List;

public class RSS {

    public Channel channel;

    public static class Channel {

        public String title;
        public String link;
        public String description;
        public String pubDate;
        public List<Item> items;

        public static class Item {
            public String title;
            public String link;
            public String description;
            public String pubDate;
            public String author;

            @Override
            public String toString() {
                return "Item{" +
                        "title='" + title + '\'' +
                        ", link='" + link + '\'' +
                        ", description='" + description + '\'' +
                        ", pubDate='" + pubDate + '\'' +
                        ", author='" + author + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Channel{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", description='" + description + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", items=" + items +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "RSS{" +
                "channel=" + channel +
                '}';
    }

}
