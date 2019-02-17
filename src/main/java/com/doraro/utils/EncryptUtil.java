package com.doraro.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Created by cyheng on 2018/2/24.
 */

public class EncryptUtil {

    public static String getSha512(String str) {
        return Hashing.sha512().hashString(str, Charsets.UTF_8).toString();
    }
}
