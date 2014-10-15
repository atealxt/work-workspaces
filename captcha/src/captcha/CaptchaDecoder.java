package captcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import captcha.filter.ImgFilter;

import com.asprise.util.ocr.OCR;

/** 有噪点干扰线的话准确性不高啊 */
public class CaptchaDecoder {

    public String recognize(final String file) throws IOException {
        BufferedImage image = ImageIO.read(new File(file));
        final int width = image.getTileWidth();
        final int height = image.getTileHeight();
        image = image.getSubimage(0, 0, width, height);
        return new OCR().recognizeEverything(image);
    }

    public static void main(final String[] args) throws IOException {
        final String path = "D:/Work/workspace/eclipse/captcha/src/captcha/";
        final CaptchaDecoder decoder = new CaptchaDecoder();
        for (int i = 1; i <= 5; i++) {
            final BufferedImage img1 = new ImgFilter().filter(path + "captcha" + i + ".png");
            ImageIOHelper.saveFile(path + "captcha" + i + ".png", img1);
            // final BufferedImage img2 = new SmartBlurFilter().filter(path + "captcha" + i + ".jpg");
            // ImageIOHelper.saveFile(path + "captcha" + i + ".jpg", img2);
            System.out.println("code" + i + ": " + decoder.recognize(path + "captcha" + i + ".jpg"));
        }
    }
}
