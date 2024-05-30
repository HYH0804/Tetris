package com.example.fxtest.brick;

import com.example.fxtest.GameBoard1;
import com.example.fxtest.GameBoardController;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.example.fxtest.Drawing.*;

public class BrickW implements Brick{
    private int center_x; //회전 기준 및 canMove 와 canRotate에 대한 기준점 (배열 Index 기준) [1][4] 기준
    private int center_y;

    private boolean blockCrashed= false; //무게추로 블록 깨지면 true로...

    private int shape; //회전
    public boolean possible = true;
    Block a;
    Block b;
    Block c;
    Block d;
    Block e;
    Block f;
    GameBoard1 gameBoard;
    public void setGameBoard(GameBoard1 gameBoard) {
        this.gameBoard = gameBoard;
    }

    List<Block> blockList = new ArrayList<>(); //그냥 하드코딩 귀차나서
    List<Block> afterList = new ArrayList<>();

    public BrickW(int center_x, int center_y, Color color, GameBoard1 gameBoard) {
        //테트리미노 모양 및 초기회전 정의 , 각각의 블록 위치 세팅
        this.center_x = center_x;
        this.center_y = center_y;
        this.a= new Block(center_x,center_y-1,Item.WEIGHT,color);
        this.b=new Block(center_x, center_y,Item.WEIGHT,color);
        this.c=new Block(center_x, center_y+1,Item.WEIGHT,color);
        this.d=new Block(center_x,center_y+2,Item.WEIGHT,color);
        this.e=new Block(center_x-1,center_y,Item.WEIGHT,color);
        this.f=new Block(center_x-1,center_y+1,Item.WEIGHT,color);
        this.shape=0;
        blockList.add(a);
        blockList.add(b);
        blockList.add(c);
        blockList.add(d);
        blockList.add(e);
        blockList.add(f);
        this.gameBoard=gameBoard;
    }
    @Override
    public boolean canRotate() { // 0 > 1 > 2 > 3
        return false;
    }

    @Override
    public boolean canMoveRight() {

        List<Block> temp = new ArrayList<>();
        List<Block> temp2 = new ArrayList<>();
        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block moveR_a;
        Block moveR_b;
        Block moveR_c;
        Block moveR_d;
        Block moveR_e;
        Block moveR_f;
        moveR_a = new Block(a.getX(), a.getY()+1);
        moveR_b = new Block(b.getX(), b.getY()+1);
        moveR_c = new Block(c.getX(), c.getY() + 1);
        moveR_d = new Block(d.getX(), d.getY() + 1);
        moveR_e=new Block(e.getX(), e.getY()+1);
        moveR_f=new Block(f.getX(),f.getY()+1);
        temp.add(moveR_a);
        temp.add(moveR_b);
        temp.add(moveR_c);
        temp.add(moveR_d);
        temp.add(moveR_e);
        temp.add(moveR_f);
        temp2.add(moveR_a);
        temp2.add(moveR_b);
        temp2.add(moveR_c);
        temp2.add(moveR_d);

        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            int[][] board= gameBoard.getBoard();

            if(!(y < gameBoard.WIDTH && y >= 0 && x< gameBoard.HEIGHT && x>=0 && board[x][y] == 0)){
                return false;
            }
            if(possible==false){
                return false;
            }

        }
        for (Block block2 : temp2) {
            int x = block2.getX()+1;
            int y = block2.getY()-1;
            int[][] board= gameBoard.getBoard();
            if (board[x-1][y] != 0) {
                possible = false;//이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)//이동 불가
                return false;
            }
        }
        return true; //이동 가능
    }

    @Override
    public boolean canMoveLeft() {

        List<Block> temp = new ArrayList<>();
        List<Block> temp2 = new ArrayList<>();
        //회전 후 a,b,c,d 임시 저장 및 세팅
        Block moveL_a;
        Block moveL_b;
        Block moveL_c;
        Block moveL_d;
        Block moveL_e;
        Block moveL_f;
        moveL_a = new Block(a.getX(), a.getY()-1);
        moveL_b = new Block(b.getX(), b.getY()-1);
        moveL_c = new Block(c.getX(), c.getY() - 1);
        moveL_d = new Block(d.getX(), d.getY() - 1);
        moveL_e = new Block(e.getX(), e.getY() - 1);
        moveL_f = new Block(f.getX(), f.getY() - 1);
        temp.add(moveL_a);
        temp.add(moveL_b);
        temp.add(moveL_c);
        temp.add(moveL_d);
        temp.add(moveL_e);
        temp.add(moveL_f);
        temp2.add(moveL_a);
        temp2.add(moveL_b);
        temp2.add(moveL_c);
        temp2.add(moveL_d);
        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            int[][] board= gameBoard.getBoard();

            if(!(y < gameBoard.WIDTH && y >= 0 && x< gameBoard.HEIGHT && x>=0 && board[x][y] == 0)){
                return false;
            }
            if(possible==false){
                return false;
            }
        }
        for (Block block2 : temp2) {
            int x = block2.getX()+1;
            int y = block2.getY()+1;
            int[][] board= gameBoard.getBoard();
            if (board[x-1][y] != 0) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                possible = false;  //이동 불가
                return false;
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
        Block moveD_e;
        Block moveD_f;
        moveD_a = new Block(a.getX()+1, a.getY());
        moveD_b = new Block(b.getX()+1, b.getY());
        moveD_c = new Block(c.getX()+1, c.getY());
        moveD_d = new Block(d.getX()+1, d.getY());
        moveD_e = new Block(e.getX()+1, e.getY());
        moveD_f = new Block(f.getX()+1, f.getY());
        temp.add(moveD_a);
        temp.add(moveD_b);
        temp.add(moveD_c);
        temp.add(moveD_d);
        temp.add(moveD_e);
        temp.add(moveD_f);

        for (Block block : temp) {
            int x = block.getX();
            int y = block.getY();
            int[][] board= gameBoard.getBoard();

            if (!(y < gameBoard.WIDTH && y >= 0 && x< gameBoard.HEIGHT && x>=0)) {   //이동 후 각 블록에 대해 ( board 밖 혹은 이미 블록이 있을때)
                return false;  //이동 불가
            }
            if(board[x][y]!=0){
                possible=false;
            }

        }
        return true; //이동 가능
    }

    @Override
    public void rotate() {
    }


    @Override
    public void moveR() {

       // preChange();


        //이동 후 a b c d 좌표 변경
        a.setY(a.getY()+1);
        b.setY(b.getY()+1);
        c.setY(c.getY()+1);
        d.setY(d.getY()+1);
        e.setY(e.getY()+1);
        f.setY(f.getY()+1);

       // postChange();

    }

    @Override
    public void moveL() {
        //preChange();

        //이동 후 a b c d 좌표 변경
        a.setY(a.getY()-1);
        b.setY(b.getY()-1);
        c.setY(c.getY()-1);
        d.setY(d.getY()-1);
        e.setY(e.getY()-1);
        f.setY(f.getY()-1);

        //postChange();
    }

    @Override
    public void moveD() {
        List<Block> temp = new ArrayList<>();
        //preChange();
        if(canMoveDown()){
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
                gameBoard.board[x][y] = 0;
                //colorErase(y,x,);
            }
            a.setX(a.getX()+1);
            b.setX(b.getX()+1);
            c.setX(c.getX()+1);
            d.setX(d.getX()+1);
            e.setX(e.getX()+1);
            f.setX(f.getX()+1);
            gameBoard.updateScore(gameBoard.downScore);
        }
        //이동 후 a b c d 좌표 변경

        //postChange();
    }

    @Override
    public void straightD() {
        List<Block> temp = new ArrayList<>();
        while(canMoveDown()){
            //이동 후 a b c d 좌표 변경
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
                gameBoard.board[x][y] = 0;
                //colorErase(y,x);
            }
            a.setX(a.getX()+1);
            b.setX(b.getX()+1);
            c.setX(c.getX()+1);
            d.setX(d.getX()+1);
            e.setX(e.getX()+1);
            f.setX(f.getX()+1);

        }
    }

    //Getter Setter
    public Block getA() {
        return a;
    }

    public void setA(Block a) {
        this.a = a;
    }

    public Block getB() {
        return b;
    }

    public void setB(Block b) {
        this.b = b;
    }

    public Block getC() {
        return c;
    }

    public void setC(Block c) {
        this.c = c;
    }

    public Block getD() {
        return d;
    }

    public void setD(Block d) {
        this.d = d;
    }

    public Block getE() {
        return e;
    }

    public void setE(Block e) {
        this.e = e;
    }


    public Block getF() {
        return f;
    }

    public void setF(Block f) {
        this.f = f;
    }

    public int getCenter_x() {
        return center_x;
    }

    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    public int getCenter_y() {
        return center_y;
    }

    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    @Override
    public List<Block> getBlockList() {
        return blockList;
    }



    @Override
    public Block getItem() {
        return null;
    }

    @Override
    public void setItem(Block item) {
    }
}


