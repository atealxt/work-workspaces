package pdms.platform.constant;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public final class MissionConstant {

    private MissionConstant() {
    }
    /** 分配状态 已分配 */
    public static final boolean DISTRIBUTION_YES = true;
    /** 分配状态 未分配 */
    public static final boolean DISTRIBUTION_NO = false;
    /** 分配确认状态(受取状态) 1:未确认 */
    public static final int DISTRIBUTION_CONFIRM_NO = 1;
    /** 分配确认状态(受取状态) 0:已确认 */
    public static final int DISTRIBUTION_CONFIRM_OK = 0;
    /** 分配确认状态(受取状态) -1:确认失败 */
    public static final int DISTRIBUTION_CONFIRM_ERROR = -1;
    /** 完成状态 true:已完成 */
    public static final boolean COMPLETE_STATUS_YES = true;
    /** 完成状态 false:未完成 */
    public static final boolean COMPLETE_STATUS_NO = false;
    /** 验收确认状态 1:未验收 */
    public static final int INSPECT_STATUS_NO = 1;
    /** 验收确认状态 0:已验收 */
    public static final int INSPECT_STATUS_OK = 0;
    /** 验收确认状态 -1:验收未通过 */
    public static final int INSPECT_STATUS_ERROR = -1;
    /** 验收确认状态 -2:再次提交请求验收 */
    public static final int INSPECT_STATUS_AGAIN = -2;
}
