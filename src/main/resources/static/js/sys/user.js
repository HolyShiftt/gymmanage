layui.use('table', function(){
    var table = layui.table,
        $ = layui.jquery;


    // 用户信息表格
    table.render({
        elem: '#userTable'
        ,url:'/user/userList'
        ,page:true
        ,cols: [[
            {field: 'id', title: 'ID', fixed: 'left'}
            ,{field:'username', title: '用户名'}
            ,{field:'name', title: '姓名'}
            ,{field:'tel', title: '电话号码'}
            ,{field:'idNumber', title: '身份证号码'}
            ,{field:'job', title: '职位', sort: true}
            ,{field:'jobTime', title: '入职时间'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]]

    });

    // 添加按钮
    $("#addUser").click(function() {
        console.log(1);
        layer.open({
            title : '添加',
            type : 2,
            area : [ '30%', '70%' ],
            content : '/user/userAddPage'
        })
    })

});
