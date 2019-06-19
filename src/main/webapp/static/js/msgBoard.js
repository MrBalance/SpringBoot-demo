$(function () {
    // 还能输入的字得个数
    var able_count = 140;
    // 是否可以发布留言
    var release_able = false;
    // 右上角文字
    var $title_txt = $('.title-txt');
    // 留言框
    var $main_area = $('.main-area');
    // 发布按钮
    var $release_btn = $('.release-btn');

    // 输入框获取焦点
    $main_area.focus(function () {
        $(this).parent().addClass('outline');
        $title_txt.addClass('title');
        if (able_count >= 0) {
            $title_txt.html("还可以输入" + able_count + "个字");
        } else {
            $title_txt.html("你以超出" + (-able_count) + "个字");
        }
    })

    // 输入框失去焦点
    $main_area.blur(function () {
        $(this).parent().removeClass('outline');
        $title_txt.removeClass('title');
        $title_txt.html("置办年货省省省！红包在手年货无忧！点击领取年货红包&nbsp;&nbsp;&nbsp;热门微博");
    })

    // 输入框文本修改
    $main_area.on('input', function () {
        // 剩余可输入的字个数
        able_count = 140 - $main_area.val().length;
        // console.log(able_count);
        // 根据可输入字的个数决定右上角文本的提示 与 是否能发布的状态
        if (able_count >= 0 && able_count <= 140) {
            $title_txt.html("还可以输入" + able_count + "个字");
            if (able_count != 140) {
                release_able = true;
            } else {
                release_able = false;
            }
        } else {
            $title_txt.html("你以超出" + (-able_count) + "个字");
            release_able = false;
        }
        // 根据发布状态决定发布按钮的样式
        if (release_able) {
            $release_btn.css({
                backgroundColor: "orange",
                borderColor: "orange"
            })
        } else {
            $release_btn.css({
                backgroundColor: "#ffc09f",
                borderColor: "#ffc09f"
            })
        }

    })

    var creatShow = function(title,key,context,date){
        var $showContent = $('<div class="show-content"></div>'),
            $showName = $('<div class="show-name"></div>'),
            $showKey = $('<div class="show-key"></div>'),
            $showTxt = $('<div class="show-txt"></div>'),
            $showTime = $('<div class="show-time"></div>'),
            $showClose = $('<div class="show-close"></div>'),
            $showP = $('<p class=""></p>');
        // 创建show对象的各个部位
        // 设置，对象结构内内容
        $showName.text(title);
        $showKey.text(key);
        $showP.text(context);
        $showTime.text(date);
        $showClose.text("x");
        // 添加进入主结构
        $showTxt.append($showP);
        $showContent.append($showName);
        $showContent.append($showKey);
        $showContent.append($showTxt);
        $showContent.append($showTime);
        $showContent.append($showClose);

        // 向所有匹配元素内部的开始处插入内容
        $('.show').prepend($showContent);

        // 添加动画
        // 位置从输入框处下移
        $showContent.css({
            top: '-150px'
        })
        $showContent.animate({
            top: 0
        }, 200)

        // 删除事件
        $showClose.click(function () {
            // 显示插入的索引位置
            // console.log($(this).parent().index());
            // console.log($showContent.index());


            // 删除操作为顺便
            // $showContent.remove();

            // 使用删除动画，创建效果
            $showContent.animate({
                height: 0
            }, 200, function () {
                // 动画结束后将自身从dom中移除
                $showContent.remove();
            })
        })
    }

    // 发布事件
    $release_btn.click(function () {
        var tittle = $("#Mtittle").val();
        var key = $("#Mkey").val();
        var context = $main_area.val();
        if (release_able) {
            $.ajax({
                type: "POST",   //post提交方式默认是get
                url: "../service/MessageBoardInfo/leavingMessage",
                data: {
                    tittle: tittle,
                    key: key,
                    context:context
                },
                error: function (request) { // 设置表单提交出错
                    $.messager.alert('警告', request, 'info');
                },
                success: function (data) {
                    if(data == "0") {
                        creatShow(tittle,key,context,new Date());
                    } else {
                        $.messager.alert('提示', data, 'info');
                    }
                }
            });
            // 发布成功后收尾工作
            $main_area.val(""); //输入框清空
            $("#Mtittle").val("");//标题清空
            $("#Mkey").val("");//关键字清空
            able_count = 140;  //输入框可输入内容数重置
            release_able = false;
            $release_btn.css({
                backgroundColor: '#ffc09f',
                borderColor: '#ffc09f'
            })  //按钮点击事件重置

        }
    })
    var loadMessage = function () {
        $.ajax({
            type: "POST",   //post提交方式默认是get
            url: "../service/MessageBoardInfo/searchMessage",
            data: {
                pageNum: '',
                pageSize: '',
                tittle:'',
                key:'',
                context:'',
                account:''
            },
            dataType: 'json',
            error: function (request) { // 设置表单提交出错
                $.messager.alert('警告', request, 'info');
            },
            success: function (data) {
                if(!data.rows){
                    data.rows = '';
                }
                $.each(data.rows, function(i, n){
                    creatShow(n.messageTittle,n.messageKey,n.messageContext,n.messageDate);
                });
            }
        });
    }
    loadMessage();


})