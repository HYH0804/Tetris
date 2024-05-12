package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneChanger {

    //이벤트 발생해야할 GUI가 있는 Stage를 넘기면 파라미터의 View를 Stage에 width, height로 장착시켜 Stage 리턴
    //Stage currentStage = (Stage) setKeyButton.getScene().getWindow();
    //newStage.show();
    //해당 loader 같은 경우는
    public Stage changeModal(Stage stage, String view, double width, double height) throws IOException {
        // 현재 스테이지 창을 가져옵니다.
        // FXML 로더를 사용하여 새로운 Scene을 로드합니다.
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        Parent root = loader.load();


        //여기에 loader 저장, 필요하면
        //FXMLLoader loader = (FXMLLoader) newStage.getScene().getRoot().getProperties().get("loader");
        root.getProperties().put("loader",loader);

        // 새 Scene을 설정하고 새로운 Stage를 생성합니다.
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);

        // 모달 창 설정: 다른 창을 사용할 수 없도록 설정
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(stage);
        // 창의 타이틀 바 제거
        newStage.initStyle(StageStyle.UNDECORATED);

        double centerX = stage.getX() + stage.getWidth() / 2;
        double centerY = stage.getY() + stage.getHeight() / 2;
        newStage.setWidth(width);
        newStage.setHeight(height);
        newStage.setX(centerX - width / 2); // 새 스테이지를 현재 스테이지의 중앙에 위치시키기
        newStage.setY(centerY - height / 2);
        return newStage;
    }

    public static void changeScene(Button buttonScene, String view, double width, double height){

    }
}
