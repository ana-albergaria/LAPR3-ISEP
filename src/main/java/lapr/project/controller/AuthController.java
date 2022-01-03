package lapr.project.controller;

/**
 *
 * @author Ana Albergaria <1201518@isep.ipp.pt> inspired in class by: Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthController {

    private App app;

    public AuthController()
    {
        this.app = App.getInstance();
    }

    public boolean doLogin(String email, String pwd)
    {
        try {
            return this.app.doLogin(email, pwd);
        } catch(IllegalArgumentException ex)
        {
            return false;
        }
    }

    /*
    public List<UserRoleDTO> getUserRoles()
    {
        if (this.app.getCurrentUserSession().isLoggedIn())
        {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }
     */

    public void doLogout()
    {
        this.app.doLogout();
    }
}
