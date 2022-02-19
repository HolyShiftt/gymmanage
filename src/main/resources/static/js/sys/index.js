layui.use(['layer', 'util', 'element'], function () {
    var layer = layui.layer
        , util = layui.util
        , element = layui.element
        , $ = layui.jquery;

    layer.load(0, {time: 3000});

    // 获取用户信息
    $.ajax({
        url: "/user/getUser",
        success: function (d) {
            sessionStorage.setItem("username",d)
            $("#username").append(d)
        }
    })

    // 注销
    $("#logout").click(function () {
        layer.confirm("你确定要注销账户？", function (index) {
            layer.load();
            $.ajax({
                method: "post",
                url: "/user/logout",
                success: function (d) {
                    if (d.success) {
                        layer.msg("注销成功", {
                            icon: 6,
                            time: 2000
                        }, function () {
                            window.location.href = "/";
                        })
                    }
                }
            })
        })
    })
    if (sessionStorage.getItem("role") == "admin") {

        $("#menu1>a>span").text("场馆管理")
        $("#menu2>a>span").text("客户管理")
        $("#menu3>a>span").text("消费管理")
        $("#menu4>a>span").text("系统管理")

        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function (othis) {
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
            , menuRight: function () {
                layer.open({
                    type: 1
                    , content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                    , area: ['260px', '100%']
                    , offset: 'rt'
                    , anim: 5
                    , shadeClose: true
                });
            }
        });

        $.ajax({
            method: "get"
            , url: "/menu/menuList"
            , success: function (data) {
                var data = data.data;
                for (const d of data) {
                    if (d.parentId === 1) {
                        $("#gym").append("<dd class='menu'><a href='javascript:;' value=" + d.url + " id=sys" + d.id + ">" + d.title + "</a></dd>")
                    } else if (d.parentId === 2) {
                        $("#client").append("<dd class='menu'><a href='javascript:;' value=" + d.url + " id=sys" + d.id + ">" + d.title + "</a></dd>")
                    } else if (d.parentId === 3) {
                        $("#spend").append("<dd class='menu'><a href='javascript:;' value=" + d.url + " id=sys" + d.id + ">" + d.title + "</a></dd>")
                    } else if (d.parentId === 4) {
                        $("#sys").append("<dd class='menu'><a href='javascript:;' value=" + d.url + " id=sys" + d.id + ">" + d.title + "</a></dd>")
                    }
                }
                $(".menu").on("click", function (data) {
                    changeTab(data.target.attributes[1].nodeValue, data.target.id, data.target.innerHTML)
                })
            }
        });

        // 默认展示场馆管理
        $(function () {
            setTimeout(function () {
                $("#sys5").click();
            }, 3000);
        });
    } else if (sessionStorage.getItem("role") == 'user') {
        $("#menu3").empty()
        $("#menu4").empty()
        $("#menu1>a>span").text("运动馆")
        $("#menu2>a>span").text("个人信息")
        $("#gym").append(`<dd class='menu' id="book"><a href='javascript:;'>运动馆预约</a></dd>
                    <dd class='menu' id="myBook"><a href='javascript:;'>我的预约</a></dd>`)
        $("#client").append(`<dd class='menu' id="updClient"><a href='javascript:;'>信息修改</a></dd>`)
        // $("#menutree").append(`<li class="layui-nav-item layui-nav-itemed">
        //             <a href="javascript:void(0)">运动馆</a>
        //             <dl class="layui-nav-child" id="book">
        //             <dd class='menu' id="book"><a href='javascript:;'>运动馆预约</a></dd>
        //             <dd class='menu' id="myBook"><a href='javascript:;'>我的预约</a></dd>
        //             </dl>
        //         </li><li class="layui-nav-item layui-nav-itemed">
        //             <a href="javascript:void(0)">个人信息</a>
        //             <dl class="layui-nav-child" id="personal">
        //             <dd class='menu' id="updClient"><a href='javascript:;'>信息修改</a></dd>
        //             </dl>
        //         </li>`)

        $(".menu").on("click", function (data) {
            var id = data.currentTarget.id
            var url, title;
            if (id == 'book') {
                url = "/client/book";
                title = '运动馆预约';
            } else if (id == 'myBook') {
                url = "/client/myBook";
                title = '我的预约'
            } else if (id == 'updClient') {
                url = "/client/updClient";
                title = '信息修改'
            }
            changeTab(url, id, title)
        })

        $(function () {
            setTimeout(function () {
                $("#book>a").click();
            }, 3000);
        });
    }

    function changeTab(url, id, title) {
        if (url) {
            // 判断点击的该项是否已经存在
            if (!$("[lay-id='" + id + "']").length) {
                // 添加内容
                var height = $(document).height() - 200;
                element.tabAdd('tabs', {
                    title: title,
                    content: "<iframe src=" + url + " frameborder='0' width='100%' height=" + height + "></iframe>",
                    id: id,
                    value: url,
                    autoRefresh: true
                });
                //切换到指定Tab项
                element.tabChange('tabs', id);
            } else {
                element.tabChange('tabs', id);

            }
        }
        //tab点击刷新
        element.on('tab(tabs)', function (data) {
            var src = $(".layui-tab-item.layui-show").find("iframe").attr("src");
            $(".layui-tab-item.layui-show").find("iframe").attr("src", src);
        });
    }


});

