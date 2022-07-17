package jvm.dump;

import java.util.ArrayList;
import java.util.List;

public class GCTest {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024*100];
            list.add(arr);
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {

            }
        }
    }
}
