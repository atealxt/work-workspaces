package test.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class SaveStreamTest {

    // 把输入流的内容转换成字节数组，进而转换成输入流的另外一个实现ByteArrayInputStream。这样做的好处是使用字节数组作为参数传递的格式要比输入流简单很多，可以不需要考虑资源相关的问题。另外也可以尽早的关闭原始的输入流，而无需等待所有使用流的操作完成。这两种做法的思路其实是相似的。BufferedInputStream在内部也创建了一个字节数组来保存从原始输入流中读入的内容。
    // 把一个InputStream保存为字节数组。
    public byte[] saveStream(final InputStream input) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ReadableByteChannel readChannel = Channels.newChannel(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream(32 * 1024);
        WritableByteChannel writeChannel = Channels.newChannel(output);
        while ((readChannel.read(buffer)) > 0 || buffer.position() != 0) {
            buffer.flip();
            writeChannel.write(buffer);
            buffer.compact();
        }
        return output.toByteArray();
    }

    public static void main(final String args[]) throws IOException {
        InputStream input = new FileInputStream("D:/Latest_File/U334P6T12D5552125F44DT20110427100456.jpg");
        System.out.println(new SaveStreamTest().saveStream(input));
    }

}
