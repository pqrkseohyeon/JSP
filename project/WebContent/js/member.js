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
		window.open("idCheck.me","","width=800 height=500")
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
		url:"idCheck.me",
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

	
});//document

function del(userid){
	if(confirm("정말 삭제할까요?")){
	$.getJSON("userDelete.me?userid="+userid,function(data){
		//alert(data.root.length);
		var htmlStr="";
		$.each(data.root,function(key,val){
			htmlStr+="<tr>";
			htmlStr+="<td>"+val.name+"</td>";
			htmlStr+="<td>"+val.userid+"</td>";
			htmlStr+="<td>"+val.phone+"</td>";
			htmlStr+="<td>"+val.email+"</td>";
			htmlStr+="<td>"+val.mode+"</td>";
			if(val.mode=="일반회원"){
				htmlStr+="<td onclick=del('"+val.userid+"'>삭제</td>";
			}else{
				htmlStr+="<td>Admin</td>";
			}
			htmlStr+="</tr>";			
		})
		$("table tbody").html(htmlStr);
		$("#cntSpan").text(data.rootCount.count);
	})
}
}