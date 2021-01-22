package zom.zoro.rwsd.chapter_05;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-19 15:56
 */
@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
