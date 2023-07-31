package jp.kobe_u.cs.daikibo.LSMS.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Lsms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; //センサエンティティの識別子
    String sensorName;    //センサ名前
    String location;    //使用場所
    String stock; //在庫数
    String purpose; //使用用途
    String username; //ユーザ名（追加）
    @ElementCollection
    @Getter @Setter
    List<String> usernameHistory = new ArrayList<>(); // ユーザ名の履歴リスト（追加）
    @ElementCollection
    @Getter @Setter   
    List<String> purposeHistory = new ArrayList<>(); // 使用目的の履歴リスト（追加）    
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt; //作成日時
}
