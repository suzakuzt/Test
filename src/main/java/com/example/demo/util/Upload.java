package com.example.demo.util;




import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


import java.io.*;
import java.nio.charset.Charset;

public class Upload {


    public static String  httpUpLoad(String url,String file,String type){
        //1:创建一个httpclient对象
        org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
        Charset charset = Charset.forName("UTF-8");//设置编码
        try {
            //2：创建http的发送方式对象，是GET还是post
            HttpPost httppost = new HttpPost(url);

            //3：创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传，很简单的。
            MultipartEntity reqEntity = new MultipartEntity();

            FileBody bin = new FileBody(new File(file));

            StringBody comment = new StringBody(type,charset);
            reqEntity.addPart("file",bin);
            reqEntity.addPart("type", comment);
            httppost.setEntity(reqEntity);

            //4：执行httppost对象，从而获得信息
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("返回状态码："+statusCode);
            HttpEntity resEntity = response.getEntity();

            //获得返回来的信息，转化为字符串string
            String resString = EntityUtils.toString(resEntity);
            System.out.println(resString);
            return resString;

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { httpclient.getConnectionManager().shutdown(); } catch (Exception ignore) {}
        }
        return "";
    }

}