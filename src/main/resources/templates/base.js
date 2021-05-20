var can = document.getElementById('can');
var ctx = can.getContext("2d");

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
};
function edit(){
    var b = $("#2").checked = false;
    editing = true;
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








//绘制点 参数：包含xy坐标的数组，半径、初始角度、结束角度、颜色
function makearc(point, r, s, e, color) {
    ctx.beginPath();
    ctx.fillStyle = color
    ctx.arc(point['x'], point['y'], r, s, e);
    ctx.fill();
}

function makerect(x, y, w, h) {
    ctx.strokeRect(x, y, w, h);
}

//绘制控制线
function controlLine(point){
    if (point == null)
        return;
    drawLin(point[0].x,point[0].y,point[1].x,point[1].y);
    for (var i = 2; i < point.length-2; i+=3) {
        drawLin(point[i].x,point[i].y,point[i+1].x,point[i+1].y);
        drawLin(point[i+1].x,point[i+1].y,point[i+2].x,point[i+2].y);
    }
    drawLin(point[point.length-2].x,point[point.length-2].y,point[point.length-1].x,point[point.length-1].y);
}

function drawLin(x1,y1,x2,y2){
    ctx.beginPath();
    //设置线条颜色为蓝色
    ctx.strokeStyle = "gray";
    ctx.lineWidth = 0.5;
    // debugger;
    //设置路径起点坐标
    ctx.moveTo(x1,y1);
    ctx.lineTo(x2,y2);
    //按照绘制路径顺序连接各个坐标点
    ctx.stroke();
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

//绘制辅助线
function drawHorizontalLine (y) {
    ctx.beginPath();
    ctx.strokeStyle = "red";
    ctx.moveTo(0, y+0.5);
    ctx.lineTo(ctx.canvas.width, y+0.5);
    ctx.stroke();
}

function drawVerticalLine (x) {
    ctx.beginPath();
    ctx.moveTo(x+0.5, 0);
    ctx.lineTo(x+0.5, ctx.canvas.height);
    ctx.stroke();
}

function drawGuidewires(x, y) {
    ctx.save();
    ctx.strokeStyle = 'rgb(212,16,94)';
    ctx.lineWidth = 0.5;
    drawVerticalLine(x);
    drawHorizontalLine(y);
    ctx.restore();
}
function drawHorizontalLine (y) {
    ctx.beginPath();
    ctx.moveTo(0, y+0.5);
    ctx.lineTo(ctx.canvas.width, y+0.5);
    ctx.stroke();
}

function drawVerticalLine (x) {
    ctx.beginPath();
    ctx.moveTo(x+0.5, 0);
    ctx.lineTo(x+0.5, ctx.canvas.height);
    ctx.stroke();
}

function drawGuidewires(x, y) {
    ctx.save();
    ctx.strokeStyle = 'rgba(0,0,230,0.4)';
    ctx.lineWidth = 0.5;
    drawVerticalLine(x);
    drawHorizontalLine(y);
    ctx.restore();
}