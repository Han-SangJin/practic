package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

@Repository("memberDao")	
public class MemberDao implements MemberDaoI {
	
	@Override
	public MemberVo getMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSession();
		MemberVo memberVo = sqlSession.selectOne("member.getMember", userid);
		
		sqlSession.close();
		return memberVo;
	}

	
	

	@Override
	public List<MemberVo> selectAllMember() {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<MemberVo> memberList =  sqlSession.selectList("member.selectAllMember");
		
		sqlSession.close();
		return memberList;
	}

	
	@Override
	public int insertMember(MemberVo memberVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = 0;

		try{
			insertCnt = sqlSession.insert("member.insertMember", memberVo);
		}catch(Exception e){
			
		}

		if(insertCnt >= 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return insertCnt;
	}

	
	@Override
	public int deleteMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = sqlSession.delete("member.deleteMember", userid);
		
		if(deleteCnt >=1){
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteCnt;
	}
	

	@Override
	public int updateMember(MemberVo memberVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = sqlSession.update("member.updateMember", memberVo);
		
		if(deleteCnt >=1){
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteCnt;
	}
	  
}
