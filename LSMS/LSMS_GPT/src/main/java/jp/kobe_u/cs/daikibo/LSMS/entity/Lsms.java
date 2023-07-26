package jp.kobe_u.cs.daikibo.LSMS.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Lsms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // センサエンティティの識別子
    String sensorName; // センサ名前
    String location; // センサの場所
    int stock; // 在庫数
    String purpose; // 使用目的
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt; // 作成日時
}
