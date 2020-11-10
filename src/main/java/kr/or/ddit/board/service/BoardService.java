package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.db.MybatisUtil;

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
	public Map<String, Object> selectBoardPageList(PageVO pageVo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int ctgr_seq1 = pageVo.getCtgr_seq1();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<BoardVO> list = boardRepository.selectBoardPageList(sqlSession, pageVo);
		for(BoardVO post : list) {
			post.setBoard_title(post.getBoard_title().replaceAll(" ", "&nbsp;"));
		}
		
		map.put("selectAllBoard", boardRepository.selectBoardPageList(sqlSession,pageVo));
		 
		int totalCnt = boardRepository.selectBoardTotalCnt(sqlSession, ctgr_seq1);
		System.out.println("totalCnt : " +totalCnt);
		int pages = (int)Math.ceil((double)totalCnt/5);
		map.put("pages", pages);
		
		sqlSession.close();
		return map;
	}
	
	
	@Override
	public int selectBoardTotalCnt(int ctgr_seq1) {
		SqlSession sqlSession = MybatisUtil.getSession();
		return boardRepository.selectBoardTotalCnt(sqlSession, ctgr_seq1);
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
