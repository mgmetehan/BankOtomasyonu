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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParaYatir extends JFrame {
	JPanel w_panel;
	JTextField txtPara = new JTextField();
	JTextField txtTc = new JTextField();
	JLabel lblPara = new JLabel("Yatirilacak Tutar");
	JLabel lblKullaniciTc = new JLabel("Kullanici T.C. no:");
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankKullanc = new JLabel("Dona Bank Para Yatir");
	JLabel lblIcon = new JLabel("");
	JButton btnParaYatir = new JButton("Para Yatir");
	JComboBox cbHesapOrBorc = new JComboBox();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaYatir frame = new ParaYatir();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void HesaptaNeKadarParaVar() {
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
			paraYatirFonk(KisiMevcutPara, KisiMevcutBorc, KisiId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void paraYatirFonk(Float KisiMevcutPara, Float KisiMevcutBorc, int KisiId) throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String sql = "";
		Float YatirilanTutar = Float.valueOf(txtPara.getText());
		Float KisiToplamPara = KisiMevcutPara + YatirilanTutar;
		Float KisiToplamBorc = KisiMevcutBorc - YatirilanTutar;
		try {
			conn = helper.getConnection();
			String secim = cbHesapOrBorc.getSelectedItem().toString();
			if (secim.equals("Hesap")) {
				sql = "Update tblkisiler set KisiPara=" + KisiToplamPara + "where KisiId=" + KisiId;
			} else if (secim.equals("Borc")) {
				if (KisiMevcutBorc < YatirilanTutar) {
					sql = "Update tblkisiler set KisiBorc=0 where KisiId=" + KisiId;
					Float ParayaEk = KisiMevcutPara + (YatirilanTutar - KisiMevcutBorc);
					String sql1 = "Update tblkisiler set KisiPara=" + ParayaEk + "where KisiId=" + KisiId;
					preStat = conn.prepareStatement(sql1);
					int res = preStat.executeUpdate();
				} else {
					sql = "Update tblkisiler set KisiBorc=" + KisiToplamBorc + "where KisiId=" + KisiId;
				}
			}
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

	public void ParaYatir() throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String sql = "";
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		Float Para = Float.valueOf(txtPara.getText());
		try {
			conn = helper.getConnection();
			sql = "UPDATE tblkisiler set KisiPara=" + Para + " where KisiId=" + KisiId;
			preStat = conn.prepareStatement(sql);
			int result = preStat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Islemler Basarili iyi Gunler", "Bilgi",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
			setVisible(false);
		}
	}

	public ParaYatir() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParaYatir.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 533);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(255, 117, 160));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		txtPara.setForeground(Color.WHITE);
		txtPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtPara.setColumns(10);
		txtPara.setBorder(null);
		txtPara.setBackground(Color.BLACK);
		txtPara.setBounds(32, 304, 483, 45);
		w_panel.add(txtPara);

		lblPara.setToolTipText("");
		lblPara.setForeground(Color.WHITE);
		lblPara.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblPara.setBounds(32, 248, 283, 45);
		w_panel.add(lblPara);

		LoginAtm atm = new LoginAtm();
		txtTc.setText(atm.txtKullaniciTc.getText());
		txtTc.setForeground(Color.WHITE);
		txtTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtTc.setEnabled(false);
		txtTc.setColumns(10);
		txtTc.setBorder(null);
		txtTc.setBackground(Color.BLACK);
		txtTc.setBounds(32, 192, 483, 45);
		w_panel.add(txtTc);

		lblKullaniciTc.setToolTipText("");
		lblKullaniciTc.setForeground(Color.WHITE);
		lblKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciTc.setBounds(32, 126, 283, 45);
		w_panel.add(lblKullaniciTc);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-59, 11, 666, 88);
		w_panel.add(panelTitle);

		lblDonaBankKullanc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKullanc.setForeground(Color.WHITE);
		lblDonaBankKullanc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKullanc.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKullanc.setBounds(210, 24, 345, 40);
		panelTitle.add(lblDonaBankKullanc);

		lblIcon.setIcon(new ImageIcon(ParaYatir.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(71, 0, 129, 76);
		panelTitle.add(lblIcon);
		btnParaYatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HesaptaNeKadarParaVar();
			}
		});

		btnParaYatir.setForeground(Color.WHITE);
		btnParaYatir.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnParaYatir.setBorder(null);
		btnParaYatir.setBackground(Color.DARK_GRAY);
		btnParaYatir.setBounds(32, 427, 482, 35);
		w_panel.add(btnParaYatir);

		cbHesapOrBorc.setModel(new DefaultComboBoxModel(new String[] { "Hesap", "Bor\u00E7", "" }));
		cbHesapOrBorc.setOpaque(false);
		cbHesapOrBorc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		cbHesapOrBorc.setBorder(null);
		cbHesapOrBorc.setBackground(Color.BLACK);
		cbHesapOrBorc.setBounds(32, 370, 163, 35);
		w_panel.add(cbHesapOrBorc);

		JButton btnHesaptanBorcOde = new JButton("Hesaptan Bro\u00E7 \u00D6de");
		btnHesaptanBorcOde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorcOde borc = new BorcOde();
				borc.setVisible(true);
			}
		});
		btnHesaptanBorcOde.setForeground(Color.WHITE);
		btnHesaptanBorcOde.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnHesaptanBorcOde.setBorder(null);
		btnHesaptanBorcOde.setBackground(Color.CYAN);
		btnHesaptanBorcOde.setBounds(250, 370, 265, 35);
		w_panel.add(btnHesaptanBorcOde);
	}
}
