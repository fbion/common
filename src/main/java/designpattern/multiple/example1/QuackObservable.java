package designpattern.multiple.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/7/2017:26 <br>
 *
 * @author 周志辉
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
