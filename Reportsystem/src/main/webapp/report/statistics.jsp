<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<html>
<head>
    <title>report</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <script  src="../easyUI/jquery.min.js"></script>
    <script  src="../easyUI/jquery.easyui.min.js"></script>
    <script  src="../easyUI/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../lib/ajaxfileupload.js"></script>
    <script src="../lib/exit.js"></script>
</head>
<body class="easyui-layout">
<%
    String user_name =  (String)request.getSession().getAttribute("user_name");
    Integer id = (Integer)request.getSession().getAttribute("id");
    if(user_name==null){
        response.sendRedirect("../index.jsp");
    }
%>

<div data-options="region:'north',split:false,collapsible:false" style="height:100px;" >
    <h1 style="float: right">欢迎：<%=user_name%></h1>
</div>

<div data-options="region:'east',title:'提示信息',split:true" style="width:150px;"></div>
<div data-options="region:'west',title:'功能导航',split:true" style="width:150px;">
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="notice.jsp" >通知公告</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="report.jsp" >信息报送</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="statistics.jsp" >报送统计</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" onclick="exit()">安全退出</a>
</div>
<div data-options="region:'center',title:'信息展示'" style="padding:5px;background:#eee;">
    <table id="reportList" class="easyui-datagrid"
           style="width:100%"
           data-options="fitColumns:true,singleSelect:true,fit:true,striped:true,pagination:true,toolbar:'#tb',rownumbers:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:100">id</th>
            <th data-options="field:'title',width:100">标题</th>
            <th data-options="field:'summary',width:100">内容</th>
            <th data-options="field:'type',width:100,align:'right'">类型</th>
            <th data-options="field:'report_date',width:100,align:'right'">上传日期</th>
            <th data-options="field:'status',width:100,align:'right'">审核状态</th>
            <th id="ttt" data-options="field:'url',width:100,align:'right',formatter:formatOper">附件</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <span>开始时间：</span>
        <input id="begin" name="reportDate" class="easyui-datebox" data-options="required:true,width:100"/>
        <span>结束时间：</span>
        <input id="end" name="reportDate" class="easyui-datebox" data-options="required:true,width:100"/>
        <a class="easyui-linkbutton" iconCls="icon-remove" onClick="searchReport()">查询</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" onClick="clearDate()">重置</a>

    </div>
</div>
<div data-options="region:'south',title:'关于我们',split:false" style="height:100px;text-align: center">
    @CopyRight 2019-4-27
</div>

</body>
<script>
    //查询信息
    function searchReport(){
        var beginDate = $("#begin").datebox("getValue");
        var endDate = $("#end").datebox("getValue");
        //获取该用户所有信息
        $('#reportList').datagrid({

            url: "/reportServlet",
            queryParams:{
                method:"getReportByDate",
                beginDate:beginDate,
                endDate:endDate
            }
        });
    }

    //初始化日期框
    function clearDate() {
        $("#begin").datebox("setValue","Today");
        $("#end").datebox("setValue","Today");
    }

    //为有附件的消息提供下载
    function formatOper(val,row,index){
        if(row['url']!=null&&row['url']!=""){
            return '<a download href="'+row["url"]+'">下载</a>';
        }
        else{
            return '<span  >未上传</span >';
        }
    }
</script>
</html>
