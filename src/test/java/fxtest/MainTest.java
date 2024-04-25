package fxtest;

import com.example.fxtest.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void loadProperties() throws IOException {
        boolean test =true;
        if(Main.loadProperties()!=null){
            test = true;
        }
        else {
            test = false;
        }
        assertTrue(test);
    }
}