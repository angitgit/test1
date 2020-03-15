$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="member/logout";
		})
		
		$("#registerBtn").on("click", function(){
			location.href="member/register";
		})
		
		$("#memberUpdateBtn").on("click", function(){
			location.href="member/memberUpdateView";
		})
		
		$("#delBtn").on("click", function(){
			location.href="member/memberDeleteView";
		})
		
	})