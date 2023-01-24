package com.example.integratedsystem.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Wu Hongjian
 */
@Slf4j
public class FileUtils {

    public static boolean saveFile(MultipartFile file,String road, String fileName){
        File reStone = new File(road, fileName+"."+file.getOriginalFilename());
        try {
            log.info("上传文件："+fileName);
            file.transferTo(reStone);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static String getFileFullName(String road, String fileName) {
        File folder = new File(road);
        for (String fileNa : folder.list()) {
            if (fileNa.substring(0,fileNa.indexOf(".")).equals(fileName)){
                return fileNa;
            }
        }
        return null;
    }

    public static byte[] getFileBytes(String road, String fileName){
        InputStream is = null;
        try {
                is = new FileInputStream(road+"\\"+fileName);
                byte[] bytes=new byte[is.available()];
                is.read(bytes);
                return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String generateFileName(Integer subjectId
            , Integer stuId, String stuName, String subjectName){
        return subjectId+"_"+stuId+"_"+stuName+"_"+subjectName;
    }
}
