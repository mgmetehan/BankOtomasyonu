import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KrediHesapla extends JFrame {

	private JPanel w_panell;
	JPanel panelTitle = new JPanel();
	JSlider sliderKrediTutari = new JSlider(0, 50000);
	JLabel lblDonaBankKredi = new JLabel("Dona Bank Kredi Hesaplayici");
	JLabel lblIcon = new JLabel("");
	JLabel lblKrediTitle = new JLabel("Kredi Tutari");
	JLabel lblKrediTutari = new JLabel("");
	JLabel lblAyTitle = new JLabel("Vade");
	JLabel lblKrediToplamAy = new JLabel("");
	JSlider sliderKrediAy = new JSlider(0, 36);
	private JTextField txtAylikOdeme = new JTextField();
	private JTextField txtFaizOrani = new JTextField();
	private JTextField txtToplamGeriOdeme = new JTextField();
	JButton btnHesapla = new JButton("Hesapla");
	JLabel lblAylikOdeme = new JLabel("Aylik Odemeniz (TL)");
	JLabel lblFaiz = new JLabel("Faiz Orani");
	JLabel lblToplamOdeme = new JLabel("Toplam Geri Odemeniz(TL)");
	JButton btnBasvur = new JButton("Krediye Basvur");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KrediHesapla frame = new KrediHesapla();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void KrediYuvarlama() {
		int kredi = sliderKrediTutari.getValue();
		int sayac = 0;
		int krediBas = kredi;
		while (krediBas != 0) {
			krediBas = krediBas / 10;
			sayac++;
		}
		int bol = 1;
		if (sayac >= 4) {
			bol = 1000;
			int son = kredi % bol;
			int ilkBas = kredi / bol;
			if (son == 500) {
				sliderKrediTutari.setValue(kredi);
			} else if (son < 500) {
				kredi = ilkBas * bol;
				sliderKrediTutari.setValue(kredi);
			} else if (son > 500) {
				kredi = (ilkBas * bol) + 1000;
				sliderKrediTutari.setValue(kredi);
			}
		} else if (sayac < 4) {
			sliderKrediTutari.setValue(kredi);
		}
	}

	public void KrediGeriOdeme() {
		float faiz = 1.21f;
		int kredi = sliderKrediTutari.getValue();
		int ay = sliderKrediAy.getValue();
		float Toplam = kredi * faiz;
		float aylikOdeme = Toplam / ay;
		txtToplamGeriOdeme.setText(String.valueOf(Toplam));
		txtAylikOdeme.setText(String.valueOf(String.format("%.2f", aylikOdeme)));
		txtFaizOrani.setText(String.valueOf(faiz));
	}

	public void KrediyiHesabaEkle() throws SQLException {
		Connection conn = null;
		DbHelper helper = new DbHelper();
		PreparedStatement preStat = null;
		ResultSet resultset;
		String sql = "";
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		Float Para = Float.valueOf(sliderKrediTutari.getValue());
		Float Borc = Float.valueOf(txtToplamGeriOdeme.getText());
		try {
			conn = helper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select KisiPara,KisiBorc from tblkisiler where KisiId=" + KisiId);
			Float KisiMevcutPara = 0f;
			Float KisiMevcutBorc = 0f;
			while (rs.next()) {
				KisiMevcutPara = rs.getFloat(1);
				KisiMevcutBorc = rs.getFloat(2);
			}
			Para += KisiMevcutPara;
			Borc += KisiMevcutBorc;
			sql = "UPDATE tblkisiler set KisiPara=" + Para + " , KisiBorc=" + Borc + " where KisiId=" + KisiId;
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

	public KrediHesapla() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(KrediHesapla.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona  Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 647);
		w_panell = new JPanel();
		w_panell.setBackground(new Color(72, 66, 109));
		w_panell.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panell);
		w_panell.setLayout(null);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-18, 11, 703, 88);
		w_panell.add(panelTitle);
		sliderKrediTutari.setBackground(Color.WHITE);

		sliderKrediTutari.setOpaque(false);
		sliderKrediTutari.setBounds(10, 164, 535, 88);

		Hashtable position = new Hashtable();
		position.put(0, new JLabel("0"));
		position.put(5000, new JLabel("5000"));
		position.put(10000, new JLabel("10000"));
		position.put(15000, new JLabel("15000"));
		position.put(20000, new JLabel("20000"));
		position.put(25000, new JLabel("25000"));
		position.put(30000, new JLabel("30000"));
		position.put(35000, new JLabel("35000"));
		position.put(40000, new JLabel("40000"));
		position.put(45000, new JLabel("45000"));
		position.put(50000, new JLabel("50000"));

		sliderKrediTutari.setLabelTable(position);
		sliderKrediTutari.setMinorTickSpacing(2500);
		sliderKrediTutari.setPaintTicks(true);
		sliderKrediTutari.setPaintLabels(true);

		sliderKrediTutari.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				String kredi = String.valueOf(sliderKrediTutari.getValue());
				lblKrediTutari.setText(kredi + " TL");
				KrediYuvarlama();
			}

		});

		sliderKrediTutari.setOpaque(false);
		sliderKrediTutari.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_panell.add(sliderKrediTutari);

		lblDonaBankKredi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonaBankKredi.setForeground(Color.WHITE);
		lblDonaBankKredi.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblDonaBankKredi.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblDonaBankKredi.setBounds(210, 24, 430, 40);
		panelTitle.add(lblDonaBankKredi);

		lblIcon.setIcon(new ImageIcon(KrediHesapla.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(62, 1, 115, 76);
		panelTitle.add(lblIcon);

		lblKrediTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblKrediTitle.setBounds(10, 110, 487, 43);
		w_panell.add(lblKrediTitle);

		lblKrediTutari.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblKrediTutari.setBounds(555, 162, 138, 43);
		w_panell.add(lblKrediTutari);

		lblAyTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblAyTitle.setBounds(10, 257, 487, 43);
		w_panell.add(lblAyTitle);

		lblKrediToplamAy.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblKrediToplamAy.setBounds(528, 286, 138, 43);
		w_panell.add(lblKrediToplamAy);

		Hashtable pos = new Hashtable();
		pos.put(0, new JLabel("0"));
		pos.put(6, new JLabel("6"));
		pos.put(12, new JLabel("12"));
		pos.put(18, new JLabel("18"));
		pos.put(24, new JLabel("24"));
		pos.put(30, new JLabel("30"));
		pos.put(36, new JLabel("36"));

		sliderKrediAy.setLabelTable(pos);
		sliderKrediAy.setMinorTickSpacing(3);
		sliderKrediAy.setPaintTicks(true);
		sliderKrediAy.setPaintLabels(true);

		sliderKrediAy.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				String ay = String.valueOf(sliderKrediAy.getValue());
				lblKrediToplamAy.setText(ay + " Ay");
			}
		});

		sliderKrediAy.setOpaque(false);
		sliderKrediAy.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		sliderKrediAy.setBounds(20, 286, 477, 88);
		w_panell.add(sliderKrediAy);

		btnHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KrediGeriOdeme();
			}
		});
		btnHesapla.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		btnHesapla.setBounds(30, 385, 138, 53);
		w_panell.add(btnHesapla);

		txtAylikOdeme.setHorizontalAlignment(SwingConstants.CENTER);
		txtAylikOdeme.setForeground(Color.WHITE);
		txtAylikOdeme.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtAylikOdeme.setColumns(10);
		txtAylikOdeme.setBorder(null);
		txtAylikOdeme.setBackground(Color.BLACK);
		txtAylikOdeme.setBounds(10, 537, 144, 45);
		w_panell.add(txtAylikOdeme);

		lblAylikOdeme.setToolTipText("");
		lblAylikOdeme.setForeground(Color.WHITE);
		lblAylikOdeme.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblAylikOdeme.setBounds(10, 481, 158, 45);
		w_panell.add(lblAylikOdeme);

		lblFaiz.setToolTipText("");
		lblFaiz.setForeground(Color.WHITE);
		lblFaiz.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblFaiz.setBounds(178, 481, 123, 45);
		w_panell.add(lblFaiz);

		txtFaizOrani.setEnabled(false);
		txtFaizOrani.setHorizontalAlignment(SwingConstants.CENTER);
		txtFaizOrani.setForeground(Color.WHITE);
		txtFaizOrani.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtFaizOrani.setColumns(10);
		txtFaizOrani.setBorder(null);
		txtFaizOrani.setBackground(Color.BLACK);
		txtFaizOrani.setBounds(178, 537, 89, 45);
		w_panell.add(txtFaizOrani);

		txtToplamGeriOdeme.setHorizontalAlignment(SwingConstants.CENTER);
		txtToplamGeriOdeme.setForeground(Color.WHITE);
		txtToplamGeriOdeme.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtToplamGeriOdeme.setColumns(10);
		txtToplamGeriOdeme.setBorder(null);
		txtToplamGeriOdeme.setBackground(Color.BLACK);
		txtToplamGeriOdeme.setBounds(293, 537, 144, 45);
		w_panell.add(txtToplamGeriOdeme);

		lblToplamOdeme.setToolTipText("");
		lblToplamOdeme.setForeground(Color.WHITE);
		lblToplamOdeme.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblToplamOdeme.setBounds(293, 481, 209, 45);
		w_panell.add(lblToplamOdeme);

		btnBasvur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAylikOdeme.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kredi Baþvurmak Ýçin Krediyi Hesapla", "Uyarý",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						JOptionPane.showMessageDialog(null, "Kredi Baþvurunuz Ýþleme Alýnmýþtýr!", "Kredi Baþvuru",
								JOptionPane.INFORMATION_MESSAGE);
						KrediyiHesabaEkle();
						setVisible(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnBasvur.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		btnBasvur.setBounds(477, 529, 159, 53);
		w_panell.add(btnBasvur);
	}
}
