package ebook.designpattern.visitor.example1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述： 药单<br>
 * 创建时间: 2017/8/1014:52 <br>
 *
 * @author 周志辉
 */
public class Presciption {
    List<Medicine> list = new ArrayList<Medicine>();

    public void accept(Visitor visitor){
        Iterator<Medicine> iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }

    public void addMedicine(Medicine medicine){
        list.add(medicine);
    }

    public void removeMedicien(Medicine medicine){
        list.remove(medicine);
    }
}