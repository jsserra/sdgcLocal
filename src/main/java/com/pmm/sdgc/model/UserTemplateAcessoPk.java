package com.pmm.sdgc.model;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 *
 * @author acg
 */
@Embeddable
public class UserTemplateAcessoPk implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "idusermenu", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserMenu userMenu;

    @ManyToOne
    @JoinColumn(name = "idusertemplate", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserTemplate userTemplate;     

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    public UserTemplate getUserTemplate() {
        return userTemplate;
    }

    public void setUserTemplate(UserTemplate userTemplate) {
        this.userTemplate = userTemplate;
    }



}
