package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    AuthController authController;

    @Test
    public void checkBootstrappedLoginAndLogout(){
        authController = new AuthController();
        assertTrue(authController.doLogin("trafficm@mail.com", "123456"));
        authController.doLogout();
    }


}