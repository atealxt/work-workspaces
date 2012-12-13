package captcha;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIOHelper {

    public static BufferedImage imageProducerToBufferedImage(
            final int w,
            final int h,
            final int[] pix,
            final int off,
            final int scan) {
        final Image tempImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(w, h, pix, off, scan));
        final BufferedImage image = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null),
                                                      BufferedImage.TYPE_INT_BGR);
        image.createGraphics().drawImage(tempImg, 0, 0, null);
        return image;
    }

    public static void saveFile(final String srcFile, final RenderedImage image) throws IOException {
        final String pname = srcFile.substring(0, srcFile.lastIndexOf("."));
        final File file = new File(pname + ".jpg");
        ImageIO.write(image, "jpg", file);
    }
}
