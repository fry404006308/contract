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
  		<h3>——设备界面</h3>
  		
	</div>
</div>
<div class="container">
	<div class="main">
		<!-- 左侧内容 -->
		<div class="col-md-3">
			<div class="list-group">
				<a href="${pageContext.request.contextPath}/device?method=queryDevice&refresh=true" class="list-group-item text-center active" >设备列表</a>
				<a href="${pageContext.request.contextPath}/contract?method=getContractList&currentPage=1&currentCount=10" class="list-group-item text-center " >返回合同列表</a>
				<a href="${pageContext.request.contextPath}/device-add.jsp"  class="list-group-item text-center " >新增设备</a>
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
					<div class="panel-heading">设备列表</div>
					<div class="panel-body">
						<table class="table table-striped table-responsive table-hover">
							<thead>
								<tr>
									<th>设备序号</th>
									<th>合同名称</th>
									<th>合同序号</th>
									<th>设备处设备唯一编号</th>
									<th>类型</th>
									<th>校区</th>
									<th>部门</th>
									<th>使用人</th>
									<th>使用日期</th>
									<th>报废否</th>
									<th>报废日期</th>
									<th>备注</th>
									<th width="100">操作</th>
								</tr>
							</thead>
							<tbody>
							<%
								int device_id= 0;             //声明在<% >内的变量
							%>
							<c:forEach items="${devices}" var="device">

								<tr>
									<th><%= ++device_id %></th>
									<th>${contract.c_name}</th>
									<th>${contract_id}</th>
									<th>${device.d_did}</th>
									<th>${device.d_type}</th>
									<th>${device.d_campus}</th>
									<th>${device.d_apartment}</th>
									<th>${device.d_people}</th>
									<th>${device.d_date}</th>
									<th>${device.d_used}</th>
									<th>${device.d_undate}</th>
									<th>${device.d_remark}</th>
									<td>

										<%--<a href="">详情</a>--%>

											<a href="${pageContext.request.contextPath}/device?method=deleteDevice&device_id=<%= device_id %>&d_id=${device.d_id}&d_cid=${device.d_cid}&d_did=${device.d_did}&d_type=${device.d_type}&d_campus=${device.d_campus}&d_apartment=${device.d_apartment}&d_people=${device.d_people}&d_date=${device.d_date}&d_used=${device.d_used}&d_undate=${device.d_undate}&d_remark=${device.d_remark}">删除</a>
											<a href="${pageContext.request.contextPath}/device-update.jsp?device_id=<%= device_id %>&d_id=${device.d_id}&d_cid=${device.d_cid}&d_did=${device.d_did}&d_type=${device.d_type}&d_campus=${device.d_campus}&d_apartment=${device.d_apartment}&d_people=${device.d_people}&d_date=${device.d_date}&d_used=${device.d_used}&d_undate=${device.d_undate}&d_remark=${device.d_remark}">修改</a>

										<%--<a href="${pageContext.request.contextPath}/device?method=deleteDevice&device_id=<%= device_id %>&contract_id=${param.contract_id}&d_id=${device.d_id}&d_cid=${device.d_cid}&d_did=${device.d_did}&d_type=${device.d_type}&d_campus=${device.d_campus}&d_apartment=${device.d_apartment}&d_people=${device.d_people}&d_date=${device.d_date}&d_used=${device.d_used}&d_undate=${device.d_undate}&d_remark=${device.d_remark}&c_id=${contract.c_id}&c_name=${contract.c_name}&c_dtype=${contract.c_dtype}&c_type=${contract.c_type}&c_supplier=${contract.c_supplier}&c_price=${contract.c_price}&c_tprice=${contract.c_tprice}&c_count=${contract.c_count}&c_date=${contract.c_date}&c_campus=${contract.c_campus}&c_person=${contract.c_person}&c_upload=${contract.c_upload}&c_remark=${contract.c_remark}">删除</a>--%>
										<%--<a href="${pageContext.request.contextPath}/device-update.jsp?&device_id=<%= device_id %>&contract_id=${param.contract_id}&d_id=${device.d_id}&d_cid=${device.d_cid}&d_did=${device.d_did}&d_type=${device.d_type}&d_campus=${device.d_campus}&d_apartment=${device.d_apartment}&d_people=${device.d_people}&d_date=${device.d_date}&d_used=${device.d_used}&d_undate=${device.d_undate}&d_remark=${device.d_remark}&c_id=${contract.c_id}&c_name=${contract.c_name}&c_dtype=${contract.c_dtype}&c_type=${contract.c_type}&c_supplier=${contract.c_supplier}&c_price=${contract.c_price}&c_tprice=${contract.c_tprice}&c_count=${contract.c_count}&c_date=${contract.c_date}&c_campus=${contract.c_campus}&c_person=${contract.c_person}&c_upload=${contract.c_upload}&c_remark=${contract.c_remark}">修改</a>--%>
									</td>

								</tr>
							</c:forEach>

							</tbody>
						</table>
					</div>
				</div>



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