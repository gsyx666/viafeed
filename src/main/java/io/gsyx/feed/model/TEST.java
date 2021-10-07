package io.gsyx.feed.model;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.Map;

public class TEST {
    public void te(){
        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader("osmosfeed.yaml"));
            Object object = reader.read();
            System.out.println(object);
            Map map = (Map)object;
            System.out.println(map.get("address"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
