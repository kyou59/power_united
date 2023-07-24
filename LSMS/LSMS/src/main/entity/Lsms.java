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
    Long id; //センサエンティティの識別子
    String sensor;    //センサ名前
    String name;    //使用者名前
    String comment; //コメント
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt; //作成日時
}
