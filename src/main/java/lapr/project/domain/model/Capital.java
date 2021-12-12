package lapr.project.domain.model;

import java.util.Objects;

public class Capital {
    private final String name;

    public Capital(String name) {
        checkName(name);
        this.name = name;
    }

    /**
     * Checks if the name of the Capital is correct, and if not throws an error message.
     * @param name of the capital
     */
    public void checkName(String name){
        if(Objects.isNull(name)){
            throw new IllegalArgumentException("Name cannot be null.");
        }
    }
}
