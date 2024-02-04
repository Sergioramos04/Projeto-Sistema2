package br.com.infox.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexaoMysql.Conexao;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setResizable(false);
		setTitle("X-Sistema para Controle de OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 541, 22);
		contentPane.add(menuBar);
		
		JMenu MenCad = new JMenu("Cadastro");
		menuBar.add(MenCad);
		
		JMenuItem MenCadCli = new JMenuItem("Cliente");
		MenCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					TelaCliente cliente = new TelaCliente();
					cliente.setVisible(true);
				
			}
		});
		MenCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.ALT_DOWN_MASK));
		MenCad.add(MenCadCli);
		
		JMenuItem MenCadOs = new JMenuItem("OS");
		MenCadOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOS os = new TelaOS();
				os.setVisible(true);
			}
		});
		MenCadOs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		MenCad.add(MenCadOs);
		
		JMenuItem MenCadUser = new JMenuItem("Usuários");
		MenCadUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeleUsuario usuario = new TeleUsuario();
				usuario.setVisible(true);
				
			}
		});
		MenCadUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		MenCad.add(MenCadUser);
		
		JMenu MenRel = new JMenu("Relatório");
		menuBar.add(MenRel);
		
		JMenuItem MenRelCli = new JMenuItem("Clientes");
		MenRelCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaOS os = new TelaOS();
				os.setVisible(true);
			}
		});
		MenRelCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		MenRel.add(MenRelCli);
		
		
		
		
		JMenuItem MenRelSer = new JMenuItem("Serviços");
		MenRelSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaOS os = new TelaOS();
				os.setVisible(true);
				
			}
		});
		MenRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
		MenRel.add(MenRelSer);
		
		JMenu MenAju = new JMenu("Ajuda");
		menuBar.add(MenAju);
		
		JMenuItem MenAjuSob = new JMenuItem("Sobre");
		MenAjuSob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		MenAjuSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		MenAju.add(MenAjuSob);
		
		JMenu MenOp = new JMenu("Opções");
		menuBar.add(MenOp);
		
		JMenuItem MenOpSair = new JMenuItem("Sair");
		MenOpSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Atenção",JOptionPane.YES_NO_OPTION);
				if(sair ==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		MenOpSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		MenOp.add(MenOpSair);
	}
}
