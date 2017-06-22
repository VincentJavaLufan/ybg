/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.baidu.api.domain.BatchRunRequest;
import com.baidu.api.domain.FileItem;
import com.baidu.api.utils.BaiduUtil;
import com.baidu.api.utils.HttpUtil;

/**
 * openapi调用客户端类， 封装了进行调用的基本操作
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaiduApiClient {

    //get请求常量
    public static final String METHOD_GET = "GET";

    //post请求常量
    public static final String METHOD_POST = "POST";

    //BatchRun的请求将按照串行顺序执行
    public static final int BATCH_MODE_SERIAL_ONLY = 1;

    //BatchRun的请求将按照并行顺序执行
    public static final int BATCH_MODE_SERVER_PARALLEL = 0;

    private String accessToken;

    private String clientId;

    /**
     * 创建openapi的调用实例，使用Https的方法访问api
     * 
     * @param accessToken 基于https调用Open API时所需要的访问授权码
     */
    public BaiduApiClient(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 通过accessToken和clientId来构建对象。
     * 如果希望访问public类型的api，请使用该方法初始化BaiduApiClient对象
     * 
     * @param accessToken
     * @param clientId appkey
     */
//    public BaiduApiClient(String accessToken, String clientId) {
//
//    }

    /**
     * 批量处理用户的请求信息，减少网络请求，方法执行结束之后，会遍历
     * queue队列中的各个Request对象,将各个api请求的返回值回填到response属性中
     * 
     * @param queue BatchRunRequest对象集合，集合上限为10
     * @param serialOnly batchRun请求的执行方式，串行或者并行(
     *        BATCH_MODE_SERIAL_ONLY常量或BATCH_MODE_SERVER_PARALLEL常量)
     * @throws BaiduApiException
     * @throws IOException 请求api获取response时发生IOException
     */
    @SuppressWarnings("unchecked")
    public void batchRun(List<BatchRunRequest> queue, int serialOnly) throws BaiduApiException,
            IOException {
        if (queue.size() > 10) {
            return;
        }
        String url = "https://openapi.baidu.com/batch/run";
        JSONArray array = new JSONArray();
        Map<String, String> params = new HashMap<String, String>();
        for (BatchRunRequest batchRunRequest : queue) {
            array.add(batchRunRequest.getBatchRunParam());
        }
        if (serialOnly != 1) {
            serialOnly = 0;
        }
        params.put("serial_only", String.valueOf(serialOnly));
        params.put("access_token", this.accessToken);
        params.put("method", array.toJSONString());
        String response = null;
        response = HttpUtil.doPost(url, params);
        BaiduUtil.checkApiResponse(response);
        //修改batchlist中的请求
        JSONArray responseJson = (JSONArray) JSONValue.parse(response);
        for (int i = 0; i < responseJson.size(); i++) {
            queue.get(i).setResponse(responseJson.get(i).toString());
        }
    }

    /**
     * 针对api请求的方法类
     * 
     * @param url api请求的url地址
     * @param parameters
     *        业务级参数，包括一本的文本参数（key-value都为string类型）、文件参数（key-fileName
     *        String类型；value byte[]）
     * @param method 请求的方法 "GET"/"POST"
     * @return 返回json格式信息
     * @throws IOException
     * @throws BaiduApiException
     */
    public String request(String url, Map<String, String> parameters, String method)
            throws BaiduApiException {
        //截取url中的访问的api的类型 eg https://openapi.baidu.com/public/2.0/mp3/  截取的类型为public类型
        String[] splits = url.split("/");
        String type = splits[3];
        if ("rest".equals(type)) {
            return restRequest(url, parameters, method);
        }
        if ("public".equals(type)) {
            return publicRequest(url, parameters, method);
        }
        return null;
    }

    /**
     * 访问rest类型的api
     * 
     * @param url rest类型的api url地址，使用全路径
     * @param parameters 业务级参数，key-value格式，key、value都必须是String类型
     * @param method 请求的方法 "GET"/"POST"
     * @return 返回 json格式的请求信息
     * @throws IOException 网络请求异常时发生IOException
     * @throws BaiduApiException
     */
    private String restRequest(String url, Map<String, String> parameters, String method)
            throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", this.accessToken);
        if (parameters != null) {
            params.putAll(parameters);
        }
        String response = null;
        try {
            if ("GET".equals(method)) {
                response = HttpUtil.doGet(url, params);
            } else {
                response = HttpUtil.doPost(url, params);
            }
        } catch (IOException e) {
            return null;
        }
        BaiduUtil.checkApiResponse(response);
        return response;

    }

    /**
     * 访问public类型的api请求
     * 
     * @param url public类型的api的全路径uri
     * @param parameters 业务级参数 （key、value均为String类型）
     * @param method 访问api的方法“GET”/“POST”
     * @return json格式数据
     * @throws IOException 当网络发生异常时发生IOException
     * @throws BaiduApiException 当返回的json信息中包含error信息时，抛出BaiduExceptioin
     */
    private String publicRequest(String url, Map<String, String> parameters, String method)
            throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        if (parameters != null) {
            params.putAll(parameters);
        }
        String response = null;
        try {
            if ("GET".equals(method)) {
                response = HttpUtil.doGet(url, params);
            } else {
                response = HttpUtil.doPost(url, params);
            }
        } catch (IOException e) {
            return null;
        }
        BaiduUtil.checkApiResponse(response);
        return response;
    }

    /**
     * 
     * @param url 请求file类型api的权路径url地址
     * @param parameters
     *        业务级参数，包含普通文本参数和文件参数，上传文件参数key-fileName-String，value-
     *        fileContent-byte[]
     * @return 返回json格式的响应数据
     * @throws IOException 当网络发生异常时，抛出IOException
     * @throws BaiduApiException 当json数据中包含error时抛出BaiduException
     */
    public String fileRequest(String url, Map<String, String> parameters,
            Map<String, FileItem> fileParams, int connectTimeOut, int readTimeOut)
            throws BaiduApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", this.accessToken);
        if (parameters != null) {
            params.putAll(params);
        }
        String response = null;
        try {
            response = HttpUtil.uploadFile(url, params, fileParams, connectTimeOut, readTimeOut);
        } catch (IOException e) {
            return null;
        }
        BaiduUtil.checkApiResponse(response);
        return response;

    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
