package axoloti.swingui.patch.object.attribute;

import axoloti.abstractui.IAxoObjectInstanceView;
import axoloti.patch.object.attribute.AttributeInstance;
import axoloti.patch.object.attribute.AttributeInstanceComboBox;
import axoloti.patch.object.attribute.AttributeInstanceInt32;
import axoloti.patch.object.attribute.AttributeInstanceObjRef;
import axoloti.patch.object.attribute.AttributeInstanceSDFile;
import axoloti.patch.object.attribute.AttributeInstanceSpinner;
import axoloti.patch.object.attribute.AttributeInstanceTablename;
import axoloti.patch.object.attribute.AttributeInstanceTextEditor;
import axoloti.swingui.patch.object.AxoObjectInstanceView;

/**
 *
 * @author jtaelman
 */
public class AttributeInstanceViewFactory {

    public static AttributeInstanceView createView(AttributeInstance model, IAxoObjectInstanceView obj) {
        AttributeInstanceView view;
        if (model instanceof AttributeInstanceComboBox) {
            view = new AttributeInstanceViewComboBox((AttributeInstanceComboBox) model, (AxoObjectInstanceView) obj);
        } else if (model instanceof AttributeInstanceInt32) {
            view = new AttributeInstanceViewInt32(model, obj);
        } else if (model instanceof AttributeInstanceObjRef) {
            view = new AttributeInstanceViewObjRef(model, obj);
        } else if (model instanceof AttributeInstanceSDFile) {
            view = new AttributeInstanceViewSDFile(model, obj);
        } else if (model instanceof AttributeInstanceSpinner) {
            view = new AttributeInstanceViewSpinner(model, obj);
        } else if (model instanceof AttributeInstanceTablename) {
            view = new AttributeInstanceViewTablename(model, obj);
        } else if (model instanceof AttributeInstanceTextEditor) {
            view = new AttributeInstanceViewTextEditor(model, obj);
        } else {
            view = null;
            throw new Error("unkonwn attribute type");
        }
        /*
         // these have different constructors... FIXME
         } else if (model instanceof AttributeInstanceWavefile) {
         return new AttributeInstanceWavefile((AttributeInstanceWavefile)model, obj);
         }
         */
        model.getController().addView(view);
        return view;
    }
}
