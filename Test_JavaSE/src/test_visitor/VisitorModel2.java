package test_visitor;

public class VisitorModel2 implements Visitor {

    public void visit(final Model m) {

        try {
            Model2 m2 = (Model2) m;
            m2.setAddress("addr");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
