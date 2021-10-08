package io.gsyx.feed;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.log.StaticLog;
import io.gsyx.feed.model.Atom;
import io.gsyx.feed.model.FeedConfig;
import io.gsyx.feed.model.RSS;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String Linux = "./gh-pages/feed.atom";
    public static String Win = "D:\\GSYX\\Java\\action-osmosfeed\\gh-pages\\feed.atom";

    public static void main(String[] args) {
        System.out.println("start");

        feedAction();
        // atomTest();
        //dateTest();

    }

    public static void dateTest() {
        String pubDate = "Fri, 08 Oct 2021 15:30:00 +0800";
        Utils.pubDate2Updated(pubDate);
    }

    public static void atomTest() {
        Atom atom = new Atom();
        atom.id = "https://gsyx666.github.io/action-osmosfeed/index.html";
        Atom.Entry entry = new Atom.Entry();
        atom.entries = entry.toXml();
        XmlUtil.toFile(XmlUtil.parseXml(atom.toXml()), "D:\\GSYX\\Java\\action-osmosfeed\\test\\test2.xml");

    }

    public static void feedAction() {
        FeedConfig feedConfig = Utils.getFeedConfig("osmosfeed2.yaml");
        if (feedConfig == null || feedConfig.sources == null || feedConfig.sources.isEmpty()) {
            StaticLog.error("osmosfeed.yaml 配置错误");
            return;
        }
        List<RSS> rsss = new ArrayList<>();
        for (FeedConfig.Sources sources : feedConfig.sources) {
            String response = HttpRequest.get(sources.href)
                    .timeout(80000)
                    .execute()
                    .body();
            RSS rss = Utils.rss2Bean(response);
            if (rss != null) {
                rsss.add(rss);
                //StaticLog.debug(rss.toString());
            }
        }
        Atom atom = Utils.rssBean2Atom(rsss);
        if (atom == null) return;
        FileUtil.del(Win);
        FileUtil.writeBytes(atom.toXml().getBytes(), Win);
        //GET请求
        // String content = HttpUtil.get("https://sspai.com/feed");
    }
}
