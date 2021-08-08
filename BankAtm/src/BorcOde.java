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

public class BorcOde extends JFrame {
	private JPanel w_panel;
	private JTextField txtPara = new JTextField();
	private JTextField txtTc = new JTextField();
	JButton btnParaYatir = new JButton("Para Yatir");
	JLabel lblPara = new JLabel("Yatirilacak Tutar");
	JLabel lblKullaniciTc = new JLabel("Kullanici T.C. no:");
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankKullanc = new JLabel("Dona Bank Borc Ode");
	JLabel lblIcon = new JLabel("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorcOde frame = new BorcOde();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void BorcYatir() {
		try {
			DbHelper helper = new DbHelper();
			Connection con;
			con = helper.getConnection();
			Statement stmt = con.createStatement();
			LoginAtm atm = new LoginAtm();
			int KisiId = atm.TcveSifreKontrol();
			ResultSet rs = stmt.executeQuery("Select KisiPara,KisiBorc from tblkisiler where KisiId=" + KisiId);
			Float KisiMevcutPara = 0f;
			Float KisiMevcutBorc = 0f;
			while (rs.next()) {
				KisiMevcutPara = rs.getFloat(1);
				KisiMevcutBorc = rs.getFloat(2);
			}
			Float IstenilenTutar = Float.valueOf(txtPara.getText());
			if (KisiMevcutPara < IstenilenTutar) {
				JOptionPane.showMessageDialog(null, "Hesapta Olan Paradan Daha Fazlasini Yatiramazsin!!", "Error",
						JOptionPane.WARNING_MESSAGE);
				txtPara.setText("");
			} else {
				BorcUpdate(IstenilenTutar, KisiMevcutPara, KisiMevcutBorc, KisiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void BorcUpdate(Float IstenilenTutar, Float KisiMevcutPara, Float KisiMevcutBorc, int KisiId)
			throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String sql = "";
		KisiMevcutPara -= IstenilenTutar;
		KisiMevcutBorc -= IstenilenTutar;
		if (KisiMevcutBorc < 0) {
			KisiMevcutBorc *= (-1);
			KisiMevcutPara += KisiMevcutBorc;
			KisiMevcutBorc = 0f;
		}
		try {
			conn = helper.getConnection();
			sql = "Update tblkisiler set KisiPara=" + KisiMevcutPara + " , KisiBorc=" + KisiMevcutBorc + "where KisiId="
					+ KisiId;
			preStat = conn.prepareStatement(sql);
			int result = preStat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Islemler Basarili iyi Gunler", "Bilgi",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println("\nKisiMevcutpar" + KisiMevcutPara + "\nKisiMevcutBorc" + KisiMevcutBorc);
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
			setVisible(false);
		}
	}

	public BorcOde() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BorcOde.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 471);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(123, 237, 159));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);
		btnParaYatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorcYatir();
			}
		});

		btnParaYatir.setForeground(Color.WHITE);
		btnParaYatir.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnParaYatir.setBorder(null);
		btnParaYatir.setBackground(Color.DARK_GRAY);
		btnParaYatir.setBounds(32, 374, 482, 35);
		w_panel.add(btnParaYatir);

		txtPara.setForeground(Color.WHITE);
		txtPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtPara.setColumns(10);
		txtPara.setBorder(null);
		txtPara.setBackground(Color.BLACK);
		txtPara.setBounds(31, 304, 483, 45);
		w_panel.add(txtPara);

		lblPara.setToolTipText("");
		lblPara.setForeground(Color.WHITE);
		lblPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblPara.setBounds(31, 248, 283, 45);
		w_panel.add(lblPara);

		LoginAtm atm = new LoginAtm();
		txtTc.setText(atm.txtKullaniciTc.getText());
		txtTc.setForeground(Color.WHITE);
		txtTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtTc.setEnabled(false);
		txtTc.setColumns(10);
		txtTc.setBorder(null);
		txtTc.setBackground(Color.BLACK);
		txtTc.setBounds(31, 192, 483, 45);
		w_panel.add(txtTc);

		lblKullaniciTc.setToolTipText("");
		lblKullaniciTc.setForeground(Color.WHITE);
		lblKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciTc.setBounds(31, 126, 283, 45);
		w_panel.add(lblKullaniciTc);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-61, 11, 786, 88);
		w_panel.add(panelTitle);

		lblDonaBankKullanc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKullanc.setForeground(Color.WHITE);
		lblDonaBankKullanc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKullanc.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKullanc.setBounds(210, 20, 363, 44);
		panelTitle.add(lblDonaBankKullanc);

		lblIcon.setIcon(new ImageIcon(BorcOde.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(83, 1, 117, 76);
		panelTitle.add(lblIcon);
	}
}
