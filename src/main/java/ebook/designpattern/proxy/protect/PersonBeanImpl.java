package ebook.designpattern.proxy.protect;

/**
 * 描述： <br>
 * 创建时间: 2017/5/3117:55 <br>
 *
 * @author 周志辉
 */
public class PersonBeanImpl implements PersonBean {

    String name;

    String gender;

    String intrests;

    int rating;

    int ratingCount = 0;


    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getGender() {
        return gender;
    }


    @Override
    public String getIntrests() {
        return intrests;
    }


    public int getRating() {
        return rating;
    }


    public int getRatingCount() {
        return ratingCount;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public void setIntrests(String intrests) {
        this.intrests = intrests;
    }


    @Override
    public int getHotOrNotRating() {
        if(ratingCount == 0) {
            return 0;
        }
        return (rating / ratingCount);
    }


    @Override
    public void setHotOrNotRating(int rating) {
        this.rating += rating;
        ratingCount++;
    }
}
