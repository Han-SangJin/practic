package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.common.model.PageVO;

public interface BoardServiceI {

	BoardVO getBoard(int boardNo);
	
	BoardVO selectBoard(int board_seq1);
	
	Map<String, Object> selectBoardPageList(PageVO pageVo);
	
	int selectBoardTotalCnt(int ctgr_seq1);
	
	int insertBoard(BoardVO boardVo);

	int inBoard(BoardVO boardVo);
	
	int deleteBoard(int board_seq1);
	
	int updateBoard(BoardVO boardVo);
}
