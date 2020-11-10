package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.common.model.PageVO;

// <bean id="boardService" /> xml bean id 와 동일한 기능
@Service("boardService")
public class BoardService implements BoardServiceI{
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	public BoardRepositoryI getBoardRepository() {
		return boardRepository;
	}
	
	public void setBoardRepository(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardService() {
		
	}
	
	public BoardService(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
	@Override
	public BoardVO getBoard(int boardNo) {
		return boardRepository.getBoard(boardNo);
	}

	
	@Override
	public BoardVO selectBoard(int board_seq1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> selectAllBoard(int ctgr_seq1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int selectBoardTotalCnt(int ctgr_seq1) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public Map<String, Object> selectBoardPageList(PageVO pageVo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
