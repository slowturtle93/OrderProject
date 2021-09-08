package dev.parctice.order.common.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 토큰을 생성하는 클래스 파일
 */
public class TokenGenerator {

    private static final int TOKEN_LENGTH = 20;

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
    }
}
