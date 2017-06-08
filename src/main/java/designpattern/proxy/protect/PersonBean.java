package designpattern.proxy.protect;

/**
 * 描述： <br>
 * 创建时间: 2017/5/3117:53 <br>
 *
 * @author 周志辉
 */
public interface PersonBean {
    String getName();
    String getGender();
    String getIntrests();
    int getHotOrNotRating();

    void setName(String name);
    void setGender(String gender);
    void setIntrests(String intrests);
    void setHotOrNotRating(int rating);
}
