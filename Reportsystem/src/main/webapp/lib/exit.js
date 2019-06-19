

//退出
function exit() {
    $.ajax({
        url: "/user/userExit.action",
        success: function (result) {
            alert(result.msg);
            window.location.href = "../index.jsp"
        }
    });
}

function judgeLogin() {
    if(sessionStorage.getItem("type")==1){
        window.location.href = "../index.jsp";
    }
}