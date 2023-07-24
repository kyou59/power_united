package jp.kobe_u.cs.daikibo.LSMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  
public class LsmsController {  
    @Autowired
    LsmsService ts;
    //タイトル画面を表示
    @GetMapping("/")
    String showIndex() {
        return "index";
    }
    //メイン画面を表示
    @GetMapping("/read")
    String showLsmsList(Model model) {
        List<Lsms> list = ts.getAllLsms(); //全つぶやきを取得
        model.addAttribute("LsmsList", list);   //モデル属性にリストをセット
        model.addAttribute("LsmsForm", new LsmsForm());  //空フォームをセット
        return "Lsms_list"; //リスト画面を返す
    }
    //つぶやきを投稿
    @PostMapping("/read")
    String postLsms(@ModelAttribute("LsmsForm") LsmsForm form, Model model) {
        //フォームからエンティティに移し替え
        Lsms t = new Lsms();
        t.setName(form.getName());
        t.setComment(form.getComment());
        //サービスに投稿処理を依頼
        ts.postLsms(t);
        return "redirect:/read"; //メイン画面に転送
    }
}
