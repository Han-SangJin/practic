package kr.or.ddit.member.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.login.web.LoginController;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/memberRegist")
@Controller
public class MemberRegistController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired	// ioc.xml내용 자동으로 불러옴
	ApplicationContext context;
	
	
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	
	@RequestMapping(path = "/view", method = RequestMethod.GET)	
	public String getView() {
		logger.debug("memberRegist-Controller.getView()");
		return "member/memberRegist";	
	}
	
	//#{userid}, #{usernm}, #{pass}, SYSDATE, #{alias}, #{addr1}, #{addr2}, #{zipcode}, #{filename}, #{realFilename})
	@RequestMapping(path="/process")							
	public String process(String userid, String usernm, String pass, String alias, String addr1, String addr2, 
										String zipcode, @RequestPart("realFilename") MultipartFile file) { 
		
		logger.debug("arameter : {}, {}, {}, {}, {}, {}, {}", userid, usernm, alias, pass, addr1, addr2, zipcode );
		logger.debug("realFilename : {} / filename : {} / size : {}", 
														file.getName(), file.getOriginalFilename(), file.getSize());
		
		String real_Filename = "D:\\upload\\" + file.getOriginalFilename();
		File uploadFile = new File(real_Filename);
		
		// 파일 업로드		// 이미지 파일 realname, name 둘중 하나만 있으면 오류발생 둘다 널이면 괜찮음
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		// 사용자 정보 등록
		MemberVo memberVo = new MemberVo(userid,usernm,pass,alias,addr1,addr2,zipcode,real_Filename,file.getOriginalFilename());
		logger.debug("memberVo : {}, {}, {}, {}, {}, {}, {}, {}, {}", userid, usernm, alias, pass, addr1, addr2, zipcode, real_Filename, file.getOriginalFilename());
		int insertCnt = memberService.insertMember(memberVo);
		logger.debug("insertCnt : {}", insertCnt);
		
		if(insertCnt == 1){
			return "redirect:/memberList/process";
		
		}else {
			return "member/memberRegist";
		}
	}
	
//	Part profile = request.getPart("realFilename");;
//	logger.debug("file : {}", profile.getHeader("Content-Disposition"));
//	String realFilename = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
//	String ext = (".").concat(FileUploadUtil.getExtension(realFilename));
//	String fileName = UUID.randomUUID().toString();
//	String filePath = "";
//	
//	if(profile.getSize() > 0) {
//		filePath = "D:\\attachfile\\" + fileName + ext;
//		profile.write(filePath);
//	}
}
