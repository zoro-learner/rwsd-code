package zom.zoro.rwsd.chapter_05;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-20 9:51
 */
public class Diagnosis {
    private final ConditionalAction conditionalAction;
    private final Facts facts;
    private final boolean isPositive;

    public Diagnosis(final Facts facts, final ConditionalAction conditionalAction, final boolean isPositive) {
        this.conditionalAction = conditionalAction;
        this.facts = facts;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
            "conditionalAction=" + conditionalAction +
            ", facts=" + facts +
            ", isPositive=" + isPositive +
            '}';
    }
}
