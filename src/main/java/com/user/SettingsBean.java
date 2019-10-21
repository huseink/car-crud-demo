/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alperk
 */
@ManagedBean
@ViewScoped
public class SettingsBean {

    public String fullname;
    public String username;
    public String password;
    public String confirmPassword;
    
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

    /**
     * Creates a new instance of settingsBean
     */
    public SettingsBean() {
    }

    //validate settings
    public String changeUsernamePassword() throws IOException {
        int valid = SettingsDAO.update(convertToNameCase(fullname), username, password);
        String reqPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (valid !=0) {
            ec.redirect(reqPath + "/login.xhtml");
            return (reqPath + "/login.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            
            return "settings";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    
    public void validatePasswordCorrect(FacesContext context, UIComponent component,
        Object value) {

    // Retrieve the value passed to this method
    String confirmPassword = (String) value;

    // Retrieve the temporary value from the password field
    UIInput passwordInput = (UIInput) component.findComponent("password");
    String password = (String) passwordInput.getLocalValue();

    if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
        String message = context.getApplication().evaluateExpressionGet(context, "Passwords not matching", String.class);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        this.password = null;
        this.confirmPassword=null;
        throw new ValidatorException(msg);
    }
}

}
