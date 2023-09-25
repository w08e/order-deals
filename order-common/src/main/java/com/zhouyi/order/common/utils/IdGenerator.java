package com.zhouyi.order.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jinyueWang
 * @date 2023/9/12
 */


public class IdGenerator {

    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));

    private long workerId;
    private long datacenterId;

    public IdGenerator(long workerId, long datacenterId) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public String nextId() {
        long snowflakeId = getSnowflakeId();
        String timeString = getTimeString();

        return timeString + String.format("%019d", snowflakeId);
    }

    private long getSnowflakeId() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(workerId, datacenterId);
        return idWorker.nextId();
    }

    private String getTimeString() {
        SimpleDateFormat sdf = IdGenerator.sdf.get();
        return sdf.format(new Date());
    }

    class SnowflakeIdWorker {

        private long workerId;
        private long datacenterId;
        private long sequence = 0L;
        private long twepoch = 1288834974657L;

        private long lastTimestamp = -1L;

        public SnowflakeIdWorker(long workerId, long datacenterId) {
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        public synchronized long nextId() {
            long timestamp = timeGen();

            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        "Clock moved backwards.  Refusing to generate id");
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) % 4096;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;

            return ((timestamp - twepoch) << timestampLeftShift) |
                    (datacenterId << datacenterIdShift) |
                    (workerId << workerIdShift) |
                    sequence;
        }

        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        private long timeGen() {
            return System.currentTimeMillis();
        }

        //移位偏移量
        private static final long timestampLeftShift = 22;
        private static final long datacenterIdShift = 17;
        private static final long workerIdShift = 12;

        private final ThreadLocal<SimpleDateFormat> sdf
                = new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMddHHmmss");
            }
        };

        // 获取格式化器

//        public static void main(String[] args) {
//            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
//            for (int i = 0; i < 10; i++) {
//                long id = idWorker.nextId();
//                System.out.println(id);
//            }
//        }
    }

    public static void main(String[] args) {
        IdGenerator generator = new IdGenerator(1, 1);
        for (int i = 0; i < 10; i++) {
            String id = generator.nextId();
            System.out.println(id);
            System.out.println(id.length());
        }
    }
}

