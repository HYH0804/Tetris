package com.example.fxtest.brick;

public enum Item {
    NORMAL(1),
    WEIGHT(2),
    ROWDELETE(3),
    COLUMNDELETE(4),
    BLIND(5),
    NUCLEAR(6);


    private final int num;

    Item(int num) {
        this.num = num;
    }

    public int getNum(){
        return this.num;
    }

    // 정수 값에 해당하는 Item enum을 반환하는 정적 메서드
    public static Item fromNum(int num) {
        for (Item item : Item.values()) {
            if (item.getNum() == num) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid Item number: " + num);
    }

    public void doItem(){
        //3번이면 구현
        //4번이면 구현
    }
}
