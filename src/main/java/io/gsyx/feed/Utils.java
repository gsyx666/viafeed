package io.gsyx.feed;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.gson.Gson;
import io.gsyx.feed.model.Atom;
import io.gsyx.feed.model.FeedConfig;
import io.gsyx.feed.model.RSS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            String title = getValue(channel, "title");
            String pubDate = getValue(channel, "pubDate");
            channelBean.title = title;
            channelBean.pubDate = pubDate;
            Elements items = channel.getElementsByTag("item");

            for (Element item : items) {
                RSS.Channel.Item itemBean = new RSS.Channel.Item();
                itemBean.title = getValue(item, "title");
                itemBean.link = getValue(item, "link");
                itemBean.description = getValue(item, "description");
                itemBean.pubDate = getValue(item, "pubDate");
                itemBean.author = getValue(item, "author");
                channelBean.items.add(itemBean);
            }
            //StaticLog.debug(title + pubDate);
        }
        rss.channel = channelBean;
        return rss;
    }

    public static Atom rssBean2Atom(List<RSS> rsss) {
        if (rsss == null || rsss.isEmpty()) return null;
        Atom atom = new Atom();
        StringBuilder entries = new StringBuilder();
        for (RSS rss : rsss) {
            RSS.Channel channel = rss.channel;
            if (channel == null || channel.items == null || channel.items.isEmpty()) return null;
            for (RSS.Channel.Item item : channel.items) {
                Atom.Entry entry = new Atom.Entry();
                entry.title = item.title;
                entry.id = item.link;
                entry.link = item.link;
                //entry.updated = pubDate2Updated(item.pubDate);// TODO
                entry.updated = item.pubDate;
                entry.summary = item.description;
                entry.author = item.author;
                entries.append(entry.toXml());

            }
        }
        atom.entries = entries.toString();
        if (StrUtil.isEmpty(atom.entries)) {
            atom = null;
        }
        return atom;
    }

    public static String getValue(Element element, String tag) {
        String value = "";
        Elements elements = element.getElementsByTag(tag);
        for (Element element2 : elements) {
            value = element2.text();
        }
        return value;
    }

    public static FeedConfig getFeedConfig(String path) {
        YamlReader reader;
        FeedConfig feedConfig = null;
        try {
            reader = new YamlReader(new FileReader(path));
            Object object = reader.read();
            //System.out.println(object);
            if (object == null) {
                return null;
            }
            Gson gson = new Gson();
            feedConfig = gson.fromJson(new Gson().toJson(object), FeedConfig.class);
        } catch (Exception e) {
            StaticLog.error(e);
        }
        return feedConfig;
    }

    public static FeedConfig getFeedConfig() {
        return getFeedConfig("osmosfeed.yaml");
    }

    public static String pubDate2Updated(String pubDate) {
        String updated = DateUtil.now();
        Date date = new Date(pubDate);
        // StaticLog.debug("updated");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updated = dateformat.format(date);
        // StaticLog.debug(updated);
        return updated;
    }
}
