package pdms.platform.constant;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public final class TopicConstant {

    private TopicConstant() {
    }
    /** 状态 1:开放 */
    public static final int STATUS_OPEN = 1;
    /** 状态 0:关闭 */
    public static final int STATUS_CLOSE = 0;
    /** 状态 -1:删除 */
    public static final int STATUS_DELETE = -1;
    /** 分类 1:private */
    public static final int TYPE_PRIVATE = 1;
    /** 分类 0:public */
    public static final int TYPE_PUBLIC = 0;
    /** 等级(Position) 1:Position1 */
    public static final int LEVEL_POSITION_1 = 1;
    /** 等级(Position) 2:Position2 */
    public static final int LEVEL_POSITION_2 = 2;
}
