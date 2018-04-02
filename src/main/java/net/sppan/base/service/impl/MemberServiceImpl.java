package net.sppan.base.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sppan.base.dao.IMemberDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Member;
import net.sppan.base.service.IMemberService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

/**
 * <p>
 * 会员服务实现类
 * </p>
 *
 * @author zzw
 * @since 2016-12-28
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Integer> implements IMemberService {

	@Autowired
	private IMemberDao memberDao;

	@Override
	public IBaseDao<Member, Integer> getBaseDao() {
		return this.memberDao;
	}

	@Override
	public Member findByMemberName(String name) {
		return memberDao.findByName(name);
	}

	@Override
	public void saveOrUpdate(Member member) {
		if (member.getId() != null) {
			Member dbMember = find(member.getId());
			dbMember.setName(member.getName());
			dbMember.setBalance(member.getBalance());
			dbMember.setUpdateTime(new Date());
			update(dbMember);
		} else {
			member.setCreateTime(new Date());			
			save(member);
		}
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

}
