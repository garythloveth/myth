
package com.thk.thkvisitor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

/**
 * 
 * @author simon
 *
 */
public abstract class BaseData extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保存文件路径
     */
    private String path;

    private String fileName;

    public static final String VERSION = "version";

    private String version;

//    public BaseData(String jsonObject) {
//        this.put(VERSION, PayManager.SDK_VERSION);
//    }

    /**
     * 设置文件名称
     */
    protected void setPath(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * 变量转换为json字符串
     *
     * @return
     */
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        JSONObject Json = new JSONObject();
        Iterator<Entry<String, Object>> it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            try {
                Json.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace(); // To change body of catch statement use
                                     // File | Settings | File Templates.
            }
        }
        return Json.toString();
    }

    /**
     * json字符串转化为数组对象
     *
     * @return
     */
    public byte[] getBytes() {
        String str = toString();
        if (!TextUtils.isEmpty(str)) {
            return str.getBytes();
        }
        return null;
    }

    /**
     * 获取保存文件名
     *
     * @return
     */
//    public String getPath() {
//        return this.path + CommonUtil.getMd5Str(fileName);
//    }

    /**
     * 初始化Map
     */
    protected abstract void initMap();
}
