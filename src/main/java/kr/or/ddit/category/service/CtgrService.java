package kr.or.ddit.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.category.dao.CtgrDao;
import kr.or.ddit.category.dao.CtgrDaoI;
import kr.or.ddit.category.model.CtgrVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDaoI;
@Service("ctgrService")
public class CtgrService implements CtgrServiceI {
	
	@Resource(name="ctgrDao")
	private CtgrDaoI ctgrDao;
	
	public CtgrService() {
		
	}
	
	public CtgrService(CtgrDaoI ctgrDao) {
		this.ctgrDao = ctgrDao;
	}
	
	
	public CtgrDaoI getCtgrDao() {
		return ctgrDao;
	}

	public void setCtgrDao(CtgrDaoI ctgrDao) {
		this.ctgrDao = ctgrDao;
	}
	 
	

	@Override
	public List<CtgrVO> selectAllCtgr() {
		System.out.println(ctgrDao.selectAllCtgr().get(0).getCtgr_name());
		return ctgrDao.selectAllCtgr();
	}

	
	@Override
	public int insertCtgr(CtgrVO ctgrVo) {
		return ctgrDao.insertCtgr(ctgrVo);
	}


	@Override
	public int updateCtgr(CtgrVO ctgrVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return ctgrDao.updateCtgr(ctgrVo);
	}
	
	
	@Override
	public CtgrVO getCtgr(int ctgr_seq1) {
		return ctgrDao.getCtgr(ctgr_seq1);
	}
	
}
