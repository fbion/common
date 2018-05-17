package ebook.designpattern.memento.example2;

import java.util.Map;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:57 <br>
 *
 * @author 周志辉
 */
public class Memento {
    private Map<String, Object> stateMap;

    public Memento(Map<String, Object> map){
        this.stateMap = map;
    }

    public Map<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}
