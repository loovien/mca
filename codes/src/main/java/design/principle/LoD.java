package design.principle;

import java.util.ArrayList;
import java.util.List;

/**
 * created 7/12/2021 3:11 PM
 *
 * @author luowen <loovien@163.com>
 */
public class LoD {
    public static class Teacher {
        public void command(GroupLeader groupLeader) {
            groupLeader.computeStudentCnt();
        }
    }

    public static class GroupLeader {

        public final List<Student> studentList;

        public GroupLeader(List<Student> studentList) {
            this.studentList = studentList;
        }

        public void computeStudentCnt() {
            System.out.println("the count of students: " + studentList.size());
        }
    }

    public static class Student {
    }

    /**
     * Teacher 只和GroupLeader 交流
     * GroupLeader 只和 Student 交流
     *
     * @param args args
     */
    public static void main(String[] args) {

        Teacher teacher = new Teacher();
        teacher.command(new GroupLeader(new ArrayList<Student>() {{
            add(new Student());
        }}));
    }
}
