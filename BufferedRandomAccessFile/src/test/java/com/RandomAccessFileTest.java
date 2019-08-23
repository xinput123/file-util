package com;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;

public class RandomAccessFileTest {
    private static final String ENCODING = "UTF-8";
    private static final int NUM = 5000;
    private static File file = new File(ClassLoader.getSystemResource("").getPath() + File.separator + "test.txt");
    private static File randomFile = new File(ClassLoader.getSystemResource("").getPath() + File.separator + "RandomFile.txt");

    /**
     * 生成1000w随机文本文件
     */
    @Test
    public void makePin() {
        String prefix = "您好";
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file, true), ENCODING);
            // 随机生成数据
            for (long j = 0L; j < 100000000L; j++) {
                out.write(prefix + (int) (130000000 * Math.random()) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
        System.out.println(file.getAbsolutePath());
    }

    /**
     * 测试RandomAccessFile读取文件
     */
    @Test
    public void testBufferedRandomAccessRead() {
        long start = System.currentTimeMillis();
//
        System.out.println(file.exists());
        long pos = 0L;
        while (true) {
            Map<String, Object> res = FileUtils.BufferedRandomAccessFileReadLine(file, ENCODING, pos, NUM);
            // 如果返回结果为空结束循环
            if (MapUtils.isEmpty(res)) {
                break;
            }
            List<String> messages = (List<String>) res.get("messages");
            if (CollectionUtils.isNotEmpty(messages)) {
                messages.forEach(message -> System.out.println(message));
                if (messages.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            pos = (Long) res.get("pos");
        }
        System.out.println(((System.currentTimeMillis() - start) / 1000));
    }

    @Test
    public void readByFis() {
        String file = "/Users/yuanlai/Downloads/test/test.txt";
        try (InputStream is = new FileInputStream(file)) {
            byte[] buff = new byte[4096];
            long counts = 0;
            int offset = 0;
            while ((offset = is.read(buff)) != -1) {
                counts = counts + offset;
            }
            System.out.println("counts : " + counts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
