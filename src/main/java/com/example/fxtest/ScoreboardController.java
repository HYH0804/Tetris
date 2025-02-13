package com.example.fxtest;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;


public class ScoreboardController implements Initializable {

    @FXML
    Label selected;
    @FXML
    Button easyBtn;
    @FXML
    Button normalBtn;
    @FXML
    Button hardBtn;
    @FXML
    Button itemModeBtn;
    @FXML
    VBox ranking;

    @FXML
    private Button GoHomeButton;


    private static String[] name = new String[10];
    private static int[] score = new int[10];
    public static int difficulty = 0; // 0: easy, 1: normal, 2: hard
    public static boolean itemMode = false;
    public static int nowDifficulty = 0; // 0: easy, 1: normal, 2: hard
    public static boolean nowItemMode = false;
    public static int nowIdx = -1; // -1 if not ranked


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreboardView();
        itemModeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button Clicked!");
                // 버튼을 눌렀을 때 실행하고 싶은 코드를 여기에 작성합니다.
                itemMode = !itemMode;
                scoreboardView();
            }
        });

        initButton(easyBtn);
        initButton(normalBtn);
        initButton(hardBtn);

    }
    @FXML
    public void goHomeButtonClick() throws IOException{
        Stage stage = (Stage) GoHomeButton.getScene().getWindow();

        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Parent root = loader.load();
        Scene scene = GoHomeButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("Game Page");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

    public void scoreboardView() {
        // itemModeBtn
        if (itemMode) {
            itemModeBtn.setText("Item Mode: O");
        } else {
            itemModeBtn.setText("Item Mode: X");
        }

        // selected's text
        selected.setText(getText());

        // Ranking
        getRanking();
        drawingRanking();
    }

    public static String getText() {
        String temp = "";
        if (difficulty == 0) {
            temp = "easy";
        } else if (difficulty == 1) {
            temp = "normal";
        } else if (difficulty == 2) {
            temp = "hard";
        } else {
            System.out.println("difficulty error!");
        }
        if (itemMode) {
            temp = temp + "(item)";
        }
        return temp;
    }

    public static void getRanking() {
        String temp = getText();
        String path = System.getProperty("user.home") + File.separator + "score" + File.separator + temp + ".txt";
        File scoreFile = new File(path);

        // 파일이 존재하지 않으면 기본 값을 사용해 파일 생성
        if (!scoreFile.exists()) {
            try {
                // score 디렉토리가 존재하지 않으면 생성
                File parentDir = scoreFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile))) {
                    for (int i = 0; i < 10; i++) {
                        bw.write("DefaultName 0");
                        bw.newLine();
                    }
                }
                System.out.println("Default score file created at " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Score.txt 파일에서 정보를 읽어와 스코어보드에 추가
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            for (int i = 0; i < 10; i++) {
                line = br.readLine();
                // 공백을 기준으로 이름과 점수를 분리
                String[] parts = line.split(" ");
                name[i] = parts[0];
                score[i] = Integer.parseInt(parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawingRanking() {
        ObservableList<Node> children = ranking.getChildren();
        int idx = 0;
        for(Node child : children) {

            HBox hBox = (HBox) child;
            ObservableList<Node> children2 = hBox.getChildren();
            Label nameLabel = (Label) children2.get(0);
            Label scoreLabel = (Label) children2.get(2);
            nameLabel.setText(name[idx]);
            scoreLabel.setText(String.valueOf(score[idx]));

            // 새로 들어온 값 강조 표시
            if (idx == nowIdx && difficulty == nowDifficulty && itemMode == nowItemMode) {
                nameLabel.setTextFill(Color.AQUA);
                scoreLabel.setTextFill(Color.AQUA);
            }
            else if (Color.AQUA == nameLabel.getTextFill()){
                nameLabel.setTextFill(Color.BLACK);
                scoreLabel.setTextFill(Color.BLACK);
            }
            idx++;
        }
    }

    public static void writeRanking() {
        // 뭐로했는지 아는 먼가가 필요함 -> static에 값 대입후 실행해야함

        String temp = getText();
        String path = System.getProperty("user.home") + File.separator + "score" + File.separator + temp + ".txt";
        File scoreFile = new File(path);

        // 파일이 존재하지 않으면 기본 값을 사용해 파일 생성
        if (!scoreFile.exists()) {
            try {
                // score 디렉토리가 존재하지 않으면 생성
                File parentDir = scoreFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile))) {
                    for (int i = 0; i < 10; i++) {
                        bw.write("DefaultName 0");
                        bw.newLine();
                    }
                }
                System.out.println("Default score file created at " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }try {
            // FileWriter를 사용하여 파일 쓰기 스트림 열기
            FileWriter fileWriter = new FileWriter(path);

            // BufferedWriter를 사용하여 버퍼링을 추가하여 효율적으로 파일에 쓰기
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 배열의 각 줄을 파일에 쓰기
            for (int i = 0; i < 10; i++) {
                bufferedWriter.write(name[i] + " " + String.valueOf(score[i]));
                bufferedWriter.newLine(); // 개행 문자 추가
            }

            // 버퍼 비우고 스트림 닫기
            bufferedWriter.flush();
            bufferedWriter.close();

            System.out.println("파일에 여러 줄 쓰기 완료");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initButton(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button Clicked!");
                // 버튼을 눌렀을 때 실행하고 싶은 코드를 여기에 작성합니다.
                if(btn == easyBtn){
                    difficulty = 0;
                }
                else if (btn == normalBtn){
                    difficulty = 1;
                }
                else{
                    difficulty = 2;
                }
                scoreboardView();
            }
        });
    }



    public static void openScoreboard(int boardScore, int difficult, boolean item) throws IOException {
        difficulty = nowDifficulty = difficult;
        itemMode = nowItemMode = item;
        getRanking();

        nowIdx = -1;
        for(int i = 0; i < 10; i++){
            if(boardScore>score[i]){
                nowIdx = i;
                break;
            }
        }
        if(nowIdx != -1){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Write name");
            dialog.setHeaderText(null);
            dialog.setContentText("Write your name:");

            // 대화상자를 보여주고 사용자 입력 받기
            Optional<String> result = dialog.showAndWait();

            // 사용자가 입력한 문자열 출력
            int saveIdx = nowIdx;
            nowIdx = -1;
            result.ifPresent(input -> {
                nowIdx = saveIdx;
                for(int i = 8; i >= nowIdx; i--) {
                    score[i+1] = score[i];
                    name[i+1] = name[i];
                }
                score[nowIdx] = boardScore;
                name[nowIdx] = input.replaceAll(" ","");
                System.out.println("입력한 문자열: " + input);
            });
        }
        writeRanking();
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("scoreboard-view.fxml"));
        Parent root = fxmlLoader.load();

        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();

        Scene mainpage = st.getScene();
        mainpage.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        st.setScene(mainpage);
        st.setTitle("Score Page");
        st.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        st.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        st.show();
    }
}
