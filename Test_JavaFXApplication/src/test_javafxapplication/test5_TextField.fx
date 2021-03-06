/*
 * test5_TextField.fx
 *
 * Created on 2008/03/18, 9:41:38
 */

package test_javafxapplication;
import javafx.ui.*;
/**
 * @author Administrator
 */

class test5_TextField {

}

class Model {
    attribute firstName: String;
    attribute lastName: String;
}

var model = Model {
    firstName: "Joe"
    lastName: "Smith"
};

Frame {
    content: GroupPanel {
        var firstNameRow = Row { alignment: BASELINE }
        var lastNameRow = Row { alignment: BASELINE }
        var labelsColumn = Column {
            alignment: TRAILING
        }
        var fieldsColumn = Column {
            alignment: LEADING
            resizable: true
        }
        rows: [firstNameRow, lastNameRow]
        columns: [labelsColumn, fieldsColumn]
        content:
        [SimpleLabel {
            row: firstNameRow
            column: labelsColumn
            text: "First Name:"
        },
        TextField {
            row: firstNameRow
            column: fieldsColumn

            columns: 25
            value: bind model.firstName
        },
        SimpleLabel {
            row: lastNameRow
            column: labelsColumn
            text: "Last Name:"
        },
        TextField {
            row: lastNameRow
            column: fieldsColumn
            columns: 25
            value: bind model.lastName
        }]
    }
    visible: true
};