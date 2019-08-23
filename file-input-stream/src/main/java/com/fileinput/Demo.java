package com.fileinput;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @Date: 2019-08-23 22:08
 */
public class Demo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String file = "/Users/yuanlai/Downloads/test/test.txt";
        try (InputStream is = new FileInputStream(file)) {
            byte[] buff = new byte[4096];
            long counts = 0;
            int offset = 0;
            while ((offset = is.read(buff)) != -1) {
                counts = counts + offset;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 : " + (end - start) / 1000);
    }
}
