import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Havale extends JFrame {

	private JPanel w_panel;
	private JTextField txtAliciHesap = new JTextField();
	private JTextField txtGondericiHesap = new JTextField();
	private JTextField txtPara = new JTextField();
	JButton btnParaGonder = new JButton("Para Gonder");
	JLabel lblAliciHesap = new JLabel("Alici Hesap T.C. No:");
	JLabel lblGondericiHesap = new JLabel("Gonderici Hesap T.C. No:");
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankKullanc = new JLabel("Dona Bank Havale Islemleri");
	JLabel lblIcon = new JLabel("");
	JLabel lblGnderilecekTutar = new JLabel("Gonderilecek Tutar:");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Havale frame = new Havale();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String setKullaniciTC() {
		try {
			DbHelper helper = new DbHelper();
			Connection con;
			con = helper.getConnection();
			Statement stmt = con.createStatement();
			LoginAtm atm = new LoginAtm();
			int KisiId = atm.TcveSifreKontrol();
			String KisiTc = "";
			ResultSet rs = stmt.executeQuery("Select KisiTc from tblkisiler where KisiId=" + KisiId);
			while (rs.next()) {
				KisiTc = rs.getString(1);
			}
			return KisiTc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void GonderenHesap() {
		try {
			DbHelper helper = new DbHelper();
			Connection con;
			con = helper.getConnection();
			Statement stmt = con.createStatement();
			LoginAtm atm = new LoginAtm();
			int KisiId = atm.TcveSifreKontrol();
			String GondericiKisiTc = "";
			Float GondericiKisiPara = 0f;
			ResultSet rs = stmt.executeQuery("Select KisiTc,KisiPara from tblkisiler where KisiId=" + KisiId);
			while (rs.next()) {
				GondericiKisiTc = rs.getString(1);
				GondericiKisiPara = rs.getFloat(2);
			}
			Float Para = Float.valueOf(txtPara.getText());
			if (Para <= GondericiKisiPara) {
				AliciHesap(GondericiKisiPara, GondericiKisiTc);
			} else if (GondericiKisiPara < Para) {
				JOptionPane.showMessageDialog(null, "Mevcut Bakiye Yetersiz Tekrar Deneyiniz!", "Uyari",
						JOptionPane.ERROR_MESSAGE);
				txtAliciHesap.setText("");
				txtPara.setText("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void AliciHesap(Float GondericiKisiPara, String GondericiKisiTc) {
		try {
			DbHelper helper = new DbHelper();
			Connection con;
			con = helper.getConnection();
			Statement stmt = con.createStatement();
			String AliciKisiTc = txtAliciHesap.getText();
			Float AliciKisiPara = -0f;
			ResultSet rs = stmt.executeQuery("Select KisiPara from tblkisiler where KisiTc=" + AliciKisiTc);
			while (rs.next()) {
				AliciKisiPara = rs.getFloat(1);
			}
			if (AliciKisiPara < 0 || AliciKisiTc.length() < 11) {
				JOptionPane.showMessageDialog(null, "Girilen T.C. no Yanlis Tekrar Deneyiniz!", "Uyari",
						JOptionPane.ERROR_MESSAGE);
				txtAliciHesap.setText("");
				txtPara.setText("");
			}
			HavaleIslemleri(GondericiKisiPara, GondericiKisiTc, AliciKisiPara, AliciKisiTc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void HavaleIslemleri(Float GondericiKisiPara, String GondericiKisiTc, Float AliciKisiPara,
			String AliciKisiTc) throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String AliciSql = "";
		String GondericiSql = "";
		Float Para = Float.valueOf(txtPara.getText());
		Float SonParaAliciHesap = AliciKisiPara + Para;
		Float SonParaGondericiHesap = GondericiKisiPara - Para;
		try {
			conn = helper.getConnection();
			AliciSql = "Update tblkisiler set KisiPara=" + SonParaAliciHesap + "where KisiTc='" + AliciKisiTc + "'";
			preStat = conn.prepareStatement(AliciSql);
			int result = preStat.executeUpdate();
			GondericiSql = "Update tblkisiler set KisiPara=" + SonParaGondericiHesap + "where KisiTc='"
					+ GondericiKisiTc + "'";
			preStat = conn.prepareStatement(GondericiSql);
			result = preStat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Islemler Basarili iyi Gunler", "", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
			setVisible(false);
		}
	}

	public Havale() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Havale.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 606);
		w_panel = new JPanel();// 1,196,197
		w_panel.setBackground(new Color(89, 9, 149));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);
		btnParaGonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GonderenHesap();
			}
		});

		btnParaGonder.setForeground(Color.WHITE);
		btnParaGonder.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnParaGonder.setBorder(null);
		btnParaGonder.setBackground(Color.DARK_GRAY);
		btnParaGonder.setBounds(30, 502, 482, 35);
		w_panel.add(btnParaGonder);

		txtAliciHesap.setForeground(Color.WHITE);
		txtAliciHesap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtAliciHesap.setColumns(10);
		txtAliciHesap.setBorder(null);
		txtAliciHesap.setBackground(Color.BLACK);
		txtAliciHesap.setBounds(29, 320, 483, 45);
		w_panel.add(txtAliciHesap);

		lblAliciHesap.setToolTipText("");
		lblAliciHesap.setForeground(Color.WHITE);
		lblAliciHesap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblAliciHesap.setBounds(30, 264, 283, 45);
		w_panel.add(lblAliciHesap);

		txtGondericiHesap.setEnabled(false);
		txtGondericiHesap.setText(setKullaniciTC());
		txtGondericiHesap.setForeground(Color.WHITE);
		txtGondericiHesap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtGondericiHesap.setColumns(10);
		txtGondericiHesap.setBorder(null);
		txtGondericiHesap.setBackground(Color.BLACK);
		txtGondericiHesap.setBounds(29, 208, 483, 45);
		w_panel.add(txtGondericiHesap);

		lblGondericiHesap.setToolTipText("");
		lblGondericiHesap.setForeground(Color.WHITE);
		lblGondericiHesap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblGondericiHesap.setBounds(30, 142, 283, 45);
		w_panel.add(lblGondericiHesap);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-84, 27, 786, 88);
		w_panel.add(panelTitle);

		lblDonaBankKullanc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKullanc.setForeground(Color.WHITE);
		lblDonaBankKullanc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKullanc.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKullanc.setBounds(210, 24, 404, 40);
		panelTitle.add(lblDonaBankKullanc);

		lblIcon.setIcon(new ImageIcon(Havale.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(91, 0, 113, 76);
		panelTitle.add(lblIcon);

		txtPara.setForeground(Color.WHITE);
		txtPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtPara.setColumns(10);
		txtPara.setBorder(null);
		txtPara.setBackground(Color.BLACK);
		txtPara.setBounds(29, 432, 483, 45);
		w_panel.add(txtPara);

		lblGnderilecekTutar.setToolTipText("");
		lblGnderilecekTutar.setForeground(Color.WHITE);
		lblGnderilecekTutar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblGnderilecekTutar.setBounds(30, 376, 283, 45);
		w_panel.add(lblGnderilecekTutar);
	}
}
