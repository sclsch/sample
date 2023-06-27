package work.hdjava.sample.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by hW3838 on 2017/10/28.
 */
public class PropertiesUtil
{
    private static Logger LOGGER = LogManager.getLogger(PropertiesUtil.class.getName());

    public static HashMap<String, String> readProperties(String filePath)
    {
        Properties props = new Properties();
        HashMap<String, String> oMap = new HashMap<String, String>();
        InputStream in = null;
        if(null != filePath && !"".equals(filePath))
        {
            try
            {
                in = new FileInputStream(filePath);
                props.load(new InputStreamReader(in, "UTF-8"));
                Enumeration<?> en = props.propertyNames();
                while (en.hasMoreElements())
                {
                    String key = (String) en.nextElement();
                    String Property = props.getProperty(key);
                    //System.out.println(key + "=" + Property);
                    if (null != key)
                    {
                        key = key.trim();
                    }
                    if (null != Property)
                    {
                        Property = Property.trim();
                    }
                    oMap.put(key, Property);
                }
            }
            catch (Exception e)
            {
                LOGGER.error("path=" + filePath + "。readProperties failed");
                //System.out.println("error " + "path=" + filePath + "。readProperties failed");
            }
            finally
            {
                if (in != null)
                {
                    try
                    {
                        in.close();
                    }
                    catch (IOException ie)
                    {
                        LOGGER.error("Error" + ie.getMessage(), ie);
                        //System.out.println("Error" + ie.getMessage() + ie);
                    }
                }
            }
        }
        return oMap;
    }
}
