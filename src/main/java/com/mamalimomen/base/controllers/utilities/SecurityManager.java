package com.mamalimomen.base.controllers.utilities;

import com.lambdaworks.crypto.SCryptUtil;

public final class SecurityManager {
    private static final String pepper = "MohammadAli1996";

    private SecurityManager() {
    }

    public static synchronized String getPasswordHash(String plainPassword) {
        return SCryptUtil.scrypt(plainPassword.concat(pepper), 16, 16, 16);
    }

    public static synchronized boolean checkPasswordHash(String plainPassword, String hashedPassword) {
        return SCryptUtil.check(plainPassword.concat(pepper), hashedPassword);
    }
}