package jp.kobe_u.cs.daikibo.LSMS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.LSMS.entity.Lsms;
import jp.kobe_u.cs.daikibo.LSMS.repository.LsmsRepository;

@Service
public class LsmsService {
    @Autowired
    LsmsRepository repo;

    // つぶやきを投稿
    public Lsms postLsms(Lsms t) {
        // 名前がない場合の業務ロジック
        String name = t.getUsername();
        if (name == null || name.length() == 0) {
            t.setUsername("名無しさん");
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

    // センサ情報の取得
    public Optional<Lsms> getLsms(Long id) {
        return repo.findById(id);
    }

// センサ情報の更新
public Lsms updateLsms(Long id, Lsms newSensorInfo) {
    Optional<Lsms> optional = repo.findById(id);
    if(optional.isPresent()){
        Lsms t = optional.get();
        // 使用目的の更新履歴に現在の使用目的を追加
        t.getPurposeHistory().add(t.getPurpose());
        t.setSensorName(newSensorInfo.getSensorName());
        t.setLocation(newSensorInfo.getLocation());
        t.setStock(newSensorInfo.getStock());
        t.setPurpose(newSensorInfo.getPurpose());
        // ユーザ名の更新履歴に現在のユーザ名を追加
        t.getUsernameHistory().add(t.getUsername());
        t.setUsername(newSensorInfo.getUsername());
        return repo.save(t);
    }else{
        return null;
    }
}

    // センサの削除
    public void deleteLsms(Long id) {
        repo.deleteById(id);
    }

    public List<Lsms> searchLsms(String keyword) {
        return repo.findBySensorNameContainsOrLocationContainsOrPurposeContains(keyword, keyword, keyword);
    }
}