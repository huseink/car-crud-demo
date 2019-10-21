/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.user.UserBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/secured/*")
public class RestrictFilter implements Filter {
    private FilterConfig fc;


    public RestrictFilter() {

    }


    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        System.out.println(userBean);
        String loginURL = request.getContextPath() + "/login.xhtml";
        if(userBean != null && userBean.isLoggedIn()){
            chain.doFilter(req, res);
        }
        else{
            response.sendRedirect(loginURL);
        }
    }


    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.fc = fConfig;
    }

}
