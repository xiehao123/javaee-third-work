package cn.edu.bjtu.ming.Entity;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class Serialization  implements Serializer<Information> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Information information) {

        return JSON.toJSONBytes(information);
    }

    @Override
    public void close() {

    }
}
