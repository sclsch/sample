package work.hdjava.sample.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Author: suncl
 * @Date: 2021/7/27 14:44
 */
@Slf4j
public class LogUtil {

    File file;
    public LogUtil(String logPath, String logFileName){
        try{
            file = new File(logPath + File.separator +logFileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("日志初始化失败：{}", e);
        }
    }

    /**
     * 写文件日志
     * @param content 日志内容
     */
    public void log(String content){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            Writer writer = new OutputStreamWriter(fileOutputStream, "utf-8");
            String newline = System.getProperty("line.separator");
            writer.append(content);
            newline = System.getProperty("line.separator");
            writer.append(newline);
            writer.flush();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("日志append失败:{}", e);
        }
    }
//    public void close(){
//        if(out != null){
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                log.info("关闭流失败:{}" , e);
//            }
//        }
//    }
}
