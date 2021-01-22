package zom.zoro.rwsd.chapter_05;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-20 9:48
 */
public interface ConditionalAction {
    boolean evaluate(Facts facts);

    void perform(Facts facts);
}
