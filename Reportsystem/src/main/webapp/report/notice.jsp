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


    <table id="noticeList" class="easyui-datagrid"
           style="width:100%"
           data-options="fitColumns:true,singleSelect:true,fit:true,striped:true,pagination:true,toolbar:'#tb',rownumbers:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:50">公告号</th>
            <th data-options="field:'title',width:50">标题</th>
            <th data-options="field:'publishDate',width:50">上传日期</th>
            <th data-options="field:'user_id',width:50,align:'right'">发布者</th>

            <th data-options="field:'content',width:50,align:'right'">内容</th>
            <th data-options="field:'url',width:50,align:'right',formatter:formatOper ">附件</th>

        </tr>
        </thead>
    </table>


</div>
<div data-options="region:'south',title:'关于我们',split:false" style="height:100px;text-align: center">
    @CopyRight 2019-4-27
</div>


<div id="dlg_report" class="easyui-dialog" data-options="title:'信息报送',closed:true"  style="padding:20px;width:400px;" buttons="#dlg-buttons">
    <form id="form_report" method="post">
        <input id="report_id" style="display: none">
        <div class="fitem">
            <label>标题：</label>
            <input id="title" name="title" class="easyui-textbox" data-options="required:true,validType:['length[0,50]']" style="width:300px;">
        </div>
        <div class="fitem">
            <label>日期：</label>
            <input id="publishDate" name="publishDate" class="easyui-datebox" data-options="required:true,width:100"/>
        </div>
        <div class="fitem">
            <label>摘要：</label>
            <input id="content" name="content" class="easyui-textbox" data-options="required:true,multiline:true,validType:['length[0,200]']" style="width:300px;height:100px">
        </div>
    </form>

</div>


</body>
<script>
    //获取所有公告
    function getAll(){
        $('#noticeList').datagrid({
            url: "/notice/getAllNotice.action",
            onDblClickRow: function (index, row) {
                $("#dlg_report").dialog("open");
                $("#form_report").form("load",row);
                $("#publishDate").datebox("setValue",row.publishDate);
            }
        });
    }
    getAll();

    //为有附件的公告提供下载
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
