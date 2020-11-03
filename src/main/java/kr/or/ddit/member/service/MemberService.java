package kr.or.ddit.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

@Service("memberService")
public class MemberService implements MemberServiceI {
	
	
	
	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	// dao getter setter 생성
	public MemberDaoI getMemberDao() {
		return memberDao;
	}
	public void setMemberDao(MemberDaoI memberDao) {
		this.memberDao = memberDao;
	}
	
	
	
	// service 기본 생성자
	public MemberService() {
		
	}
	// dao 정보를 받아오는 service 생성자
	public MemberService(MemberDaoI memberRepository) {
		this.memberDao = memberRepository;
	}
	
	
	
	
	// 멤버 체크
	@Override
	public MemberVo getMember(String userid) {
		return memberDao.getMember(userid);
	}

}
