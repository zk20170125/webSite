package net.sppan.base.controller.member;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Member;
import net.sppan.base.service.IMemberService;
import net.sppan.base.service.specification.SimpleSpecificationBuilder;
import net.sppan.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private IMemberService memberService;	
	
	@RequestMapping(value = { "/index" })
	public String index() {
		return "admin/member/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Member> list() {
		SimpleSpecificationBuilder<Member> builder = new SimpleSpecificationBuilder<Member>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", Operator.likeAll.name(), searchText);
		}
		Page<Member> page = memberService.findAll(builder.generateSpecification(), getPageRequest());
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/member/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Member member = memberService.find(id);
		map.put("member", member);
		return "admin/member/form";
	}
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Member member,ModelMap map){
		try {
			memberService.saveOrUpdate(member);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			memberService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	
}
