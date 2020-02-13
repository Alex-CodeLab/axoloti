package axoloti.swingui.components;

import axoloti.abstractui.PatchView;
import axoloti.datatypes.Bool32;
import axoloti.datatypes.CharPtr32;
import axoloti.datatypes.Frac32;
import axoloti.datatypes.Frac32buffer;
import axoloti.datatypes.Int32;
import axoloti.datatypes.Int32Ptr;
import axoloti.preferences.Theme;

public class VisibleCablePanel extends javax.swing.JPanel {

    private final PatchView p;

    public VisibleCablePanel(PatchView p) {
        this.p = p;
        initComponents();

        jCheckBox1.setBackground(Theme.getCurrentTheme().Cable_Frac32Buffer);
        jCheckBox1.setOpaque(true);
        jCheckBox1.setSelected(true);
        jCheckBox1.setRolloverEnabled(false);

        jCheckBox2.setBackground(Theme.getCurrentTheme().Cable_Frac32);
        jCheckBox2.setOpaque(true);
        jCheckBox2.setSelected(true);
        jCheckBox2.setRolloverEnabled(false);

        jCheckBox4.setBackground(Theme.getCurrentTheme().Cable_Int32Pointer);
        jCheckBox4.setOpaque(true);
        jCheckBox4.setSelected(true);
        jCheckBox4.setRolloverEnabled(false);

        jCheckBox5.setBackground(Theme.getCurrentTheme().Cable_CharPointer32);
        jCheckBox5.setOpaque(true);
        jCheckBox5.setSelected(true);
        jCheckBox5.setRolloverEnabled(false);

        jCheckBox6.setBackground(Theme.getCurrentTheme().Cable_Bool32);
        jCheckBox6.setOpaque(true);
        jCheckBox6.setSelected(true);
        jCheckBox6.setRolloverEnabled(false);

        jCheckBox7.setBackground(Theme.getCurrentTheme().Cable_Int32);
        jCheckBox7.setOpaque(true);
        jCheckBox7.setSelected(true);
        jCheckBox7.setRolloverEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();

        setAlignmentX(0.0F);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel3.setText("Visible Cables");
        jLabel3.setAlignmentX(0.5F);
        jLabel3.setMaximumSize(null);
        jLabel3.setMinimumSize(null);
        jLabel3.setPreferredSize(null);
        add(jLabel3);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1);

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox2);

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox6);

        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox7);

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox4);

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox5);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        p.setCableTypeEnabled(Frac32buffer.d, jCheckBox1.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        p.setCableTypeEnabled(Frac32.d, jCheckBox2.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        p.setCableTypeEnabled(Bool32.d, jCheckBox6.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        p.setCableTypeEnabled(Int32.d, jCheckBox7.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        p.setCableTypeEnabled(Int32Ptr.d, jCheckBox4.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        p.setCableTypeEnabled(CharPtr32.d, jCheckBox5.isSelected());
        p.updateNetVisibility();
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}