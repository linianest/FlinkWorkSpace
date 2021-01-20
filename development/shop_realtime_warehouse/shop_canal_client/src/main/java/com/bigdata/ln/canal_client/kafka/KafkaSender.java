package com.bigdata.ln.canal_client.kafka;

import com.bigdata.ln.canal_client.util.ConfigUtil;
import com.ln.canal.bean.CanalRowData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @ProjectName: shop_realtime_warehouse
 * @Package: com.bigdata.ln.canal_client.kafka
 * @Name:KafkaSender
 * @Author:linianest
 * @CreateTime:2020/12/28 18:00
 * @version:1.0
 * @Description TODO:
 */
public class KafkaSender {
    // 定义properties，封装kafka相关参数
    private Properties properties = new Properties();

    // 定义生产者对象，value使用的是自定义序列化方式
    private KafkaProducer<String, CanalRowData> kafkaProducer;

    /**
     * 构造函数，初始化kafka相关参数
     */
    public KafkaSender() {

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConfigUtil.kafkaBootstrap_servers_config());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, ConfigUtil.kafkaBtach_size_config());
        properties.put(ProducerConfig.ACKS_CONFIG, ConfigUtil.kafkaAcks());
        properties.put(ProducerConfig.RETRIES_CONFIG, ConfigUtil.kafkaRetries());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, ConfigUtil.kafkaClient_id_config());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ConfigUtil.kafkaKey_serializer_class_config());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ConfigUtil.kafkaValue_serializer_class_config());

        // 实例化生产者对象
        kafkaProducer = new KafkaProducer<String, CanalRowData>(properties);
    }

    /**
     * 传递参数，将数据写入kafka
     * @param canalRowData
     */
    public void send(CanalRowData canalRowData){
        kafkaProducer.send(new ProducerRecord<String, CanalRowData>(
                ConfigUtil.kafkaTopic(),
                canalRowData
        ));
    }
}
