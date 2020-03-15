package kr.co.dao;

import java.util.List;
import java.util.Map;

import kr.co.vo.BoardVO;
import kr.co.vo.Criteria;
import kr.co.vo.SearchCriteria;

public interface BoardDAO {            //interface는 모든 메소드가 기본적으로 public

	// 게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	// 게시물 목록 조회
		public List<BoardVO> list(SearchCriteria scri) throws Exception;
		//게시물 총 갯수
		public int listCount(SearchCriteria scri) throws Exception;
		
		// 게시물 조회
		public BoardVO read(int bno) throws Exception;
		// 게시물 수정
		public void update(BoardVO boardVO) throws Exception;
		
		// 게시물 삭제
		public void delete(int bno) throws Exception;
	
		public void insertFile(Map<String, Object> map) throws Exception;
		//첨부파일 조회
		//타입을 List형식으로 한 이유는 나중에 여러개의 파일을 등록할 수 있도록 하기 위함입니다.
		public List<Map<String, Object>> selectFileList(int bno) throws Exception;
		// 첨부파일 다운
		public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
		// 첨부파일 수정
		public void updateFile(Map<String, Object> map) throws Exception;
		// 게시판 조회수
		public void boardHit(int bno) throws Exception;
}