package designpattern.visitor.example1.test;

import designpattern.visitor.example1.Charger;
import designpattern.visitor.example1.Medicine;
import designpattern.visitor.example1.MedicineA;
import designpattern.visitor.example1.MedicineB;
import designpattern.visitor.example1.Presciption;
import designpattern.visitor.example1.Visitor;
import designpattern.visitor.example1.WorkerOfPharmacy;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1014:53 <br>
 *
 * @author 周志辉
 */
public class Client {
    public static void main(String[] args) {
        Medicine a = new MedicineA("板蓝根", 11.0);
        Medicine b = new MedicineB("感康", 14.3);

        Presciption presciption = new Presciption();
        presciption.addMedicine(a);
        presciption.addMedicine(b);

        Visitor charger = new Charger();
        charger.setName("张三");

        Visitor workerOfPharmacy = new WorkerOfPharmacy();
        workerOfPharmacy.setName("李四");

        presciption.accept(charger);
        System.out.println("-------------------------------------");
        presciption.accept(workerOfPharmacy);
    }

}
