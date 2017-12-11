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

        <h3>——合同管理系统</h3>

    </div>
</div>
<div class="container">
    <div class="main">
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/contract?method=getContractList&currentPage=1&currentCount=10" class="list-group-item text-center ">合同列表</a>
                    <a href="${pageContext.request.contextPath}/contract-add.jsp"
                       class="list-group-item text-center active">新增合同</a>
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
                    <div class="panel-heading">修改合同种类</div>
                    <div class="panel-body">
                        <form action="contract" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <input type="hidden" name="method" value="updateContract">
                                <label class="col-sm-2 control-label">编号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_id" value="${param.c_id}" readonly="readonly"  class="form-control" placeholder="合同系统编号" >
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同的系统编号不能修改</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_name" value="${param.c_name}" class="form-control" placeholder="合同名称">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同的名称</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">型号</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_dtype" value="${param.c_dtype}" class="form-control" placeholder="型号">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产品的型号</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">类型</label>
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input <c:if test="${param.c_type eq '未知'}">checked="checked"</c:if>  type="radio" name="c_type" value="未知">未知
                                    </label>
                                    <label class="radio-inline">
                                        <input <c:if test="${param.c_type eq '软件'}">checked="checked"</c:if> type="radio" name="c_type" value="软件">软件
                                    </label>
                                    <label class="radio-inline">
                                        <input <c:if test="${param.c_type eq '硬件'}">checked="checked"</c:if> type="radio" name="c_type" value="硬件">硬件
                                    </label>
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">请选择产品的分类</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">供应商</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_supplier" value="${param.c_supplier}" class="form-control" placeholder="供应商">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产品的供应商</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">单价</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_price" value="${param.c_price}"  class="form-control" placeholder="单价">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产品的单价</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">总价</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_tprice" value="${param.c_tprice}" class="form-control" placeholder="总价">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产品的总价</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">数量</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_count" value="${param.c_count}" class="form-control" placeholder="数量">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产品的数量</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">购买日期</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_date" value="${param.c_date}" class="form-control" placeholder="购买日期">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同的购买日期（签订日期）</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">校区</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_campus" value="${param.c_campus}" class="form-control" placeholder="中北/闵行">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">校区</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">购买人</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_person" value="${param.c_person}" class="form-control" placeholder="购买人">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同的签订人</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上传否</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_upload" value="${param.c_upload}" class="form-control" placeholder="上传否">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">合同是否已经上传</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_remark" value="${param.c_remark}" class="form-control" placeholder="备注">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary">修改</button>
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