<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>form</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        body {
            font-family: 'Microsoft YaHei';
        }

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
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/device?method=queryDevice&c_id=${param.c_id}&c_name=${param.c_name}&c_dtype=${param.c_dtype}&c_type=${param.c_type}&c_supplier=${param.c_supplier}&c_price=${param.c_price}&c_tprice=${param.c_tprice}&c_count=${param.c_count}&c_date=${param.c_date}&c_campus=${param.c_campus}&c_person=${param.c_person}&c_upload=${param.c_upload}&c_remark=${param.c_remark}" class="list-group-item text-center ">合同列表</a>
                    <a href="${pageContext.request.contextPath}/device-add.jsp?d_cid=${param.c_id}" class="list-group-item text-center active">新增合同</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 错误提示 -->
                <div style="display: none" class="alert alert-danger" role="alert">
                    <ul>
                        <li>合同名不能为空</li>
                        <li>年龄只能为整数</li>
                        <li>请选择类别</li>
                    </ul>
                </div>
                <div id="sucess-info" style="display: none" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span
                            aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
                    <strong>成功！</strong> 操作成功提示
                </div>
                <!-- 失败提示框 -->
                <div id="fail-info" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <strong>失败！</strong> 操作失败提示
                </div>
                <!-- 自定义内容 -->
                <div class="panel panel-default">
                    <div class="panel-heading">新增合同种类</div>
                    <div class="panel-body">
                        <form action="device" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <input type="hidden" name="method" value="addDevice">
                                <input type="hidden" name="c_id" value="${param.c_id}">
                                <input type="hidden" name="c_name" value="${param.c_name}">
                                <input type="hidden" name="c_dtype" value="${param.c_dtype}">
                                <input type="hidden" name="c_type" value="${param.c_type}">
                                <input type="hidden" name="c_supplier" value="${param.c_supplier}">
                                <input type="hidden" name="c_price" value="${param.c_price}">
                                <input type="hidden" name="c_tprice" value="${param.c_tprice}">
                                <input type="hidden" name="c_count" value="${param.c_count}">
                                <input type="hidden" name="c_date" value="${param.c_date}">
                                <input type="hidden" name="c_campus" value="${param.c_campus}">
                                <input type="hidden" name="c_person" value="${param.c_person}">
                                <input type="hidden" name="c_upload" value="${param.c_upload}">
                                <input type="hidden" name="c_remark" value="${param.c_remark}">
                                <label class="col-sm-2 control-label">合同编号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_cid" class="form-control" value="${param.c_id}"  readonly="readonly" placeholder="合同编号">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备处唯一编号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_did" class="form-control" placeholder="设备处唯一编号">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备处唯一编号不能为空</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_type" class="form-control" value="${param.c_type}" placeholder="类型">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">类型不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">校区</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_campus" class="form-control" placeholder="校区">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">校区不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用者部门</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_apartment" class="form-control" placeholder="使用者部门">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">使用者部门不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用者姓名</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_people" class="form-control" placeholder="使用者姓名">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">使用者姓名不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用日期</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_date" class="form-control" placeholder="使用日期">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">使用日期不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备报废否</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_used" class="form-control" placeholder="设备报废否">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备报废否不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备报废日期</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_undate" class="form-control" placeholder="设备报废日期">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备报废日期不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_remark" class="form-control" placeholder="备注">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>

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
<%
    System.out.println(response.getStatus() + "--------------status");
    if (response.getStatus() == 201) {
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        showdiv();\n" +
                "       }</script>");
    } else if (response.getStatus() == 200) {

    } else {
        out.write("<script type=\"text/javascript\">\n" +
                "    window.onload=function(){\n" +
                "        hidediv();\n" +
                "       }</script>");
    }

%>


<script>


    function showdiv() {

        document.getElementById('sucess-info').style.display = 'block';//show的display属性设置为block（显示）
        document.getElementById('fail-info').style.display = 'none';//show的display属性设置为block（显示）


    }
    function hidediv() {


        document.getElementById('fail-info').style.display = 'block';
        document.getElementById('sucess-info').style.display = 'none';//show的display属性设置为none（隐藏）


    }
</script>

</body>
</html>