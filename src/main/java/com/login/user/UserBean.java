/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.user;

import com.login.util.Util;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@SessionScoped
public class UserBean {
    

    public String fullname;
    public String username;

    public String password;
    boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public UserBean() {
    }
    public static String convertToNameCase(String s)
    {
        if (s != null)
        {
            StringBuilder b = new StringBuilder();
            String[] split = s.split(" ");
            for (String srt : split)
            {
                if (srt.length() > 0)
                {
                    b.append(srt.substring(0, 1).toUpperCase()).append(srt.substring(1).toLowerCase()).append(" ");
                }
            }
            return b.toString().trim();
        }
        return s;
    }

    //validate login
    public String validateUsernamePassword() {
        String name = UserDAO.validate(username, password);
        if (name!=null) {
            HttpSession session = Util.getSession();
            session.setAttribute("username", username);
            fullname = name;
            loggedIn = true;
            return "secured/home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Incorrect Username or Password",
                            "Please enter correct username & password"));
            loggedIn = false;

            return "login";
        }
    }

    public void doLogout() throws IOException {
        HttpSession hs = Util.getSession();
        hs.invalidate();
        loggedIn = false;
        String reqPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect(reqPath + "/login.xhtml");

    }

    public String signUp() {
        int created = UserDAO.create(convertToNameCase(fullname), username, password);
        if (created != 0) {
            return "login?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Oops!",
                            "Unable to create account!"));
            return "login";
        }
    }
    
   

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
