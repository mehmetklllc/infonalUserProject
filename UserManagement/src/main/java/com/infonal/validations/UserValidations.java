/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infonal.validations;

import com.infonal.entities.Users;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mkilic
 */
public class UserValidations {

    public static String message;

    public static String checkNull(Users users) {
        if (users.getFirstName().equals("")) {
            setMessage("Kullanıcı adı alanı zorunludur");
            return getMessage();
        }
        if (users.getLastName().equals("")) {
            setMessage("Kullanıcı soyadı alanı zorunludur");
            return getMessage();
        }
        return "99";
    }

    public static FacesContext context() {
        return FacesContext.getCurrentInstance();
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        UserValidations.message = message;
    }
}
