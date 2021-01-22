package zom.zoro.rwsd.chapter_05;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-20 10:13
 */
@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
