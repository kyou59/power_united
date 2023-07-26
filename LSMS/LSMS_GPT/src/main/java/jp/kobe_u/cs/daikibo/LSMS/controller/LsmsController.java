package jp.kobe_u.cs.daikibo.LSMS.controller;

import java.util.List;

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

    @GetMapping("/")
    String showIndex() {
        return "index";
    }

    @GetMapping("/read")
    String showLsmsList(Model model) {
        List<Lsms> list = ts.getAllLsms();
        model.addAttribute("LsmsList", list);
        model.addAttribute("LsmsForm", new LsmsForm());
        return "Lsms_list";
    }

    @PostMapping("/read")
    String postLsms(@ModelAttribute("LsmsForm") LsmsForm form, Model model) {
        //フォームからエンティティに移し替え
        Lsms t = new Lsms();
        t.setSensorName(form.getSensorName());
        t.setLocation(form.getLocation());
        t.setStock(form.getStock());
        t.setPurpose(form.getPurpose());
        t.setUsername(form.getUsername()); //ユーザ名をエンティティに設定
        //サービスに投稿処理を依頼
        ts.postLsms(t);
        return "redirect:/read"; //メイン画面に転送
    }

    @GetMapping("/search")
    String searchLsms(@RequestParam String keyword, Model model) {
        List<Lsms> list = ts.searchLsms(keyword);
        model.addAttribute("LsmsList", list);
        model.addAttribute("LsmsForm", new LsmsForm());
        return "Lsms_list";
    }

    @GetMapping("/sensor/{id}")
    String showSensorDetails(@PathVariable Long id, Model model) {
        Lsms sensor = ts.getLsms(id);
        model.addAttribute("sensor", sensor);
        return "sensor_details";
    }

}
