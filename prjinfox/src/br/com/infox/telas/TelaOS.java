package br.com.infox.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ConexaoMysql.Conexao;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class TelaOS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOs;
	private JTextField txtData;
	private JTable tblClientes;
	private JTextField txtCliId;
	private JTextField txtOsEquip;
	private JTextField txtOsDef;
	private JTextField txtOsServ;
	private JTextField txtOsTec;
	private JTextField txtOsValor;
	private JTextField tfBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOS frame = new TelaOS();
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
	public TelaOS() {
		setTitle("OS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(10, 11, 258, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N:OS");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(128, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(10, 25, 66, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(128, 25, 124, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		
		JRadioButton rbtOrc = new JRadioButton("Orçamento");
		rbtOrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "select  *from tbos";
					
					PreparedStatement stmt = con.prepareStatement(sql);
				String tipo;
				tipo = "Orçamento";
				rbtOrc.setSelected(true);
				
				
				
				stmt.close();
				con.close();
			
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rbtOrc.setBounds(10, 75, 242, 23);
		panel.add(rbtOrc);
		
		JLabel lblNewLabel_2 = new JLabel("Situação");
		lblNewLabel_2.setBounds(0, 145, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox cboOsSit = new JComboBox();
		cboOsSit.setModel(new DefaultComboBoxModel(new String[] {" ", " Na bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retornou"}));
		cboOsSit.setBounds(66, 145, 202, 22);
		contentPane.add(cboOsSit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(278, 11, 261, 227);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select  *from tbclientes";

					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					
					DefaultTableModel modelo = (DefaultTableModel)	tblClientes .getModel();
					
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[] {rs.getString("idcli"), rs.getString("nomecli"),  rs.getString("fonecli")});
						
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListar.setBounds(10, 40, 89, 23);
		panel_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 241, 86);
		panel_1.add(scrollPane);
		
		tblClientes = new JTable();
		scrollPane.setViewportView(tblClientes);
		tblClientes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Id", "Nome", "Fone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		txtCliId = new JTextField();
		txtCliId.setEditable(false);
		txtCliId.setBounds(147, 41, 86, 20);
		panel_1.add(txtCliId);
		txtCliId.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("id");
		lblNewLabel_3.setBounds(116, 44, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
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
		btnPesquisar.setBounds(144, 88, 89, 23);
		panel_1.add(btnPesquisar);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(13, 89, 121, 20);
		panel_1.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Equipamento");
		lblNewLabel_4.setBounds(10, 268, 98, 14);
		contentPane.add(lblNewLabel_4);
		
		txtOsEquip = new JTextField();
		txtOsEquip.setBounds(86, 265, 357, 20);
		contentPane.add(txtOsEquip);
		txtOsEquip.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Defeito");
		lblNewLabel_5.setBounds(10, 303, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		txtOsDef = new JTextField();
		txtOsDef.setBounds(86, 300, 357, 20);
		contentPane.add(txtOsDef);
		txtOsDef.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Serviço");
		lblNewLabel_6.setBounds(10, 336, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		txtOsServ = new JTextField();
		txtOsServ.setBounds(86, 331, 357, 20);
		contentPane.add(txtOsServ);
		txtOsServ.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Técnico");
		lblNewLabel_7.setBounds(10, 361, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		txtOsTec = new JTextField();
		txtOsTec.setBounds(86, 362, 162, 20);
		contentPane.add(txtOsTec);
		txtOsTec.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Valor Total");
		lblNewLabel_8.setBounds(258, 365, 67, 14);
		contentPane.add(lblNewLabel_8);
		
		txtOsValor = new JTextField();
		txtOsValor.setText("0");
		txtOsValor.setBounds(335, 362, 153, 20);
		contentPane.add(txtOsValor);
		txtOsValor.setColumns(10);
		
		JButton btnOsAdicionar = new JButton("Adiconar");
		btnOsAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
		
					String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) value(?,?,?,?,?,?,?,?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
				
					stmt.setString(1, rbtOrc.getText());
					stmt.setString(2, (String) cboOsSit.getSelectedItem());
					stmt.setString(3, txtOsEquip.getText());
					stmt.setString(4, txtOsDef.getText());
					stmt.setString(5, txtOsServ.getText());
					stmt.setString(6, txtOsTec.getText());
					stmt.setString(7, txtOsValor.getText().replace(",", "."));
					stmt.setString(8, txtCliId.getText());
									
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
					
				
					
					txtCliId.setText("");
					tfBuscar.setText("");
					cboOsSit.setSelectedItem(" ");
					txtOsEquip.setText("");
					txtOsDef.setText("");
					txtOsServ.setText("");
					txtOsTec.setText("");
					txtOsValor.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnOsAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsAdicionar.setToolTipText("Emitir OS");
		btnOsAdicionar.setBounds(10, 415, 89, 23);
		contentPane.add(btnOsAdicionar);
		
		JButton btnOsPesquisar = new JButton("Pesquisar");
		btnOsPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					
					String num_os = JOptionPane.showInputDialog("Número da OS");
					
					String sql = "select os,date_format(data_os,'%d/%m/%Y - %H:%i'),tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli from tbos where os="+num_os;
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						
						txtOs.setText(rs.getString(1));
						txtData.setText(rs.getString(2));
						
						String rbtTipo = rs.getString(3);
						if (rbtTipo.equals("Orçamento")) {
							rbtOrc.setSelected(true);
							String tipo = "Orçamento";
							
						} else {

						}
						
						cboOsSit.setSelectedItem(rs.getString(4));
						txtOsEquip.setText(rs.getString(5));
						txtOsDef.setText(rs.getString(6));
						txtOsServ.setText(rs.getString(7));
						txtOsTec.setText(rs.getString(8));
						txtOsValor.setText(rs.getString(9));
						txtCliId.setText(rs.getString(10));
						
						btnOsAdicionar.setEnabled(false);
						btnPesquisar.setEnabled(false);
						tblClientes.setVisible(false);
						
						
					} else {
						JOptionPane.showMessageDialog(null, "OS não cadastrada");

					}
					
					
					
					
					
				} catch (java.sql.SQLSyntaxErrorException e1) {
					JOptionPane.showMessageDialog(null, "OS Inválida");
					// TODO Auto-generated catch block
					e1.printStackTrace();
					//System.out.println(e);
				} catch (SQLException e1) {
					
				}
			}
		});
		btnOsPesquisar.setToolTipText("Consultar");
		btnOsPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsPesquisar.setBounds(131, 415, 89, 23);
		contentPane.add(btnOsPesquisar);
		
		JButton btnOsAtualizar = new JButton("Atualizar");
		btnOsAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, rbtOrc.getText());
					stmt.setString(2, (String) cboOsSit.getSelectedItem());
					stmt.setString(3, txtOsEquip.getText());
					stmt.setString(4, txtOsDef.getText());
					stmt.setString(5, txtOsServ.getText());
					stmt.setString(6, txtOsTec.getText());
					stmt.setString(7, txtOsValor.getText().replace(",", "."));
					stmt.setString(8, txtOs.getText());
									
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
					
					txtOs.setText("");
					txtData.setText("");
					txtCliId.setText("");
					txtOsEquip.setText("");
					txtOsDef.setText("");
					txtOsServ.setText("");
					txtOsTec.setText("");
					txtOsValor.setText("");
					
					btnOsAdicionar.setEnabled(true);
					btnPesquisar.setEnabled(true);
					tblClientes.setVisible(true);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOsAtualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsAtualizar.setToolTipText("Alterar");
		btnOsAtualizar.setBounds(258, 415, 89, 23);
		contentPane.add(btnOsAtualizar);
		
		JButton btnOsExcluir = new JButton("Excluir");
		btnOsExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja ecluir essa OS?","Atenção",JOptionPane.YES_NO_OPTION);
					if (confirma == JOptionPane.YES_OPTION) {
						String sql = "delete from tbos where os=?";
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setString(1, txtOs.getText());
						int apagado = stmt.executeUpdate();
						if (apagado>0) {
							JOptionPane.showMessageDialog(null, "OS ecluida com sucesso!");
							
							txtOs.setText("");
							txtData.setText("");
						
							cboOsSit.setSelectedItem(" ");
							txtCliId.setText("");
							txtOsEquip.setText("");
							txtOsDef.setText("");
							txtOsServ.setText("");
							txtOsTec.setText("");
							txtOsValor.setText("");
							
							btnOsAdicionar.setEnabled(true);
							btnPesquisar.setEnabled(true);
							tblClientes.setVisible(true);
							
							stmt.close();
							con.close();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOsExcluir.setToolTipText("Deletar");
		btnOsExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsExcluir.setBounds(399, 415, 89, 23);
		contentPane.add(btnOsExcluir);
		
		JButton btnPdf = new JButton("PDF Clientes");
		btnPdf.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con =  Conexao.faz_conexao();
					String sql = "select *from tbclientes";
					PreparedStatement stmt = con.prepareStatement(sql);
				
					
					String file_name = "E:\\PDF\\clientes.pdf";
					Document document = new Document();
					try {
						PdfWriter.getInstance(document, new FileOutputStream(file_name));
						
						document.open();
						
						Paragraph para = new Paragraph("Tabela Cliente");
						
						Date data = new Date();
						DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
						document.add(new Paragraph(formatador.format(data)));
						
						document.add(new Paragraph("Listagem de Clientes:"));
						document.add(new Paragraph(" "));
						
						PdfPTable tabela = new PdfPTable(5);
						PdfPCell col1 = new PdfPCell(new Paragraph("idcli"));
						tabela.addCell(col1);
						PdfPCell col2 = new PdfPCell(new Paragraph("nomecli"));
						tabela.addCell(col2);
						PdfPCell col3 = new PdfPCell(new Paragraph("endcli"));
						tabela.addCell(col3);
						PdfPCell col4 = new PdfPCell(new Paragraph("fonecli"));
						tabela.addCell(col4);
						PdfPCell col5 = new PdfPCell(new Paragraph("emailcli"));
						tabela.addCell(col5);
						String readLista = "select *from tbclientes order by nomecli";
						
						try {
							Connection con1 = Conexao.faz_conexao();
							PreparedStatement stmt1 = con.prepareStatement(readLista); 
							ResultSet rs = stmt1.executeQuery();
							while(rs.next()) {
								tabela.addCell(rs.getString(1));
								tabela.addCell(rs.getString(2));
								tabela.addCell(rs.getString(3));
								tabela.addCell(rs.getString(4));
								tabela.addCell(rs.getString(5));
								
							}
							con.close();
							
							
						} catch (Exception e1) {
							System.out.println(e1);
							
						}
						
						
						
						document.add(tabela);
						
						
					
						
						
						document.close();
					} catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				} catch (Exception ex){
					System.out.println(ex);
					
				}
				
				
					
			}
		});
		btnPdf.setBounds(68, 200, 130, 23);
		contentPane.add(btnPdf);
		
		JButton btnPdfOs = new JButton("PDF OS");
		btnPdfOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select\r\n"
							+ "OSER.os,data_os,tipo,situacao,equipamento,valor,\r\n"
							+ "CLI.nomecli,fonecli\r\n"
							+ "from tbos as OSER\r\n"
							+ "inner join tbclientes as CLI\r\n"
							+ "on (CLI.idcli = OSER .idcli)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					String file_name = "E:\\PDF\\OS.pdf";
					Document document = new Document();
					
					try {
						PdfWriter.getInstance(document, new FileOutputStream(file_name));
						
						document.open();
						
						Paragraph para = new Paragraph("Tabela OS");
						
						Date data = new Date();
						DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
						document.add(new Paragraph(formatador.format(data)));
						
						document.add(new Paragraph("Listagem da OS:"));
						document.add(new Paragraph(" "));
						
						PdfPTable tabela = new PdfPTable(8);
						PdfPCell col1 = new PdfPCell(new Paragraph("data_os"));
						tabela.addCell(col1);
						PdfPCell col2 = new PdfPCell(new Paragraph("tipo"));
						tabela.addCell(col2);
						PdfPCell col3 = new PdfPCell(new Paragraph("situacao"));
						tabela.addCell(col3);
						PdfPCell col4 = new PdfPCell(new Paragraph("equipamento"));
						tabela.addCell(col4);
						PdfPCell col5 = new PdfPCell(new Paragraph("valor"));
						tabela.addCell(col5);
						PdfPCell col6 = new PdfPCell(new Paragraph("nomecli"));
						tabela.addCell(col6);
						PdfPCell col7 = new PdfPCell(new Paragraph("fonecli"));
						tabela.addCell(col7);
						
						String readLista = "select\r\n"
								+ "OSER.os,data_os,tipo,situacao,equipamento,valor,\r\n"
								+ "CLI.nomecli,fonecli\r\n"
								+ "from tbos as OSER\r\n"
								+ "inner join tbclientes as CLI\r\n"
								+ "on (CLI.idcli = OSER .idcli)";
						
						try {
							Connection con1 = Conexao.faz_conexao();
							PreparedStatement stmt1 = con.prepareStatement(readLista);
							ResultSet rs = stmt1.executeQuery();
							while(rs.next()) {
								tabela.addCell(rs.getString(1));
								tabela.addCell(rs.getString(2));
								tabela.addCell(rs.getString(3));
								tabela.addCell(rs.getString(4));
								tabela.addCell(rs.getString(5));
								tabela.addCell(rs.getString(6));
								tabela.addCell(rs.getString(7));
								tabela.addCell(rs.getString(8));
								
								
							}
							con.close();
							
						} catch (Exception e2) {
							System.out.println(e2);
						}
						
						document.add(tabela);
						
						document.close();
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				} catch (SQLException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
			}
		});
		btnPdfOs.setBounds(66, 234, 89, 23);
		contentPane.add(btnPdfOs);
	}
}

