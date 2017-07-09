/**
 * Created by Administrator on 2017/6/14.
 */
//判断操作数的小数点后是否超过toNumber位数，超过，则进行四舍五入，否则返回原操作数
function fixNumber(value, toNumber) {
    var reg = new RegExp("^\\d*\\.\\d{" + (toNumber+1) + ",}$");
    if((value + "").match(reg)) {
        return value.toFixed(toNumber)
    }
    return value;
}

//日期类格式化
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
    return fmt;
}

function format(timestamp) {
    return timestamp?new Date(timestamp).Format("yyyy-MM-dd hh:mm:ss"):"";
}

//查询字符串转对象
function convertQueryString() {
    var url = location.search.substr(1, location.search.length);
    if(url) {
        var queryStrings = array[1].split("&");
        var result = {};
        for (var i = 0; i < queryStrings.length; i++) {
            var tempArray = queryStrings[i].split("=");
            if (tempArray && tempArray.length > 1) {
                result[tempArray[0]] = tempArray[1];
            }
        }
    }
    return result;
}

//深度克隆对象
function cloneFun(obj){
    if(!obj||"object" != typeof obj){
        return null;
    }
    var result = (obj instanceof Array)?[]:{};
    for(var i in obj){
        result[i] = ("object" != typeof obj[i])?obj[i]:cloneFun(obj[i]);
    }
    return result;
}

//防止事件冒泡
function stopBubble(e)
{
    if (e && e.stopPropagation)
        e.stopPropagation()
    else
        window.event.cancelBubble=true
}

//获取字符串长度,汉字按两个算
function getLength(str) {
    var len = 0;
    for (var j = 0;j < str.length; j++) {
        if (/[\u4e00-\u9fa5]/.test(str.charAt(j))) {
            len= len +2;
        }else {
            len = len + 1;
        }
    }
    return len;
}

/*
 函数：计算两个日期之间的天数差值
 */
function dateDiff(date1, date2) {
    var msCount = 24 * 60 * 60 * 1000;
    //alert(Date.parse(date1));
    //alert(Date.parse(date2));
    var diff =  Date.parse(date1) - Date.parse(date2);
    return Math.floor(diff / msCount);
};

//取两位小数
function fixTo2(val){
    if(!/^\d+(\.\d{1,2})?$/.test(val)){
        return val.toFixed(2);
    }
    return val;
}

function log(msg){
    if(window.console && console.log){
        console.log(msg);
    }
}