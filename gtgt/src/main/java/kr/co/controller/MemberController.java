package kr.co.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
			// 회원가입 post
			@RequestMapping(value = "/register", method = RequestMethod.POST)
			public String postRegister(MemberVO vo) throws Exception {
				logger.info("post register");
				int result = service.idChk(vo);
				try {
					if(result == 1) {
						return "/member/register";
					}else if(result ==0 ) {
					String inputPass = vo.getUserPass();
					String pwd = pwdEncoder.encode(inputPass);
					vo.setUserPass(pwd);
					service.register(vo);
					}
					// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
					// 존재하지 않는다면 -> register
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return "redirect:/";
			}
//로그인 post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo,  HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		session.getAttribute("member");
		MemberVO login = service.login(vo);
		boolean pwdMatch = pwdEncoder.matches(vo.getUserPass(), login.getUserPass());
		
		if(login != null && pwdMatch == true) {
			session.setAttribute("member", login);
			
		}else {
			session.setAttribute("member", null); 
			rttr.addFlashAttribute("msg" , false);
		
		
		}
		 /* 회원가입을 암호화를 하여 했기때문에 로그인을 할 때에는 입력된 비밀번호와 조회한 비밀번호가 맞아야하는데요. 먼저 비밀번호를 조회해야겠지요.
		 * service.login서비스를 실행하여 login.getUserPass를 가져올 수 있도록합니다.
		 * pwdEncoder.matches(입력된 비밀번호(), 암호화된 비밀번호()) 비교를 해줍니다. 맞으면 true 틀리면 false를
		 * 반환하여 로그인을 진행합니다.*/
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	// 회원정보 수정 get
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "member/memberUpdateView";
	}
  //회원정보 수정 post
	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		
		/*
		 * MemberVO login = service.login(vo); boolean pwdMatch=
		 * pwdEncoder.matches(vo.getUserPass(), login.getUserPass()); if(pwdMatch) {
		 * service.memberUpdate(vo); session.invalidate(); }else { return
		 * "member/memberUpdateView"; }
		 */
		
		//회원정보 수정 페이지에서 수정버튼을 누르면 /memberUpdate요청
		service.memberUpdate(vo);
		//파라미터들을 service.memberUpdate(vo)에 넣어줘서 service로 보내줍니다
		session.invalidate();
		//세션을 끊고 redirect로
		return "redirect:/";
	}
	// 회원 탈퇴 get
		@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
		public String memberDeleteView() throws Exception{
			return "member/memberDeleteView";
		}
		
		// 회원 탈퇴 post
		@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
		public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
			
			service.memberDelete(vo);
			session.invalidate();
			
			return "redirect:/";
		}
		
		// 패스워드 체크
		@ResponseBody
		@RequestMapping(value="/passChk", method = RequestMethod.POST)
		public boolean passChk(MemberVO vo) throws Exception {

			MemberVO login = service.login(vo);
			boolean pwdChk = pwdEncoder.matches(vo.getUserPass(), login.getUserPass());
			return pwdChk;            // USERID와 USERPASS가 일치하면 result에는 1이 들어가겠지요.
		}
		
		// 아이디 중복 체크
		@ResponseBody
		@RequestMapping(value="/idChk", method = RequestMethod.POST)
		public int idChk(MemberVO vo) throws Exception {
			int result = service.idChk(vo);
			return result;
		}
		
	}


