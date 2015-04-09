
package com.thk.thkvisitor.parser;

import org.json.JSONException;

/**
 * @ClassName: BaseParser
 * @Description: TODO
 * @author xiaoming.yuan
 * @date 2013-5-29 上午10:12:26
 */
public abstract class BaseParser<T> {

    public abstract T parseJSON(String paramString) throws JSONException;

    /*    *//**
     * @Title: checkResponse(这里用一句话描述这个方法的作用)
     * @author xiaoming.yuan
     * @data 2013-7-24 下午5:33:50
     * @return String 返回类型
     */
    /*
     * public String checkResponse(String paramString) throws JSONException { if
     * (paramString == null) { return null; } else { JSONObject jsonObject = new
     * JSONObject(paramString); return jsonObject } }
     */
}
