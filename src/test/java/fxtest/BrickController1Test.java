package fxtest;

import com.example.fxtest.BrickController;
import com.example.fxtest.brick.Brick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrickController1Test {

    @Mock
    Brick brickMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMoveL_WhenCanMoveLeft() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(true).when(brickMock).canMoveLeft();

        // When
        brickController.moveL(brickMock);

        // Then
        verify(brickMock).moveL();
    }

    @Test
    public void testMoveL_WhenCannotMoveLeft() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(false).when(brickMock).canMoveLeft();

        // When
        brickController.moveL(brickMock);

        // Then
        // Ensure that moveL() is not called when cannot move left
        verify(brickMock, never()).moveL();
    }

    @Test
    public void testMoveD_WhenCanMoveDown() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(true).when(brickMock).canMoveDown();

        // When
        brickController.moveD(brickMock);

        // Then
        verify(brickMock).moveD();
    }

    @Test
    public void testMoveD_WhenCannotMoveDown() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(false).when(brickMock).canMoveDown();

        // When
        brickController.moveD(brickMock);

        // Then
        // Ensure that moveD() is not called when cannot move down
        verify(brickMock, never()).moveD();
    }

    @Test
    public void testRotate_WhenCanRotate() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(true).when(brickMock).canRotate();

        // When
        brickController.rotate(brickMock);

        // Then
        verify(brickMock).rotate();
    }

    @Test
    public void testRotate_WhenCannotRotate() {
        // Given
        BrickController brickController = BrickController.getBrickController();
        doReturn(false).when(brickMock).canRotate();

        // When
        brickController.rotate(brickMock);

        // Then
        // Ensure that rotate() is not called when cannot rotate
        verify(brickMock, never()).rotate();
    }

    @Test
    public void testStraightD() {
        // Given
        BrickController brickController = BrickController.getBrickController();

        // When
        brickController.straightD(brickMock);

        // Then
        // Ensure that straightD() is called
        verify(brickMock).straightD();
    }

    // You can add more tests as needed for other methods and edge cases
}