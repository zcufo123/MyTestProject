package com.featuretest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by T_DanteZ1 on 26/01/18.
 */

public class TypeInferenceTest {

    static <T> T pick(T a1, T a2) { return a2; }

    private void test() {
        Serializable s = pick("d", new ArrayList<String>());
    }
}
