package net.sppan.base.service;

import net.sppan.base.entity.Member;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author zzw
 * @since 2016-12-28
 */
public interface IMemberService extends IBaseService<Member, Integer> {

	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	Member findByMemberName(String name);

	/**
	 * 增加或者修改
	 * @param user
	 */
	void saveOrUpdate(Member member);

}
