package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import controller.HospedeController;
import controller.ReservaController;
import model.Hospede;
import model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController = new ReservaController();
	private HospedeController hospedeController = new HospedeController();
	private ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
	private Reserva reserva = new Reserva(null, null, null, new BigDecimal(0));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true); 
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(panel.getSelectedIndex() == 0 ) {
					try {
					    int id = Integer.parseInt(txtBuscar.getText());
					    buscarReserva(id);
					} catch (NumberFormatException numberException) {
					    JOptionPane.showMessageDialog(null, "Digite apenas números");
					}
				}
				else {
					    String sobrenome = txtBuscar.getText();
					    buscarHospede(sobrenome);
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				 if (panel.getSelectedIndex() == 0) {
		                // Editar reserva
					 int selectedRow = tbReservas.getSelectedRow();
					 selectedRow = selectedRow < 0 ? 0 : selectedRow; 
					 Reserva reserva = criarReservaComLinha(selectedRow);
		             reservaController.updateReserva(reserva);
		             JOptionPane.showMessageDialog(null, "Reserva atualizada");
					
		             
		            } else {
		                // Editar hóspede
		            	for (int row = 0; row < modeloHospedes.getRowCount(); row++) {
		            		
		                    Hospede hospede = criarHospedeComLinha(row);
		                    hospedeController.atualizarHospede(hospede);
		                    JOptionPane.showMessageDialog(null, "Hospedes atualizados");
		            		}
		                    
		                    
		                }
				
			}
		});
		
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if (panel.getSelectedIndex() == 0) {
		                // Excluir reserva
					 int selectedRow = tbReservas.getSelectedRow();
					 if(selectedRow < 0) {
						 JOptionPane.showMessageDialog(null, "Nenhuma Reserva Selecionada");
						 return;
					 }
					 int choice = JOptionPane.showOptionDialog(
				                null,
				                "Tem certeza de que deseja fazer excluir a Reserva?",
				                "Confirmação",
				                JOptionPane.YES_NO_OPTION,
				                JOptionPane.QUESTION_MESSAGE,
				                null,
				                new String[]{"Sim", "Não"},
				                "Sim"
				        );

				        if (choice == JOptionPane.YES_OPTION) {
				        	int id = reserva.getId();
				        	reservaController.excluirReserva(id);
				           JOptionPane.showMessageDialog(null, "Reserva excluída");
				           modelo.setRowCount(0);
				        	
				        } else if (choice == JOptionPane.NO_OPTION) {
				        	return;
				        } else if (choice == JOptionPane.CLOSED_OPTION) {
				            return;
				        }
					 
				 } 
				 else { 
					 int selectedRow = tbHospedes.getSelectedRow();
					 if(selectedRow < 0) {
						 JOptionPane.showMessageDialog(null, "Nenhum Hospede Selecionado");
						 return;
					 }
					 int choice = JOptionPane.showOptionDialog(
				                null,
				                "Tem certeza de que deseja fazer excluir o Hospede?",
				                "Confirmação",
				                JOptionPane.YES_NO_OPTION,
				                JOptionPane.QUESTION_MESSAGE,
				                null,
				                new String[]{"Sim", "Não"},
				                "Sim"
				        );
				        if (choice == JOptionPane.YES_OPTION) {
				        	int id = hospedes.get(selectedRow).getId();
				        	hospedeController.excluirHospede(id);
				           JOptionPane.showMessageDialog(null, "Hospede excluído");
				           modeloHospedes.removeRow(selectedRow);
				        	
				        } else if (choice == JOptionPane.NO_OPTION) {
				        	return;
				        } else if (choice == JOptionPane.CLOSED_OPTION) {
				            return;
				        }
				 }
	 
				 
			}
		});
	
		
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	
	private void buscarReserva(int id) {
		modelo.setRowCount(0);
		reserva = reservaController.buscarReserva(id);
		if(reserva.getDataEntrada() == null) {
			JOptionPane.showMessageDialog(null, "Reserva não encontrada");
			return;
		}
		
		
		
	    Object[] rowData = {
	        reserva.getId(),
	        reserva.getDataEntrada(),
	        reserva.getDataSaida(),
	        reserva.getValor(),
	        reserva.getFormaPagamento()
	    };
	    
	    modelo.addRow(rowData);
	}
	private void buscarHospede(String sobrenome) {
			modeloHospedes.setRowCount(0);
			hospedes = hospedeController.buscarHospedes(sobrenome);
			if(hospedes.size() <= 0) {
				JOptionPane.showMessageDialog(null, "Hospede(s) não encontrado");
				return;
			}
			
			for(Hospede hospede : hospedes) {
				Object[] rowData = {
						hospede.getId(),
						hospede.getNome(),
						hospede.getSobrenome(),
						hospede.getDataNascimento(),
						hospede.getNacionalidade(),
						hospede.getTelefone(),
						hospede.getIdReserva()
				};
				modeloHospedes.addRow(rowData);
				
			}
	}
	
	//Este método serve para criar um objeto reserva com os valores atualizados da tabela
	public Reserva criarReservaComLinha(int linha) {
		 try {
			 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String idReservaStr = String.valueOf(modelo.getValueAt(linha, 0));
				String dataEntradaStr = String.valueOf(modelo.getValueAt(linha, 1));
				String dataSaidaStr = String.valueOf(modelo.getValueAt(linha, 2));
				String valorStr = String.valueOf(modelo.getValueAt(linha, 3));
				
	             int idReserva = Integer.parseInt(idReservaStr);	                       
	             java.util.Date dataEntrada =    dateFormat.parse(dataEntradaStr); 
	             java.util.Date dataSaida = dateFormat.parse(dataSaidaStr);       
	             BigDecimal  valor = new BigDecimal(valorStr);
	             String formaPagamento = (String) modelo.getValueAt(linha, 4);
	             
	             reserva.setId(idReserva);
	             reserva.setDataEntrada(dataEntrada);
	             reserva.setDataSaida(dataSaida);
	             reserva.setValor(valor);
	             reserva.setFormaPagamento(formaPagamento);
	             return reserva;
				 }
				 catch(Exception ex) {
					 JOptionPane.showMessageDialog(null, "Verifique "
					 		+ "todos os campos se estão no formato adequado");
					 throw new RuntimeException(ex);
				 }
	}
	
	//Este método serve para criar um hospede atualizado com os valores da tabela
	public Hospede criarHospedeComLinha(int linha) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String idHospedeStr =  String.valueOf(modeloHospedes.getValueAt(linha, 0)); 
            String nome = String.valueOf(modeloHospedes.getValueAt(linha, 1)); 
            String sobrenome =  String.valueOf(modeloHospedes.getValueAt(linha, 2)); 
            String dataNascimentoStr = String.valueOf(modeloHospedes.getValueAt(linha, 3)); 
            String nacionalidade =  String.valueOf(modeloHospedes.getValueAt(linha, 4)); 
            String telefoneStr = String.valueOf(modeloHospedes.getValueAt(linha, 5)); 
            String idReservaStr =  String.valueOf(modeloHospedes.getValueAt(linha, 6));
            
            int idHospede = Integer.parseInt(idHospedeStr);
            java.util.Date dataNascimento = dateFormat.parse(dataNascimentoStr);
            long telefone = Long.parseLong(telefoneStr);
            int idReserva = Integer.parseInt(idReservaStr);
            
            
            Hospede hospede = hospedes.get(linha);
            hospede.setId(idHospede);
            hospede.setNome(nome);
            hospede.setSobrenome(sobrenome);
            hospede.setDataNascimento(dataNascimento);
            hospede.setNacionalidade(nacionalidade);
            hospede.setTelefone(telefone);
            hospede.setIdReserva(idReserva);
            return hospede;
           
    		}
            catch(Exception ex) {
            	JOptionPane.showMessageDialog(null, "Verifique "
				 		+ "todos os campos se estão no formato adequado");
            		throw new RuntimeException(ex);
            	}
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }
	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
