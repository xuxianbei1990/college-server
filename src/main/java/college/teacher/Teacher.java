package college.teacher;

/**
 * User: Administrator
 * Date: 2018/8/10
 * Time: 21:53
 * Version:V1.0
 */
public class Teacher {
    //定义实体类的属性，与teacher表中的字段对应
    private int id;            //id===>t_id
    private String name;    //name===>t_name

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + "]";
    }
}
