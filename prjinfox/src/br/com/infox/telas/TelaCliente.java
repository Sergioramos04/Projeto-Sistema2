package br.com.infox.telas;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import ConexaoMysql.Conexao;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public  class TelaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCliNome;
	private JTextField txtCliEndereco;
	private JTextField txtCliFone;
	private JTextField txtCliEmail;
	private JTextField txtCliPesquisar;
	private JTable tblClientes;
	private JTable tabDados;
	private JTextField tfBuscar;
	private JTextField txtCliId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(90, 199, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(61, 224, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setBounds(61, 249, 75, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(90, 274, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCliNome = new JTextField();
		txtCliNome.setBounds(147, 196, 290, 20);
		contentPane.add(txtCliNome);
		txtCliNome.setColumns(10);
		
		txtCliEndereco = new JTextField();
		txtCliEndereco.setBounds(146, 221, 315, 20);
		contentPane.add(txtCliEndereco);
		txtCliEndereco.setColumns(10);
		
		txtCliFone = new JTextField();
		txtCliFone.setBounds(146, 246, 187, 20);
		contentPane.add(txtCliFone);
		txtCliFone.setColumns(10);
		
		txtCliEmail = new JTextField();
		txtCliEmail.setBounds(146, 271, 242, 20);
		contentPane.add(txtCliEmail);
		txtCliEmail.setColumns(10);
		
		JButton btnAdiconar = new JButton("Criar");
		btnAdiconar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into tbclientes(nomecli, endcli, fonecli, emailcli) value (?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtCliNome.getText());
					stmt.setString(2, txtCliEndereco.getText());
					stmt.setString(3, txtCliFone.getText());
					stmt.setString(4, txtCliEmail.getText());
					
					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
					
					txtCliNome.setText("");
					txtCliEndereco.setText("");
					txtCliFone.setText("");
					txtCliEmail.setText("");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
			}
			
		
				
				
			
			
			
		});
		btnAdiconar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdiconar.setToolTipText("Adicionar");
		btnAdiconar.setBounds(90, 315, 89, 23);
		contentPane.add(btnAdiconar);
		
		JButton btnRemover = new JButton("Excluir");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse usuário","Atenção",JOptionPane.YES_NO_OPTION);
				if (confirma==JOptionPane.YES_NO_OPTION) {
					
				}
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql ="delete from tbclientes where idcli=?";
							
					PreparedStatement stmt = con.prepareStatement(sql);		
					
					stmt.setString(1, txtCliId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Cliente excluido!");
					
					txtCliNome.setText("");
					txtCliEndereco.setText("");
					txtCliFone.setText("");
					txtCliEmail.setText("");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRemover.setToolTipText("Remover");
		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.setBounds(386, 315, 89, 23);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 28, 462, 115);
		contentPane.add(scrollPane);
		
		tabDados = new JTable();
		tabDados.addMouseListener(new MouseAdapter() {
			
			
			
			
			
			
		});
		tabDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "nome", "endere\u00E7o", "telefone", "email"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tabDados);
		
		JButton btnNewButton = new JButton("Listar Dados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select  *from tbclientes";

					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					
					DefaultTableModel modelo = (DefaultTableModel) tabDados.getModel();
					
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[] {rs.getString("idcli"), rs.getString("nomecli"), rs.getString("endcli"), rs.getString("fonecli"), rs.getString("emailcli")});
						
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
				
			
			
		});
		btnNewButton.setBounds(386, 154, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o NOME");
				}else {
					
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select *from tbclientes where nomecli like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%"+tfBuscar.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						txtCliId.setText(rs.getString("idcli"));
						txtCliNome.setText(rs.getString("nomecli"));
						txtCliEndereco.setText(rs.getString("endcli"));
						txtCliFone.setText(rs.getString("fonecli"));
						txtCliEmail.setText(rs.getString("emailcli"));
						
						
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
			}
		});
		btnAbrir.setBounds(152, 386, 89, 23);
		contentPane.add(btnAbrir);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(266, 387, 229, 20);
		contentPane.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Id Cliente");
		lblNewLabel_4.setBounds(61, 168, 75, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCliId = new JTextField();
		txtCliId.setEnabled(false);
		txtCliId.setBounds(152, 165, 86, 20);
		contentPane.add(txtCliId);
		txtCliId.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtCliId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Nome");
				}else {
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtCliNome.getText());
					stmt.setString(2, txtCliEndereco.getText());
					stmt.setString(3, txtCliFone.getText());
					stmt.setString(4, txtCliEmail.getText());
					stmt.setString(5, txtCliId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				
			}
		});
		btnAtualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtualizar.setToolTipText("Alterar");
		btnAtualizar.setBounds(244, 315, 89, 23);
		contentPane.add(btnAtualizar);
	}
}
