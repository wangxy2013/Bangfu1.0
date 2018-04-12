package com.bangfu.utils;

import java.io.File;

public class FileUtil {

    /**
     * 删除一个文件。
     * 注意：如果这个文件是一个目录，将会同时删除该目录下所有的文件！
     *
     * @param file 将要被删除的文件，或者目录
     */
    public static void delete(File file) {
        if (!file.isDirectory()) {
            file.delete();
        } else {
            for (File f : file.listFiles()) {
                delete(f);
            }
        }
    }

    /**
     * 获取文件大小，包括文件夹
     *
     * @param file
     * @return
     */
    public static long getSize(File file) {
        long size = 0l;
        if (!file.isDirectory()) {
            size = file.length();
        } else {
            for (File f : file.listFiles()) {
                size += getSize(f);
            }
        }
        return size;
    }


}
