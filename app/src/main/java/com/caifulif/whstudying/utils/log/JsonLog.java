package com.caifulif.whstudying.utils.log;


import android.util.Log;

import com.caifulif.whstudying.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 皓 on 2018/3/20.
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogUtil.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(LogUtil.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        Util.printLine(tag, true);
        message = headString + LogUtil.LINE_SEPARATOR + message;
        String[] lines = message.split(LogUtil.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        Util.printLine(tag, false);
    }
}
