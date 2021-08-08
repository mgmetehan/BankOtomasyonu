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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.nio.file.Paths;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import com.google.zxing.NotFoundException;

public class QrKodIslemleri extends JFrame {
	private JPanel w_panel;
	private JTextField txtQrOkut = new JTextField();
	private JTextField txtQrYarat = new JTextField();
	JButton btnQrOkut = new JButton("Qr Okut");
	JLabel lblQrParaCek = new JLabel("Qr Islem");
	JLabel lblQrParaYarat = new JLabel("Qr Para Cekilecek Tutar:");
	JPanel panelTitle = new JPanel();
	JLabel lblTitle = new JLabel("Dona Bank Qr Kod Islemler");
	JLabel lblIcon = new JLabel("");
	JButton btnQrYarat = new JButton("Qr Olustur");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QrKodIslemleri frame = new QrKodIslemleri();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void QrMake() throws WriterException, IOException, SQLException {
		String data = txtQrYarat.getText();
		LoginAtm atm = new LoginAtm();
		int KisiId = atm.TcveSifreKontrol();
		DbHelper helper = new DbHelper();
		Connection con = helper.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select KisiPara from tblkisiler where KisiId=" + KisiId);
		int KisiPara = 0;
		while (rs.next()) {
			KisiPara = rs.getInt(1);
		}
		if (KisiPara < Integer.valueOf(data)) {
			JOptionPane.showMessageDialog(null, "Hesapta Olan Paradan Daha Fazlasini Cekemezsiniz!!", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int x;
			String name = "";
			int sayac = 0;
			while (sayac < 6) {
				sayac++;
				x = (int) (Math.random() * 9);
				name += x;
			}
			String path = "C:\\BankQr\\"+"DonaBank" + name + ".jpg";
			//String path = "D:\\BankQr\\DonaBank" + name + ".jpg";
			BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
			MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
		}
	}

	public void QrRead() throws FileNotFoundException, IOException, NotFoundException {
		String name = txtQrOkut.getText();
		if (name.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Qr Kod Ismi Giriniz!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		String path = "C:\\BankQr\\" + name + ".jpg";
		BufferedImage bf = ImageIO.read(new FileInputStream(path));
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
		Result result = new MultiFormatReader().decode(bitmap);
		System.out.println(result.getText());// Tutar
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
			Float IstenilenTutar = Float.valueOf(result.getText());
			if (KisiMevcutPara < IstenilenTutar) {
				JOptionPane.showMessageDialog(null, "Hesapta Olan Paradan Daha Fazlasini Cekemezsiniz!!", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				QrUpdate(IstenilenTutar, KisiMevcutPara, KisiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void QrUpdate(Float IstenilenTutar, Float KisiMevcutPara, int KisiId) throws SQLException {
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

	public QrKodIslemleri() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(QrKodIslemleri.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 540);
		w_panel = new JPanel();
		w_panel.setBackground(new Color(178, 190, 195));
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);
		btnQrOkut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QrRead();
				} catch (NotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnQrOkut.setForeground(Color.WHITE);
		btnQrOkut.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnQrOkut.setBorder(null);
		btnQrOkut.setBackground(Color.DARK_GRAY);
		btnQrOkut.setBounds(33, 431, 482, 35);
		w_panel.add(btnQrOkut);

		txtQrOkut.setForeground(Color.WHITE);
		txtQrOkut.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtQrOkut.setColumns(10);
		txtQrOkut.setBorder(null);
		txtQrOkut.setBackground(Color.BLACK);
		txtQrOkut.setBounds(32, 361, 483, 45);
		w_panel.add(txtQrOkut);

		lblQrParaCek.setToolTipText("");
		lblQrParaCek.setForeground(Color.WHITE);
		lblQrParaCek.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblQrParaCek.setBounds(32, 305, 283, 45);
		w_panel.add(lblQrParaCek);

		txtQrYarat.setForeground(Color.WHITE);
		txtQrYarat.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		txtQrYarat.setColumns(10);
		txtQrYarat.setBorder(null);
		txtQrYarat.setBackground(Color.BLACK);
		txtQrYarat.setBounds(32, 192, 483, 45);
		w_panel.add(txtQrYarat);

		lblQrParaYarat.setToolTipText("");
		lblQrParaYarat.setForeground(Color.WHITE);
		lblQrParaYarat.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblQrParaYarat.setBounds(32, 126, 283, 45);
		w_panel.add(lblQrParaYarat);

		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.BLACK);
		panelTitle.setBounds(-33, 11, 620, 88);
		w_panel.add(panelTitle);

		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblTitle.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		lblTitle.setBounds(168, 24, 392, 40);
		panelTitle.add(lblTitle);

		lblIcon.setIcon(new ImageIcon(QrKodIslemleri.class.getResource("/img/google_wallet_80px.png")));
		lblIcon.setBounds(41, 0, 117, 76);
		panelTitle.add(lblIcon);
		btnQrYarat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					QrMake();
				} catch (WriterException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnQrYarat.setForeground(Color.WHITE);
		btnQrYarat.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnQrYarat.setBorder(null);
		btnQrYarat.setBackground(Color.DARK_GRAY);
		btnQrYarat.setBounds(33, 259, 482, 35);
		w_panel.add(btnQrYarat);
	}
}
