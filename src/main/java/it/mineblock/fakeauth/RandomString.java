package it.mineblock.fakeauth;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Copyright © 2017 by Lorenzo Magni
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class RandomString {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase();
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public String nextString() {
        for(int idx = 0; idx < buf.length; idx++) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public RandomString(int lenght, Random random, String symbols) {
        if(lenght < 1) {
            throw new IllegalArgumentException();
        }

        if(symbols.length() < 2) {
            throw new IllegalArgumentException();
        }

        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[lenght];
    }

    public RandomString(int lenght, Random random) {
        this(lenght, random, alphanum);
    }

    public RandomString(int lenght) {
        this(lenght, new SecureRandom());
    }

    public RandomString() {
        this(21);
    }
}
