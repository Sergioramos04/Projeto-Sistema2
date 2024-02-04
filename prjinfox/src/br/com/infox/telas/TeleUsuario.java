package br.com.infox.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexaoMysql.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TeleUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JTextField txtUsuFone;
	private JTextField txtUsuLogin;
	private JTextField txtUsuSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeleUsuario frame = new TeleUsuario();
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
	public TeleUsuario() {
		setTitle("Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(41, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(41, 71, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(41, 171, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fone");
		lblNewLabel_3.setBounds(41, 119, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(41, 230, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Perfil");
		lblNewLabel_5.setBounds(41, 290, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		txtUsuId = new JTextField();
		txtUsuId.setBounds(78, 24, 86, 20);
		contentPane.add(txtUsuId);
		txtUsuId.setColumns(10);
		
		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(78, 68, 226, 20);
		contentPane.add(txtUsuNome);
		txtUsuNome.setColumns(10);
		
		txtUsuFone = new JTextField();
		txtUsuFone.setBounds(78, 116, 226, 20);
		contentPane.add(txtUsuFone);
		txtUsuFone.setColumns(10);
		
		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(79, 168, 225, 20);
		contentPane.add(txtUsuLogin);
		txtUsuLogin.setColumns(10);
		
		txtUsuSenha = new JTextField();
		txtUsuSenha.setBounds(80, 227, 162, 20);
		contentPane.add(txtUsuSenha);
		txtUsuSenha.setColumns(10);
		
		JComboBox cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		cboUsuPerfil.setBounds(78, 286, 134, 22);
		contentPane.add(cboUsuPerfil);
		
		JButton btnNewButton = new JButton("Criar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into tbusuarios(iduser, usuario, fone, login, senha, perfil) value (?, ?, ?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtUsuId.getText());
					stmt.setString(2, txtUsuNome.getText());
					stmt.setString(3, txtUsuFone.getText());
					stmt.setString(4, txtUsuLogin.getText());
					stmt.setString(5, txtUsuSenha.getText());
					stmt.setString(6, (String) cboUsuPerfil.getSelectedItem());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
					
					txtUsuId.setText("");
					txtUsuNome.setText("");
					txtUsuFone.setText("");
					txtUsuLogin.setText("");
					txtUsuSenha.setText("");
					cboUsuPerfil.setSelectedItem("");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Adicionar");
		btnNewButton.setBounds(65, 367, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsuId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}else {
					
					
					
					
				
					
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select *from tbusuarios where iduser=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtUsuId.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						
						txtUsuNome.setText(rs.getString("usuario"));
						txtUsuFone.setText(rs.getString("fone"));
						txtUsuLogin.setText(rs.getString("login"));
						txtUsuSenha.setText(rs.getString("senha"));
						cboUsuPerfil.setSelectedItem(rs.getNString("perfil"));
						
						
						
					
						
					
						
					}
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
					
					txtUsuId.setText("");
					txtUsuNome.setText("");
					txtUsuFone.setText("");
					txtUsuLogin.setText("");
					txtUsuSenha.setText("");
					cboUsuPerfil.setSelectedItem("");
					
					
					
					
				
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
				
				
			}
		});
		btnNewButton_1.setToolTipText("Consultar");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(193, 367, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtUsuId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}else {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "update tbusuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtUsuNome.getText());
					stmt.setString(2, txtUsuFone.getText());
					stmt.setString(3, txtUsuLogin.getText());
					stmt.setString(4, txtUsuSenha.getText());
					stmt.setString(5, (String) cboUsuPerfil.getSelectedItem());
					stmt.setString(6, txtUsuId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
					
					txtUsuId.setText("");
					txtUsuNome.setText("");
					txtUsuFone.setText("");
					txtUsuLogin.setText("");
					txtUsuSenha.setText("");
					cboUsuPerfil.setSelectedItem("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
		});
		btnNewButton_2.setToolTipText("Alterar");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(321, 367, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse usuário","Atenção",JOptionPane.YES_NO_OPTION);
				if (confirma==JOptionPane.YES_NO_OPTION) {
					
				}
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql ="delete from tbusuarios where iduser=?";
							
					PreparedStatement stmt = con.prepareStatement(sql);		
					
					stmt.setString(1, txtUsuId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Usuário excluido!");
					
					txtUsuId.setText("");
					txtUsuNome.setText("");
					txtUsuFone.setText("");
					txtUsuLogin.setText("");
					txtUsuSenha.setText("");
					cboUsuPerfil.setSelectedItem("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setToolTipText("Remover");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBounds(441, 367, 89, 23);
		contentPane.add(btnNewButton_3);
	}
}
