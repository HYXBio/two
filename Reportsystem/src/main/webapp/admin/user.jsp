<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/25
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
    <script  src="../easyUI/jquery.min.js"></script>
    <script  src="../easyUI/jquery.easyui.min.js"></script>
    <script  src="../easyUI/locale/easyui-lang-zh_CN.js"></script>
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
<div data-options="region:'north',split:false,collapsible:false" style="height:100px;">
</div>
<div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
<div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
<div data-options="region:'west',title:'功能导航',split:true" style="width:150px;">
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="notice.jsp" >通知公告</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="report.jsp" >信息报送</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="statistics.jsp" >报送统计</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" href="user.jsp" >通信录&nbsp;&nbsp;&nbsp;</a>
    <a style="width:100%;" data-options="plain:true,iconCls:'icon-upload'" class="easyui-linkbutton" onclick="exit()">安全退出</a>
</div>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">

    <table id="userList" class="easyui-datagrid"
           style="width:100%"
           data-options="fitColumns:true,singleSelect:true,striped:true,pagination:true,toolbar:'#tb'">
        <thead>
        <tr>
            <th data-options="field:'id',width:100">用户编号</th>
            <th data-options="field:'user_name',width:100">用户名</th>
            <th data-options="field:'password',width:100,align:'right'">密码</th>
            <th data-options="field:'department',width:100,align:'right'">部门</th>
            <th data-options="field:'contact',width:100,align:'right'">联系人</th>
            <th data-options="field:'phone',width:100,align:'right'">联系电话</th>
            <th data-options="field:'type',width:100,align:'right'">用户类型</th>

        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-add" onClick="newUser()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" onClick="updateUser()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" onClick="deleteUser()">删除</a>
    </div>


    <div id="dlg_user" class="easyui-dialog" data-options="title:'用户信息',closed:true" style="padding:20px;" buttons="#dlg-buttons">
        <form id="form_user" method="post" >
            <div class="fitem"  style="display: none">
                <label>用户编号：</label>
                <input id="id" name="id" class="easyui-textbox" data-options="required:true,validType:['length[0,20]']"/>
            </div>
            <div class="fitem">
                <label>用户帐号：</label>
                <input id="user_name" name="user_name" class="easyui-textbox" data-options="required:true,validType:['length[0,20]']"/>
            </div>
            <div class="fitem">
                <label>&emsp;&emsp;密码：</label>
                <input id="password" name="password" class="easyui-textbox" data-options="required:true,validType:['length[0,8]']" >
            </div>
            <div class="fitem">
                <label>单位名称：</label>
                <input id="department" name="department" class="easyui-textbox" data-options="required:true,validType:['length[0,50]']" >
            </div>
            <div class="fitem">
                <label>&emsp;联系人：</label>
                <input id="contact" name="contact" class="easyui-textbox" data-options="required:true,validType:['length[0,8]']" >
            </div>
            <div class="fitem">
                <label>联系电话：</label>
                <input id="phone" name="phone" class="easyui-textbox" data-options="required:true,validType:['phone']" >
            </div>
            <div class="fitem" style="display: none">
                <label>用户类型：</label>
                <input id="type" name="type" class="easyui-textbox" data-options="required:true,validType:['type']" >
            </div>
        </form>
    </div>
</div>
<div id="dlg-buttons">
    <a class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_user').dialog('close')">取消</a>
</div>


</div>
</body>
<script>

    judgeLogin();
    //获取所有
    $('#userList').datagrid({
        url: '/user/getAllUser.action'
    });

    //新用户
    function newUser(){
        $("#dlg_user").dialog("open");
        $("#form_user").form("clear");
    }

    //增加
    function saveUser() {
        $.ajax({
            url:"/user/addOrUpdateUser.action",
            type:"POST",
            data:{
                id:$("#id").val(),
                user_name:$("#user_name").val(),
                password:$("#password").val(),
                department:$("#department").val(),
                contact:$("#contact").val(),
                phone:$("#phone").val(),
                type:$("#type").val(),
            },
            success:function (result) {
                if(result.code ==0){
                    $("#userList").datagrid("reload");
                    $("#dlg_user").dialog("close");
                    alert(result.msg);
                }
                else {
                    alert(result.msg);
                }
            }
        });
    }

    //修改
    function updateUser() {
        var row = $("#userList").datagrid("getSelected");
        if(row ==null){
            alert("选择修改！")
            return false;
        }
        $("#dlg_user").dialog("open");
        $("#form_user").form("load",row);

    }

    //删除
    function deleteUser() {
        var row = $("#userList").datagrid("getSelected");
        if(row ==null){
            alert("选择删除！")
            return false;
        }
        $.ajax({
            url:"/user/deleteUserById.action",
            method:"POST",
            dataType : 'json',
            data:{
                id:row.id
            },
            success:function (result) {
                if(result.code ==0){
                    $("#userList").datagrid("reload");
                    $("#dlg_user").dialog("close");
                    alert(result.msg);
                }
                else {
                    alert(result.msg);
                }
            }
        });
    }

</script>
</html>
