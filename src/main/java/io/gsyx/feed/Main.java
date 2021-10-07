package io.gsyx.feed;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import io.gsyx.feed.model.FeedConfig;
import io.gsyx.feed.model.RSS;
import org.w3c.dom.Document;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("start");

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
        String content = HttpUtil.get("https://sspai.com/feed");
        // StaticLog.debug(Utils.rss2Bean(content).toString());

    }
}
