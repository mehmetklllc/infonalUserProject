package com.infonal.controler;

import com.infonal.business.UsersRepository;
import com.infonal.db.IUsersRepo;
import com.infonal.entities.Users;
import com.infonal.validations.UserValidations;
import static com.infonal.validations.UserValidations.context;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author mkilic
 */
@Component
@ManagedBean
@ViewScoped
public class UserBean {

    Users user;
    Users resultUser;
    List<Users> userList;
    IUsersRepo repoImpl = new UsersRepository("mongo_PU");

    public UserBean() {
        user = new Users();
        resultUser = new Users();
        userList = new ArrayList<Users>();
        listAllUser();
    }

    public void userCreate() {

        if (UserValidations.checkNull(user).equals("99")) {
            repoImpl.persist(user);
            listAllUser();
            user = new Users();
        } else {
            messageShow();
        }
    }

    public void userUpdate() {
        repoImpl.merge(resultUser);
        resultUser = new Users();
        listAllUser();
    }

    public void userDelete() {
        repoImpl.remove(resultUser.getId());
        resultUser = new Users();
        listAllUser();

    }

    public void singleUser() {
        resultUser = new Users();
        System.out.println("girdi :" + resultUser.getFirstName());
        String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        userId = "'" + userId + "'";
        resultUser = repoImpl.getSingleEntity(userId, "Users", "u.id");
    }

    public void listAllUser() {
        userList = repoImpl.getAllResultlist("Users");
    }

    public void messageShow() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Zorunlu : ", UserValidations.getMessage());
        context().addMessage(null, message);
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public Users getResultUser() {
        return resultUser;
    }

    public void setResultUser(Users resultUser) {
        this.resultUser = resultUser;
    }
}
