package io.gsyx.feed.model;

public class Atom {

    public static String TEST = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?><feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
            "    <id>https://gsyx666.github.io/action-osmosfeed/index.html</id>\n" +
            "    <title>gsyx::feed</title>\n" +
            "    <updated>2021-10-06T01:01:58.096Z</updated>\n" +
            "    <generator>osmosfeed 1.11.2</generator>\n" +
            "    <link href=\"https://gsyx666.github.io/action-osmosfeed/index.html\" rel=\"alternate\"/>\n" +
            "    <link href=\"https://gsyx666.github.io/action-osmosfeed/feed.atom\" rel=\"self\"/>\n" +
            "    <entry>\n" +
            "        <title type=\"html\"><![CDATA[Scroll Shadows With JavaScript]]></title>\n" +
            "        <id>https://css-tricks.com/?p=352754</id>\n" +
            "        <link href=\"https://css-tricks.com/scroll-shadows-with-javascript/\"/>\n" +
            "        <updated>2021-10-05T22:40:21.000Z</updated>\n" +
            "        <summary type=\"html\"><![CDATA[Scroll shadows are when you can see a little inset shadow on elements if (and only if) you can scroll in that direction. It’s just good UX. You can actually pull it off in CSS, which I think is …\n" +
            "The post Scroll Shadows With JavaScript appeared first on CSS-Tricks. You can support CSS-Tricks by being an MVP Supporter.]]></summary>\n" +
            "        <author>\n" +
            "            <name>Chris Coyier</name>\n" +
            "        </author>\n" +
            "    </entry>\n" +
            "</feed>";
    public String id;
    public String title;
    public String updated;
    public String generator;
    public String entries;

    private static String getEvEntries(String str, String str2) {
        return str + "\n" + str2;
    }

    public static class Entry {
        public String title;
        public String id;
        public String link;
        public String updated;
        public String summary;
        public String author;


        @Override
        public String toString() {
            return toXml();
        }

        public String toXml() {
            return "    <entry>\n" +
                    "        <title type=\"html\"><![CDATA[" + title + "]]></title>\n" +
                    "        <id>" + id + "</id>\n" +
                    "        <link href=\"" + link + "\"/>\n" +
                    "        <updated>" + updated + "</updated>\n" +
                    "        <summary type=\"html\"><![CDATA[" + summary + "]]></summary>\n" +
                    "        <author>\n" +
                    "            <name>" + author + "</name>\n" +
                    "        </author>\n" +
                    "    </entry>\n";
        }

        public String toXml2() {
            return "<entry>" +
                    "<title type=\"html\"><![CDATA[" + title + "]]></title>" +
                    "<id>" + id + "</id>" +
                    "<link href=\"" + link + "\"/>" +
                    "<updated>" + updated + "</updated>" +
                    "<summary type=\"html\"><![CDATA[" + summary + "]]></summary>" +
                    "<author>\n" +
                    "    <name>" + author + "</name>\n" +
                    "    </author>" +
                    "</entry>";
        }
    }

    public String toXml() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?><feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
                "   <id>" + id + "</id>\n" +
                "    <title>" + title + "</title>\n" +
                "    <updated>" + updated + "</updated>\n" +
                "    <generator>osmosfeed 1.11.2</generator>\n" +
                "    <link href=\"https://gsyx666.github.io/action-osmosfeed/index.html\" rel=\"alternate\"/>\n" +
                "    <link href=\"https://gsyx666.github.io/action-osmosfeed/feed.atom\" rel=\"self\"/>\n" +
                entries +
                "</feed>";
    }
}
