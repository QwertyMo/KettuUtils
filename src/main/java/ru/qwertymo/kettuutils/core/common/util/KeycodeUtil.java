package ru.qwertymo.kettuutils.core.common.util;

import java.util.ArrayList;
import java.util.List;

public class KeycodeUtil {
    private static final List<Integer> _charKeys = new ArrayList<>();

    public static void init(){
        for(int i=2;i<=13;i++) _charKeys.add(i);
        for(int i=16;i<=26;i++) _charKeys.add(i);
        for(int i=30;i<=41;i++) _charKeys.add(i);
        for(int i=44;i<=53;i++) _charKeys.add(i);
        _charKeys.add(57);
    }

    public static List<Integer> getCharKeys(){
        return _charKeys;
    }
}
