/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import COO.ArquivoConf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Fernanda
 */
public class ArquivosContemPalavra extends javax.swing.JFrame {

    /**
     * Creates new form ArquivosContemPalavra
     */
    public ArquivosContemPalavra(String busca) {
        ArquivosContemPalavra.busca = busca;
        initComponents();
        textoNao.setVisible(false);
        instrucao.setVisible(false);
        arquivos.setVisible(false);
        confirmar.setVisible(false);
        boolean temPalavra = false;
        for (int i = 0; i < ArquivoConf.Arquivos.size(); i++) {
            if (ArquivoConf.Arquivos.get(i).contem == true) {
                String aux = ArquivoConf.Arquivos.get(i).nome + " ("+ArquivoConf.Arquivos.get(i).quant+")";
                arquivos.addItem(aux);
                temPalavra = true;
            }
        }
        if (temPalavra == false) {
            textoNao.setVisible(true);
            instrucao.setVisible(true);
            sim.setVisible(false);
            nao.setVisible(false);
            pergunta.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        arquivos = new javax.swing.JComboBox();
        pergunta = new javax.swing.JLabel();
        sim = new javax.swing.JButton();
        nao = new javax.swing.JButton();
        confirmar = new javax.swing.JButton();
        textoNao = new javax.swing.JLabel();
        instrucao = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Busca_Palavra = new javax.swing.JMenu();
        BuscarPalavra = new javax.swing.JMenuItem();
        Editar = new javax.swing.JMenu();
        TipoDeBuscaMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Sair = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        arquivos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arquivos que contém a palavra" }));
        arquivos.setToolTipText("");
        arquivos.setInheritsPopupMenu(true);

        pergunta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pergunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pergunta.setText("Deseja visualizar algum arquivo?");

        sim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sim.setText("Sim");
        sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                simMousePressed(evt);
            }
        });

        nao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nao.setText("Não");
        nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                naoMousePressed(evt);
            }
        });

        confirmar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        confirmar.setText("Confirmar");
        confirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                confirmarMousePressed(evt);
            }
        });

        textoNao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textoNao.setForeground(new java.awt.Color(255, 0, 0));
        textoNao.setText("Palavra não encontrada!");
        textoNao.setFocusable(false);

        instrucao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        instrucao.setText("Vá em Arquivo -> Buscar Palavra para uma nova busca");
        instrucao.setFocusable(false);

        Busca_Palavra.setText("Arquivo");

        BuscarPalavra.setText("Buscar Palavra");
        BuscarPalavra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BuscarPalavraMousePressed(evt);
            }
        });
        Busca_Palavra.add(BuscarPalavra);

        jMenuBar1.add(Busca_Palavra);

        Editar.setText("Editar");

        TipoDeBuscaMenu.setText("Tipo de Busca");
        TipoDeBuscaMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TipoDeBuscaMenuMousePressed(evt);
            }
        });
        Editar.add(TipoDeBuscaMenu);

        jMenuItem1.setText("Visualizar outro arquivo");
        jMenuItem1.setEnabled(false);
        Editar.add(jMenuItem1);

        jMenuBar1.add(Editar);

        Sair.setText("Sair");
        Sair.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SairMousePressed(evt);
            }
        });
        jMenuBar1.add(Sair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(sim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nao)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(confirmar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(pergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(textoNao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arquivos, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instrucao))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(pergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sim)
                    .addComponent(nao))
                .addGap(18, 18, 18)
                .addComponent(textoNao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(instrucao)
                .addGap(15, 15, 15)
                .addComponent(arquivos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmar)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarPalavraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarPalavraMousePressed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaBusca().setVisible(true);
                dispose();
            }
        });
    }//GEN-LAST:event_BuscarPalavraMousePressed

    private void SairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SairMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_SairMousePressed

    private void simMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simMousePressed
        // TODO add your handling code here:
        arquivos.setVisible(true);
        confirmar.setVisible(true);
    }//GEN-LAST:event_simMousePressed

    private void naoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_naoMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_naoMousePressed

    private void confirmarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmarMousePressed
        // TODO add your handling code here:
        int opcao = arquivos.getSelectedIndex();
        String arquivo = (String)arquivos.getSelectedItem();
        boolean para = false;
        while (!para) {
            if (opcao != 0) {
                para = true;
                try {
                    new Ctrl_F(arquivo, busca).setVisible(true);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ArquivosContemPalavra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        dispose();
    }//GEN-LAST:event_confirmarMousePressed

    private void TipoDeBuscaMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipoDeBuscaMenuMousePressed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipoDeBusca(busca).setVisible(true);
                dispose();
            }
        });
    }//GEN-LAST:event_TipoDeBuscaMenuMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArquivosContemPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArquivosContemPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArquivosContemPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArquivosContemPalavra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArquivosContemPalavra(busca).setVisible(true);
            }
        });
         
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Busca_Palavra;
    private javax.swing.JMenuItem BuscarPalavra;
    private javax.swing.JMenu Editar;
    private javax.swing.JMenu Sair;
    private javax.swing.JMenuItem TipoDeBuscaMenu;
    private javax.swing.JComboBox arquivos;
    private javax.swing.JButton confirmar;
    private javax.swing.JLabel instrucao;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JButton nao;
    private javax.swing.JLabel pergunta;
    private javax.swing.JButton sim;
    private javax.swing.JLabel textoNao;
    // End of variables declaration//GEN-END:variables
    private static String busca;
}
