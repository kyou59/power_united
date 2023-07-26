package jp.kobe_u.cs.daikibo.LSMS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.kobe_u.cs.daikibo.LSMS.entity.Lsms;
import jp.kobe_u.cs.daikibo.LSMS.repository.LsmsRepository;

@Service
public class LsmsService {
    @Autowired
    LsmsRepository repo;

    public Lsms postLsms(Lsms t) {
        t.setCreatedAt(new Date());
        return repo.save(t);
    }

    public List<Lsms> getAllLsms() {
        Iterable<Lsms> found = repo.findAll();
        ArrayList<Lsms> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }

    public Lsms getLsms(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Lsms> searchLsms(String keyword) {
        return repo.findBySensorNameContainsOrLocationContainsOrPurposeContains(keyword, keyword, keyword);
    }
}
