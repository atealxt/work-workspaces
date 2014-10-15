package sshdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sshdemo.core.dao.BaseDao;
import sshdemo.core.service.BaseServiceImpl;
import sshdemo.dao.TeacherDao;
import sshdemo.entity.Teacher;

@Service("TeacherService")
public class TeacherService extends BaseServiceImpl<Teacher, String> {

    @Autowired
    @Qualifier("TeacherDao")
    private TeacherDao teacherDao;

    @Override
    protected BaseDao<Teacher, String> getDao() {
        return teacherDao;
    }

    @Transactional
    public List<Teacher> getTeathers() {
        List<Teacher> teathers = teacherDao.findByQuery("from Teacher");
        return teathers;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Teacher> getTeathers2() {
        return confirmTeathers();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private List<Teacher> confirmTeathers() {
        List<Teacher> teathers = teacherDao.findByQuery("from Teacher");
        for (Teacher t : teathers) {
            initialize(t.getStudents());
        }
        return teathers;
    }
}
