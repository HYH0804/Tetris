package fxtest;

import com.example.fxtest.BrickController;
import com.example.fxtest.brick.Brick;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
<<<<<<< HEAD
<<<<<<< HEAD
import org.junit.jupiter.api.DisplayName;
=======
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
=======
import org.junit.jupiter.api.DisplayName;
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BrickControllerTest {

    @Mock
    Brick brickMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMoveR_WhenCanMoveRight() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(true).when(brickMock).canMoveRight();

        // When
        brickController.moveR(brickMock);

        // Then
        verify(brickMock).moveR();
    }

    @Test
    public void testMoveR_WhenCannotMoveRight() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(false).when(brickMock).canMoveRight();

        // When
        brickController.moveR(brickMock);

        // Then
        // Ensure that moveR() is not called when cannot move right
        verify(brickMock, never()).moveR();
    }

    // Similarly, you can write tests for other methods like moveL, moveD, rotate, and straightD.

    @Test
    public void brickControllerKey(){
        BrickController brickController = BrickController.getBrickController();
        brickController.setMOVED("D");
        brickController.setMOVEL("L");
        brickController.setMOVER("R");
        brickController.setHARDDROP("H");
        Assertions.assertEquals(brickController.getMOVED(), "D");
        Assertions.assertEquals(brickController.getMOVEL(), "L");
        Assertions.assertEquals(brickController.getMOVER(), "R");
        Assertions.assertEquals(brickController.getSTRAIGHT(), "H");

    }


}