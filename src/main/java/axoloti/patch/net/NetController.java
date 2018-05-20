package axoloti.patch.net;

import axoloti.abstractui.INetView;
import axoloti.mvc.AbstractController;
import axoloti.patch.PatchModel;
import axoloti.patch.object.IAxoObjectInstance;
import axoloti.patch.object.inlet.InletInstance;
import axoloti.patch.object.outlet.OutletInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jtaelman
 */
public class NetController extends AbstractController<Net, INetView> {

    protected NetController(Net model) {
        super(model);
        if (model.getParent() != null) {
            PatchModel patchModel = model.getParent();
            ArrayList<OutletInstance> source2 = new ArrayList<>();
            for (OutletInstance i : model.getSources()) {
                String objname = i.getObjname();
                String outletname = i.getName();

                IAxoObjectInstance o = patchModel.findObjectInstance(objname);
                if (o == null) {
                    Logger.getLogger(NetController.class.getName()).log(Level.SEVERE, "could not resolve net source obj : {0}::{1}", new Object[]{i.getObjname(), i.getName()});
                    patchModel.nets.remove(model);
                    return;
                }
                OutletInstance r = o.findOutletInstance(outletname);
                if (r == null) {
                    Logger.getLogger(NetController.class.getName()).log(Level.SEVERE, "could not resolve net source outlet : {0}::{1}", new Object[]{i.getObjname(), i.getName()});
                    patchModel.nets.remove(model);
                    return;
                }
                source2.add(r);
                r.getController().changeConnected(true);
            }
            ArrayList<InletInstance> dest2 = new ArrayList<>();
            for (InletInstance i : model.getDestinations()) {
                String objname = i.getObjname();
                String inletname = i.getName();
                IAxoObjectInstance o = patchModel.findObjectInstance(objname);
                if (o == null) {
                    Logger.getLogger(NetController.class.getName()).log(Level.SEVERE, "could not resolve net dest obj :{0}::{1}", new Object[]{i.getObjname(), i.getName()});
                    patchModel.nets.remove(model);
                    return;
                }
                InletInstance r = o.findInletInstance(inletname);
                if (r == null) {
                    Logger.getLogger(NetController.class.getName()).log(Level.SEVERE, "could not resolve net dest inlet :{0}::{1}", new Object[]{i.getObjname(), i.getName()});
                    patchModel.nets.remove(model);
                    return;
                }
                dest2.add(r);
                r.getController().changeConnected(true);
            }
            model.setSources(source2.toArray(new OutletInstance[]{}));
            model.setDestinations(dest2.toArray(new InletInstance[]{}));
        }
    }

    /**
     * connects inlet to this Net
     *
     * @param inlet
     */
    public void connectInlet(InletInstance inlet) {
        inlet.getController().changeConnected(true);

        ArrayList<InletInstance> n = new ArrayList<>(Arrays.asList(getModel().getDestinations()));
        n.add(inlet);
        setModelUndoableProperty(Net.NET_DESTINATIONS, n.toArray(new InletInstance[]{}));
    }

    /**
     * connects outlet to this Net
     *
     * @param outlet
     */
    public void connectOutlet(OutletInstance outlet) {
        outlet.getController().changeConnected(true);

        ArrayList<OutletInstance> n = new ArrayList<>(Arrays.asList(getModel().getSources()));
        n.add(outlet);
        setModelUndoableProperty(Net.NET_SOURCES, n.toArray(new OutletInstance[]{}));
    }

    /**
     * Use @see axoloti.patch.PatchController#disconnect(OutletInstance)
     * instead!
     *
     * @param outlet
     */
    public void disconnect(OutletInstance outlet) {
        outlet.getController().changeConnected(false);

        List<OutletInstance> n = new LinkedList<>(Arrays.asList(getModel().getSources()));
        n.remove(outlet);
        setModelUndoableProperty(Net.NET_SOURCES, n.toArray(new OutletInstance[]{}));
        // TODO: migrate NET_SOURCES to ListProperty, so this reduces to:
        // removeUndoableElementFromList(Net.NET_SOURCES, outlet);
    }

    /**
     * Use @see axoloti.patch.PatchController#disconnect(InletInstance) instead!
     *
     * @param inlet
     */
    public void disconnect(InletInstance inlet) {
        inlet.getController().changeConnected(false);

        List<InletInstance> n = new LinkedList<>(Arrays.asList(getModel().getDestinations()));
        n.remove(inlet);
        setModelUndoableProperty(Net.NET_DESTINATIONS, n.toArray(new InletInstance[]{}));
        // TODO: migrate NET_DESTINATIONS to ListProperty, so this reduces to:
        // removeUndoableElementFromList(Net.NET_DESTINATIONS, outlet);
    }


}
