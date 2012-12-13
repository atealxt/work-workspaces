package pdms.components.dao;

import java.util.Date;
import java.util.List;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface MissionDao {

    public List<Mission> findByUserLoginId(String loginId);

    public List<Mission> findByUserLoginId(String loginId, int maxNum, int startNum);

    /**
     * @param loginId 用户LoginID
     * @param conditions 任务内容条件
     */
    public List<Mission> findByUserLoginIdAndCond(String loginId, int maxNum, int startNum, String conditions);

    public List<Mission> findByProjectId(int prjId, int maxCnt);

    public List<Mission> findByProjectId(int prjId, int maxNum, int startNum);

    /**
     * @param prjId 项目ID
     * @param conditions 任务内容条件
     */
    public List<Mission> findByPIdAndCond(int prjId, int maxNum, int startNum, String conditions);

    /**
     * 返回已完成的任务（暂定包含已完成未确认的任务）
     */
    public List<Mission> find4ChartByProjectId(int prjId, int maxNum, int startNum);

    public Mission findById(int id);

    /**
     * Update
     * @return 0:ok 1:error 2:warning
     */
    public int Update(Mission obj);

    /**
     * Insert
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(MissionSubmit obj);

    /**
     * Insert
     * @return 0:ok 1:error 2:warning
     */
    public int Insert(Mission obj);

    /**
     * @return 0:ok 1:error 2:warning
     */
    public int Delete(Mission obj);

    /** 指定执行SQL */
    public List<Mission> findAll(int maxNum, int startNum, String querySQL);

    /** 按附属文件ID取得任务内容，如有重复则取第一条 */
    public Mission findBySubmitFileId(String fileId);

    /**
     * 返回总完成任务数(暂定包含已完成未确认的任务)<br>
     * select count(*)
     */
    public int Count4Chart(int projectId);

    /**
     * 返回指定时间内完成任务数(暂定包含已完成未确认的任务)<br>
     * select count(*)
     *
     * @param start 最小日期(包括)
     * @param end 最大日期(不包括)
     */
    public int CountByTime4Chart(Date start, Date end);

    /**
     * 返回指定时间内总任务数(包括以前未完成的)<br>
     * select count(*)
     *
     * @param start 最小日期(包括)
     * @param end 最大日期(不包括)
     */
    public int CountAllByTime4Chart(Date start, Date end);
}
