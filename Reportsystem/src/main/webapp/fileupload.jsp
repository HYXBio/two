<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/26
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ajax File Uploader Plugin For Jquery</title>
    <script type="text/javascript" src="lib/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="lib/ajaxfileupload.js"></script>

</head>
<body>
<h1>选择图片后,点击按钮上传</h1>
<input id="fileToUpload" type="file" size="45" name="fileToUpload"
       class="input">
<button class="button" onclick="ajaxFileUpload()">上传</button>
<br>
<p id = "link">456465</p>
<input type="text" style="width:20px;" id = "url1">
<br>
<img id="viewImg">
</body>
<script type="text/javascript">
    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url : '/upload',
            secureuri : false,
            fileElementId : 'fileToUpload',
            dataType : 'json',
            success : function(data, status) {

                alert(data.data.net_url);
            },
            error : function(date, status, e) {
                alert('上传出错');
            }
        });
    }
</script>
</html>
