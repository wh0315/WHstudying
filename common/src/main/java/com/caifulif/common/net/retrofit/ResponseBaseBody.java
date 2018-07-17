package com.caifulif.common.net.retrofit;

import java.io.Serializable;

/**
 * Created by SuperFan on 2016/8/31.
 *
 * {
 "code": 1,
 "object": null,
 "error": null,
 "message": null,
 "exception": null,
 "result": "<p>\n    这里写你的初始化内容\n</p>",
 "sessionid": 0
 }
 */
public class ResponseBaseBody implements Serializable {


    private String JSON;

    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }

    //    /**
//     * code : 1
//     * object : null
//     * error : null
//     * message : null
//     * exception : null
//     * result : <p>
//     这里写你的初始化内容
//     </p>
//     * sessionid : 0
//     */
//
//    private int code;
//    private Object object;
//    private Object error;
//    private String message;
//    private Object exception;
//    private String result;
//    private int sessionid;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public Object getObject() {
//        return object;
//    }
//
//    public void setObject(Object object) {
//        this.object = object;
//    }
//
//    public Object getError() {
//        return error;
//    }
//
//    public void setError(Object error) {
//        this.error = error;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Object getException() {
//        return exception;
//    }
//
//    public void setException(Object exception) {
//        this.exception = exception;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
//
//    public int getSessionid() {
//        return sessionid;
//    }
//
//    public void setSessionid(int sessionid) {
//        this.sessionid = sessionid;
//    }
}
