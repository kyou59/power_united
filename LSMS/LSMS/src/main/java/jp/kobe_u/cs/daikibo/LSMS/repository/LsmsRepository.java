package jp.kobe_u.cs.daikibo.LSMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.LSMS.entity.Lsms;

@Repository

public interface LsmsRepository extends CrudRepository<Lsms, Long>{

}