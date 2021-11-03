layui.use('element', function() {
    var element = layui.element
        , $ = layui.jquery
        , form = layui.form;

    form.on('submit(sub)', function (data) {
        $.post("/index",function(data) {
            console.log("data:" + data)
            window.location.href = "/index";
        })
    })

})
