<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>信息管理系统界面</title>

</head>
<div style="z-index: -1">
	<frameset rows="88,*,31" id="ffff" cols="*" frameborder="no" border="0"
		framespacing="0">
		<frame src='<st:url value="/Manager/showTop.do"></st:url>'
			name="topFrame" scrolling="no" noresize="noresize" id="topFrame"
			title="topFrame" />
		<frameset cols="187,*" id="left" frameborder="no" border="0" framespacing="0">
			<frame src='<st:url value="/Manager/showLeft.do"></st:url>'
				scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frameset rows="*,0" id="index" frameborder="no" border="0"
				framespacing="0">
				<frame src='<st:url value="/Manager/showIndex.do"></st:url>'
					name="rightFrame" id="rightFrame" title="rightFrame" />
				<frame src='<st:url value="/Manager/showIndex.do"></st:url>'
					name="rightbottomFrame" id="rightbottomFrame"
					title="rightbottomFrame" />
			</frameset>
		</frameset>
		<frame src='<st:url value="/Manager/showFooter.do"></st:url>'
			name="bottomFrame" scrolling="no" noresize="noresize"
			id="bottomFrame" title="bottomFrame" />
	</frameset>
</div>
<noframes>
	<body>
	</body>
</noframes>

</html>

