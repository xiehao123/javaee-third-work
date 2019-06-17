package cn.edu.bjtu.ming.Entity;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class Deserialization implements Deserializer<Information> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Information deserialize(String s, byte[] bytes) {
        return JSON.parseObject(bytes, Information.class);
    }

    @Override
    public void close() {

    }
}
