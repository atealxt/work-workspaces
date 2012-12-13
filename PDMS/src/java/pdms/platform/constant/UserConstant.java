package pdms.platform.constant;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public final class UserConstant {

    private UserConstant() {
    }
    /** 状态 1在职 */
    public static final int STATUS_IN = 1;
    /** 状态 -1离职 */
    public static final int STATUS_OUT = -1;
    /** 状态 0其他 */
    public static final int STATUS_OTHER = 0;
    /** 状态(表示) 1在职 */
    public static final String STATUS_IN_SHOW = "在职";
    /** 状态(表示) -1离职 */
    public static final String STATUS_OUT_SHOW = "离职";
    /** 状态(表示) 0其他 */
    public static final String STATUS_OTHER_SHOW = "其他";
}
