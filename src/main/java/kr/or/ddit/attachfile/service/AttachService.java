package kr.or.ddit.attachfile.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attachfile.dao.AttachDao;
import kr.or.ddit.attachfile.dao.AttachDaoI;
import kr.or.ddit.attachfile.model.AttachVO;
import kr.or.ddit.db.MybatisUtil;

public class AttachService implements AttachServiceI {

	
	private AttachDaoI attachDao;
	
	public AttachService() {
		attachDao = new AttachDao();
	}
	
	
	@Override
	public List<AttachVO> selectAllAttach(int board_seq1) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return attachDao.selectAllAttach(board_seq1);
	}
	
	@Override
	public int deleteAttach(List<AttachVO> attachList) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return attachDao.deleteAttach(attachList); 
	}

	@Override
	public int insertAttach(List<AttachVO> attachList) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return attachDao.insertAttach(attachList);
	}


	@Override
	public int updateBoard(List<AttachVO> attachList) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return attachDao.updateBoard(attachList);
	}
	
}
