package zom.zoro.rwsd.chapter_05;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-20 10:14
 */
@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
