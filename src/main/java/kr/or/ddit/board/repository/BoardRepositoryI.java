package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.common.model.PageVO;

public interface BoardRepositoryI {

	BoardVO getBoard(int boardNo);
	
	BoardVO selectBoard(int board_seq1);
	
	List<BoardVO> selectBoardPageList(SqlSession sqlSession, PageVO pageVo);
	
	int selectBoardTotalCnt(SqlSession sqlSession, int ctgr_seq1);
	 
	int insertBoard(BoardVO boardVo);
	
	int inBoard(BoardVO boardVo);
	
	int deleteBoard(int board_seq1);
	
	int updateBoard(BoardVO boardVo);
}
