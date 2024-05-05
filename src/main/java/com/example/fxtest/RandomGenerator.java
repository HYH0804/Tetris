package com.example.fxtest;

import com.example.fxtest.brick.*;
import javafx.scene.paint.Color;

import java.util.Random;
public class RandomGenerator {
    private final int UPPER_EASY = 72;
    private final int UPPER_NOMAL = 70;
    private final int UPPER_HARD = 68;

    //여기서 색상 주입 >> 색맹이냐 아니냐 나눠야될듯

    //일반블록: 아이템 자체 블록 제외하고 블록 모양 택
        //a b c d 만들고 각각 색상, 폰트 주입
    public Brick genarateNormal(int difficulty, boolean colorBlindness, GameBoard1 gameBoard){ //아이템블록 제외 랜덤생성
        switch (difficulty){
            case 0: //이지모드
                return generateBlock(UPPER_EASY,colorBlindness, gameBoard);
            case 1: //노말모드
                return generateBlock(UPPER_NOMAL,colorBlindness, gameBoard);
            default: //하드모드
                return generateBlock(UPPER_HARD,colorBlindness, gameBoard);
        }
    }


    //아이템일때: 블록 모양 먼저 택
        //아이템 자체 블록이면 끝
        //아니면
            //랜덤으로 a,b,c,d 중 하나만 랜덤으로 아이템 기능 가져와서 + a b c d 각각 폰트 주입
            //블록 모양대로 만듦
    public Brick generateItem(int difficulty,boolean colorBlindness, GameBoard1 gameBoard){
        Random rand = new Random(); // Random 객체 생성
        int upperBound=5; //0 ~ 4까지
        int item = rand.nextInt(upperBound) + 2; //2 부터 6까지 , Item 랜덤선택
        upperBound = 4; //0~3까지
        int block = rand.nextInt(upperBound); //0 ~ 3까지 , 0이면 a에 Item, 1이면 b에 Item, 2이면 c에 Item, 3이면 d에 Item
        if(item==2){
            if(colorBlindness==true)
                return new BrickW(1, 4,Color.BLACK); //색맹때 해야됨
            else
                return new BrickW(1,4, Color.BLACK);
        }
        else{
            Brick brick = genarateNormal(difficulty, colorBlindness ,gameBoard);

            //각 블록에 아이템 장착
            if(block==0){
                Block a = brick.getA();
                a.setItem(Item.fromNum(item));
                brick.setItem(a);
            }
            else if(block==1){
                Block b = brick.getB();
                b.setItem(Item.fromNum(item));
                brick.setItem(b);
            }
            else if(block==2){
                Block c = brick.getC();
                c.setItem(Item.fromNum(item));
                brick.setItem(c);
            }
            else if(block==3){
                Block d = brick.getD();
                d.setItem(Item.fromNum(item));
                brick.setItem(d);
            }
            return brick;

        }

    }


    //아이템 블록인지 (브릭 자체가 아이템인지 , 블록 안에 아이템이 있는지) 확인 어케? + 아이템이면 배열에 어케 표시할건지 + 아이템 기능 구현을 어디 메서드 안에 할건지
    // 브릭 자체 아이템인지 체크는 그냥 메서드 만들어서
    //Item 종류는 ENUM으로

    // 블록 안에 아이템도 a b c d 돌아다니면서 있는지 확인하고 있으면 해당 ENUM 리턴, 없으면 null 리턴 >> 각 브릭에도 어떤 특정 아이템인지 ENUM 필드값 필요
    // 브릭 자체가 아이템이면 이것도 ENUM으로 , 브릭에도 Item ENUM 필드값 있어야됨.
    // 이러면 타임라인 함수에서 시작 수행 아이템인지, 안착 수행 아이템인지, 줄 제거 수행 아이템인지 ENUM값으로 확인하고 타임라인에
    // 이 후 업데이트

    //Item Controller 객체 따로 만들어서 메서드 한개의 메서드 안에서 각각 어떤 ENUM 값에 대한 board쪽과 그래픽쪽 처리구현. board쪽 처리 없으면 뭐 냅두고 그래픽쪽도 마찬가지고  매개변수로 ENUM 값 주는거지 , switch문으로?
    // 이 컨트롤러 클래스 안에서 ENUM 값에 대한 board쪽 처리와 그래픽 처리

    //process(Item 숫자)
    // 숫자 1:
        //보드쪽
        //그래픽쪽
    // 숫자 2:
    // 마찬가지
    //...

    //이러면 canMoveDown 쪽에서 한번 호출 (스폰 되자마자)
    //canMoveDown 에 대한 else에서 한번 호출(안착)
    //gravity 쪽에서 그 줄에 대해 하나씩 호출(줄 제거)

    public Brick generateBlock(int upperBound,boolean colorBlindness, GameBoard1 gameBoard){
        Random rand = new Random();
        int point = rand.nextInt(upperBound)+1; //1~upperBound까지
        if(point>=1 && point<10) {
            if(colorBlindness==true)
                return new BrickJ(0,4, Color.ORANGE, gameBoard);
            else
                return new BrickJ(0,4,Color.ORANGE, gameBoard);
        }
        else if(point>=10 && point<20){
            if(colorBlindness==true)
                return new BrickL(0,3,Color.BLUE, gameBoard);
            else
                return new BrickL(0,3,Color.BLUE, gameBoard);
        }
        else if(point>=20 && point<30){
            if (colorBlindness==true)
                return new BrickO(0,4,Color.color(240/255.0,228/255.0,66/255.0), gameBoard);
            else
                return new BrickO(0,4,Color.color(240/255.0,228/255.0,66/255.0), gameBoard);
        }
        else if(point>=30 && point<40){
            if(colorBlindness==true)
                return new BrickS(0, 4,Color.color(0,158/255.0,87/255.0), gameBoard);
            else
                return new BrickS(0,4,Color.GREEN , gameBoard);
        }
        else if(point>=40 && point<50){
            if(colorBlindness==true)
                return new BrickT(1,4, Color.PALEVIOLETRED, gameBoard);
            else
                return new BrickT(1,4, Color.PURPLE, gameBoard);
        }
        else if(point>=50 && point<60){
            if (colorBlindness==true)
                return new BrickZ(0,4, Color.color(213/255.0,94/255.0,0), gameBoard);
            else
                return new BrickZ(0,4, Color.color(210/255.0,50/255.0,50/255.0), gameBoard);
        }
        else { //I블록
            if (colorBlindness==true)
                return new BrickI(1,4, Color.color(86/255.0, 180/255.0, 232/255.0), gameBoard);
            else
                return new BrickI(1,4, Color.SKYBLUE, gameBoard);
        }
    }
}
