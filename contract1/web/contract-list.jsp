<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>index</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">   
	<style type="text/css">
		body{ font-family: 'Microsoft YaHei';}
		/*.panel-body{ padding: 0; }*/
	</style>
</head>
<body>
<div class="jumbotron">
	<div class="container">
  		<h3>——合同管理系统</h3>
  		
	</div>
</div>
<div class="container">
	<div class="main">
		<!-- 左侧内容 -->
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath}/contract?method=getContractList&currentPage=1&currentCount=10" class="list-group-item text-center active">合同列表</a>
				<a href="${pageContext.request.contextPath}/contract-add.jsp" class="list-group-item text-center ">新增合同</a>
			</div>

		</div>
		<div class="row">

			<!-- 右侧内容 -->
			<div class="col-md-12">
				<!-- 成功提示框 -->
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">合同列表</div>
					<div class="panel-body">
						<table class="table table-striped table-responsive table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>型号</th>
									<th>类型</th>
									<th>供应商</th>
									<th>单价</th>
									<th>总价</th>
									<th>数量</th>
									<th>购买日期</th>
									<th>校区</th>
									<th>合同签订者</th>
									<th>上传否</th>
									<th>备注</th>
									<th width="120">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${page.list}" var="contract">

								<tr>
									<th>${contract.c_id}</th>
									<th>${contract.c_name}</th>
									<th>${contract.c_dtype}</th>
									<th>${contract.c_type}</th>
									<th>${contract.c_supplier}</th>
									<th>${contract.c_price}</th>
									<th>${contract.c_tprice}</th>
									<th>${contract.c_count}</th>
									<th>${contract.c_date}</th>
									<th>${contract.c_campus}</th>
									<th>${contract.c_person}</th>
									<th>${contract.c_upload}</th>
									<th>${contract.c_remark}</th>
									<td>
										<%--<a href="">详情</a>--%>
										<a href="${pageContext.request.contextPath}/contract-upload.jsp?c_id=${contract.c_id}&c_name=${contract.c_name}&c_dtype=${contract.c_dtype}&c_type=${contract.c_type}&c_supplier=${contract.c_supplier}&c_price=${contract.c_price}&c_tprice=${contract.c_tprice}&c_count=${contract.c_count}&c_date=${contract.c_date}&c_campus=${contract.c_campus}&c_person=${contract.c_person}&c_upload=${contract.c_upload}&c_remark=${contract.c_remark}">上传</a>
										<a href="${pageContext.request.contextPath}/category?method=deleteCategory&c_id=${category.c_id}">下载</a>
										<a href="${pageContext.request.contextPath}/category?method=deleteCategory&c_id=${category.c_id}">明细</a>
										<a href="${pageContext.request.contextPath}/contract?method=deleteContract&c_id=${contract.c_id}">删除</a>
										<a href="${pageContext.request.contextPath}/contract-update.jsp?c_id=${contract.c_id}&c_name=${contract.c_name}&c_dtype=${contract.c_dtype}&c_type=${contract.c_type}&c_supplier=${contract.c_supplier}&c_price=${contract.c_price}&c_tprice=${contract.c_tprice}&c_count=${contract.c_count}&c_date=${contract.c_date}&c_campus=${contract.c_campus}&c_person=${contract.c_person}&c_upload=${contract.c_upload}&c_remark=${contract.c_remark}">修改</a>
									</td>

								</tr>
							</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

				<%--<nav>--%>
					<%--<ul class="pagination pull-right">--%>
					<%--<li  class="previous"><a href="#">&laquo;</a></li>--%>
						<%--<c:forEach begin="1" end="${pageBean.totalPage}" var="page">--%>
							<%--<li><a href="#">${page}</a></li>--%>
							<%--<!-- 判断是否是当前页 -->--%>
							<%--&lt;%&ndash;<c:if test="${page==pageBean.currentPage }">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<li class="active"><a href="javascript:void(0);">${page}</a></li>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<c:if test="${page!=pageBean.currentPage }">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<li><a href="${pageContext.request.contextPath}/productListByCid?cid=${cid}&currentPage=${page }">${page }</a></li>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
						<%--</c:forEach>--%>

						<%--<li><a href="#">&raquo;</a></li>--%>
					<%--</ul>--%>

				<%--</nav>--%>

				<!--分页 -->
				<nav>
					<ul class="pagination pull-right">
						<li  class="previous"><a href="#">&laquo;</a></li>
						<c:forEach begin="1" end="${page.totalPage}" var="Page">
							<li><a href="${pageContext.request.contextPath}/contract?method=getContractList&currentPage=${Page}&currentCount=10">${Page}</a></li>
						</c:forEach>
						<li><a href="#">&raquo;</a></li>
					</ul>

				</nav>







				<!-- 分页结束 -->
				<%--<ul class="pagination pull-right">--%>
					<%--<li  class="previous"><a href="#">&laquo;</a></li>--%>
					<%--<c:forEach begin="1" end="${pageBean.totalPage+1}" var="page">--%>
						<%--<li><a href="${pageContext.request.contextPath}/category?method=getCategoryList&currentPage=${page}&currentCount=10">${page}</a></li>--%>
					<%--</c:forEach>--%>
					<%--<li><a href="#">&raquo;</a></li>--%>
				<%--</ul>--%>
			</div>
		</div>
  	</div>
</div>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
	<div class="container">
	<span>&copy; 2017 Fry</span>
	</div>
</div>


	<script src="static/js/jquery-3.1.0.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>