package jp.kobe_u.cs.daikibo.LSMS.controller;

import lombok.Data;

@Data
public class LsmsForm {
    String sensorName; //センサ名
    String location; //場所
    String stock; //在庫数
    String purpose; //使用目的
    String username; //ユーザ名（追加）
}
