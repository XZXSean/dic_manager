/**
 * Created by xzx14 on 2017/6/15.
 */
//数据库数据
var dic_data;
//排序
$("#one").bind("click",function () {
    sortData($("#one").attr("value"))
    //循环更改value值
    if ($("#one").attr("value") == "0"){
        $("#one").attr("value","1");
    } else {
        $("#one").attr("value","0");
    }
})
$("#two").bind("click",function () {
    sortData($("#two").attr("value"))
    //循环更改value值
    if ($("#two").attr("value") == "2"){
        $("#two").attr("value","3");
    } else {
        $("#two").attr("value","2");
    }
})
$("#three").bind("click",function () {
    sortData($("#three").attr("value"))
    //循环更改value值
    if ($("#three").attr("value") == "4"){
        $("#three").attr("value","5");
    } else {
        $("#three").attr("value","4");
    }
})
//排序
function sortData(n) {
    $.ajax({
        type : "POST",
        url : "http://localhost:8080//sortData",
        data : {sta: n},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        //dataType : "json",
        success : function(data) {
            if (data != null){
                dic_data = eval(data);
                createTable();

            } else {
                alert("失败！")
            }
        },
        error: function () {
            alert("err");
        }
    })
}
//动态展示是否勾选checkbox
$("#add_def_value").bind("click",function () {
    if ($("#add_def_value").is(':checked')){
        $("#add_def_value_text").attr("value","True");
    } else {
        $("#add_def_value_text").attr("value","False");
    }
})
$("#update_def_value").bind("click",function () {
    if ($("#update_def_value").is(':checked')){
        $("#update_def_value_text").attr("value","True");
    } else {
        $("#update_def_value_text").attr("value","False");
    }
})
//增加数据按钮
$("#addSubmit").bind("click", function () {
    //checkbox
    var checked_value = false;
    if ($("#add_def_value").is(':checked')){
        checked_value = true;
    }
    //select-option
    var index = $("#add_dic_type").get(0).selectedIndex;
    //alert(index);
    var data = { //获取信息
        add_order_id: $("#add_order_id").val(),
        add_dic_name: $("#add_dic_name").val(),
        add_def_value: checked_value,
        add_dic_type: index + 1
    };
    if(data.add_order_id == ""){
        alert("字典序号不能为空！！");
    } else { //增加数据
        $.ajax({
            type : "POST",
            url : "http://localhost:8080//addData",
            data : data,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            //dataType : "json",
            success : function(data) {
                if (data){
                    alert("成功填加数据！");
                    //清空modal并隐藏
                    $("#add_order_id").val("");
                    $("#add_dic_name").val("");
                    $("#addModal").modal("hide");
                    getData();

                } else {
                    alert("添加数据失败！")
                }
            },
            error: function () {
                alert("err");
            }
        })
    }
})
//清空增加数据modal
$("#addClear").bind("click",function () {
    $("#add_order_id").val("");
    $("#add_dic_name").val("");
})
//修改数据按钮
$("#updateSubmit").bind("click", function () {
    //checkbox
    var checked_value = false;
    if ($("#update_def_value").is(':checked')){
        checked_value = true;
    }
    //select-option
    var index = $("#update_dic_type").get(0).selectedIndex;
    //alert(index);
    var data = { //获取信息
        update_dic_id: $("#update_dic_id").val(),
        update_order_id: $("#update_order_id").val(),
        update_dic_name: $("#update_dic_name").val(),
        update_def_value: checked_value,
        update_dic_type: index + 1
    };
    $.ajax({
        type : "POST",
        url : "http://localhost:8080//updateData",
        data : data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        //dataType : "json",
        success : function(data) {
            if (data){
                alert("修改数据成功！");
                $("#updateModal").modal("hide");
                getData();

            } else {
                alert("修改数据失败！")
            }
        },
        error: function () {
            alert("err");
        }
    })
})
//ajax 调用 servlet 获取json格式数据
function getData(){
    var xmlhttp;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4&&xmlhttp.status==200){
            //alert(xmlhttp.responseText);
            var respText = xmlhttp.responseText;
            console.log(respText);
            dic_data = eval( respText);
            createTable();
            //console.log("get dic_data ***");
        }
    }
    var url="../getData";
    var name = "this is first data";
    var sendStr = "name="+name;
    xmlhttp.open("POST",url, true);
    //xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send(sendStr);
    console.log("发送sendStr***");
}
//创建表格
function createTable() {
    console.log("createTable**");
    var tbody = document.getElementById("tbody");
    //删除表格原有内容
    $('#tbody').html('');
    //创建新表格
    for (var i = 0; i < dic_data.length; i++) {
        console.log("创建表格",i);
        var tr = document.createElement("tr"); //创建一行
        tr.setAttribute("id","tr" + i);
        //console.log(tr.getE);
        var className;
        i % 2 == 0 ? className="odd_row" : className = "even_row"; //奇偶行样式
        tr.setAttribute("class",className);
        //tr.addClass("odd_row");
        for (var j = 0; j < 7; j++) {
            var td = document.createElement("td"); //创建列
            tr.appendChild(td);
            switch (j) {
                case 0:
                    td.innerHTML = dic_data[i].dic_id;
                    break;       //第一行
                case 1:
                    td.innerHTML = dic_data[i].order_id;
                    break;      //第二行
                case 2:
                    var dic_type_name;
                    switch (dic_data[i].dic_type_id){
                        case 1:
                            dic_type_name = "水果";
                            break;
                        case 2:
                            dic_type_name = "蔬菜";
                            break;
                        case 3:
                            dic_type_name = "文具";
                            break;
                        case 4:
                            dic_type_name = "书籍";
                            break;
                        default:
                            dic_type_name = "Unknow";
                    }

                    td.innerHTML = dic_type_name;
                    break;   //第三行
                case 3:
                    td.innerHTML = dic_data[i].dic_name;
                    break;    //第四行
                case 4:
                    td.innerHTML = dic_data[i].def_value;
                    break;    //第五行
                case 5:
                    var deBtn = document.createElement("button");
                    deBtn.innerText = "Update";
                    deBtn.setAttribute("id", "updateBtn"+i);
                    deBtn.setAttribute("type", "button");
                    deBtn.setAttribute("class", "btn btn-success btn-xm");
                    deBtn.setAttribute("data-toggle","modal")
                    deBtn.setAttribute("data-target","#updateModal")
                    td.appendChild(deBtn);
                    break;
                case 6:
                    //alert(dic_data[i].dic_id);
                    var deBtn = document.createElement("button");
                    deBtn.innerText = "Detele";
                    deBtn.setAttribute("id", "deleteBtn"+i);
                    deBtn.setAttribute("type", "button");
                    deBtn.setAttribute("class", "btn btn-danger btn-xm");
                    td.appendChild(deBtn);
                    break;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
        //addListener();
    }
    console.log("tbody.rows.length",tbody.rows.length);
    addListener();
}
//添加监听
function addListener() {
    var tbody = document.getElementById("tbody");
    //动态绑定所有button
    var l = tbody.rows.length;
    for (var i = 0; i < l; i++){
        (function (n) {
            var btn = document.getElementById("deleteBtn"+n);
            //btn.addListener("click",deleteData(n));
            btn.onclick = function () {
                var flag = confirm("确定删除字典编号为 " + tbody.rows[n].cells[0].innerText+" 的信息？");
                if (flag == true) {
                    deleteData(tbody.rows[n].cells[0].innerText, n);
                }
            }
        })(i);
        (function (n) {
            var btn = document.getElementById("updateBtn"+n);
            btn.onclick = function () {
                var update_order_id = document.getElementById("update_order_id");
                update_order_id.setAttribute("value",tbody.rows[n].cells[1].innerText);
                var update_dic_id = document.getElementById("update_dic_id");
                update_dic_id.setAttribute("value",tbody.rows[n].cells[0].innerText);
                var update_dic_name = document.getElementById("update_dic_name");
                update_dic_name.setAttribute("value",tbody.rows[n].cells[3].innerText);
                var update_def_value = document.getElementById("update_def_value");
                var def_value = tbody.rows[n].cells[4].innerText;
                if (def_value == "true"){
                    update_def_value.checked = true;
                    var update_def_value_text = document.getElementById("update_def_value_text");
                    update_def_value_text.setAttribute("value","True");
                }
                var update_dic_type = document.getElementById("update_dic_type");
                var text = tbody.rows[n].cells[2].innerText
                switch (text){
                    case "水果":
                        update_dic_type.options[0].selected = true;
                        break;
                    case "蔬菜":
                        update_dic_type.options[1].selected = true;
                        break;
                    case "文具":
                        update_dic_type.options[2].selected = true;
                        break;
                    case "书籍":
                        update_dic_type.options[3].selected = true;
                        break;
                }
            }
        })(i);
    }
}
//删除数据
function deleteData(id, n) {
    $.ajax({
        type : "POST",
        url : "http://localhost:8080//deleteData",
        data : {
            id : id
        },
        //dataType : "json",
        success : function(data) {
            //alert(data);
            if (data){
                alert("删除信息成功！");
                //$("#tbody").removeChild()
                var tbody = document.getElementById("tbody");
                tbody.deleteRow(n);
            }
        }
    })

}



