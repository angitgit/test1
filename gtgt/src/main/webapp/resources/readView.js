$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			// 수정뷰 
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/board/updateView");
				formObj.attr("method", "get");
				formObj.submit();				
			})
			
			// 삭제
			$(".delete_btn").on("click", function(){
				
				var deleteYN = confirm("삭제하시겠습니까?");
				if(deleteYN == true){
					
				formObj.attr("action", "/board/delete");
				formObj.attr("method", "post");
				formObj.submit();
					
				}
			})
			
			// 목록
			/*$(".list_btn").on("click", function(){
				
				location.href = "/board/list?page=${scri.page}"
						      +"&perPageNum=${scri.perPageNum}"
						      +"&searchType=${scri.searchType}&keyword=${scri.keyword}";
			})*/
			// 댓글 작성
			$(".replyWriteBtn").on("click", function(){            
				var formObj = $("form[name='replyForm']");    //formObj가 이미 쓰고있기때문에 함수 안에 다시 만들어주고 
				formObj.attr("action", "/board/replyWrite");  //form의 name을 replyForm으로 바꾸어줍니다.
				formObj.submit();
			});
			
			
		})
		//파일 다운
function fn_fileDown(fileNo){
			var formObj = $("form[name='readForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/board/fileDown");
			formObj.submit();
		}