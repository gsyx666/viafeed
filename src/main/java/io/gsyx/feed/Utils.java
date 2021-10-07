package io.gsyx.feed;


import cn.hutool.log.StaticLog;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.gson.Gson;
import io.gsyx.feed.model.FeedConfig;
import io.gsyx.feed.model.RSS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.util.ArrayList;

public class Utils {

    public static RSS rss2Bean(String rssXml) {
        Document doc = Jsoup.parse(rssXml);
        Elements channels = doc.getElementsByTag("channel");
        if (channels.isEmpty()) {
            return null;
        }
        RSS rss = new RSS();
        RSS.Channel channelBean = new RSS.Channel();
        channelBean.items = new ArrayList<>();
        for (Element channel : channels) {
            String title =getValue(channel,"title");
            String pubDate =getValue(channel,"pubDate");
            channelBean.title = title;
            channelBean.pubDate = pubDate;
            Elements items = channel.getElementsByTag("item");

            for (Element item : items) {
                RSS.Channel.Item itemBean = new RSS.Channel.Item();
                itemBean.title = getValue(item, "title");
                itemBean.link = getValue(item, "link");
                itemBean.description =getValue(item, "description");
                itemBean.pubDate = getValue(item, "pubDate");
                itemBean.author = getValue(item, "author");
                channelBean.items.add(itemBean);
            }
            StaticLog.debug(title + pubDate);
        }
        rss.channel = channelBean;
        return rss;
    }

    public static String getValue(Element element, String tag) {
        String value = "";
        Elements elements = element.getElementsByTag(tag);
        for (Element element2 : elements) {
            value = element2.text();
        }
        return value;
    }

    public static FeedConfig getFeedConfig() {
        YamlReader reader = null;
        FeedConfig feedConfig = null;
        try {
            reader = new YamlReader(new FileReader("osmosfeed.yaml"));
            Object object = reader.read();
            //System.out.println(object);
            if (object == null) {
                return null;
            }
            Gson gson = new Gson();
            feedConfig = gson.fromJson(new Gson().toJson(object), FeedConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedConfig;
    }
}
