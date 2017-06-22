package com.ybg.weixinMenu;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;

/**
 * 返回的xml
 * @author yonson
 */
public class XmlView implements View{
    
    static Logger logger = Logger.getLogger(XmlView.class);
    
    public String returnValue;
    
    public XmlView(){
        
    }
    
    public XmlView(String returnValue){
        this.returnValue = returnValue;
    }
    
    @Override
    public String getContentType() {
        // TODO Auto-generated method stub
        return "text/plain;charset=UTF-8";
    }

    @Override
    public void render(Map arg0, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        PrintWriter out = response.getWriter();
        logger.info("服务返回的数据："+returnValue.trim());
        out.print(returnValue);
        out.close();
        
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
    
}
