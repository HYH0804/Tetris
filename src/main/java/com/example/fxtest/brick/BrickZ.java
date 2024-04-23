package com.example.fxtest.brick;

import com.example.fxtest.GameBoard;
import com.example.fxtest.GameBoardController;
import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BrickZ implements Brick {
    private int center_x; //회전 기준 및 canMove 와 canRotate에 대한 기준점 (배열 Index 기준) [1][4] 기준
    private int center_y;

    private int shape; //회전

    Block a;
    Block b;
    Block c;
    Block d;

    List<Block> blockList = new ArrayList<>(); //그냥 하드코딩 귀차나서
    List<Block> afterList = new ArrayList<>();

    public BrickZ(int center_x, int center_y, Color color) {
        //테트리미노 모양 및 초기회전 정의 , 각각의 블록 위치 세팅
        this.center_x = center_x;
        this.center_y = center_y;
        this.a= new Block(center_x,center_y-1,color);
        this.b=new Block(center_x, center_y,color); //b가 센터
        this.c=new Block(center_x+1, center_y,color);
        this.d=new Block(center_x+1,center_y+1,color);
        this.shape=0;
        blockList.add(a);
        blockList.add(b);
        blockList.add(c);
        blockList.add(d);
    }

    @Override
    public boolean canRotate() { // 0 > 1 > 2 > 3
        int nextShape = (this.shape + 1) % 4; //다음 회전모양
        List<Block> temp = new ArrayList<>();

        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block Rotate_a;
        Block Rotate_b;
        Block Rotate_c;
        Block Rotate_d;

        if (nextShape == 0) {
            Rotate_a = new Block(b.getX(), b.getY() - 1);
            Rotate_b = new Block(b.getX(), b.getY()); // b 중심점이라 변환 X
            Rotate_c = new Block(b.getX() + 1, b.getY());
            Rotate_d = new Block(b.getX()+1, b.getY() + 1);
            temp.add(Rotate_a);
            temp.add(Rotate_b);
            temp.add(Rotate_c);
            temp.add(Rotate_d);

        } else if (nextShape == 1) {
            Rotate_a = new Block(b.getX() - 1, b.getY());
            Rotate_b = new Block(b.getX(), b.getY()); //b 중심점이라 변환 X
            Rotate_c = new Block(b.getX(), b.getY() - 1);
            Rotate_d = new Block(b.getX()+1, b.getY() - 1);
            temp.add(Rotate_a);
            temp.add(Rotate_b);
            temp.add(Rotate_c);
            temp.add(Rotate_d);
        } else if (nextShape == 2) {
            Rotate_a = new Block(b.getX(), b.getY() + 1);
            Rotate_b = new Block(b.getX(), b.getY()); //b 중심점이라 변환 X
            Rotate_c = new Block(b.getX() - 1, b.getY());
            Rotate_d = new Block(b.getX()-1, b.getY()-1);
            temp.add(Rotate_a);
            temp.add(Rotate_b);
            temp.add(Rotate_c);
            temp.add(Rotate_d);
        } else if (nextShape == 3) {
            Rotate_a = new Block(b.getX()+1, b.getY());
            Rotate_b = new Block(b.getX(), b.getY()); //b 중심점이라 변환 X
            Rotate_c = new Block(b.getX(), b.getY()+1);
            Rotate_d = new Block(b.getX()-1, b.getY()+1);
            temp.add(Rotate_a);
            temp.add(Rotate_b);
            temp.add(Rotate_c);
            temp.add(Rotate_d);
        }
        //회전 후 a,b,c,d 임시 저장 및 세팅

        //여기부터는 회전시킨 Rotate 블록 a b c d 의 좌표값과 동일한 Board 좌표값에 1이 있는지 없는지, a b c d 위치에 1이 하나라도 있으면 회전 불가 return false , 모두 없으면 회전 가능 return true
        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            if (!(y < GameBoard.WIDTH && y >= 0 && x<GameBoard.HEIGHT && x>=0 && GameBoard.board[x][y] != 1)) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                return false;  //이동 불가
            }
        }
        return true; //이동 가능
    }

    //구현 해야됨 24-03-22
    @Override
    public boolean canMoveRight() {

        List<Block> temp = new ArrayList<>();

        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block moveR_a;
        Block moveR_b;
        Block moveR_c;
        Block moveR_d;
        moveR_a = new Block(a.getX(), a.getY()+1);
        moveR_b = new Block(b.getX(), b.getY()+1);
        moveR_c = new Block(c.getX(), c.getY() + 1);
        moveR_d = new Block(d.getX(), d.getY() + 1);
        temp.add(moveR_a);
        temp.add(moveR_b);
        temp.add(moveR_c);
        temp.add(moveR_d);

        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            if (!(y < GameBoard.WIDTH && y >= 0 && x<GameBoard.HEIGHT && x>=0 && GameBoard.board[x][y] != 1)) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                return false;  //이동 불가
            }
        }
        return true; //이동 가능
    }

    @Override
    public boolean canMoveLeft() {

        List<Block> temp = new ArrayList<>();

        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block moveL_a;
        Block moveL_b;
        Block moveL_c;
        Block moveL_d;
        moveL_a = new Block(a.getX(), a.getY()-1);
        moveL_b = new Block(b.getX(), b.getY()-1);
        moveL_c = new Block(c.getX(), c.getY() - 1);
        moveL_d = new Block(d.getX(), d.getY() - 1);
        temp.add(moveL_a);
        temp.add(moveL_b);
        temp.add(moveL_c);
        temp.add(moveL_d);

        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            if (!(y < GameBoard.WIDTH && y >= 0 && x<GameBoard.HEIGHT && x>=0 && GameBoard.board[x][y] != 1)) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                return false;  //이동 불가
            }
        }
        return true; //이동 가능

    }

    @Override
    public boolean canMoveDown() {
        List<Block> temp = new ArrayList<>();

        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block moveD_a;
        Block moveD_b;
        Block moveD_c;
        Block moveD_d;
        moveD_a = new Block(a.getX()+1, a.getY());
        moveD_b = new Block(b.getX()+1, b.getY());
        moveD_c = new Block(c.getX()+1, c.getY());
        moveD_d = new Block(d.getX()+1, d.getY());
        temp.add(moveD_a);
        temp.add(moveD_b);
        temp.add(moveD_c);
        temp.add(moveD_d);

        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            if (!(y < GameBoard.WIDTH && y >= 0 && x<GameBoard.HEIGHT && x>=0 && GameBoard.board[x][y] != 1)) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                return false;  //이동 불가
            }
        }
        return true; //이동 가능
    }
    //구현 해야됨 24-03-22

    @Override
    public void rotate() {
        //돌리기 전 현재 위치 0 세팅
        //preChange();



        //회전 후 a b c d 좌표 변경 + (shape+1)%4
        this.shape = (this.shape+1)%4; //다음 회전모양
        int x=b.getX(); // 회전 중심 x
        int y=b.getY(); // 회전 중심 y
        if(shape==0){ //3 > 0 회전
            a.setX(b.getX());
            a.setY(b.getY() - 1);
            //b는 중심점이라 그대로
            c.setX(b.getX() + 1);
            c.setY(b.getY());
            d.setX(b.getX()+1);
            d.setY(b.getY() + 1);
        }
        else if (shape==1) { //0 > 1 회전
            a.setX(b.getX() - 1);
            a.setY(b.getY());
            //b는 중심점이라 그대로
            c.setX(b.getX());
            c.setY(b.getY() - 1);
            d.setX(b.getX()+1);
            d.setY(b.getY() - 1);
        }
        else if (shape==2) { //1 > 2 회전
            a.setX(b.getX());
            a.setY(b.getY() + 1);
            //b는 중심점이라 그대로
            c.setX(b.getX() - 1);
            c.setY(b.getY());
            d.setX(b.getX()-1);
            d.setY(b.getY()-1);
        }
        else{ //2 > 3 회전
            a.setX(b.getX()+1);
            a.setY(b.getY());
            //b는 중심점이라 그대로
            c.setX(b.getX());
            c.setY(b.getY()+1);
            d.setX(b.getX()-1);
            d.setY(b.getY()+1);
        }
        //돌린 후 1 세팅
        //postChange();
    }

    @Override
    //돌리기 전 블록 위치 0 세팅
    public void preChange() {
        for(Block block : blockList){
            GameBoard.board[block.getX()][block.getY()]=0;
        }
        //색 지우고
    }

    @Override
    //돌린 후 1 세팅
    public void postChange() {
        for(Block block : blockList){
            GameBoard.board[block.getX()][block.getY()]=1;
        }
        //색 채우고
    }


    @Override
    public void moveR() {
        //preChange();

        //이동 후 a b c d 좌표 변경
        a.setY(a.getY()+1);
        b.setY(b.getY()+1);
        c.setY(c.getY()+1);
        d.setY(d.getY()+1);
        System.out.println("제대로 Brick 각 위치 이동함");
        //postChange();
    }

    @Override
    public void moveL() {
        //preChange();

        //이동 후 a b c d 좌표 변경
        a.setY(a.getY()-1);
        b.setY(b.getY()-1);
        c.setY(c.getY()-1);
        d.setY(d.getY()-1);

        //postChange();
    }

    @Override
    public void moveD() {
        //preChange();

        //이동 후 a b c d 좌표 변경
        a.setX(a.getX()+1);
        b.setX(b.getX()+1);
        c.setX(c.getX()+1);
        d.setX(d.getX()+1);
        GameBoard.updateScore(GameBoardController.downScore);
        //postChange();
    }

    @Override
    public void straightD() {
        while(canMoveDown()){
            //이동 후 a b c d 좌표 변경
            a.setX(a.getX()+1);
            b.setX(b.getX()+1);
            c.setX(c.getX()+1);
            d.setX(d.getX()+1);
            GameBoard.updateScore(GameBoardController.downScore);
        }
    }

    //Getter Setter
    @Override
    public Block getA() {
        return a;
    }

    @Override
    public void setA(Block a) {
        this.a = a;
    }

    @Override
    public Block getB() {
        return b;
    }

    @Override
    public void setB(Block b) {
        this.b = b;
    }

    @Override
    public Block getC() {
        return c;
    }

    @Override
    public void setC(Block c) {
        this.c = c;
    }

    @Override
    public Block getD() {
        return d;
    }

    @Override
    public void setD(Block d) {
        this.d = d;
    }

    @Override
    public int getCenter_x() {
        return center_x;
    }

    @Override
    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    @Override
    public int getCenter_y() {
        return center_y;
    }

    @Override
    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }

    @Override
    public int getShape() {
        return shape;
    }

    @Override
    public void setShape(int shape) {
        this.shape = shape;
    }

    @Override
    public List<Block> getBlockList() {
        return blockList;
    }
}


//각 블록 왼쪽 아래부터 기준으로 a b c d 다시 세팅해야됨