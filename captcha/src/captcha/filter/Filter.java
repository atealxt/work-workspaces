package captcha.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Filter {

    BufferedImage filter(final String srcFile) throws IOException;
}
