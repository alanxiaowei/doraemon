package com.alanma.doraemon.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.alanma.doraemon.utils.time.DateUtil;

public class FileUtils {

    public static String getRelativeDownloadPath(String downloadFolder) {
    	Date date=new Date();
        int month = DateUtil.getMonth(date);
        int day = DateUtil.getDay(date);
        String relativePath = month + "/" + day;
        File downloadFolderFile = new File(downloadFolder, relativePath);
        if (!downloadFolderFile.exists()) {
            downloadFolderFile.mkdirs();
        }
        return relativePath;
    }
    
    public static String getAbsDownloadPath(String downloadFolder, String relativePath) {
        File downloadFolderFile = new File(downloadFolder, relativePath);
        return downloadFolderFile.getAbsolutePath();
    }
    
    public static void downloadFile(String remoteFilePath, String localFilePath) throws IOException {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
