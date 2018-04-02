package net.sppan.base.dao;

import org.springframework.stereotype.Repository;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Member;

@Repository
public interface IMemberDao extends IBaseDao<Member, Integer> {

	Member findByName(String name);

}
