//绘制图形的类型
var curveInput = {
    paintingType:"Line",
    pointList:[]
};
//获取多段线按钮
$("polyline").click(function (){

    curveInput.paintingType = "Line";
    curveInput.pointList = new Array();
});

//获取贝塞尔按钮
$("bezier").click(function (){

    curveInput.paintingType = "Bezier";
    curveInput.pointList = new Array();
});

//获取三次样条曲线按钮
$("spline").click(function (){

    curveInput.paintingType = "Spline";
    curveInput.pointList = new Array();
});

//获取圆按钮
$("circle").click(function (){

    curveInput.paintingType = "Circle";
    curveInput.pointList = new Array();
});

//获取圆弧按钮
$("Arc").click(function (){
    curveInput.paintingType = "Arc";
    curveInput.pointList = new Array();
});

//鼠标点击生成圆
$("#can").click(function (e) {
    if(editing == true)
        return;
    var ev = ev || window.event;
    var mousePos = mouseCoords(ev);
    mousePosX = mousePos.x;
    mousePosY = mousePos.y;

    var point = {};
    point['x'] = mousePosX;
    point['y'] = mousePosY;
    curveInput.pointList.push(point);
    // pointList.push(point);
    index++;

    var data = JSON.stringify(pointList);
    $.ajax({
        url: "/test/1",
        type: 'POST',//默认为get请求,这里设置为post请求
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(curveInput.pointList),
        beforeSend: function (data) {
            console.log(typeof data);
        },
        success: function (data, status) {
            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
            drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);
            for (var i = 0; i < index; i++) {
                makearc(curveInput.pointList[i], GetRandomNum(rx, ry), 0, 180, 'red');
            }
            var finalPointList = eval(data);
            /* for (var i = 0; i < pointList.length; i++) {
                 makearc(curveInput.pointList[i], GetRandomNum(rx,ry), 0, 180, 'red');
             };*/
            drawLine(finalPointList);
        },
        error: function () {
            console.log("ERROR");
        }
    });
});

//removeOne按钮
$("#btn1").click(function (e){
    // debugger
    curveInput.pointList.pop();
    index--;
    $.ajax({
        url: "/test/1",
        type: 'POST',//默认为get请求,这里设置为post请求
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(curveInput.pointList),
        beforeSend: function (data) {
            console.log(typeof data);
        },
        success: function (data, status) {

            var finalPointList = eval(data);

            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
            drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);
            for (var i = 0; i < curveInput.pointList.length; i++) {
                makearc(curveInput.pointList[i], GetRandomNum(rx,ry), 0, 180, 'red');
            };
            drawLine(finalPointList);
        },
        error: function () {
            console.log("ERROR");
        }
    });

});

//clear按钮
$("#btn2").click(function (e){
    curveInput.pointList = [];
    index = 0;
    $.ajax({
        url: "/test/1",
        type: 'POST',//默认为get请求,这里设置为post请求
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(curveInput.pointList),
        beforeSend: function (data) {
            console.log(typeof data);
        },
        success: function (data, status) {

            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
            drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);
            // 登录状态下不会出现这行文字，点击页面右上角一键登录
        },
        error: function () {
            console.log("ERROR");
        }
    });

});

//手动输入xy坐标的值
$("#submit").click(function (){
    var inputVal = $("#coor").val();
    inputVal = inputVal.split(" ");
    if (inputVal.length % 2 !=0){
        alert("输入的坐标个数有误,请重新输入！");
    };

    inputVal = inputVal.map(Number);

    for (var i =0;i<inputVal.length;i=i+2){
        var point = {};
        point['x']=inputVal[i];
        point['y']=inputVal[i+1];
        index++;
        // console.log(point+"point打印");
        curveInput.pointList.push(point);
    }

    $.ajax({
        url: "/test/1",
        type: 'POST',//默认为get请求,这里设置为post请求
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(curveInput.pointList),
        beforeSend: function (data) {
            console.log(typeof data);
        },
        success: function (data, status) {
            var finalPointList = eval(data);
            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
            drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);
            for (var i = 0; i < curveInput.pointList.length; i++) {
                makearc(curveInput.pointList[i], GetRandomNum(rx,ry), 0, 180, 'red');
            };
            drawLine(finalPointList);
            // 登录状态下不会出现这行文字，点击页面右上角一键登录
        },
        error: function () {
            console.log("ERROR");
        }
    });

});
//计算点击时canvas画布中的坐标x y；

var dragIndex;
//拖拽功能
addEventListener('mousedown', function (e) {
    if(editing == false)
        return;
    console.log(editing)
    debugger;
    var mousePos = mouseCoords(e);
    var X = mousePos.x;
    var Y = mousePos.y;
    console.log("鼠标按下时所得坐标" + X + Y);
    //取消事件的默认动作
    e.preventDefault();
    for (var i = 0; i < curveInput.pointList.length; i++) {
        if (Math.abs(X - curveInput.pointList[i].x) <= 5 && Math.abs(Y - curveInput.pointList[i].y) <= 5) {
            dragging = curveInput.pointList[i];
            dragIndex = i;
            break;
        }
    }
}, false);

//鼠标移动时绘制曲线
addEventListener('mousemove', function (e) {
    if (editing == false)
        return;

    var mousePos = mouseCoords(e);
    var X = mousePos.x;
    var Y = mousePos.y;

    e.preventDefault();

    if (dragging && editing) {
        curveInput.pointList[dragIndex].x = X;
        curveInput.pointList[dragIndex].y = Y;

        $.ajax({
            url: "/test/1",
            type: 'POST',//默认为get请求,这里设置为post请求
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(curveInput.pointList),
            beforeSend: function (data) {
                console.log(typeof data);
            },
            success: function (data, status) {
                var finalPointList = eval(data);
                ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
                drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);
                for (var i = 0; i < index; i++) {
                    makearc(curveInput.pointList[i], GetRandomNum(rx, ry), 0, 180, 'red');
                }
                drawLine(finalPointList);
            },
            error: function () {
                console.log("ERROR");
            }
        });
    }
}, false);

//鼠标松开事件
addEventListener('mouseup', function (e) {
    if(editing == false)
        return;
    if (dragging) {
        dragging = null
    }
}, false);
