package test.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class FileChannelTest {

    void test() throws IOException {
        // 对文件操作方面，文件通道FileChannel提供了与其它通道之间高效传输数据的能力，比传统的基于流和字节数组作为缓冲区的做法，要来得简单和快速。比如下面的把一个网页的内容保存到本地文件的实现。

        final FileOutputStream output = new FileOutputStream("baidu.txt");
        final FileChannel channel = output.getChannel();
        final URL url = new URL("http://www.baidu.com");
        final InputStream input = url.openStream();
        final ReadableByteChannel readChannel = Channels.newChannel(input);
        channel.transferFrom(readChannel, 0, Integer.MAX_VALUE);

        // 文件通道的另外一个功能是对文件的部分片段进行加锁。当在一个文件上的某个片段加上了排它锁之后，其它进程必须等待这个锁释放之后，才能访问该文件的这个片段。文件通道上的锁是由JVM所持有的，因此适合于与其它应用程序协同时使用。比如当多个应用程序共享某个配置文件的时候，如果Java程序需要更新此文件，则可以首先获取该文件上的一个排它锁，接着进行更新操作，再释放锁即可。这样可以保证文件更新过程中不会受到其它程序的影响。
//        FileLock lock = FileChannel.lock (INDEX_START, INDEX_SIZE, true);
//        lock.release();

        // 另外一个在性能方面有很大提升的功能是内存映射文件的支持。通过FileChannel的map方法可以创建出一个MappedByteBuffer对象，对这个缓冲区的操作都会直接反映到文件内容上。这点尤其适合对大文件进行读写操作。

    }

    public static void main(final String args[]) {
        try {
            new FileChannelTest().test();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        System.out.println("test end");
    }
}
