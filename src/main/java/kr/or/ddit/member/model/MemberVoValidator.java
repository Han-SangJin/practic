package kr.or.ddit.member.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberVoValidator implements Validator{
	private static final Logger logger = LoggerFactory.getLogger(MemberVoValidator.class);

	// 검증하려고 하는 객체가 MemberVoValidator 에서 검증이 가능한 객체인지 boolean으로 리턴하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVo.class.isAssignableFrom(clazz);
	}

	// 검증 로직을 작성하는 메소드
	// Object target : command 객체
	@Override
	public void validate(Object target, Errors errors) {
		MemberVo memberVo = (MemberVo)target;

		// usernm 값이 null 이거나 empty 문자열이면 안됨
		System.out.println("memberVo.getUsernm() : " + memberVo.getUsernm());
		logger.debug("MemberVoValidator memberVo.getUsernm() : " + memberVo.getUsernm());
		if(memberVo.getUsernm() == null || memberVo.getUsernm().equals("")){
			errors.rejectValue("usernm", "required"); 	//required(필수의)
		}
	}
	
}
