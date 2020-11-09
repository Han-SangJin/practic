package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.model.MemberVo;

public interface MemberServiceI {

	MemberVo getMember(String userId);
 
	List<MemberVo> selectAllMember();
	
	int insertMember(MemberVo memberVo);
	
	int deleteMember(String userid);
	
	int updateMember(MemberVo memberVo);

	Map<String, Object> selectMemberPageList(PageVO pageVo);
} 
