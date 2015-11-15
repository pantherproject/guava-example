package com.panther.file;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * guava文件的其他操作
 * copy，move(复制，移动，剪切)
 * 在Guava中复制文件操作提供了一组的copy方法，
 * 示例如下：
 * Created by panther.dongdong on 2015/11/15.
 */
public class FileOperateOther {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileOperateOther.class.getName());
    private static final String SOURCE = "./src/main/resources/file/source";
    private static final String TARGET = "./src/main/resources/file/target";


    public static void main(String[] args) {
        LOGGER.info("拷贝文件开始");
        long start = System.currentTimeMillis();
        FileOperateOther.demoFileCopy(SOURCE, TARGET);
        long middle = System.currentTimeMillis();
        LOGGER.info("拷贝文件结束,所花费时间:{}ms", (middle - start));
        LOGGER.info("比较文件开始");
        demoFileEqual(SOURCE, TARGET);
        LOGGER.info("文件比较结束,耗时:{}", (System.currentTimeMillis() - start));
    }

    /**
     * 演示如何使用guava的Files.copy方法复制文件
     * Guava中移动文件使用move方法，用法和copy一样。
     *
     * @param sourceFileName 复制的源文件名
     * @param targetFileName 目标文件名
     */
    public static void demoFileCopy(
            final String sourceFileName, final String targetFileName) {
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try {
            Files.copy(sourceFile, targetFile);
        } catch (Exception e) {
            LOGGER.error("复制文件出现异常:{},异常信息:{}", e, e.getMessage());
        }
    }


    /**
     * 演示 Files.equal(File,File) 来比较两个文件的内容
     * Guava中提供了Files.equal(File,File)方法来比较两个文件的内容是否完全一致，请看下面的示
     *
     * @param fileName1 比较的文件1文件名
     * @param fileName2 比较的文件2文件名
     */
    public static void demoFileEqual(final String fileName1, final String fileName2) {
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        try {
            LOGGER.info("两个文件的比较结果是:{}", Files.equal(file1, file2));
        } catch (Exception e) {
            LOGGER.error(
                    "比较两个文件内容时候发生异常,文件1:{},文件2:{},异常信息为:{},异常为:{}", file1, file2, e.getMessage(), e);
        }
    }
}

/**
 * 补充:
 * Guava的Files类中还提供了其他一些文件的简捷方法。比如
 * touch方法创建或者更新文件的时间戳。
 * createTempDir()方法创建临时目录
 * Files.createParentDirs(File) 创建父级目录
 * getChecksum(File)获得文件的checksum
 * hash(File)获得文件的hash
 * map系列方法获得文件的内存映射
 * getFileExtension(String)获得文件的扩展名
 * getNameWithoutExtension(String file)获得不带扩展名的文件名
 */