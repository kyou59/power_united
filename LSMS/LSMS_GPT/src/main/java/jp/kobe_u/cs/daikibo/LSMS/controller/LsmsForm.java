package jp.kobe_u.cs.daikibo.LSMS.controller;

import lombok.Data;

@Data
public class LsmsForm {
    String sensorName; // センサ名前
    String location; // センサの場所
    int stock; // 在庫数
    String purpose; // 使用目的
}
