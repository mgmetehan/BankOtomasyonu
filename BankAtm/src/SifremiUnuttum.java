import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class SifremiUnuttum extends JFrame {
	private JPanel w_panel;
	private JTextField txtKullaniciTc = new JTextField();
	private JTextField txtKisiAnneSoyad = new JTextField();
	private JTextField txtSifre = new JTextField();
	JPanel panelTitle = new JPanel();
	JLabel lblTitle = new JLabel("Dona Bank Sifre Sorgula");
	JLabel lblIcon = new JLabel("");
	JLabel lblKullaniciTc = new JLabel("T.C Kimlik/Musteri Numaraniz");
	JLabel lblKisiAnneSoyad = new JLabel("Anne Kizlik Soyadi:");
	JButton btnSorgula = new JButton("Sorgula");
	JLabel lblSifre = new JLabel("Sifreniz");
	JButton btnGeriDon = new JButton("Islemlere Geri Don");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SifremiUnuttum frame = new SifremiUnuttum();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void SqlSorgula() throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		Scanner scan = new Scanner(System.in);
		String SonSifre = "";
		try {
			conn = helper.getConnection();
			String KisiAnneSoyad = txtKisiAnneSoyad.getText();
			String KisiTc = txtKullaniciTc.getText();
			if (txtKullaniciTc.getText().length() < 11 || txtKullaniciTc.getText().length() > 11) {
				JOptionPane.showMessageDialog(null, "T.C no 11 Haneli Olmali Tekrar Deneyiniz!!", "Uyari",
						JOptionPane.ERROR_MESSAGE);
				txtKullaniciTc.setText("");
				txtSifre.setText("");
			} else {
				String sql = "Select KisiSifre,KisiTc from tblkisiler where KisiAnneSoyad='" + KisiAnneSoyad
						+ "' and KisiTc='" + KisiTc + "'";
				preStat = conn.prepareStatement(sql);
				ResultSet result = preStat.executeQuery();
				while (result.next()) {
					SonSifre = result.getString(1);
				}
				txtSifre.setText(SonSifre);
			}
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
		}
	}

	public SifremiUnuttum() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(SifremiUnuttum.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 653);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(217, 128, 250));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(0, 11, 584, 88);
		w_panel.add(panelTitle);

		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblTitle.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblTitle.setBounds(117, 24, 403, 40);
		panelTitle.add(lblTitle);

		lblIcon.setIcon(new ImageIcon(SifremiUnuttum.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(10, 11, 115, 76);
		panelTitle.add(lblIcon);

		lblKullaniciTc.setToolTipText("");
		lblKullaniciTc.setForeground(Color.WHITE);
		lblKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciTc.setBounds(52, 126, 283, 45);
		w_panel.add(lblKullaniciTc);

		txtKullaniciTc.setForeground(Color.WHITE);
		txtKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKullaniciTc.setColumns(10);
		txtKullaniciTc.setBorder(null);
		txtKullaniciTc.setBackground(Color.BLACK);
		txtKullaniciTc.setBounds(52, 192, 483, 45);
		w_panel.add(txtKullaniciTc);

		lblKisiAnneSoyad.setToolTipText("");
		lblKisiAnneSoyad.setForeground(Color.WHITE);
		lblKisiAnneSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKisiAnneSoyad.setBounds(52, 248, 283, 45);
		w_panel.add(lblKisiAnneSoyad);

		txtKisiAnneSoyad.setForeground(Color.WHITE);
		txtKisiAnneSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKisiAnneSoyad.setColumns(10);
		txtKisiAnneSoyad.setBorder(null);
		txtKisiAnneSoyad.setBackground(Color.BLACK);
		txtKisiAnneSoyad.setBounds(52, 304, 483, 45);
		w_panel.add(txtKisiAnneSoyad);
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtKullaniciTc.getText().length() != 11) {
					txtKullaniciTc.setText("");
					JOptionPane.showMessageDialog(null, "T.C no 11 Haneli Olmalý!", "Uyarý",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						SqlSorgula();
					} catch (SQLException e1) {
					}
				}
			}
		});

		btnSorgula.setForeground(Color.WHITE);
		btnSorgula.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnSorgula.setBorder(null);
		btnSorgula.setBackground(Color.LIGHT_GRAY);
		btnSorgula.setBounds(52, 380, 482, 35);
		w_panel.add(btnSorgula);

		txtSifre.setForeground(Color.BLACK);
		txtSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtSifre.setColumns(10);
		txtSifre.setBorder(null);
		txtSifre.setBackground(Color.WHITE);
		txtSifre.setBounds(52, 469, 483, 45);
		txtSifre.setEnabled(false);
		w_panel.add(txtSifre);

		lblSifre.setToolTipText("");
		lblSifre.setForeground(Color.WHITE);
		lblSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblSifre.setBounds(52, 413, 283, 45);
		w_panel.add(lblSifre);

		btnGeriDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAtm login = new LoginAtm();
				login.setVisible(true);
				dispose();
			}
		});
		btnGeriDon.setForeground(Color.WHITE);
		btnGeriDon.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnGeriDon.setBorder(null);
		btnGeriDon.setBackground(Color.RED);
		btnGeriDon.setBounds(174, 552, 225, 53);
		w_panel.add(btnGeriDon);
	}

}
