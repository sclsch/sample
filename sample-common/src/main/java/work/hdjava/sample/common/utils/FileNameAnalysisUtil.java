package work.hdjava.sample.common.utils;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameAnalysisUtil {
    public long AnalysisFileName(String devId) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");

        String fileRootPath="D:/dqRadarData/1/"+devId;
        File rootFile=new File(fileRootPath);
        File[] fileLst=rootFile.listFiles();
        int items= 0;
        for(File files : fileLst){

            int fileTimes = Integer.parseInt(files.getName());
            if(fileTimes>items){
                items=fileTimes;
            }
        }
        String childPath=fileRootPath+"/"+items+"";
        File childFile=new File(childPath);
        File[] fileChildLst=childFile.listFiles();
        long childItem=0l;
        for(File filechilds:fileChildLst){

            String bytes = filechilds.getName().substring(0,13);
            Date date=sdf.parse(bytes);
            long timeSpan = date.getTime();
            if(timeSpan>childItem){
                childItem=timeSpan;
            }
        }
        return childItem;
    }

    public void delFileByTime(String devId,long times) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd_HHmm");

        String fileRootPath="D:/dqRadarData/1/"+devId;
        File rootFile=new File(fileRootPath);
        File[] fileLst=rootFile.listFiles();
        if(fileLst.length>0){
            for(File files : fileLst){
                Date date = new Date();
                long dates=date.getTime()-times*24*60*60*1000;
                int fileTimes = Integer.parseInt(sdf.format(new Date(dates)));
                if(Integer.parseInt(files.getName())<fileTimes){
                    try {
                        Path path = Paths.get(fileRootPath+"/"+files.getName());
                        FileSystemUtils.deleteRecursively(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(Integer.parseInt(files.getName())==fileTimes){
                    File childFile=new File(fileRootPath+"/"+files.getName());
                    File[] childFileLst=childFile.listFiles();
                    if(childFileLst.length>0){
                        for(File childfiles : childFileLst){
                            String childFileTime = childfiles.getName().substring(0,13);
                            long datess=date.getTime()-times*24*60*60*1000;
                            int fileTimess = Integer.parseInt(sdf2.format(new Date(dates)));
                            Date childFileDates = sdf2.parse(childFileTime);
                            if(fileTimess>childFileDates.getTime()){
                                childfiles.delete();
                            }
                        }
                    }
                }
            }
        }
    }
}
