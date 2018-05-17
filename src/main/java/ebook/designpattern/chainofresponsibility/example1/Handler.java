package ebook.designpattern.chainofresponsibility.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/8/815:12 <br>
 *
 * @author 周志辉
 */
public abstract class Handler {

    /**
     * 持有下一个处理请求的对象
     */
    protected Handler nextHandler = null;

    /**
     * 取值方法
     */
    public Handler getNextHandler() {
        return nextHandler;
    }

    /**
     * 设置下一个处理请求的对象
     */
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 处理聚餐费用的申请
     * @param user    申请人
     * @param fee    申请的钱数
     * @return        成功或失败的具体通知
     */
    public abstract String handleFeeRequest(String user , double fee);
}
