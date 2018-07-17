package com.caifulif.whstudying.modle;

/**
 * Created by 皓 on 2018/3/26.
 */

public class ErrorBean {

    private String code ;
    private String codeDesc ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }


    public ErrorBean(String code, String codeDesc) {
        this.code = code;
        this.codeDesc = codeDesc;
    }
}
