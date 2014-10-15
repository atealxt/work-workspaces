package easycache.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public final class CacheKeyUtils {

    private CacheKeyUtils() {}

    /** 生成缓存键的唯一字符串 */
    public final static String genCacheKey(final Object o) {
        if (o == null) {
            return null;
        }
        try {
            final BeanInfo info = getBeanInfo(o);
            final PropertyDescriptor[] propertyDescriptor = info.getPropertyDescriptors();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < propertyDescriptor.length; i++) {
                final String name = propertyDescriptor[i].getDisplayName();
                sb.append(name);
                sb.append(":");
                setValue(sb, propertyDescriptor[i].getReadMethod().invoke(o));
                sb.append(";");
            }
            final String key = sb.toString();
            if (logger.isTraceEnabled()) {
                logger.trace("cache key: " + key);
            }
            final String digestKey = DigestUtils.shaHex(key.getBytes());
            if (logger.isTraceEnabled()) {
                logger.trace("cache key encrypt: " + digestKey);
            }
            return digestKey;
        } catch (final IntrospectionException e) {
            warn(e);
            return null;
        } catch (final IllegalArgumentException e) {
            warn(e);
            return null;
        } catch (final IllegalAccessException e) {
            warn(e);
            return null;
        } catch (final InvocationTargetException e) {
            warn(e);
            return null;
        }
    }

    private static BeanInfo getBeanInfo(final Object o) throws IntrospectionException {
        final String className = o.getClass().getName();
        if (mapBeanInfo.containsKey(className)) {
            return mapBeanInfo.get(className);
        }
        final BeanInfo info = Introspector.getBeanInfo(o.getClass());
        mapBeanInfo.put(className, info);
        return info;
    }

    private static void setValue(final StringBuilder sb, final Object value) {
        if (value == null) {
            sb.append("null");
            return;
        }
        if (!value.getClass().isArray()) {
            sb.append(value);
            return;
        }
        if (value instanceof Object[]) {
            final Object[] v = (Object[]) value;
            final int iMax = v.length - 1;
            if (iMax == -1) {
                sb.append("[]");
                return;
            }
            sb.append("[");
            for (int i = 0;; i++) {
                setValue(sb, v[i]);
                if (i == iMax) {
                    sb.append(']').toString();
                    break;
                }
                sb.append(", ");
            }
        } else if (value instanceof int[]) {
            sb.append(Arrays.toString((int[]) value));
        } else if (value instanceof long[]) {
            sb.append(Arrays.toString((long[]) value));
        } else if (value instanceof float[]) {
            sb.append(Arrays.toString((float[]) value));
        } else if (value instanceof double[]) {
            sb.append(Arrays.toString((double[]) value));
        } else if (value instanceof boolean[]) {
            sb.append(Arrays.toString((boolean[]) value));
        } else if (value instanceof char[]) {
            sb.append(Arrays.toString((char[]) value));
        } else if (value instanceof byte[]) {
            sb.append(Arrays.toString((byte[]) value));
        } else if (value instanceof short[]) {
            sb.append(Arrays.toString((short[]) value));
        }
    }

    private static void warn(final Exception e) {
        logger.warn("缓存键生成失败，返回Null", e);
    }

    private final static Logger logger = Logger.getLogger(CacheKeyUtils.class);
    private final static Map<String, BeanInfo> mapBeanInfo = new HashMap<String, BeanInfo>();
}
