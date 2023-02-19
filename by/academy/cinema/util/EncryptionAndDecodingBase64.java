/**
 * Класс предоставляет возможность хэширования пароля Base64
 */
package by.academy.cinema.util;

import java.util.Base64;

public final class EncryptionAndDecodingBase64 {

    public static String encryptionPasswordBase64(String password) {
        byte[] bytes = password.getBytes();
        String result = Base64.getEncoder().encodeToString(bytes);
        return result;
    }

    public static String decodingPasswordBase64(String password) {
        byte[] decode = Base64.getDecoder().decode(password);
        String result = new String(decode);
        return result;
    }
}
