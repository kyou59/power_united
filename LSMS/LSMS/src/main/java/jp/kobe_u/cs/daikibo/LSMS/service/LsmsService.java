package jp.kobe_u.cs.daikibo.LSMS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class LsmsService {

    @Autowired

    LsmsRepository repo; // レポジトリ

    // つぶやきを投稿

    public Lsms postLsms(Lsms t) {

        // 名前がない場合の業務ロジック

        String name = t.getName();

        if (name == null || name.length() == 0) {

            t.setName("名無しさん");

        }

        t.setCreatedAt(new Date()); // 作成日時をセット

        return repo.save(t); // セーブしたオブジェクトを返却

    }

    // 全つぶやきを取得

    public List<Lsms> getAllLsms() {

        Iterable<Lsms> found = repo.findAll();

        ArrayList<Lsms> list = new ArrayList<>();

        found.forEach(list::add);

        return list;

    }

}
