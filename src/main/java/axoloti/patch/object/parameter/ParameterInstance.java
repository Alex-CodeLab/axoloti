/**
 * Copyright (C) 2013, 2014, 2015 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package axoloti.patch.object.parameter;

import axoloti.Modulation;
import axoloti.mvc.AbstractController;
import axoloti.object.AxoObjectPatcher;
import axoloti.object.atom.AtomDefinition;
import axoloti.object.atom.AtomDefinitionController;
import axoloti.object.parameter.Parameter;
import axoloti.patch.PatchModel;
import axoloti.patch.object.AxoObjectInstance;
import axoloti.patch.object.AxoObjectInstancePatcher;
import axoloti.patch.object.atom.AtomInstance;
import axoloti.patch.object.parameter.preset.Preset;
import axoloti.property.BooleanProperty;
import axoloti.property.MidiCCProperty;
import axoloti.property.ObjectProperty;
import axoloti.property.PropagatedProperty;
import axoloti.property.Property;
import axoloti.realunits.NativeToReal;
import axoloti.utils.CharEscape;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 *
 * @author Johannes Taelman
 */
@Root(name = "param")
public abstract class ParameterInstance<T extends Parameter, DT> extends AtomInstance<T> {

    @Attribute
    public String name;
    @Attribute(required = false)
    private Boolean onParent = false;
    @Attribute(required = false)
    private Integer MidiCC = null;

    public T parameter; // TODO: make private, create ParameterInstanceFactory

    public NativeToReal convs[];
    NativeToReal conversion;
//    int selectedConv = 0;

    // TODO: create ParameterController class
    // replace AtomDefinitionController with ParameterController
    AtomDefinitionController controller;

    public ParameterInstance() {
    }

    public ParameterInstance(T param, AxoObjectInstance axoObjInstance) {
        super();
        parameter = param;
        setParent(axoObjInstance);
        name = parameter.getName();
    }

    public final static Property ON_PARENT = new BooleanProperty("OnParent", ParameterInstance.class, "Parameter on parent");
    public final static Property MIDI_CC = new MidiCCProperty("MidiCC", ParameterInstance.class, "Midi Continuous controller");
    public final static Property PRESETS = new ObjectProperty("Presets", Object.class, ParameterInstance.class);
    public final static Property VALUE = new ObjectProperty("Value", Object.class, ParameterInstance.class);
    public final static Property CONVERSION = new ObjectProperty("Conversion", NativeToReal.class, ParameterInstance.class);
    public final static PropagatedProperty NOLABEL = new PropagatedProperty(Parameter.NOLABEL, ParameterInstance.class);

//    public static final String ELEMENT_PARAM_VALUE = "Value";
//    public static final String ELEMENT_PARAM_PRESETS = "Presets";
//    public static final String ELEMENT_PARAM_PARAM_ON_PARENT = "ParamOnParent";
    @Override
    public List<Property> getProperties() {
        List<Property> l = super.getProperties();
        l.add(ON_PARENT);
        l.add(MIDI_CC);
        l.add(VALUE);
        l.add(NOLABEL);
        return l;
    }

    public List<Property> getEditableFields() {
        ArrayList<Property> l = new ArrayList<>();
        l.add(ON_PARENT);
        return l;
    }

    public String GetCName() {
        return parameter.GetCName();
    }

    public void CopyValueFrom(ParameterInstance p) {
        if (p.onParent != null) {
            setOnParent(p.onParent);
        }
        // TODO: review: preset data type might be incompatible...
        setPresets(p.getPresets());
        setMidiCC(p.MidiCC);
    }

    public Preset getPreset(int i) {
        if (getPresets() == null) {
            return null;
        }
        for (Object o : getPresets()) {
            Preset p = (Preset) o;
            if (p.index == i) {
                return p;
            }
        }
        return null;
    }

    public abstract DT getValue();

    public void setValue(Object value) {
//        firePropertyChange(ELEMENT_PARAM_VALUE, null, value);
    }

//    public void SetValueRaw(int v) {
//        // FIXME, different types possible
//        ValueFrac32 v1 = new ValueFrac32();
//        v1.setRaw(v);
//        setValue(v1);
//    }
//    public int GetValueRaw() {
//        return getValue().getRaw();
//    }

    public String ControlOnParentName() {
        if (getParent().getParameterInstances().size() == 1) {
            return getParent().getInstanceName();
        } else {
            return getParent().getInstanceName() + ":" + parameter.getName();
        }
    }


    public String GetPFunction() {
        return "0";
    }

    // review!
    public String GetUserParameterName() {
        if (getParent().getParameterInstances().size() == 1) {
            return getParent().getInstanceName();
        } else {
            return getName();
        }
    }

    public abstract int valToInt32(DT o);

    public abstract DT int32ToVal(int v);

    public String GetCMultiplier() {
        return "0";
    }

    public String GetCOffset() {
        return "0";
    }

    public String getLegalName() {
        return CharEscape.CharEscape(getModel().getName());
    }

    public T createParameterForParent() {
        T pcopy = (T) parameter.getClone();
        pcopy.setName(ControlOnParentName());
        pcopy.noLabel = null;
        pcopy.PropagateToChild = getParent().getLegalName() + "_" + getLegalName();
        return pcopy;
    }

    // TODO: obsolete: replace with getParent()
    public AxoObjectInstance getObjectInstance() {
        return (AxoObjectInstance) getParent();
    }

    @Override
    public T getModel() {
        return (T) getController().getModel();
    }

    public String GenerateCodeInitModulator(String vprefix, String StructAccces) {
        return "";
    }

    public ArrayList<Modulation> getModulators() {
        return null;
    }

    public NativeToReal[] getConvs() {
        return convs;
    }

    public NativeToReal getConversion() {
        if ((conversion == null) && (convs != null) && (convs.length != 0)) {
            conversion = convs[0];
        }
        return conversion;
    }

    public void setConversion(NativeToReal conversion) {
        this.conversion = conversion;
        firePropertyChange(CONVERSION, null, conversion);
    }

    public void cycleConversions() {
        if ((convs == null) || (convs.length == 0)) {
            return;
        }
        if (conversion == null) {
            setConversion(convs[0]);
        }
        List l = java.util.Arrays.asList(convs);
        int i = l.indexOf(conversion);
        i++;
        if (i >= convs.length) {
            i = 0;
        }
        setConversion(convs[i]);
    }

    @Override
    public AtomDefinitionController getController() {
        return controller;
    }

    public void setController(AtomDefinitionController controller) {
        this.controller = controller;
    }

    /* MVC getters and setters */

    /* View personality */
    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        super.modelPropertyChange(evt);
        // triggered by a model definition change, triggering instance view changes
        if (Parameter.NOLABEL.is(evt)) {
            firePropertyChange(ParameterInstance.NOLABEL, evt.getOldValue(), evt.getNewValue());
        } else if (Parameter.NAME.is(evt)) {
            name = (String) evt.getNewValue();
            updateParamOnParent();
        }
    }

    /* Model personality */
    public Integer getMidiCC() {
        if (MidiCC == null) {
            return -1;
        } else {
            return MidiCC;
        }
    }

    public void setMidiCC(Integer MidiCC) {
        Integer prevValue = this.MidiCC;
        if ((MidiCC != null) && (MidiCC >= 0)) {
            this.MidiCC = MidiCC;
        } else {
            this.MidiCC = null;
        }
        this.MidiCC = MidiCC;
        firePropertyChange(MIDI_CC, prevValue, MidiCC);
    }

    public Boolean getOnParent() {
        if (onParent == null) {
            return false;
        } else {
            return onParent;
        }
    }

    T paramOnParent;

    public abstract Preset presetFactory(int index, DT value);

    public void updateParamOnParent() {
        if (paramOnParent != null) {
            paramOnParent.setName(ControlOnParentName());
            paramOnParent.noLabel = null;
            paramOnParent.PropagateToChild = getParent().getLegalName() + "_" + getLegalName();
        }
    }

    public void setOnParent(Boolean onParent) {
        if (onParent == null) {
            onParent = false;
        }
        if (getOnParent() == (boolean) onParent) {
            return;
        }
        if (!onParent) {
            onParent = null;
        }
        Boolean oldValue = this.onParent;
        if (onParent != null && onParent) {
            if (paramOnParent == null) {
                paramOnParent = createParameterForParent();
            }
        }
        this.onParent = onParent;

        PatchModel pm = getObjectInstance().getParent();
        if (pm != null) {
            AxoObjectInstancePatcher aoip = pm.getParent();
            if (aoip != null) {
                AxoObjectPatcher aop = (AxoObjectPatcher) aoip.getController().getModel();
                if (onParent != null && onParent) {
                    paramOnParent.setParent(aop);
                    aop.getControllerFromModel().addParameter(paramOnParent);
                } else {
                    aop.getControllerFromModel().removeParameter(paramOnParent);
                }
            }
        }

        firePropertyChange(
                ParameterInstance.ON_PARENT,
                oldValue, onParent);
    }

    public abstract ArrayList getPresets();

    public abstract void setPresets(Object o);
    /*
     public void setPresets(ArrayList<Preset> presets) {
     ArrayList<Preset> prevValue = getPresets();
     this.presets = presets;
     firePropertyChange(ParameterInstance.ELEMENT_PARAM_PRESETS, prevValue, this.presets);
     }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String prevValue = this.name;
        this.name = name;
        firePropertyChange(AtomDefinition.NAME, prevValue, name);
    }

    @Override
    public void dispose() {
        super.dispose();
        getControllerFromModel().changeOnParent(false);
    }

    @Override
    public AbstractController createController() {
        return new ParameterInstanceController(this);
    }

    @Override
    public ParameterInstanceController getControllerFromModel() {
        return (ParameterInstanceController) super.getControllerFromModel();
    }

}
