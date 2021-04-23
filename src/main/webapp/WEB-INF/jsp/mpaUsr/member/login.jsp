<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<script>
	let MemberLogin__submitFormDone = false;
	function MemberLogin__submitForm(form) {

		if (MemberLogin__submitFormDone) {
			return;
		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();

			return;
		}

		form.loginPw.value = form.loginPw.value.trim();

		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();

			return;
		}
	

		form.submit();
		MemberLogin__submitFormDone = true;
	}
</script>
<div class="section section-article-list">
	<div class="container mx-auto">
		<div class="card bordered shadow-lg item-bt-1-not-last-child">
			<div class="card-title">
				<a href="javascript:history.back();" class="cursor-pointer"> <i
					class="fas fa-chevron-left"></i>
				</a> <span>회원가입</span>

			</div>
			<div class="px-4 py-8">
				<form action="doLogin" class="grid form-type-1"
					onsubmit="MemberLogin__submitForm(this); return false;"
					method="POST">
					<input type="hidden" name="redirectUrl" value="/" />
					<div class="form-control">
						<label class="label cursor-pointer"> 아이디 </label>
						<input type="text" name="loginId" placeholder="아이디" class="input input-bordered w-full" maxlength="20"/>
						
						<label class="cursor-pointer label"> 비밀번호 </label> 
						<input type="password" class="input input-bordered w-full" name="loginPw"
							placeholder="비밀번호" maxlength="20" />
							
                 

						<div class="mt-4 btn-wrap gap-1">
							<button type="submit" class="btn btn-sm btn-primary mb-1">
								<span><i class="fas fa-sign-in-alt"></i></span> &nbsp; <span>로그인</span>
							</button>

							<a href="join" class="btn btn-sm mb-1"
								title="자세히 보기"> <span><i class="fas fa-user-plus"></i></span>
								&nbsp; <span>회원가입</span>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>

<%@ include file="../common/foot.jspf"%>