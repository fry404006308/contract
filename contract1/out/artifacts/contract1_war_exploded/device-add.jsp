<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>增加设备</title>
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
                    <a href="${pageContext.request.contextPath}/device?method=queryDevice&refresh=true" class="list-group-item text-center ">设备列表</a>
                    <a href="${pageContext.request.contextPath}/device-add.jsp" class="list-group-item text-center active">新增设备</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 错误提示 -->
                <div style="display: none" class="alert alert-danger" role="alert">
                    <ul>
                        <li>不能为空</li>
                        <li>只能为整数</li>
                        <li>请选择</li>
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
                    <div class="panel-heading">新增设备</div>
                    <div class="panel-body">
                        <form action="device" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <input type="hidden" name="method" value="addDevice">
                                <input type="hidden" name="d_cid" value="${contract.c_id}">
                                <label class="col-sm-2 control-label">合同序号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="contract_id" class="form-control" value="${contract_id}"  readonly="readonly" placeholder="合同编号">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同序号不能修改</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">合同名称</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_name" class="form-control" value="${contract.c_name}"  readonly="readonly" placeholder="合同名称">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同名称不用填写</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备处唯一编号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_did" class="form-control" placeholder="设备处唯一编号">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备处唯一编号</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_type" class="form-control" value="${contract.c_type}" placeholder="类型">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备的类型</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">校区</label>
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input type="radio" name="d_campus"  value="其它">其它
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="d_campus" value="中北">中北
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="d_campus" value="闵行">闵行
                                    </label>
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备所在的校区</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用者部门</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_apartment" class="form-control" placeholder="使用者部门">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">使用者所在的部门</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用者姓名</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_people" class="form-control" placeholder="使用者姓名">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">使用者的姓名</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">使用日期</label>
                                <div class="col-sm-5">
                                    <input type="date" name="d_date" class="form-control" placeholder="使用日期">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备使用日期</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备报废否</label>
                                <div class="col-sm-5">
                                    <input type="text" name="d_used" class="form-control" placeholder="设备报废否">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备报废否</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">设备报废日期</label>
                                <div class="col-sm-5">
                                    <input type="date" name="d_undate" class="form-control" placeholder="设备报废日期">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">设备报废的日期</p>
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
//    System.out.println(response.getStatus() + "--------------status");
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