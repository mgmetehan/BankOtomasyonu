import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class KullaniciAyarlari extends JFrame {

	private JPanel w_panel;
	private JTextField txtKullaniciYeniSifre = new JTextField();
	private JTextField txtKullaniciTc = new JTextField();
	JButton btnDegistir = new JButton("Yeni Sifre Olustur");
	JLabel lblYeniifre = new JLabel("Yeni Sifre");
	JLabel lblJullaniciTc = new JLabel("Kullanici T.C. no:");
	JPanel panelTitle = new JPanel();
	JLabel lblDonaBankKullanc = new JLabel("Dona  Bank Kullanici Ayarlari");
	JLabel lblIcon = new JLabel("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciAyarlari frame = new KullaniciAyarlari();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Update() throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		Scanner scan = new Scanner(System.in);
		String sql = "";
		try {
			conn = helper.getConnection();
			String yeniDeger = txtKullaniciYeniSifre.getText().toString();
			String Tc = txtKullaniciTc.getText();
			if (Tc.length() < 11) {
				JOptionPane.showMessageDialog(null, "T.C. no 11 Haneli Olmali", "Bilgi",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				sql = "update tblkisiler set " + "KisiSifre='" + yeniDeger + "'where KisiTc=" + Tc;
				preStat = conn.prepareStatement(sql);
				int result = preStat.executeUpdate();
				JOptionPane.showMessageDialog(null, "Kayit Guncellendi", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
			System.exit(0);
		}
	}

	public KullaniciAyarlari() {
		setResizable(false);
		setTitle("Dona Bank");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(KullaniciAyarlari.class.getResource("/img/google_wallet_80px.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 465);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(255, 153, 5));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		btnDegistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtKullaniciYeniSifre.getText().length() != 6) {
					JOptionPane.showMessageDialog(null, "Þifre 6 Haneli Olmalý!!", "Uyarý", JOptionPane.ERROR_MESSAGE);
					txtKullaniciYeniSifre.setText("");
				} else {
					try {
						Update();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDegistir.setForeground(Color.WHITE);
		btnDegistir.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnDegistir.setBorder(null);
		btnDegistir.setBackground(Color.DARK_GRAY);
		btnDegistir.setBounds(25, 374, 482, 35);
		w_panel.add(btnDegistir);

		txtKullaniciYeniSifre.setForeground(Color.WHITE);
		txtKullaniciYeniSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKullaniciYeniSifre.setColumns(10);
		txtKullaniciYeniSifre.setBorder(null);
		txtKullaniciYeniSifre.setBackground(Color.BLACK);
		txtKullaniciYeniSifre.setBounds(24, 304, 483, 45);
		w_panel.add(txtKullaniciYeniSifre);

		lblYeniifre.setToolTipText("");
		lblYeniifre.setForeground(Color.WHITE);
		lblYeniifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblYeniifre.setBounds(24, 248, 283, 45);
		w_panel.add(lblYeniifre);

		txtKullaniciTc.setForeground(Color.WHITE);
		txtKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKullaniciTc.setColumns(10);
		txtKullaniciTc.setBorder(null);
		txtKullaniciTc.setBackground(Color.BLACK);
		txtKullaniciTc.setBounds(24, 192, 483, 45);
		w_panel.add(txtKullaniciTc);

		lblJullaniciTc.setToolTipText("");
		lblJullaniciTc.setForeground(Color.WHITE);
		lblJullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblJullaniciTc.setBounds(24, 126, 283, 45);
		w_panel.add(lblJullaniciTc);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-68, 11, 786, 88);
		w_panel.add(panelTitle);

		lblDonaBankKullanc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKullanc.setForeground(Color.WHITE);
		lblDonaBankKullanc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKullanc.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKullanc.setBounds(193, 24, 417, 40);
		panelTitle.add(lblDonaBankKullanc);

		lblIcon.setIcon(new ImageIcon(KullaniciAyarlari.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(68, 0, 115, 76);
		panelTitle.add(lblIcon);
	}
}
