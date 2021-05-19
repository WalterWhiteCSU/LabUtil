    var can = document.getElementById('can');
    var ctx = can.getContext("2d");
    //定义是否拖拽标志，拖拽x偏移，拖拽y偏移
    var dragging;
    //是否可编辑
    var editing = false;
    var  GRID_STROKE_STYLE = 'lightblue', GRID_SPACING = 10;
    //小圆点的半径
    var rx = 3,ry=3;
    var globl_w = 500;

    //设置点的大小
    function GetRandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    return (Min + Math.round(Rand * Range));
}

    //获取鼠标的坐标
    function mouseCoords(ev) {
    var e = event || window.event;
    var x = e.offsetX || e.layerX;
    var y = e.offsetY || e.layerY;
    return {x, y};
}

    //绘制边框
    makerect(0, 0, globl_w, globl_w);
    var index = 0;
    var pointList = new Array();

    //画线拖拽单选框
    function draw(){
    var a= $("#1").checked = true;
    editing = false;
    console.log(editing+"  nima***")
};
    function edit(){
    var b = $("#2").checked = false;
    editing = true;
    console.log(editing+"  nida+++")
};

    //绘制网格
    function drawGrid(color, stepX, stepY){
    ctx.save()

    ctx.strokeStyle = color;
    ctx.lineWidth = 0.5;
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);

    for (var i = stepX + 0.5; i < ctx.canvas.width; i += stepX) {
    ctx.beginPath();
    ctx.moveTo(i, 0);
    ctx.lineTo(i, ctx.canvas.height);
    ctx.stroke();
}

    for (var i = stepY + 0.5; i < ctx.canvas.height; i += stepY) {
    ctx.beginPath();
    ctx.moveTo(0, i);
    ctx.lineTo(ctx.canvas.width, i);
    ctx.stroke();
}

    ctx.restore();
};
    drawGrid(GRID_STROKE_STYLE, GRID_SPACING, GRID_SPACING);

    //绘制点
    function makearc(point, r, s, e, color) {
    ctx.beginPath();
    ctx.fillStyle = color
    ctx.arc(point['x'], point['y'], r, s, e);
    ctx.fill();
}

    function makerect(x, y, w, h) {
    ctx.strokeRect(x, y, w, h);
}

    //绘制线段
    function drawLine(pointList) {
    // ctx.clearRect(0, 0, 500, 500);
    if (pointList == null){
    return;
};
    for (var i = 0; i < pointList.length; i++) {
    //开始一个新的绘制路径
    ctx.beginPath();
    //设置线条颜色为蓝色
    ctx.strokeStyle = "navy";
    ctx.lineWidth = 0.5;
    //设置路径起点坐标
    ctx.moveTo(pointList[0].x, pointList[0].y);
    for (var j = 1; j < pointList.length; j++) {
    ctx.lineTo(pointList[j].x, pointList[j].y);
}
    //按照绘制路径顺序连接各个坐标点
    ctx.stroke();
}
}