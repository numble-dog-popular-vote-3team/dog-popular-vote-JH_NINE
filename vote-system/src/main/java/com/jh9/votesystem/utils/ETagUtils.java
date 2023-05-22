package com.jh9.votesystem.utils;

import io.lettuce.core.StrAlgoArgs.By;
import org.springframework.util.DigestUtils;

public class ETagUtils {

    public static String generate(byte[] bytes) {
        String hash = DigestUtils.md5DigestAsHex(bytes);
        return "\"" + hash + "\"";
    }
}
