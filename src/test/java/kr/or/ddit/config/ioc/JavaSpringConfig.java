package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

@Configuration
public class JavaSpringConfig {
	
	// boardRepository, boardService
	// 메소드이름 ==> 스프링 빈이름
	// <bean id="boardRepository" class="kr.or.ddit.board.repository.BoardRepository"/> 와 동일한 기능 (xml)
	@Bean
	public BoardRepositoryI boardRepository() {
		return new BoardRepository();	// 컨테이너가 읽어들여서 실행하는것이기 때문에 new 로 매번 생성하는것이 아니라 한번만 생성됨
	}
	
	// <bean id="boardService" class="kr.or.ddit.board.service.BoardService"/> 와 동일한 기능 (xml) 
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setBoardRepository(boardRepository());
		
		// 아래와 같이 직접 new 연산자를 통해 생성한 객체는 스프링 빈이 아니다
		// @Bean 어노테이션이 붙은 메소드를 호출해야 스프링 컨테이너에서 관리되는 스프링빈을 얻을수 있다.
//		boardService.setBoardRepository(new BoardRepository());
		return boardService;
	}
	
	
}
