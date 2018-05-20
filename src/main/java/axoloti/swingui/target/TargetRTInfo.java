package axoloti.swingui.target;

import axoloti.mvc.IView;
import axoloti.target.TargetModel;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author jtaelman
 */
public class TargetRTInfo extends javax.swing.JPanel implements IView<TargetModel> {

    final TargetModel targetModel;

    /**
     * Creates new form TargetRTInfo
     */
    public TargetRTInfo(TargetModel targetModel) {
        this.targetModel = targetModel;
        initComponents();
        jLabelVuIn1.setSize(jLabelVuIn1.getPreferredSize());
        jLabelVuIn2.setSize(jLabelVuIn2.getPreferredSize());
        jLabelVuOut1.setSize(jLabelVuOut1.getPreferredSize());
        jLabelVuOut2.setSize(jLabelVuOut2.getPreferredSize());
        jLabelUnderruns.setSize(jLabelUnderruns.getPreferredSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelVuIn1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelVuIn2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelVuOut1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelVuOut2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelUnderruns = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(186, 80));
        setPreferredSize(new java.awt.Dimension(200, 100));
        setSize(new java.awt.Dimension(200, 100));
        setLayout(new java.awt.GridLayout(5, 2));

        jLabel1.setText("in L");
        add(jLabel1);

        jLabelVuIn1.setText("0           ");
        add(jLabelVuIn1);

        jLabel2.setText("in R");
        add(jLabel2);

        jLabelVuIn2.setText("0");
        add(jLabelVuIn2);

        jLabel3.setText("out L");
        add(jLabel3);

        jLabelVuOut1.setText("0");
        add(jLabelVuOut1);

        jLabel4.setText("out R");
        add(jLabel4);

        jLabelVuOut2.setText("0");
        add(jLabelVuOut2);

        jLabel5.setText("underruns ");
        add(jLabel5);

        jLabelUnderruns.setText("0");
        add(jLabelUnderruns);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if (TargetModel.RTINFO.is(evt)) {
            axoloti.target.TargetRTInfo rtinfo = (axoloti.target.TargetRTInfo) evt.getNewValue();
            if (rtinfo != null) {
                double r = 10 * Math.log10(Math.pow(2.0, 73));
                double inLevel1dB = 10 * Math.log10(Math.abs(rtinfo.inLevel1) + 1) - r;
                double inLevel2dB = 10 * Math.log10(Math.abs(rtinfo.inLevel2) + 1) - r;
                double outLevel1dB = 10 * Math.log10(Math.abs(rtinfo.outLevel1) + 1) - r;
                double outLevel2dB = 10 * Math.log10(Math.abs(rtinfo.outLevel2) + 1) - r;
                jLabelVuIn1.setText(String.format("%3.1f", inLevel1dB));
                jLabelVuIn2.setText(String.format("%3.1f", inLevel2dB));
                int mininf = -130;
                jLabelVuOut1.setText(outLevel1dB > mininf ? String.format("%3.1f", outLevel1dB) : "-inf");
                jLabelVuOut2.setText(outLevel2dB > mininf ? String.format("%3.1f", outLevel2dB) : "-inf");
                jLabelUnderruns.setText(Integer.toString(rtinfo.underruns));
            } else {
                jLabelVuIn1.setText("");
                jLabelVuIn2.setText("");
                jLabelVuOut1.setText("");
                jLabelVuOut2.setText("");
                jLabelUnderruns.setText("");
            }
        }
    }

    @Override
    public TargetModel getDModel() {
        return targetModel;
    }

    @Override
    public void dispose() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelUnderruns;
    private javax.swing.JLabel jLabelVuIn1;
    private javax.swing.JLabel jLabelVuIn2;
    private javax.swing.JLabel jLabelVuOut1;
    private javax.swing.JLabel jLabelVuOut2;
    // End of variables declaration//GEN-END:variables
}
