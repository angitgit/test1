package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.MemberDAO;
import kr.co.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);
		
	}
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	@Override         
	public void memberUpdate(MemberVO vo) throws Exception {
		//Controller에서 보내는 파라미터들을 memberUpdate(MemberVO vo)로 받고
		//받은 vo를 DAO로 보내줍니다.
		dao.memberUpdate(vo);
		
	}
	
		@Override
		public void memberDelete(MemberVO vo) throws Exception {
			dao.memberDelete(vo);
		}
		// 패스워드 체크
		@Override
		public int passChk(MemberVO vo) throws Exception {
			int result = dao.passChk(vo);
			return result;
		}
		// 아이디 중복 체크
		@Override
		public int idChk(MemberVO vo) throws Exception {
			int result = dao.idChk(vo);
			return result;
		}//memberController에서 보낸 파라미터를 memberService로 받아주고 받은 파라미터는 DAO로 보내줍니다.
         //DAO에서 반환한 값 result를 return합니다.

 
}
