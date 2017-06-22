package com.ybg.component.jwt;


public enum ResultStatusCode {  
    OK(0, "OK"),  
    SYSTEM_ERR(30001, "System error"), INVALID_CLIENTID(500,""), INVALID_PASSWORD(501,""), INVALID_TOKEN(502,""), PERMISSION_DENIED(400,"");  
      
    private int errcode;  
    private String errmsg;  
    public int getErrcode() {  
        return errcode;  
    }  
  
    public void setErrcode(int errcode) {  
        this.errcode = errcode;  
    }  
  
    public String getErrmsg() {  
        return errmsg;  
    }  
  
    public void setErrmsg(String errmsg) {  
        this.errmsg = errmsg;  
    }  
    private ResultStatusCode(int Errode, String ErrMsg)  
    {  
        this.errcode = Errode;  
        this.errmsg = ErrMsg;  
    }  
}  

