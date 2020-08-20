var exp= /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
$(document).ready(function(){
	$("#send").click(function(){	
		//아이디
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		//이름
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		//비밀번호
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		//비밀번호 확인
		if($("#pwd_check").val()==""){
			alert("비밀번호 확인을 입력하세요");
			$("#pwd_check").focus();
			return false;
		}
		//비밀번호 일치 확인
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호 불일치");
			$("#pwd_check").focus();
			return false;
		}
		//전화번호 확인(정규식을 만들어놓고 비교)
		if(!$("#phone").val().match(exp)){﻿//전화번호와 위에 선언한 변수가 맞냐고 물어본다.맞으면 보여주면 안되고,﻿안맞을때 보여줘야한다.
			alert("전화번호를 정확하게 입력하세요");
			$("#phone").focus();
			return false;
		}
		//이메일
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		//전화번호
		if($("#phone").val()==""){
			alert("전화번호를 입력하세요");
			$("#phone").focus();
			return false;
		}
		//전화번호 확인
		if(!$("#phone").val().match(exp)){
			alert("전화번호 입력 양식이 아닙니다.");
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();
	})//send
	
	//아이디 중복확인 페이지
	$("#idcheckBtn").click(function(){
		window.open("idcheck.me","","width=800 height=500")
	});
	
	//아이디 중복확인버튼 중복확인
	$("#useBtn").click(function(){
		if($("#userid").val()==""){
		alert("아이디를 입력하세요");
		$("#userid").focus();
		return false;
	}
	$.ajax({
		type:"post",
		url:"idcheck.me",
		data:{"userid":$("#userid").val()},
		success:function(val){
			//alert(val);
			if(val.trim()=="yes"){
				alert("사용가능한 아이디입니다.")
				$(opener.document).find("#userid").val($("#userid").val());
				self.close();
			}else if(val.trim()=="no"){
				alert("사용불가능한 아이디입니다.")
				$("#userid").val("")
			}			
		},
		error:function(e){
			alert("error:"+e)
		}
	});//ajax
	})//useBtn
	
	$("#loginBtn").click(function(){
	if($("#userid").val()==""){
		alert("ID 입력");
		$("#userid").focus();
		return false;
	}
	if($("#pwd").val()==""){
		alert("PWD 입력");
		$("#pwd").focus();
		return false;
	}
	$.ajax({
		type     :"post",
		url      :"login.me",
		data     :{"userid":$("#userid").val(),"pwd":$("#pwd").val()},
		success  :function(value){
					//alert(value.trim());
				if(value.trim()==0){
					alert("일반회원 로그인");
					location.href="view.me";
				}else if(value.trim()==1){
					alert("관리자 로그인");
					location.href="list.me";
				}else if(value.trim()==-1){
					alert("회원이 아닙니다. 회원가입하세요.");
					location.href="insert.me";
				}else if(value.trim()==2){
					alert("비밀번호를 확인하세요");
				}
			},
		error:function(e){
			alert("error:"+e)
		}
	})//ajax
})

	
});//document

