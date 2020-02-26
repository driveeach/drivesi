package interfaceGrafica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TipoDeBusca extends javax.swing.JFrame {

    
    private static TipoDeBusca singleton;
    
    public static TipoDeBusca getInstance(){
        if (singleton == null) {
            singleton = new TipoDeBusca(busca);
        }
        return singleton;
    }
    
    public TipoDeBusca(String busca) {
        TipoDeBusca.busca = busca;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoxEscolha = new javax.swing.JComboBox();
        confirma = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        Busca_Palavra = new javax.swing.JMenu();
        BuscarPalavra = new javax.swing.JMenuItem();
        Editar = new javax.swing.JMenu();
        TipoDeBuscaMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Sair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscador de Palavras");

        BoxEscolha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione um tipo de busca", "Sequencial", "Índice" }));
        BoxEscolha.setToolTipText("");

        confirma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        confirma.setText("Confirma");
        confirma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                confirmaMousePressed(evt);
            }
        });
        confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(BoxEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(BoxEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void confirmaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmaMousePressed
        // TODO add your handling code here:
        int opcao = BoxEscolha.getSelectedIndex();
        boolean para = false;
        while (!para) {
            if (opcao == 1 || opcao == 2) {
                para = true;
                try {
                    COO.Fachada.iniciaBusca(busca, opcao);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TipoDeBusca.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TipoDeBusca.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TipoDeBusca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            new ArquivosContemPalavra(busca).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_confirmaMousePressed

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

    private void confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmaActionPerformed

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
            java.util.logging.Logger.getLogger(TipoDeBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipoDeBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipoDeBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipoDeBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TipoDeBusca(busca).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox BoxEscolha;
    private javax.swing.JMenu Busca_Palavra;
    private javax.swing.JMenuItem BuscarPalavra;
    private javax.swing.JMenu Editar;
    private javax.swing.JMenu Sair;
    private javax.swing.JMenuItem TipoDeBuscaMenu;
    private javax.swing.JButton confirma;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    private static String busca;
}
