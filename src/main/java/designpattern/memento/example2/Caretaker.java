package designpattern.memento.example2;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:58 <br>
 *
 * @author 周志辉
 */
public class Caretaker {
    private Map<String, Memento> memMap = new HashMap<String, Memento>();
    public Memento getMemento(String index){
        return memMap.get(index);
    }

    public void setMemento(String index, Memento memento){
        this.memMap.put(index, memento);
    }
}
