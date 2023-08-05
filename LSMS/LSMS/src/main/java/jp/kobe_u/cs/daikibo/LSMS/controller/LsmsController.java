package jp.kobe_u.cs.daikibo.LSMS.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobe_u.cs.daikibo.LSMS.entity.Lsms;
import jp.kobe_u.cs.daikibo.LSMS.service.LsmsService;

@Controller
public class LsmsController {
    @Autowired
    LsmsService ts;

    // タイトル画面を表示
    @GetMapping("/")
    String showIndex() {
        return "index";
    }

    // メイン画面を表示
    @GetMapping("/read")
    String showLsmsList(Model model) {
        List<Lsms> list = ts.getAllLsms(); //全つぶやきを取得
        model.addAttribute("LsmsList", list);   //モデル属性にリストをセット
        model.addAttribute("LsmsForm", new LsmsForm());  //空フォームをセット
        return "Lsms_list"; //リスト画面を返す
    }

    // 投稿
    @PostMapping("/read")
    String postLsms(@ModelAttribute("LsmsForm") LsmsForm form, Model model) {
        // フォームからエンティティに移し替え
        Lsms t = new Lsms();
        t.setSensorName(form.getSensorName());
        t.setLocation(form.getLocation());
        t.setStock(form.getStock());
        t.setPurpose(form.getPurpose());
        t.setUsername(form.getUsername());
        // 投稿日時を設定
        t.setPostDate(LocalDateTime.now());
        // サービスに投稿処理を依頼
        ts.postLsms(t);
        return "redirect:/read"; //メイン画面に転送
    }

    // センサ詳細画面を表示
    @GetMapping("/sensor/{id}")
    String showLsmsDetail(@PathVariable Long id, Model model) {
        Optional<Lsms> optional = ts.getLsms(id);
        if(optional.isPresent()){
            Lsms t = optional.get();
            model.addAttribute("Lsms", t);
            // 投稿日時をフォーマットしてモデルに追加
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedPostDate = t.getPostDate().format(formatter);
            model.addAttribute("formattedPostDate", formattedPostDate);

            model.addAttribute("LsmsForm", new LsmsForm());
            model.addAttribute("usernameHistory", t.getUsernameHistory());            
            // 使用目的の更新履歴をモデルに追加            
            model.addAttribute("purposeHistory", t.getPurposeHistory());
            return "sensor_detail"; 
        }else{
            return "redirect:/read";
        }
    }

    // センサ情報を更新
    @PostMapping("/sensor/{id}")
    String updateLsms(@PathVariable Long id, @ModelAttribute("LsmsForm") LsmsForm form, Model model) {
        Lsms t = new Lsms();
        t.setSensorName(form.getSensorName());
        t.setLocation(form.getLocation());
        t.setStock(form.getStock());
        t.setPurpose(form.getPurpose());
        t.setUsername(form.getUsername());
        ts.updateLsms(id, t);
        return "redirect:/sensor/"+id;
    }

    // センサを削除
    @PostMapping("/sensor/{id}/delete")
    String deleteLsms(@PathVariable Long id, Model model) {
        ts.deleteLsms(id);
        return "redirect:/read";
    }

    //センサを検索する
    @GetMapping("/search")
    String searchLsms(@RequestParam String keyword, Model model) {
        List<Lsms> list = ts.searchLsms(keyword);
        model.addAttribute("LsmsList", list);
        model.addAttribute("LsmsForm", new LsmsForm()); 
        return "search_result";
    }
}