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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class YeniUye extends JFrame {

	JPanel w_panel;
	JTextField txtYeniUyeAd = new JTextField();
	JTextField txtYeniUyeSoyad = new JTextField();
	JTextField txtYeniUyeIl = new JTextField();
	JTextField txtYeniUyeTc = new JTextField();
	JTextField txtYeniUyeTelefon = new JTextField();
	JTextField txtYeniUyeBorc = new JTextField();
	JTextField txtYeniUyePara = new JTextField();
	JTextField txtYeniUyeAnneSoyad = new JTextField();
	JTextField txtYeniUyeSifre = new JTextField();
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankYeni = new JLabel("Dona Bank Yeni Kayit");
	JLabel lblIcon = new JLabel("");
	JLabel lblYeniUyeAd = new JLabel("Ad: ");
	JButton btnYeniUye = new JButton("Uye Ol");
	JLabel lblYeniUyeSoyad = new JLabel("Soyad:");
	JLabel lblYeniUyeIl = new JLabel("Dogum Yeri:");
	JLabel lblYeniUyeTc = new JLabel("T.C Kimlik No:");
	JLabel lblYeniUyeTelefon = new JLabel("Telefon Numarasi:");
	JLabel lblYeniUyeBorc = new JLabel("Mevcut Borc:");
	JLabel lblYeniUyeAnneSoyad = new JLabel("Anne Kizlik Soyadi:");
	JLabel lblYeniUyePara = new JLabel("Mevcut Para:");
	JLabel lblYeniUyeCinsiyet = new JLabel("Cinsiyet:");
	JLabel lblYeniUyeSifre = new JLabel("Sifre: ");
	JComboBox cbYeniUyeCinsiyet = new JComboBox();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YeniUye frame = new YeniUye();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void SqlEkle() throws SQLException {// YeniUye
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		Scanner scan = new Scanner(System.in);
		try {
			conn = helper.getConnection();
			String sql = "INSERT INTO tblkisiler(KisiAd,KisiSoyad,KisiTc,KisiIl,KisiAnneSoyad,KisiPara,KisiBorc,KisiTelefon,KisiSifre,KisiCinsiyet) values (?,?,?,?,?,?,?,?,?,?)";
			preStat = conn.prepareStatement(sql);
			String ad = txtYeniUyeAd.getText();
			preStat.setString(1, ad);
			String soyad = txtYeniUyeSoyad.getText();
			preStat.setString(2, soyad);
			String Tc = txtYeniUyeTc.getText();
			if (Tc.length() != 11) {
				JOptionPane.showMessageDialog(null, "T.C 11 Haneli Olmali!!", "Uyari", JOptionPane.ERROR_MESSAGE);
				txtYeniUyeTc.setText("");
				Tc = txtYeniUyeTc.getText();
			}
			preStat.setString(3, Tc);
			String Il = txtYeniUyeIl.getText();
			preStat.setString(4, Il);
			String AnneSoyad = txtYeniUyeAnneSoyad.getText();
			preStat.setString(5, AnneSoyad);
			float mevcutPara = Float.valueOf(txtYeniUyePara.getText());
			preStat.setFloat(6, mevcutPara);
			float mevcutBorc = Float.valueOf(txtYeniUyeBorc.getText());
			preStat.setFloat(7, mevcutBorc);
			String telefonNo = txtYeniUyeTelefon.getText();
			if (telefonNo.length() != 10) {
				JOptionPane.showMessageDialog(null, "Telefon Numarasi Yanlis Tekrar Deneyiniz!!", "Uyari",
						JOptionPane.ERROR_MESSAGE);
				txtYeniUyeTelefon.setText("");
				telefonNo = txtYeniUyeTelefon.getText();
			}
			preStat.setString(8, telefonNo);
			String sifre = txtYeniUyeSifre.getText();
			if (sifre.length() != 6) {
				JOptionPane.showMessageDialog(null, "Sifre 6 Haneli Olmali Tekrar Deneyiniz!!", "Uyari",
						JOptionPane.ERROR_MESSAGE);
				txtYeniUyeSifre.setText("");
				sifre = txtYeniUyeSifre.getText();
			}
			preStat.setString(9, sifre);
			String Cinsiyet = cbYeniUyeCinsiyet.getSelectedItem().toString();
			preStat.setString(10, Cinsiyet);
			System.out.println("Kayit Eklendi");
			int result = preStat.executeUpdate();
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
		}
	}

	public YeniUye() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(YeniUye.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 823);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(0, 128, 0));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(0, 11, 622, 88);
		w_panel.add(panelTitle);

		lblDonaBankYeni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankYeni.setForeground(Color.WHITE);
		lblDonaBankYeni.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankYeni.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankYeni.setBounds(155, 23, 345, 40);
		panelTitle.add(lblDonaBankYeni);

		lblIcon.setIcon(new ImageIcon(YeniUye.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(10, 11, 118, 76);
		panelTitle.add(lblIcon);

		lblYeniUyeAd.setToolTipText("");
		lblYeniUyeAd.setForeground(Color.WHITE);
		lblYeniUyeAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeAd.setBounds(10, 126, 264, 45);
		w_panel.add(lblYeniUyeAd);

		txtYeniUyeAd.setForeground(Color.WHITE);
		txtYeniUyeAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeAd.setColumns(10);
		txtYeniUyeAd.setBorder(null);
		txtYeniUyeAd.setBackground(Color.BLACK);
		txtYeniUyeAd.setBounds(284, 126, 327, 45);
		w_panel.add(txtYeniUyeAd);

		btnYeniUye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SqlEkle();
					txtYeniUyeAd.setText("");
					txtYeniUyeAnneSoyad.setText("");
					txtYeniUyeBorc.setText("");
					txtYeniUyeIl.setText("");
					txtYeniUyePara.setText("");
					txtYeniUyeSifre.setText("");
					txtYeniUyeSoyad.setText("");
					txtYeniUyeTc.setText("");
					txtYeniUyeTelefon.setText("");
					LoginAtm login = new LoginAtm();
					login.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnYeniUye.setForeground(Color.WHITE);
		btnYeniUye.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnYeniUye.setBorder(null);
		btnYeniUye.setBackground(Color.DARK_GRAY);
		btnYeniUye.setBounds(0, 699, 622, 58);
		w_panel.add(btnYeniUye);

		lblYeniUyeSoyad.setToolTipText("");
		lblYeniUyeSoyad.setForeground(Color.WHITE);
		lblYeniUyeSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeSoyad.setBounds(10, 182, 264, 45);
		w_panel.add(lblYeniUyeSoyad);

		txtYeniUyeSoyad.setForeground(Color.WHITE);
		txtYeniUyeSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeSoyad.setColumns(10);
		txtYeniUyeSoyad.setBorder(null);
		txtYeniUyeSoyad.setBackground(Color.BLACK);
		txtYeniUyeSoyad.setBounds(284, 182, 327, 45);
		w_panel.add(txtYeniUyeSoyad);

		lblYeniUyeIl.setToolTipText("");
		lblYeniUyeIl.setForeground(Color.WHITE);
		lblYeniUyeIl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeIl.setBounds(10, 299, 264, 45);
		w_panel.add(lblYeniUyeIl);

		txtYeniUyeIl.setForeground(Color.WHITE);
		txtYeniUyeIl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeIl.setColumns(10);
		txtYeniUyeIl.setBorder(null);
		txtYeniUyeIl.setBackground(Color.BLACK);
		txtYeniUyeIl.setBounds(284, 299, 327, 45);
		w_panel.add(txtYeniUyeIl);

		lblYeniUyeTc.setToolTipText("");
		lblYeniUyeTc.setForeground(Color.WHITE);
		lblYeniUyeTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeTc.setBounds(10, 243, 264, 45);
		w_panel.add(lblYeniUyeTc);

		txtYeniUyeTc.setForeground(Color.WHITE);
		txtYeniUyeTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeTc.setColumns(10);
		txtYeniUyeTc.setBorder(null);
		txtYeniUyeTc.setBackground(Color.BLACK);
		txtYeniUyeTc.setBounds(284, 243, 327, 45);
		w_panel.add(txtYeniUyeTc);

		lblYeniUyeTelefon.setToolTipText("");
		lblYeniUyeTelefon.setForeground(Color.WHITE);
		lblYeniUyeTelefon.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeTelefon.setBounds(10, 531, 264, 45);
		w_panel.add(lblYeniUyeTelefon);

		txtYeniUyeTelefon.setForeground(Color.WHITE);
		txtYeniUyeTelefon.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeTelefon.setColumns(10);
		txtYeniUyeTelefon.setBorder(null);
		txtYeniUyeTelefon.setBackground(Color.BLACK);
		txtYeniUyeTelefon.setBounds(284, 531, 327, 45);
		w_panel.add(txtYeniUyeTelefon);

		lblYeniUyeBorc.setToolTipText("");
		lblYeniUyeBorc.setForeground(Color.WHITE);
		lblYeniUyeBorc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeBorc.setBounds(10, 475, 264, 45);
		w_panel.add(lblYeniUyeBorc);

		txtYeniUyeBorc.setForeground(Color.WHITE);
		txtYeniUyeBorc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeBorc.setColumns(10);
		txtYeniUyeBorc.setBorder(null);
		txtYeniUyeBorc.setBackground(Color.BLACK);
		txtYeniUyeBorc.setBounds(284, 475, 327, 45);
		w_panel.add(txtYeniUyeBorc);

		lblYeniUyeAnneSoyad.setToolTipText("");
		lblYeniUyeAnneSoyad.setForeground(Color.WHITE);
		lblYeniUyeAnneSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeAnneSoyad.setBounds(10, 358, 264, 45);
		w_panel.add(lblYeniUyeAnneSoyad);

		lblYeniUyePara.setToolTipText("");
		lblYeniUyePara.setForeground(Color.WHITE);
		lblYeniUyePara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyePara.setBounds(10, 414, 264, 45);
		w_panel.add(lblYeniUyePara);

		txtYeniUyePara.setForeground(Color.WHITE);
		txtYeniUyePara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyePara.setColumns(10);
		txtYeniUyePara.setBorder(null);
		txtYeniUyePara.setBackground(Color.BLACK);
		txtYeniUyePara.setBounds(284, 414, 327, 45);
		w_panel.add(txtYeniUyePara);

		txtYeniUyeAnneSoyad.setForeground(Color.WHITE);
		txtYeniUyeAnneSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeAnneSoyad.setColumns(10);
		txtYeniUyeAnneSoyad.setBorder(null);
		txtYeniUyeAnneSoyad.setBackground(Color.BLACK);
		txtYeniUyeAnneSoyad.setBounds(284, 358, 327, 45);
		w_panel.add(txtYeniUyeAnneSoyad);

		lblYeniUyeCinsiyet.setToolTipText("");
		lblYeniUyeCinsiyet.setForeground(Color.WHITE);
		lblYeniUyeCinsiyet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeCinsiyet.setBounds(10, 643, 264, 45);
		w_panel.add(lblYeniUyeCinsiyet);

		lblYeniUyeSifre.setToolTipText("");
		lblYeniUyeSifre.setForeground(Color.WHITE);
		lblYeniUyeSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniUyeSifre.setBounds(10, 587, 264, 45);
		w_panel.add(lblYeniUyeSifre);

		txtYeniUyeSifre.setForeground(Color.WHITE);
		txtYeniUyeSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtYeniUyeSifre.setColumns(10);
		txtYeniUyeSifre.setBorder(null);
		txtYeniUyeSifre.setBackground(Color.BLACK);
		txtYeniUyeSifre.setBounds(284, 587, 327, 45);
		w_panel.add(txtYeniUyeSifre);

		cbYeniUyeCinsiyet.setBorder(null);
		cbYeniUyeCinsiyet.setOpaque(false);
		cbYeniUyeCinsiyet.setModel(new DefaultComboBoxModel(new String[] { "Erkek", "Kadýn" }));
		cbYeniUyeCinsiyet.setBackground(new Color(0, 0, 0));
		cbYeniUyeCinsiyet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		cbYeniUyeCinsiyet.setBounds(284, 652, 163, 35);
		w_panel.add(cbYeniUyeCinsiyet);

		JLabel lblUyari = new JLabel("(Ba\u015F\u0131nda 0 Olmadan Giriniz!)");
		lblUyari.setToolTipText("");
		lblUyari.setForeground(Color.RED);
		lblUyari.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblUyari.setBounds(8, 558, 264, 45);
		w_panel.add(lblUyari);
	}
}
