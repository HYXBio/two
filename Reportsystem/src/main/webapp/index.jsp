<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/22
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script  src="easyUI/jquery.min.js"></script>

    <script src="easyUI/jquery.easyui.min.js"></script>
    <style type="text/css">
        body {
            MARGIN: 0px;
            font-family: "Microsoft YaHei","微软雅黑";
        }
        .formsubmit{ background:url(pic/login.jpg) no-repeat bottom left; width:81px; height:26px;cursor:pointer; border:0px;}
        .STYLE1 {font-size: 14px;color:#666666;}
        .STYLE2 {font-size: 15px;}
    </style>
    <style type="text/css">
        .div1{
            padding-left:30px;
            background:url(pic/1.png) 4px  no-repeat;
            background-color:#FFFFFF;
            width:140px;
            height:25px;
            line-height:25px;
            border:1px solid #ccc;
        }
        .div2{
            padding-left:30px;
            background:url(pic/2.png) 4px  no-repeat;
            background-color:#FFFFFF;
            width:140px;
            height:25px;
            line-height:25px;
            border:1px solid #ccc;
        }
        input{
            border:0;
            font-size:18px;
        }

    </style>
</head>
<body>
<img src="pic/bg.jpg" width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="200">&nbsp;</td>
    </tr>
</table>
<table width="543" height="255" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <form id="formid" action="" method="post">
                <table width="320" border="0" align="center" cellpadding="0" cellspacing="15">
                    <tr>
                        <td width="25%"><span class="STYLE2">用户名   :</span></td>
                        <td width="75%">
                            <div class="div1">
                                <input name="user_name" type="text" id="user_name"  style="height:22px;width:137px;outline:none; vertical-align:middle;" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%"><span class="STYLE2">密码 :</span></td>
                        <td width="75%"><div class="div2">
                            <input name="password" type="password" id="password" style="height:22px;width:137px;outline:none;vertical-align:middle;"/>
                        </div></td>
                    </tr>
                    <tr>
                        <td  colspan="2">
                            <div align="center">
                                <span id="wrongtip" style="font-size:12px;color:red"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div align="center">
                                <input type="button" id="submit" class="formsubmit" onclick="Login()"></input>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
<table width="100%" height="140" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="26"><div align="center" class="STYLE1"></div></td>
    </tr>
</table>
</body>

<script>
    function Login() {
        var user_name = $("#user_name").val();
        var password = $("#password").val();
        if(user_name==""||password==""){
            alert("用户名密码不能为空！");
        }else {
            $.ajax({
                url:"/user/userLogin.action",
                method:"POST",
                data:{
                    user_name:user_name,
                    password:password
                },
                success:function (result) {
                    if(result.code ==0){
                        if(result.data.type==0){
                            alert(result.data.user_name);
                            sessionStorage.setItem("id",result.data.id);
                            sessionStorage.setItem("user_name",result.data.user_name);
                            sessionStorage.setItem("type",result.data.type);
                            window.location.href="admin/report.jsp";
                        }
                        else {
                            alert(result.data.user_name);
                            sessionStorage.setItem("id",result.data.id);
                            sessionStorage.setItem("user_name",result.data.user_name);
                            sessionStorage.setItem("type",result.data.type);
                            window.location.href="report/report.jsp";
                        }
                    }
                    else if (result.code==2){
                        alert(result.msg);
                    }
                    else if (result.code==1) {
                        alert(result.msg);
                    }
                }
            });
        }
    }
</script>

</html>
