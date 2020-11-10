package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.common.model.PageVO;

@Repository("boardRepository")	// xml파일에서 bean id="boardRepository" 와 같은 역할
public class BoardRepository implements BoardRepositoryI {

	
	
	
	@Override
	public List<BoardVO> selectBoardPageList(SqlSession sqlSession, PageVO pageVo) {
		return sqlSession.selectList("board.selectBoardPageList", pageVo);
	}
	
	
	@Override
	public int selectBoardTotalCnt(SqlSession sqlSession, int ctgr_seq1) {
		return sqlSession.selectOne("board.selectBoardTotalCnt", ctgr_seq1);
	
	}
	
	
	
	
	@Override
	public BoardVO getBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public BoardVO selectBoard(int board_seq1) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public int insertBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int board_seq1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return 0;
	}




}
