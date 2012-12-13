package ipchecker.plugin.nio;

import ipchecker.IpChecker;

import java.nio.channels.SocketChannel;

class ServerDataEvent {

    public NioServer server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(final NioServer server, final SocketChannel socket, final byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;

        // 验证
        String ip = new String(data);
        valid = IpChecker.valid(new String(data));
        if (!valid) {
            System.out.println(ip + " access denied.");
        }
    }

    private boolean valid;

    public boolean isValid() {
        return valid;
    }
}