package zom.zoro.rwsd.chapter_04;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-19 9:41
 */
public interface Importer {
    Document importFile(File file) throws IOException;
}
