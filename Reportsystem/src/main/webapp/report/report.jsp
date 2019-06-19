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
    <a class="easyui-linkbutton" iconCls="icon-add" onClick="newReport()">添加</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" onClick="updateReport()">修改</a>
    <a class="easyui-linkbutton" iconCls="icon-remove" onClick="deleteReport()">删除</a>
</div>

</div>
<div data-options="region:'south',title:'关于我们',split:false" style="height:100px;text-align: center">
    @CopyRight 2019-4-27
</div>


<div id="dlg_report" class="easyui-dialog" data-options="title:'信息报送',closed:true"  style="padding:20px;width:400px;" buttons="#dlg-buttons">
    <div id="form_report" method="post">
        <input id="report_id" style="display: none">
        <div class="fitem">
            <label>标题：</label>
            <input id="title" name="title" class="easyui-textbox" data-options="required:true,validType:['length[0,50]']" style="width:300px;">
        </div>
        <div class="fitem">
            <label>日期：</label>
            <input id="reportDate" name="reportDate" class="easyui-datebox" data-options="required:true,width:100"/>
            &emsp;&emsp;
            <label>类型：</label>
            <input id="type" name="type" class="easyui-combobox" data-options="required:true,editable:false,textField:'text',valueField:'text',url:'../json/reportType.json',width:100" >
        </div>
        <div class="fitem">
            <label>摘要：</label>
            <input id="summary" name="summary" class="easyui-textbox" data-options="required:true,multiline:true,validType:['length[0,200]']" style="width:300px;height:100px">
        </div>
        <input id="url" type="text" style="display: none">
    </div>



    <form method="post" action="#" id="uploadForm" enctype="multipart/form-data">
        <input type="file" name="multipartFile" >
        <input type="button" id="uploadBtn" value="提交">
    </form>


</div>
<div id="dlg-buttons">
    <a class="easyui-linkbutton" iconCls="icon-ok" onclick="saveReport()">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_report').dialog('close')">取消</a>
</div>


</body>
<script>

    //获取所有
    function getAll(){
        //获取该用户所有信息
        $('#reportList').datagrid({
            url: "/report/getReportById.action"
        });
    }
    getAll();

    //获取行的信息
    function getRow() {
        var row = $("#reportList").datagrid("getSelected");
        console.log(row.id);
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

    //删除
    function deleteReport() {
        var row = $("#reportList").datagrid("getSelected");
        if(row == null){
            alert("请选择删除信息！")
        }
        if(row.status==="审核通过"){
            alert("审核通过无法删除！！")
        } else {
            $.ajax({
                url: "/report/deleteReportById.action",
                method:"POST",
                data:{
                    id:row.id
                },
                success: function(result){
                    var code = result.code;
                    console.log(result.msg);
                    if (code==0){
                        $("#reportList").datagrid("reload");
                        alert(result.msg);
                    }
                }
            });
        }
    }

    //新的信息
    function newReport() {
            $("#dlg_report").dialog("open");
            $("#form_report").form("clear");
            $("#reportDate").datebox("setValue","Today");
            $("#type").combobox("setValue","舆情");
            $("#url").val(" ");
    }

    //保存
    function saveReport() {
        $.ajax({
            url: "/report/updateOrAddReport.action",
            method:"POST",
            data:{
                id:$("#report_id").val(),
                title:$("#title").val(),
                report_date:$("#reportDate").datebox("getValue"),
                type:$("#type").combobox("getValue"),
                summary:$("#summary").val(),
                url: $("#url").val()
            },
            success: function(result){
                var success = result.code;
                if (success==0){
                    $("#reportList").datagrid("reload");
                    $("#url").val(" ");
                    $("#dlg_report").dialog("close");
                    alert(result.msg);
                }
            }
        });
    }

    //更新信息
    function updateReport() {
        var row = $("#reportList").datagrid("getSelected");
        if(row == null){
            alert("请选择修改信息！")
        }
        if(row.status==="审核通过"){
            alert("审核通过无法修改！！")
        }else {
            $("#dlg_report").dialog("open");
            $("#form_report").form("load",row);
            $("#reportDate").datebox("setValue",row.report_date);
            $("#report_id").val(row.id);
            $("#user_id").val(row.user_id);
        }
    }

    $("#uploadBtn").click(function () {
        var formData = new FormData($("#uploadForm")[0])
        $.ajax({
            url:"/file/fileUpload.action",
            type:"POST",
            data:formData,
            async:false,
            cache:false,
            contentType:false,
            processData:false,
            success:function (result) {
                console.log(result.msg);
                $("#url").val(result.data);
            },
            error:function (error) {
                console.log("error:"+error);
            }
        })
    })
</script>
</html>
