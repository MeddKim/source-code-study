package com.smartio;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class IntegerProtocol implements Protocol<Integer> {

    private static final int INT_LENGTH = 4;

    /**
     * 解码
     * @param data
     * @param session
     * @param eof
     * @return
     */
    @Override
    public Integer decode(ByteBuffer data, AioSession<Integer> session, boolean eof) {
        if (data.remaining() < INT_LENGTH)
            return null;
        return data.getInt();
    }

    /**
     * 编码
     * @param s
     * @param session
     * @return
     */
    @Override
    public ByteBuffer encode(Integer s, AioSession<Integer> session) {
        ByteBuffer b = ByteBuffer.allocate(INT_LENGTH);
        b.putInt(s);
        b.flip();
        HashMap
        return b;
    }

}
