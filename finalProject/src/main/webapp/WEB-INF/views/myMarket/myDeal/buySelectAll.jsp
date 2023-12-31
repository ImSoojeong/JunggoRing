<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/buySelectAll.css">
<jsp:include page="../../css.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="../../top_menu.jsp"></jsp:include>
	<header>
		<div class="headerTitle">
			<h4>
				<b>내 거래 목록</b>
			</h4>
		</div>
	</header>

	<main>

		<!--사이드메뉴 -->
		<div class="sideMarket">
			<p><a href="buySelectAll.do " style="font-weight: bold;">내 거래 목록</a></p>
			<p><a href="myWriteBuySelectAll.do">내가 쓴 글</a></p>
			<p><a href="myLikeSelectAll.do">내 찜 목록</a></p>
		</div>

		<div class="reportContainer">
			<div class="listBtns">
				<button onclick="openTab('buy')" class="listBtn selected"
					id="buyBtn">구해요</button>
				<button onclick="openTab('sell')" class="listBtn" id="sellBtn">팔아요</button>
			</div>
			<hr />
			<div class="contenList">
				<div id="buy" class="tabContent active">

					<c:choose>
						<c:when test="${empty buyvos}">
							<p>작성된 구매게시글이 없습니다.</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="buyvo" items="${buyvos}" varStatus="buyStatus">
								<div class="contents">
									<div class="contentImg">
										<a href="boardSelectOne.do?board_num=${buyvo.board_num}">
											<img src="resources/img/${buyvo.board_savename1}" alt="" />
										</a>
									</div>
									<div class="contentTitle">
										<p>

											<span>${buyvo.board_title}</span>
										</p>
										<p>가격: ${buyvo.price}원</p>
										<p>지역 : ${buyvo.deal_region}</p>
										<p>
											♡4 채팅2 <span>조회 ${buyvo.view_count}</span>
										</p>
										<c:if test="${empty buyvos}">
											<p>작성된 구매게시글이 없습니다.</p>
										</c:if>
									</div>
									<div class="dropdown">
										<button class="dropBtn"
											onclick="dropdownBuy(${buyStatus.index})"
											style="font-weight: bold;">...</button>
										<div id="dropdownBuy_${buyStatus.index}" class="dropMenuBuy">

											<a href="buyDeleteOK.do?board_num=${buyvo.board_num}">삭제</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="1" end="${buyPageCount }" step="1">
						<a href="buySelectAll.do?cpage=${i }">${i }</a>&nbsp;
					</c:forEach>
				</div>

				
				
			</div>
		</div>
		
	</main>

	<script type="text/javascript">
//탭이동에 따라 block & 버튼색깔변경
function openTab(tabName) {
  var tabContent = document.getElementsByClassName("tabContent");

 /*  for (var i = 0; i < tabContent.length; i++) {
    tabContent[i].style.display = tabContent[i].id === tabName ? "block" : "none";
  } */

  var tabButtons = document.getElementsByClassName("listBtn");
  for (var i = 0; i < tabButtons.length; i++) {
    tabButtons[i].classList.toggle("selected", tabButtons[i].id === tabName + "Btn");
  }
  if(tabName==='sell'){
	  location.href='sellSelectAll.do';
  }
}

// 구매 및 판매 드롭다운 메뉴
function dropdownMenu(index, menuClass) {
  var dropdownMenus = document.getElementsByClassName(menuClass);

  for (var i = 0; i < dropdownMenus.length; i++) {
    if (i === index) {
      var display = dropdownMenus[i].style.display;
      dropdownMenus[i].style.display = display === "block" ? "none" : "block";
    } else {
      dropdownMenus[i].style.display = "none";
    }
  }
}

function dropdownBuy(buyIndex) {
  dropdownMenu(buyIndex, "dropMenuBuy");
}

function dropdownSell(sellIndex) {
  dropdownMenu(sellIndex, "dropMenuSell");
}


</script>


</body>
</html>