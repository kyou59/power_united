package jp.kobe_u.cs.daikibo.LSMS.controller;
import lombok.Data;
@Data  
public class LsmsForm {
    String name; //投稿者
    String comment; //つぶやき（省略不可）  
}
