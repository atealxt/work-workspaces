package sshdemo.dao.jpa;

import org.springframework.stereotype.Repository;

import sshdemo.core.dao.BaseDaoJpa;
import sshdemo.dao.TeacherDao;
import sshdemo.entity.Teacher;

@Repository("TeacherDao")
public class TeacherDaoJpa extends BaseDaoJpa<Teacher, String> implements TeacherDao {

    public TeacherDaoJpa() {
        super(Teacher.class);
    }
}
