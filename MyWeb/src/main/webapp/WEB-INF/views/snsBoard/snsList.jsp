<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<style type="text/css">
	section {
		margin-top: 70px;
	}
	
	/*부트스트랩 li의 drophover클래스 호버시에 드롭다운 적용코드*/
	ul.nav li.drophover:hover>ul.dropdown-menu {
		display: block;
		margin: 0;
	}
	
	.aside-inner {
		position: fixed;
		top: 70px;
		width: 180px;
	}
	
	.section-inner {
		border-right: 1px solid #ddd;
		border-left: 1px solid #ddd;
		background-color: white;
	}
	
	.section-inner p, .aside-inner p {
		margin: 0;
	}
	
	.title-inner {
		position: relative;
		padding: 15px 0;
	}
	
	.title-inner .profile {
		position: absolute; /*부모기준으로 위치지정 릴레이티브*/
		top: 15px;
		left: 0;
	}
	
	.title-inner .title {
		padding-left: 50px;
	}
	/*내용*/
	.content-inner {
		padding: 10px 0;
	}
	/* 이미지영역  */
	.image-inner img {
		width: 100%;
	}
	/*좋아요*/
	.like-inner {
		padding: 10px 0;
		border-bottom: 1px solid #ddd;
		border-top: 1px solid #ddd;
		margin-top: 10px;
	}
	
	.like-inner img {
		width: 20px;
		height: 20px;
	}
	
	.link-inner {
		overflow: hidden;
		padding: 10px 0;
	}
	
	.link-inner a {
		float: left;
		width: 33.3333%;
		text-align: center;
		text-decoration: none;
		color: #333333;
	}
	
	.link-inner i {
		margin: 0 5px;
	}
	
	/*767미만 사이즈에서 해당 css가 적용됨*/
	/*xs가 767사이즈   */
	@media ( max-width :767px) {
		aside {
			display: none;
		}
	}
	/* 파일업로드 버튼 바꾸기 */
	.filebox label {
		display: inline-block;
		padding: 6px 10px;
		color: #fff;
		font-size: inherit;
		line-height: normal;
		vertical-align: middle;
		background-color: #5cb85c;
		cursor: pointer;
		border: 1px solid #4cae4c;
		border-radius: none;
		-webkit-transition: background-color 0.2s;
		transition: background-color 0.2s;
	}
	
	.filebox label:hover {
		background-color: #6ed36e;
	}
	
	.filebox label:active {
		background-color: #367c36;
	}
	
	.filebox input[type="file"] {
		position: absolute;
		width: 1px;
		height: 1px;
		padding: 0;
		margin: -1px;
		overflow: hidden;
		clip: rect(0, 0, 0, 0);
		border: 0;
	}
	
	/* sns파일 업로드시 미리보기  */
	.fileDiv {
		height: 100px;
		width: 200px;
		display: none;
		margin-bottom: 10px;
	}
	
	.fileDiv img {
		width: 100%;
		height: 100%;
	}
	/* 모달창 조절 */
	.modal-body {
		padding: 0px;
	}
	
	.modal-content>.row {
		margin: 0px;
	}
	
	.modal-body>.modal-img {
		padding: 0px;
	}
	
	.modal-body>.modal-con {
		padding: 15px;
	}
	
	.modal-inner {
		position: relative;
	}
	
	.modal-inner .profile {
		position: absolute; /*부모기준으로 위치지정 릴레이티브*/
		top: 0px;
		left: 0px;
	}
	
	.modal-inner .title {
		padding-left: 50px;
	}
	
	.modal-inner p {
		margin: 0px;
	}
	</style>
	
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<section>
		<div class="container">
			<div class="row">
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
						<c:choose>
							<c:when test="${login != null}">
								<p>
									<img src="../resources/img/profile.png"> &nbsp; &nbsp;${login.userName}
								</p>
								<ul>
									<li>내정보</li>
									<li>내쿠폰</li>
									<li>내좋아요게시물</li>
								</ul>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/user/userLogin"/>'">로그인</button>
							</c:otherwise>
						</c:choose>
						</div>
						<div class="menu2">
							<p>둘러보기</p>
							<ul>
								<li>기부 캠페인</li>
								<li>페이지</li>
								<li>그룹</li>
								<li>이벤트</li>
								<li>친구리스트</li>
							</ul>
						</div>
					</div>
				</aside>
				<div class="col-xs-12 col-sm-8 section-inner">
					<h4>게시물 만들기</h4>
					<!-- 파일 업로드 폼입니다 -->
					<div class="fileDiv">
						<img id="fileImg" src="<c:url value='/img/img_ready.png'/>">
					</div>
					<div class="reply-content">
						<textarea class="form-control" rows="3" name="content"
							id="content" placeholder="무슨 생각을 하고 계신가요?"></textarea>
						<div class="reply-group">
							<div class="filebox pull-left">
								<label for="file">이미지업로드</label> 
								<input type="file" name="file" id="file">
							</div>
							<button type="button" class="right btn btn-info" id="uploadBtn">등록하기</button>
						</div>
					</div>


					<!-- 파일 업로드 폼 끝 -->
					<div id="contentDiv">
					
					<!-- 비동기 방식으로 서버와 통신을 진행한 후
						목록을 만들어서 붙일 영역. -->
						
				</div>
				<!--우측 어사이드-->
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>목록</p>
							<ul>
								<li>목록1</li>
								<li>목록2</li>
								<li>목록3</li>
								<li>목록4</li>
								<li>목록5</li>
							</ul>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</section>
	<%@ include file="../include/footer.jsp" %>
	<!-- 모달 -->
	<div class="modal fade" id="snsModal" role="dialog">
			<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body row">
					<div class="modal-img col-sm-8 col-xs-6" >
						<img src="../resources/img/img_ready.png" id="snsImg" width="100%">
					</div>
					<div class="modal-con col-sm-4 col-xs-6">
						<div class="modal-inner">
						<div class="profile">
							<img src="../resources/img/profile.png">
						</div>
						<div class="title">
							<p id="snsWriter">테스트</p>
							<small id="snsRegdate">21시간전</small>
						</div>
						<div class="content-inner">
							<p id="snsContent">삶이 우리를 끝없이 시험하기에 고어텍스는 한계를 테스트합니다</p>
						</div>
						<div class="link-inner">
							<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
							<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a> 
							<a href="##"><i class="glyphicon glyphicon-share-alt"></i>공유하기</a>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<script>
	
	$(function() {
		
		//등록하기 버튼 클릭 이벤트
		$('#uploadBtn').click(function() {
			regist();
		});
		
		//등록을 담당하는 함수
		function regist() {
			//세션에서 현재 로그인 중인 사용자 정보(아이디)를 얻어오자
			const user_id = '${sessionScope.login.userId}'; //sessionScope는 생략해도 무방
			//자바스크립트의 파일 확장자 체크 검색.
			let file = $('#file').val();
			
			console.log(user_id);
			console.log(file);
			//.을 제거한 확장자만 얻어낸 후 그것을 소문자로 일괄 변경
			file = file.slice(file.indexOf('.') +1).toLowerCase();
			console.log(file);
			if(file !=='jpg' && file !== 'png' && file !=='jpeg' && file != 'bmp') {
				alert('이미지 파일(jpg, png, jpeg, bmp)만 등록이 가능합니다.');
				$('#file').val('');
				return;
			} else if(user_id === '') { //세션 데이터가 없다 -> 로그인 x
				alert('로그인이 필요한 서비스 입니다.');	
				return;
			}
			
			//ajax 폼 전송의 핵심 FormData 객체.
			const formData = new FormData();
			const data = $('#file');
			
			console.log('폼 데이터: ' + formData);
			console.log('data: ' + data);
			console.log(data[0]); //input type="file" 요소를 지목할 때 사용.
			console.log(data[0].files); //파일 태그에 담긴 파일 정보를 확인하는 키값.
			console.log(data[0].files[0]); //사용자가 등록한 최종 파일 정보.
			
			/*
			data[index] -> 파일 업로드 버튼이 여러 개 존재할 경우 요소의 인덱스를 지목해서 가져오는 법.
			우리는 요소를 id로 취득했기 때문에 하나만 찍히지만,class이름 같은 걸로 지목하면 여러 개가 취득되겠죠?
			files[index] -> 파일이 여러 개 전송되는 경우, 몇 번째 파일인지를 지목.
			우리는 multiple 속성을 주지 않았기 때문에 0번 인덱스 밖에 없는 겁니다.
			*/
			
			//FormData 객체에 사용자가 업로드한 파일의 정보가 들어있는 객체를 전달한다.
			formData.append('file', data[0].files[0]);
			//만약 더 있다면 formData.append('file', data[0].files[1]);
			//formData.append('file', data[0].files[2]); 버튼이 여러개면 data[]숫자가증가, 한 버튼에서 파일 개수가 늘면 file[]숫자가 증가
			const content = $('#content').val();
			formData.append('content', content);
			
			//비동기 방식으로 파일 업로드 및 게시글 등록을 진행.
			$.ajax({
				url: '<c:url value="/snsBoard/upload"/>',
				type: 'post',
				data: formData, //폼 데이터 객체를 넘깁니다.
				contentType: false, //ajax 방식에서 파일을 넘길 때는 반드시 false로 처리 -> "multipart/form-data"로 선언됨.
				processData: false, //폼 데이터를 &변수=값&변수=값...형식으로 변경되는 것을 막는 요소.
				success: function(result) {
					if(result === 'success') {
						$('#file').val(''); //파일 선택지 비우기
						$('#content').val(''); //글 영역 비우기
						$('.fileDiv').css('display', 'none'); //미리보기 감추기
						getList(1, true); //글 목록 함수 호출.
					} else {
						alert('업로드에 실패했습니다. 관리자에게 문의 해 주세요.');
					}
				},
				error: function(request, status, error) {
					console.log('code:' + request + '\n message: ' + request.responseText + "\n" + 'error: ' + error);
					alert('업로드에 실패했습니다. 관리자에게 문의 해 주세요.');
				}
			}); //end ajax
			
		}//end regist()
		
		//리스트 작업
		let str = '';
		let page = 1;
		getList(1, true);
		
		function getList(page, reset) {
			if(reset === true) {
				str = ''; //화면 리셋 여부가 true라면 str변수를 초기화.
			}
			
			$.getJSON(
				'<c:url value="/snsBoard/getList?pageNum='+page'"/>',
				function(list) {
					console.log(list);
					
					for(let i=0; i<list.length; i++) {
						
						str +=
							`<div class="title-inner">
							<!--제목영역-->
							<div class="profile">
								<img src="<c:url value='/img/profile.png' />">
							</div>
							<div class="title">
								<p>`+ list[i].writer + `</p>
								<small>21시간</small>
							</div>
						</div>
						<div class="content-inner">
							<!--내용영역-->
							<p>삶이 우리를 끝없이 시험하기에 고어텍스는 한계를 테스트합니다</p>
						</div>
						<div class="image-inner">
							<!-- 이미지영역 -->
							<img src="../resources/img/facebook.jpg">
							
						</div>
						<div class="like-inner">
							<!--좋아요-->
							<img src="../resources/img/icon.jpg"> <span>522</span>
						</div>
						<div class="link-inner">
							<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
							<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a> 
							<a href="##"><i class="glyphicon glyphicon-remove"></i>삭제하기</a>
						</div>
						</div>`
					
					}
				}
			);
			
		} //end getList
		
		
		
		
	}); //end jQuery
	
		//자바 스크립트 파일 미리보기 기능
		function readURL(input) {
        	if (input.files && input.files[0]) {
        		
            	var reader = new FileReader(); //비동기처리를 위한 파읽을 읽는 자바스크립트 객체
            	//readAsDataURL 메서드는 컨텐츠를 특정 Blob 이나 File에서 읽어 오는 역할 (MDN참조)
	        	reader.readAsDataURL(input.files[0]); 
            	//파일업로드시 화면에 숨겨져있는 클래스fileDiv를 보이게한다
	            $(".fileDiv").css("display", "block");
            	
            	reader.onload = function(event) { //읽기 동작이 성공적으로 완료 되었을 때 실행되는 익명함수
                	$('#fileImg').attr("src", event.target.result); 
                	console.log(event.target);//event.target은 이벤트로 선택된 요소를 의미
	        	}
        	}
	    }
		$("#file").change(function() {
	        readURL(this); //this는 #file자신 태그를 의미
	        
	    });
	</script>
	
	
	
</body>
</html>