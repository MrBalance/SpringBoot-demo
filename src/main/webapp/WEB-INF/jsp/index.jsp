<%--
  Created by IntelliJ IDEA.
  User: kenfor
  Date: 2019/6/19
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎登录</title>
    <link rel="stylesheet" type="text/css" href="static/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="static/themes/gray/easyui.css"/>
</head>
<script type="text/javascript" src="static/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jQuery/jquery.easyui.min.js"></script>
<script type="text/javascript">
    /*键盘敲击事件监听*/
    document.onkeydown = function(e){
        var event = e || window.event;
        var code = event.keyCode || event.which || event.charCode;
        if (code === 13) {
            login();
        }
    };
    $(function(){
        $("input[id='login']").focus();
    });
    function cleardata(){
        $('#loginForm').form('clear');
    }
    function login(){
        var account = $("#login").val();
        var  password= $("#password").val();
        if(account==="" || password===""){
            $("#showMsg").html("用户名或密码为空，请输入");
            $("input[name='login']").focus();
        }else{
            //ajax异步提交
            $.ajax({
                type:"POST",   //post提交方式默认是get
                url:"service/usrTable/login",
                data:{
                    account:account,
                    password:password
                },
                error:function(request) {      // 设置表单提交出错
                    $("#showMsg").html(request);  //登录错误提示信息
                },
                success:function(data) {
                    if(data !== "0"){
                        $("#showMsg").html(data);
                    }
                    else {
                        document.location = "view/index.jsp";
                    }

                }
            });
        }
    }
</script>
<body background="static/image/login/background.jpg">
<div id="loginWin" class="easyui-window" data-options="closable:false , draggable:false" title="登录" style="width:350px;height:225px;padding:5px;"
     minimizable="false" maximizable="false" resizable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form id="loginForm" method="post">
                <div style="padding:5px 0;">
                    <label>帐号:</label>
                    <label>
                        <input class="easyui-textbox" type="text" id="login" data-options="iconCls:'icon-man'" value="admin" style="width:260px;"/>
                    </label>
                </div>
                <div style="padding:5px 0;">
                    <label>密码:</label>
                    <label>
                        <input class="easyui-textbox" type="password"  id="password" data-options="iconCls:'icon-lock'" value="123456" style="width:260px;"/>
                    </label>
                </div>
                <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
            </form>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="login()">登录</a>
            <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cleardata()">重置</a>
        </div>
    </div>
</div>
</body>
</html>
