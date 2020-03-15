package kr.co.dao;

import kr.co.vo.MemberVO;

public interface MemberDAO {
	
	// 회원가입
	public void register(MemberVO vo) throws Exception;
//
	public MemberVO login(MemberVO vo) throws Exception;
	//
	// 회원정보 수정
	public void memberUpdate(MemberVO vo)throws Exception;
	//서비스에서 보낸 파라미터들을 memberUpdate(MemberVO vo)에 담습니다.
	// 회원 탈퇴
		public void memberDelete(MemberVO vo)throws Exception;
		// 패스워드 체크
		public int passChk(MemberVO vo) throws Exception;
		// 아이디 중복체크
		public int idChk(MemberVO vo) throws Exception;
}
