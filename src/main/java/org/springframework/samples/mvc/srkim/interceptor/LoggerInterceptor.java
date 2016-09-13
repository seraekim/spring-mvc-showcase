package org.springframework.samples.mvc.srkim.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import static org.fusesource.jansi.Ansi.*;
//import static org.fusesource.jansi.Ansi.Color.*;
/**
 * 
 * shows pre and posthandle with time, remoteIP:port, requestURI. <br>
 * This is likely to be shown as 15:32:11.564 0:0:0:0:0:0:0:1:51642 - /hw/chart/json <br>
 * in the debug mode from a programmer. <br>
 * if there is a need to monitor the whole resource flow including css, js, etc, 
 * then remove the condition of indexOf from if clause.
 * @author Serae Kim
 *
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	private final SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss.SSS");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String uri = request.getRequestURI();
        if (logger.isDebugEnabled() && uri.indexOf("resources")==-1) {
        	//System.out.println("\u001b[0;37m======================================          START         ======================================\u001b[0m");
//        	ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset();
        	System.out.println("\u001b[1;36m ▶ Start "+sf.format(Calendar.getInstance().getTime())+" "
        			+request.getRemoteAddr()+":"+request.getRemotePort()+" - " + uri+"\u001b[0m");
//        	CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        	System.out.println("token: "+token.getToken());
        }
        return super.preHandle(request, response, handler);
    }
     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String uri = request.getRequestURI();
    	if (logger.isDebugEnabled() && uri.indexOf("resources")==-1) {
        	System.out.println("\u001b[0;36m ▶ End "+sf.format(Calendar.getInstance().getTime())+" "
                	+request.getRemoteAddr()+":"+request.getRemotePort()+" - " + uri +"\u001b[0m \n");
        	//System.out.println("\u001b[1;30m======================================           END          ======================================\u001b[0m");
        }
    }
}
