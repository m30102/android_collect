package com.fan.collect.module.jsbridge.api.utils;

import java.util.Collection;

public class EmptyUtils {

    /**
     * 功能描述: Object 等于空
     *
     * @return boolean
     * @Param [obj]
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 功能描述: collection集合为空
     *
     * @return boolean
     * @Param [obj]
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 功能描述: 对象数组为空
     *
     * @return boolean
     * @Param [strings]
     */
    public static boolean isEmpty(Object[] obj) {
        return ((obj == null) || (obj.length == 0));
    }

    /**
     * 功能描述: byte数组为空
     *
     * @return boolean
     * @Param [bys]
     */
    public static boolean isEmpty(byte[] bys) {
        return ((bys == null) || (bys.length == 0));
    }
}
