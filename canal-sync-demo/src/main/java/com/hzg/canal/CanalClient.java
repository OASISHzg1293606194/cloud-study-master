package com.hzg.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.canal
 * @Description: Canal客户端
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-31 18:51
 */
public class CanalClient {

    public static void main(String[] args) {
        // 获取连接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.213.225", 11111),
                "example", "", "");
        while (true) {
            // 连接canal服务端
            connector.connect();
            // 订阅数据库
            connector.subscribe("test_canal_sync.*");
            // 每次拉取100条Message，变化数据超过100每次只拉取100，少于100则拉取全部，不阻塞
            Message message = connector.get(100);
            // 获取Entry集合
            List<CanalEntry.Entry> entryList = message.getEntries();
            if (null == entryList || entryList.isEmpty()) {
                System.out.println("====================>未拉取到数据，休眠2秒钟");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("====================>未拉取到数据，休眠异常:" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                // 遍历entryList，解析Entry
                for (CanalEntry.Entry entry : entryList) {
                    // 获取表名
                    String tableName = entry.getHeader().getTableName();
                    // 获取类型
                    CanalEntry.EntryType entryType = entry.getEntryType();
                    // 获取序列化后的数据
                    ByteString storeValue = entry.getStoreValue();

                    // 判断类型是否为ROWDATA
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                        try {
                            // 反序列化
                            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                            // 获取当前事件的操作类型
                            CanalEntry.EventType eventType = rowChange.getEventType();
                            // 获取数据集
                            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                            // 遍历rowDatasList并打印数据集
                            for (CanalEntry.RowData rowData : rowDatasList) {
                                // 改变前的数据
                                JSONObject beforeData = new JSONObject();
                                List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                                for (CanalEntry.Column column : beforeColumnsList) {
                                    beforeData.put(column.getName(), column.getValue());
                                }
                                // 改变后的数据
                                JSONObject afterData = new JSONObject();
                                List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                                for (CanalEntry.Column column : afterColumnsList) {
                                    afterData.put(column.getName(), column.getValue());
                                }

                                // 打印最终结果
                                System.out.println("Table:" + tableName +
                                        "\nEventType:" + eventType +
                                        "\nBeforeData:" + beforeData.toJSONString() +
                                        "\nAfterData:" + afterData.toJSONString());
                            }
                        } catch (InvalidProtocolBufferException e) {
                            System.out.println("====================>反序列化异常:" + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("====================>非ROWDATA数据类型:" + entryType);
                    }
                }
            }
        }
    }

}
