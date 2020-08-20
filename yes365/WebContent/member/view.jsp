<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file="../include/header.jsp" %>
<style>
.bottom{
align:center;
width: 8=70%;
background-color: #f0f8ff;

}
#del{
display: inline-block;
align: left;
position: absolute;
left: 600px
}
.form-control{
size: 30px;
}
</style>
  
  <div class="container">
  	
<br/><br/><br/>
      <ol class="breadcrumb">
      <li class="breadcrumb-item">
     	   <h2>회원정보</h2> 
      </li>
      
    </ol>

  <div class="bottom">
  
  <form action="update.me" method="post" id="frm">
        <div class="row">
    <div class="col">
    	<label for="userid">아이디:</label>
      <input type="text" class="form-control" size="20" id="userid" placeholder="Enter id" name="userid" value="${member.userid}" readonly="readonly">
    </div>
      
    </div>
    <div class="form-group">
      <label for="name">이름:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${member.name}">
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd" value="${member.pwd}">
    </div>
    <div class="form-group">
      <label for="pwd_check">비밀번호 확인:</label>
      <input type="password" class="form-control" id="pwd_check" placeholder="Enter password Confirm" name="pwd_check">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email" value="${member.email}">
    </div>
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone" value="${member.phone}">
    </div>
       <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">우편번호</span>
    </div>
      <input type="text" id="sample6_postcode" name="sample6_postcode" readonly="readonly" class="form-control" value="${member.zipcode}">
    <div class="col align-self-end" >
      <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" class="btn btn-primary"><br>
   	</div>
  </div>
  <br/>  
	
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">주소</span>
    </div>
    <input type="text" id="addr" name="addr"  class="form-control"  value="${member.addr}"><br>
  </div>

  <div class="input-group mb-3">
	<input type="text" id="detailAddr" name="detailAddr" placeholder="상세주소" class="form-control" value="${member.detailAddr}">
	<input type="text" id="extraAddr" name="extraAddr" placeholder="참고항목" class="form-control" value="${member.extraAddr}">
  </div>
  
  <button id="submit" class="btn btn-outline-secondary" onclick="Update">회원정보수정</button>  
	<button id="reset" class="btn btn-outline-secondary">취소</button>
	<div id="del">
	<input type="button" class="btn btn-outline-danger" value="회원탈퇴" onclick="delCheck('${member.userid}')" id="del">
	</div>
	</br></br>
  </form>
  </div>
</div>

<br/><br/><br/><br/>
<%@include file="../include/footer.jsp" %>
<script>
function delCheck(userid){
    if(confirm("탈퇴하시겠습니까? 삭제된 회원정보는 되돌릴 수 없습니다.")){
    	location.href="delete.me?userid="+userid;
    }
}

$(document).ready(function(){
	$("#submit").click(function(){
		alert("회원정보가 수정되었습니다. 다시 로그인 해주세요.");
		//이름이 공백일때
		if($("#name").val()==""){
			alert("이름을 입력하세요.");
			return false;
		}
		//비밀번호가 공백일때
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		//비번확인이 공백일때
		if($("#pwd_check").val()==""){
			alert("비밀번호 확인 해주세요.");
			$("#pwd_check").focus();
			return false;
		}
		//암호 일치확인
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호 불일치! 다시 입력해주세요.");
			$("#pwd_check").focus();
			return false;
		}
		
		//이메일이 공백일때
		if($("#email").val()==""){
			alert("이메일을 입력하세요.");
			return false;
		}
		//이메일 양식
		if(!$("#email").val().match(reg_email)){
			alert("이메일 양식에 맞게 입력하세요.");
			$("#email").focus();
			return false;
			
		}if($("#submit").val()!=""){
			
			return false;
		}
		$("#frm").submit();	
		
		
	})
});//document

</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
					
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddr").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddr").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("addr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddr").focus();
            }
        }).open();
    }
</script>


