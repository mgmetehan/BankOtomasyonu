import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.Component;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginAtm extends JFrame {

	JPanel w_panel;
	static JTextField txtKullaniciTc = new JTextField();
	static JTextField txtKullaniciSifre = new JTextField();
	JPanel panelTitle = new JPanel();
	JLabel lblTitle = new JLabel("Dona Bank'a Hos Geldiniz");
	JLabel lblIcon = new JLabel("");
	JLabel lblKullaniciTc = new JLabel("T.C Kimlik Numaraniz");
	JLabel lblKullaniciSifre = new JLabel("Sifre");
	JButton btnDevam = new JButton("DEVAM");
	JButton btnYeniUye = new JButton("Uye Ol");
	JButton btnSifremiUnuttum = new JButton("Sifremi Unuttum ");
	JLabel lblMasterCard = new JLabel("");
	JLabel lblVisa = new JLabel("");
	JLabel lblKullaniciIco = new JLabel("");
	JLabel lblUyeOlco = new JLabel("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAtm frame = new LoginAtm();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static int TcveSifreKontrol() throws SQLException {
		DbHelper helper = new DbHelper();
		Connection con = helper.getConnection();
		Statement stmt = con.createStatement();
		String TCKontrol = txtKullaniciTc.getText();
		String SifreKontrol = txtKullaniciSifre.getText();
		ResultSet rs = stmt.executeQuery(
				"Select KisiId from tblkisiler where KisiTc='" + TCKontrol + "'and KisiSifre='" + SifreKontrol + "'");
		while (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	/*
	 * Connection conn = null; DbHelper helper = new DbHelper(); PreparedStatement
	 * preStat = null; ResultSet resultset; Scanner scan = new Scanner(System.in);
	 * String SonSifre = null; try { conn = helper.getConnection();
	 * 
	 * String sql =
	 * "Select KisiId from tblkisiler where KisiTc="+TCKontrol+"and KisiSifre="
	 * +SifreKontrol; preStat = conn.prepareStatement(sql); ResultSet result =
	 * preStat.executeQuery(); while (result.next()) { SonSifre =
	 * result.getString(5); System.out.println("Kullanýcý Þifresi: " +
	 * result.getString(1)); } System.out.println(SonSifre); } catch (SQLException
	 * e) { helper.showErrorMessage(e); } finally { preStat.close(); conn.close(); }
	 */

	public LoginAtm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginAtm.class.getResource("/img/google_wallet_80px.png")));
		setResizable(false);
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 600);
		w_panel = new JPanel();
		w_panel.setAlignmentY(5.0f);
		w_panel.setBackground(new Color(232, 0, 0));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-47, 25, 786, 88);
		w_panel.add(panelTitle);
		panelTitle.setLayout(null);

		lblTitle.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblTitle.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(210, 24, 407, 40);
		panelTitle.add(lblTitle);

		lblIcon.setIcon(new ImageIcon(LoginAtm.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(77, 1, 123, 87);
		panelTitle.add(lblIcon);

		lblKullaniciTc.setToolTipText("");
		lblKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciTc.setForeground(Color.WHITE);
		lblKullaniciTc.setBounds(45, 140, 283, 45);
		w_panel.add(lblKullaniciTc);

		txtKullaniciTc.setBorder(null);
		txtKullaniciTc.setBackground(Color.BLACK);
		txtKullaniciTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKullaniciTc.setForeground(Color.WHITE);
		txtKullaniciTc.setBounds(45, 206, 483, 45);
		w_panel.add(txtKullaniciTc);
		txtKullaniciTc.setColumns(10);

		lblKullaniciSifre.setToolTipText("");
		lblKullaniciSifre.setForeground(Color.WHITE);
		lblKullaniciSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciSifre.setBounds(45, 262, 283, 45);
		w_panel.add(lblKullaniciSifre);

		txtKullaniciSifre.setForeground(Color.WHITE);
		txtKullaniciSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtKullaniciSifre.setColumns(10);
		txtKullaniciSifre.setBorder(null);
		txtKullaniciSifre.setBackground(Color.BLACK);
		txtKullaniciSifre.setBounds(45, 318, 483, 45);
		w_panel.add(txtKullaniciSifre);

		btnDevam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int Id = TcveSifreKontrol();// Kiþinin Id si
					if (Id > 0) {
						System.out.println(Id);
						Hesap hesap = new Hesap();
						hesap.setVisible(true);
						dispose();
					} else {
						txtKullaniciSifre.setText("");
						txtKullaniciTc.setText("");
						JOptionPane.showMessageDialog(null, "Þifre veya T.C. no Hatalý!", "Uyari",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDevam.setBorder(null);
		btnDevam.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnDevam.setBackground(Color.DARK_GRAY);
		btnDevam.setForeground(Color.WHITE);
		btnDevam.setBounds(46, 388, 482, 35);
		w_panel.add(btnDevam);

		btnYeniUye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				YeniUye yeni = new YeniUye();
				yeni.setVisible(true);
				dispose();
			}
		});
		btnYeniUye.setContentAreaFilled(false);
		btnYeniUye.setForeground(Color.WHITE);
		btnYeniUye.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnYeniUye.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnYeniUye.setBackground(new Color(255, 255, 255));
		btnYeniUye.setBounds(45, 511, 482, 35);
		w_panel.add(btnYeniUye);

		btnSifremiUnuttum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SifremiUnuttum sifre = new SifremiUnuttum();
				sifre.setVisible(true);
				dispose();
			}
		});
		btnSifremiUnuttum.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSifremiUnuttum.setForeground(Color.WHITE);
		btnSifremiUnuttum.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnSifremiUnuttum.setContentAreaFilled(false);
		btnSifremiUnuttum.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		btnSifremiUnuttum.setBackground(new Color(255, 255, 255));
		btnSifremiUnuttum.setBounds(45, 434, 482, 35);
		w_panel.add(btnSifremiUnuttum);

		lblMasterCard.setIcon(new ImageIcon(LoginAtm.class.getResource("/img/mastercard_48px.png")));
		lblMasterCard.setBounds(544, 524, 48, 35);
		w_panel.add(lblMasterCard);

		lblVisa.setIcon(new ImageIcon(LoginAtm.class.getResource("/img/visa_50px.png")));
		lblVisa.setBounds(590, 524, 46, 38);
		w_panel.add(lblVisa);

		lblKullaniciIco.setIcon(new ImageIcon(LoginAtm.class.getResource("/img/male_user_48px.png")));
		lblKullaniciIco.setBounds(275, 107, 55, 53);
		w_panel.add(lblKullaniciIco);

		lblUyeOlco.setIcon(new ImageIcon(LoginAtm.class.getResource("/img/add_user_male_48px.png")));
		lblUyeOlco.setBounds(45, 493, 65, 53);
		w_panel.add(lblUyeOlco);
	}
}
