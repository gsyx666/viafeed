package io.gsyx.feed;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import io.gsyx.feed.model.Atom;
import io.gsyx.feed.model.FeedConfig;
import io.gsyx.feed.model.RSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("start");
        /**
         try {
         String xml= XmlUtil.toStr(XmlUtil.readXML(new FileReader("test.atom")));
         XmlUtil.toFile(XmlUtil.readXML(xml),"D:\\GSYX\\Java\\action-osmosfeed\\test\\test.xml");
         //System.out.println(xml);
         } catch (FileNotFoundException e) {
         e.printStackTrace();
         }
         */
        //feedAction();
        // atomTest();
        dateTest();

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
        FeedConfig feedConfig = Utils.getFeedConfig();
        if (feedConfig == null || feedConfig.sources == null || feedConfig.sources.isEmpty()) {
            StaticLog.error("osmosfeed.yaml 配置错误");
            return;
        }
        for (FeedConfig.Sources sources : feedConfig.sources) {
            RSS rss = Utils.rss2Bean(HttpUtil.get(sources.href));
            if (rss != null) {
                StaticLog.debug(rss.toString());
            }
        }
        //GET请求
        // String content = HttpUtil.get("https://sspai.com/feed");
    }
}
