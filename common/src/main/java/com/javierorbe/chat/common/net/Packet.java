package com.javierorbe.chat.common.net;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
