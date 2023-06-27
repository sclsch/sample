package work.hdjava.sample.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class HttpUtil {
    /**
     * 绕过SSL验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sslContext.init(null, new TrustManager[] { trustManager }, null);
        return sslContext;
    }

    /**
     * 绕过SSL证书，发送Post请求（Json形式）
     * @param url
     * @param param
     * @return
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String doIgnoreVerifySSLPost(String url, JSONObject param,String token)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {
        //采用绕过验证的方式处理https请求
        SSLContext sslContext = createIgnoreVerifySSL();
        final SSLConnectionSocketFactory sslsf;

        //设置协议http和https对应的处理socket链接工厂的对象
        sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);

        //创建自定义的httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .build();

        String result = null;
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        //设置参数到请求对象中
        httpPost.setEntity(entity);
        httpPost.setHeader("X-Auth-Token",token);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200){
            //获取结果实体
            HttpEntity httpEntity = response.getEntity();
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(httpEntity,"UTF-8");
        }

        //释放链接
        response.close();

        return result;
    }
    public static String doIgnoreVerifySSLPost(String url, JSONObject param)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {
        //采用绕过验证的方式处理https请求
        SSLContext sslContext = createIgnoreVerifySSL();
        final SSLConnectionSocketFactory sslsf;

        //设置协议http和https对应的处理socket链接工厂的对象
        sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);

        //创建自定义的httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .build();

        String result = null;
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        //设置参数到请求对象中
        httpPost.setEntity(entity);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200){
            //获取结果实体
            HttpEntity httpEntity = response.getEntity();
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(httpEntity,"UTF-8");
        }

        //释放链接
        response.close();

        return result;
    }
    public static String doIgnoreVerifySSLPut(String url, JSONObject param,String token)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {
        //采用绕过验证的方式处理https请求
        SSLContext sslContext = createIgnoreVerifySSL();
        final SSLConnectionSocketFactory sslsf;

        //设置协议http和https对应的处理socket链接工厂的对象
        sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);

        //创建自定义的httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .build();

        String result = null;
        //创建post方式请求对象
        HttpPut httpPost = new HttpPut(url);
        //装填参数
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        //设置参数到请求对象中
        httpPost.setEntity(entity);
        httpPost.setHeader("X-Auth-Token",token);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200){
            //获取结果实体
            HttpEntity httpEntity = response.getEntity();
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(httpEntity,"UTF-8");
        }

        //释放链接
        response.close();

        return result;
    }
}
