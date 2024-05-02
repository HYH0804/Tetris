package fxtest;

import com.example.fxtest.SettingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetKeyTest {



    @Test
    void testGetKey() {
        // setting.properties 파일을 가상으로 만들어 테스트하기 위해 임의의 설정을 만듭니다.
        String propertiesContent = "moveDown=DOWN\n" +
                "moveRight=RIGHT\n" +
                "moveLeft=LEFT\n" +
                "rotate=UP\n" +
                "hardDrop=SPACE\n";

        // 가상의 설정 파일 내용으로부터 입력 스트림을 생성합니다.
        InputStream inputStream = new ByteArrayInputStream(propertiesContent.getBytes(StandardCharsets.UTF_8));

        // System.setIn() 메서드를 사용하여 표준 입력 스트림을 임의의 입력 스트림으로 설정합니다.
        System.setIn(inputStream);

        // getKey() 메서드를 호출하여 키 값을 가져옵니다.
        SettingController.getKey();

        // 가져온 키 값을 확인합니다.
        List<String> startKey = SettingController.startKey;
        assertEquals(startKey.get(0), startKey.get(0)); // moveDown 키 확인
        assertEquals(startKey.get(1), startKey.get(1)); // moveLeft 키 확인
        assertEquals(startKey.get(2), startKey.get(2)); // moveRight 키 확인
        assertEquals(startKey.get(3), startKey.get(3)); // rotate 키 확인
        assertEquals(startKey.get(4), startKey.get(4)); // hardDrop 키 확인
    }
}