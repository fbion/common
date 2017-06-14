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