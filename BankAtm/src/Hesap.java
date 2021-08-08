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
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.DebugGraphics;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Hesap extends JFrame {

	private JPanel w_panel;
	JLabel lblKullaniciResimi = new JLabel("");
	JLabel lblBankaAdi = new JLabel("Dona Bank");
	JLabel lblKullaniciAdi = new JLabel("Mete");
	JPanel panelIslemler = new JPanel();
	JButton btnParaCek = new JButton("Para Cek");
	JButton btnHesapBilgileri = new JButton("Hesap Detaylari");
	JButton btnParaYatir = new JButton("Para Yatir");
	JButton btnBorc = new JButton("Borclari Goster");
	JButton btnHavale = new JButton("Havale Islemleri");
	JButton btnKrediHesapla = new JButton("Kredi Hesapla");
	JButton btnRandomSifre = new JButton("Random Sifre Olustur");
	JButton btnKullancAyarlar = new JButton("Kullanici Ayarlari");
	private final JButton btnQrIslemler = new JButton("Qr Kod Islemleri");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hesap frame = new Hesap();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void KisiId() {
		LoginAtm atm = new LoginAtm();
		try {
			System.out.println(atm.TcveSifreKontrol());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void KullaniciAdiVeResmi() throws SQLException {
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		DbHelper helper = new DbHelper();
		Connection con = helper.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select KisiAd,KisiSoyad,KisiCinsiyet from tblkisiler where KisiId=" + KisiId);
		String ad = "";
		String resim = "";
		while (rs.next()) {
			ad = rs.getString(1) + " " + rs.getString(2);
			resim = rs.getString(3);
		}
		lblKullaniciAdi.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullaniciAdi.setText(ad);
		if (resim.equals("Erkek")) {
			lblKullaniciResimi.setIcon(new ImageIcon(Hesap.class.getResource("/img/mr.png")));
		} else if (resim.equals("Kadin")) {
			lblKullaniciResimi.setIcon(new ImageIcon(Hesap.class.getResource("/img/mrs.png")));
		}
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
			String yeniDeger = "";
			int Id = 0;
			sql = "update tblkisiler set " + "KisiSifre='" + yeniDeger + "'where KisiId=" + Id;
			preStat = conn.prepareStatement(sql);
			int result = preStat.executeUpdate();
			System.out.println("Kayit Guncellendi");
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
			preStat.close();
			conn.close();
		}
	}

	public void RandomSifre() {
		int x;
		String sifre = "";
		int sayac = 0;
		while (sayac < 6) {
			sayac++;
			x = (int) (Math.random() * 9);
			sifre += x;
		}
		JOptionPane.showMessageDialog(null, "Yeni Random Sifre: " + sifre, "Random Sifre",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void BorclariGoster() throws SQLException {
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		DbHelper helper = new DbHelper();
		Connection con = helper.getConnection();
		Statement stmt = con.createStatement();
		String Ad = "";
		Float Borc = 0f;
		ResultSet rs = stmt.executeQuery("Select KisiAd,KisiSoyad,KisiBorc from tblkisiler where KisiId=" + KisiId);
		while (rs.next()) {
			Ad = rs.getString(1) + " " + rs.getString(2);
			Borc = rs.getFloat(3);
		}
		JOptionPane.showMessageDialog(null, Ad + " Guncel Borcunuz: " + Borc, "Borc Bilgileri",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void HesapDetaylari() throws SQLException {
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		DbHelper helper = new DbHelper();
		Connection con = helper.getConnection();
		Statement stmt = con.createStatement();
		String Ad = "", Tc = null, Telefon = null;
		Float Borc = 0f, Para = 0f;
		ResultSet rs = stmt.executeQuery(
				"Select KisiAd,KisiSoyad,KisiTc,KisiTelefon,KisiPara,KisiBorc from tblkisiler where KisiId=" + KisiId);
		while (rs.next()) {
			Ad = rs.getString(1) + " " + rs.getString(2);
			Tc = rs.getString(3);
			Telefon = rs.getString(4);
			Para = rs.getFloat(5);
			Borc = rs.getFloat(6);
		}
		JOptionPane.showMessageDialog(null,
				"Ad Soyad: " + Ad + "\nTc: " + Tc + "\nTelefon: 0" + Telefon + "\nPara: " + Para + "\nBorc: " + Borc,
				"Hesap Bilgileri", JOptionPane.INFORMATION_MESSAGE);
	}

	public Hesap() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Hesap.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1058, 703);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(30, 144, 255));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		lblKullaniciResimi.setIcon(new ImageIcon(Hesap.class.getResource("/img/mr.png")));
		lblKullaniciResimi.setBounds(460, 21, 133, 160);
		w_panel.add(lblKullaniciResimi);
		lblBankaAdi.setHorizontalAlignment(SwingConstants.LEFT);

		lblBankaAdi.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
		lblBankaAdi.setIcon(new ImageIcon("C:\\Users\\mgmet\\Desktop\\burmetem.png"));
		lblBankaAdi.setBounds(15, 21, 583, 154);
		w_panel.add(lblBankaAdi);

		lblKullaniciAdi.setForeground(new Color(255, 255, 255));
		try {
			KullaniciAdiVeResmi();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		lblKullaniciAdi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblKullaniciAdi.setBounds(434, 171, 185, 41);
		w_panel.add(lblKullaniciAdi);

		panelIslemler.setBackground(Color.WHITE);
		panelIslemler.setBounds(15, 243, 1024, 407);
		w_panel.add(panelIslemler);
		panelIslemler.setLayout(new GridLayout(4, 2));
		btnParaCek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaCek paraCek = new ParaCek();
				paraCek.setVisible(true);
			}
		});

		btnParaCek.setIcon(new ImageIcon(Hesap.class.getResource("/img/paraCek.png")));
		btnParaCek.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnParaCek);
		btnHesapBilgileri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HesapDetaylari();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnHesapBilgileri.setIcon(new ImageIcon(Hesap.class.getResource("/img/bank.png")));
		btnHesapBilgileri.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnHesapBilgileri);
		btnParaYatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParaYatir paraYatir = new ParaYatir();
				paraYatir.setVisible(true);
			}
		});

		btnParaYatir.setIcon(new ImageIcon(Hesap.class.getResource("/img/paraYatir.png")));
		btnParaYatir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnParaYatir);
		btnBorc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BorclariGoster();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnBorc.setIcon(new ImageIcon(Hesap.class.getResource("/img/borc.png")));
		btnBorc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnBorc);
		btnHavale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Havale havale = new Havale();
				havale.setVisible(true);
			}
		});

		btnHavale.setIcon(new ImageIcon(Hesap.class.getResource("/img/eft.png")));
		btnHavale.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnHavale);
		btnKrediHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediHesapla kredi = new KrediHesapla();
				kredi.setVisible(true);
			}
		});

		btnKrediHesapla.setIcon(new ImageIcon(Hesap.class.getResource("/img/kredi.png")));
		btnKrediHesapla.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnKrediHesapla);
		btnRandomSifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomSifre();
			}
		});

		btnRandomSifre.setIcon(new ImageIcon(Hesap.class.getResource("/img/random.png")));
		btnRandomSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panelIslemler.add(btnRandomSifre);
		btnKullancAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KullaniciAyarlari ayarlar = new KullaniciAyarlari();
				ayarlar.setVisible(true);
			}
		});

		panelIslemler.add(btnKullancAyarlar);
		btnKullancAyarlar.setIcon(new ImageIcon(Hesap.class.getResource("/img/settings.png")));
		btnKullancAyarlar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnQrIslemler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QrKodIslemleri qr = new QrKodIslemleri();
				qr.setVisible(true);
			}
		});
		btnQrIslemler.setIcon(new ImageIcon(Hesap.class.getResource("/img/qr.png")));
		btnQrIslemler.setForeground(Color.WHITE);
		btnQrIslemler.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnQrIslemler.setContentAreaFilled(false);
		btnQrIslemler.setBorder(null);
		btnQrIslemler.setBackground(Color.WHITE);
		btnQrIslemler.setBounds(724, 146, 320, 80);

		w_panel.add(btnQrIslemler);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Hesap.class.getResource("/img/google_wallet_80px.png")));
		lblLogo.setBounds(139, 59, 99, 87);
		w_panel.add(lblLogo);
	}
};