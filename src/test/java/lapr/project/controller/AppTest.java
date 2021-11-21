package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void testDbConnection(){
        App app = App.getInstance();
        /*Connection connection = app.getConnection();*/
    }
}