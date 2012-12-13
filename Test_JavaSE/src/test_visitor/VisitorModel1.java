package test_visitor;

public class VisitorModel1 implements Visitor {

    public void visit(final Model m) {

        try {
            Model1 m1 = (Model1) m;
            m1.setAge("100");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

}
