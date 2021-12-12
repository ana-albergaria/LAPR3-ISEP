package lapr.project.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalTest {
    private Capital capital1;

    @BeforeEach
    void setUp() {
        capital1 = new Capital("Nicosia");

    }

    @Test
    void ensureNotNullNameIsAllowed() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Capital(null));
        assertEquals("Name cannot be null.", thrown.getMessage());
    }
}