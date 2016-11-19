package com.hybrid.codilarproject;

/**
 * Created by monster on 19/11/16.
 */

import org.json.JSONObject;

public class Utils {
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }

    public static boolean isNotZeroOrNull(String value){
        return value != null && !value.equals("null") && !value.equals("0") ? true : false;
    }

}
