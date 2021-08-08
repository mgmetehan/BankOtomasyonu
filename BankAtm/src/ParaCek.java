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
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParaCek extends JFrame {

	private JPanel w_panel;
	private JTextField txtPara = new JTextField();
	private JTextField txtTC = new JTextField();
	JButton btnCek = new JButton("Para Cek");
	JLabel lblPara = new JLabel("Cekilecek Tutar");
	JLabel lblKullaniciTc = new JLabel("Kullanici T.C. no:");
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankKullanc = new JLabel("Dona Bank Para Cek");
	JLabel lblIcon = new JLabel("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaCek frame = new ParaCek();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ParaCekFonk() {
		try {
			DbHelper helper = new DbHelper();
			Connection con;
			con = helper.getConnection();
			Statement stmt = con.createStatement();
			LoginAtm atm = new LoginAtm();
			int KisiId = atm.TcveSifreKontrol();
			ResultSet rs = stmt.executeQuery("Select KisiPara from tblkisiler where KisiId=" + KisiId);
			Float KisiMevcutPara = 0f;
			while (rs.next()) {
				KisiMevcutPara = rs.getFloat(1);
			}
			Float IstenilenTutar = Float.valueOf(txtPara.getText());
			if (KisiMevcutPara < IstenilenTutar) {
				JOptionPane.showMessageDialog(null, "Hesapta Olan Paradan Daha Fazlasini Cekemezsiniz!!", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				ParaUpdate(IstenilenTutar, KisiMevcutPara, KisiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ParaUpdate(Float IstenilenTutar, Float KisiMevcutPara, int KisiId) throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String sql = "";
		Float KalanPara = KisiMevcutPara - IstenilenTutar;
		try {
			conn = helper.getConnection();
			sql = "Update tblkisiler set KisiPara=" + KalanPara + " where KisiId=" + KisiId;
			preStat = conn.prepareStatement(sql);
			int result = preStat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Islemler Basarili iyi Gunler", "", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
			setVisible(false);
		}
	}

	public ParaCek() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParaCek.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 464);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(0, 0, 255));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		btnCek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCekFonk();
			}
		});
		btnCek.setForeground(Color.WHITE);
		btnCek.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnCek.setBorder(null);
		btnCek.setBackground(Color.DARK_GRAY);
		btnCek.setBounds(47, 374, 482, 35);
		w_panel.add(btnCek);

		txtPara.setForeground(Color.WHITE);
		txtPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtPara.setColumns(10);
		txtPara.setBorder(null);
		txtPara.setBackground(Color.BLACK);
		txtPara.setBounds(46, 304, 483, 45);
		w_panel.add(txtPara);

		lblPara.setToolTipText("");
		lblPara.setForeground(Color.WHITE);
		lblPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblPara.setBounds(46, 248, 283, 45);
		w_panel.add(lblPara);

		LoginAtm atm = new LoginAtm();
		txtTC.setText(atm.txtKullaniciTc.getText());
		txtTC.setEnabled(false);
		txtTC.setForeground(Color.WHITE);
		txtTC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtTC.setColumns(10);
		txtTC.setBorder(null);
		txtTC.setBackground(Color.BLACK);
		txtTC.setBounds(46, 192, 483, 45);
		w_panel.add(txtTC);

		lblKullaniciTc.setToolTipText("");
		lblKullaniciTc.setForeground(Color.WHITE);
		lblKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciTc.setBounds(46, 126, 283, 45);
		w_panel.add(lblKullaniciTc);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-46, 11, 786, 88);
		w_panel.add(panelTitle);

		lblDonaBankKullanc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKullanc.setForeground(Color.WHITE);
		lblDonaBankKullanc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKullanc.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKullanc.setBounds(210, 24, 345, 40);
		panelTitle.add(lblDonaBankKullanc);

		lblIcon.setIcon(new ImageIcon(ParaCek.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(59, 1, 118, 76);
		panelTitle.add(lblIcon);
	}
}
