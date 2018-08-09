package org.adidas.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;


public class Files extends FileUtils {
    public final static String TMP_DIRECTORY = ".\\tmp";
    final static Logger log = Logger.getLogger(Files.class.getName());

    public static void cleanDirectory(String path) {
        try {
            if (isExists(path))
                cleanFolder(path);
            log.info("Finish cleaning  " + path);
        } catch (IOException e) {
            log.fatal(e.getMessage());
        }
    }

    private static void cleanFolder(String path) throws IOException {
        FileUtils.cleanDirectory(new File(path));
    }

    private static boolean isExists(String path) {
        return new File(path).exists();
    }

    public static void copyFiles(String filePath, File scrFile) throws IOException {
        FileUtils.copyFile(scrFile, new File(filePath));
    }
}
