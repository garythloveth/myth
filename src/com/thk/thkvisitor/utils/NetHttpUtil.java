package com.thk.thkvisitor.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.thk.thkvisitor.model.RequestModel;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 网络请求类
 * @author simon
 *
 */
public class NetHttpUtil {
    private static final String TAG = "NetUtil";

    public final static int FAILED = 1;

    public final static int SUCCESS = 2;

    private final static int TIMEOUT_CONNECTION = 10000;

    private final static int TIMEOUT_SOCKET = 20000;

    private static Header[] headers = new BasicHeader[11];
    static {
        headers[0] = new BasicHeader("Appkey", "");
        headers[1] = new BasicHeader("Udid", "");
        headers[2] = new BasicHeader("Os", "");
        headers[3] = new BasicHeader("Osversion", "");
        headers[4] = new BasicHeader("Appversion", "");
        headers[5] = new BasicHeader("Sourceid", "");
        headers[6] = new BasicHeader("Ver", "");
        headers[7] = new BasicHeader("Userid", "");
        headers[8] = new BasicHeader("Usersession", "");
        headers[9] = new BasicHeader("Unique", "");
        headers[10] = new BasicHeader("Cookie", "");

    }

    public static <T> void getDataFromServer(Context context, RequestModel mRequestModel,
            DataCallback<T> callBack) {
        BaseHandler handler = new BaseHandler(callBack);
        BaseTask taskThread = new BaseTask(context, mRequestModel, handler);
        ThreadPoolManager.getInstance().addTask(taskThread);
    }

    /**
     * post(请求模型)
     * @param mRequestModel
     * @return
     */
    public static Object post(RequestModel mRequestModel) {
        Object obj = null;
        try {
            if (mRequestModel.params == null || mRequestModel.params.size() <= 0
                    || TextUtils.isEmpty(mRequestModel.requestUrl)) {
                return obj;
            }
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
            HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
            // 构造Httpclient实例
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            // 创建post方法实例
            HttpPost httpPost = new HttpPost(mRequestModel.requestUrl);
            // 设置httppost请求参数
            httpPost.setHeaders(headers);
            HttpEntity entity = new UrlEncodedFormEntity(hashMapTOpostParams(mRequestModel.params),
                    HTTP.UTF_8);
            httpPost.setEntity(entity);
            // 使用execute方法发送Http post请求并返回Httpresponse对象
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                setCookie(httpResponse);
                String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                obj = mRequestModel.baseParser.parseJSON(result);
            }
        } catch (ClientProtocolException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;
        } catch (IOException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;
        } catch (JSONException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;

        }
        return obj;
    }

    /**
     * get(请求模型)
     * @param mRequestModel
     * @return
     */
    public static Object get(RequestModel mRequestModel) {
        Object obj = null;
        try {
            if (mRequestModel.params == null || mRequestModel.params.size() <= 0
                    || TextUtils.isEmpty(mRequestModel.requestUrl)) {
                return obj;
            }
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(hashMapTOgetParams(mRequestModel.params,
                    mRequestModel.requestUrl));
            httpGet.setHeaders(headers);
            HttpResponse httpResponse = client.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                setCookie(httpResponse);
                String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                obj = mRequestModel.baseParser.parseJSON(result);
            }
        } catch (ClientProtocolException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;
        } catch (IOException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;
        } catch (JSONException e) {
            LogHelper.i(TAG, e.getLocalizedMessage(), e);
            return null;
        }
        return obj;
    }

    /**
     * 添加Cookie
     * @param response
     */
    private static void setCookie(HttpResponse response) {
        if (response.getHeaders("Set-Cookie").length > 0) {
            LogHelper.d(TAG, response.getHeaders("Set-Cookie")[0].getValue()) ;
            headers[10] = new BasicHeader("Cookie", response.getHeaders("Set-Cookie")[0].getValue());
        }
    }

    /**
     * 把params集合转换为post(NameValuePair)请求参数的集合
     * @param params
     * @return
     */
    public static synchronized List<BasicNameValuePair> hashMapTOpostParams(HashMap<String, Object> params) {

        List<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();
        Iterator<String> iterators = params.keySet().iterator();
        while (iterators.hasNext()) {
            String key = iterators.next();
            BasicNameValuePair pair = new BasicNameValuePair(key, params.get(key).toString());
            result.add(pair);
        }
        return result;
    }

    /**
     * hashmap转化成get请求的参数
     * @param params
     * @param url
     * @return
     */
    public static synchronized String hashMapTOgetParams(HashMap<String, Object> params, String url) {
        if (params == null || params.size() <= 0 || TextUtils.isEmpty(url)) {
            return null;
        }
        StringBuilder buf = new StringBuilder(url);
        buf.append("?");
        Iterator<String> iterators = params.keySet().iterator();
        while (iterators.hasNext()) {
            String key = iterators.next();
            try {
                buf.append(key).append("=")
                .append(URLEncoder.encode(params.get(key).toString(), "UTF-8")).append("&");
//                buf.append(key).append("=")
//                .append(params.get(key).toString()).append("&");
            } catch (UnsupportedEncodingException e) {
                LogHelper.d(TAG, e.getLocalizedMessage());
            }
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    /**
     * hashmap转化成get请求的参数
     * @param params
     * @return
     */
    public static synchronized String hashMapTOgetParams(HashMap<String, Object> params) {
        if (params == null || params.size() <= 0) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        Iterator<String> iterators = params.keySet().iterator();
        while (iterators.hasNext()) {
            String key = iterators.next();
            try {
                buf.append(key).append("=")
                .append(URLEncoder.encode(params.get(key).toString(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                LogHelper.d(TAG, e.getLocalizedMessage());
            }
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL
     * @return
     */
    public static Map<String, String> getParamsTOhashMap(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String strUrlParam = truncateUrl(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组
        String[] arrSplit = strUrlParam.split("[&]");
        String[] arrSplitEqual = null;
        for (String strSplit : arrSplit) {
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (null != arrSplitEqual && arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1].replaceAll("\"", ""));
            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL
     * @return
     */
    public static String truncateUrl(String strURL) {
        String strAllParam = strURL.trim();
        String[] arrSplit = null;
        if (strURL.contains("[?]")) {
            arrSplit = strURL.split("[?]");
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    private static class BaseHandler extends Handler {
        private DataCallback mCallBack;

        public BaseHandler(DataCallback callBack) {
            this.mCallBack = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg); // To change body of overridden methods
            // use File | Settings | File Templates.
            if (msg.what == NetHttpUtil.SUCCESS) {
                if (msg.obj == null) {
                    if (mCallBack != null) {
                        mCallBack.callbackError("请求成功，服务器返回参数有误");
                    }
                } else {
                    if (mCallBack != null) {
                        mCallBack.callbackSuccess(msg.obj);
                    }
                }
            } else if (msg.what == NetHttpUtil.FAILED) {
                if (mCallBack != null) {
                    mCallBack.callbackError("当前网络不可用，请先连接Internet！");
                }

            } else {
                if (mCallBack != null) {
                    mCallBack.callbackError("当前网络不可用，请先连接Internet！");
                }
            }
        }
    }

    private static class BaseTask implements Runnable {
        private Context context;

        private RequestModel mRequestModel;

        private Handler handler;

        public BaseTask(Context context, RequestModel mRequestModel, Handler handler) {
            this.context = context;
            this.mRequestModel = mRequestModel;
            this.handler = handler;
        }

        @Override
        public void run() {
            // To change body of implemented methods use File | Settings | File
            // Templates.
            Object obj = null;
            Message msg = Message.obtain();
            try {
                if (NetWorkUtil.isNetworkAvailable(context)) {
                    obj = NetHttpUtil.get(mRequestModel);
                    msg.what = NetHttpUtil.SUCCESS;
                    msg.obj = obj;
                    handler.sendMessage(msg);
                } else {
                    msg.what = NetHttpUtil.FAILED;
                    msg.obj = obj;
                    handler.sendMessage(msg);
                }
            } catch (Exception e) {
                msg.what = NetHttpUtil.FAILED;
                handler.sendMessage(msg);
            }
        }
    }

    /**
     * @ClassName: DataCallback
     * @author xiaoming.yuan
     * @date 2013-9-26 上午11:10:49
     * @param <T>
     */
    public abstract interface DataCallback<T> {
        /**
         * 回调
         *
         * @param paramObject
         * @param paramBoolean 请求是否成功
         */
        public abstract void callbackSuccess(T paramObject);

        public abstract void callbackError(String error);
    }
}
