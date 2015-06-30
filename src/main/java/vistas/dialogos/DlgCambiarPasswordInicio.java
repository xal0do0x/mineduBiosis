/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.dialogos;

import controladores.Controlador;
import controladores.UsuarioControlador;
import entidades.Usuario;
import com.personal.utiles.FormularioUtil;
import javax.swing.JOptionPane;
import utiles.Encriptador;
import utiles.UsuarioActivo;

/**
 *
 * @author RyuujiMD
 */
    /**
     * Creates new form DlgCambiarPassword
     */
public class DlgCambiarPasswordInicio extends javax.swing.JDialog {

    private final UsuarioControlador uc = new UsuarioControlador();
    private final Usuario usuario;
    public DlgCambiarPasswordInicio(java.awt.Frame parent,Usuario usuario, boolean modal) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(parent);
        this.setAlwaysOnTop(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtNuevoPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtPassActual = new javax.swing.JPasswordField();
        txtRepNuevoPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cambiar contraseña");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Escriba la nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Repita la nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        txtNuevoPass.setColumns(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtNuevoPass, gridBagConstraints);

        jLabel3.setText("Escriba su actual contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        txtPassActual.setColumns(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtPassActual, gridBagConstraints);

        txtRepNuevoPass.setColumns(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtRepNuevoPass, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(hayErrores()){
            return;
        }
        String passActual = new String(txtPassActual.getPassword()).trim();
        String nuevoPass = new String(txtNuevoPass.getPassword()).trim();
        String repNuevoPass = new String(txtRepNuevoPass.getPassword()).trim();
        
        if(!(passActual.isEmpty() || nuevoPass.isEmpty() || repNuevoPass.isEmpty()) && nuevoPass.equals(repNuevoPass) && Encriptador.encrypt(passActual).equals(this.usuario.getPassword())){
            this.usuario.setPassword(Encriptador.encrypt(nuevoPass));
            this.usuario.setCambiarPassword(false);
            if(uc.modificar(usuario)){
                
                FormularioUtil.mensajeExito(this, Controlador.MODIFICAR);
                this.dispose();
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Verifique que no ha escrito espacios en blanco y que se haya escrito las dos contraseñas igual", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtNuevoPass;
    private javax.swing.JPasswordField txtPassActual;
    private javax.swing.JPasswordField txtRepNuevoPass;
    // End of variables declaration//GEN-END:variables

    private boolean hayErrores() {
        int errores = 0;
        String mensaje = "Se ha(n) encontrado el(los) siguiente(s) error(es):\n";
        
        
        String actPwd = new String(txtPassActual.getPassword());
        String newPwd = new String(txtNuevoPass.getPassword());
        String repNewPwd = new String(txtRepNuevoPass.getPassword());
        
        Usuario up = uc.login(usuario.getLogin(), actPwd);        
        
        if(newPwd.length() < 8 || repNewPwd.length() < 8){
            mensaje += "> El password debe tener como mínimo 8 caracteres\n";
            errores++;
        }
        System.out.println("ACT PWD "+actPwd);
        System.out.println("NEW PWD "+newPwd);
        System.out.println("REP PWD "+repNewPwd);
        if(!newPwd.equals(repNewPwd)){
            mensaje += "> No ha repetido correctamente la contraseña\n";
            errores++;
        }
        if(up == null || actPwd.isEmpty()){
            mensaje += "> Escriba correctamente su contraseña actual\n";
            errores++;
        }
        if(errores > 0){
            JOptionPane.showMessageDialog(this, mensaje, "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        
        return errores != 0;
    }
}
