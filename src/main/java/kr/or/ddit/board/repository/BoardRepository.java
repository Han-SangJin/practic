package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.common.model.PageVO;

@Repository("boardRepository")	// xml파일에서 bean id="boardRepository" 와 같은 역할
public class BoardRepository implements BoardRepositoryI{

	@Override
	public BoardVO getBoard(int boardNo) {
		// db에서 조회를 해야하나, 지금은 설정이 갖춰지지 않았기 때문에 
		// 가짜 객체(Mock)을 반환한다.
		if(boardNo == 1) {
			return new BoardVO(1, "첫번째 글",  "내용");
		}
		else
			return null;
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
	public List<BoardVO> selectBoardPageList(SqlSession sqlSession, PageVO pageVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectBoardTotalCnt(SqlSession sqlSession, int ctgr_seq1) {
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

}
