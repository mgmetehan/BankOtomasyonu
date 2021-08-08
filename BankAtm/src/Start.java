
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

public class Start extends JFrame {

	private JPanel w_panel;
	Timer time;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);

					Timer time = new Timer();
					TimerTask gorev = new TimerTask() {
						int sayac = 0;

						@Override
						public void run() {
							sayac++;
							if (sayac == 15) {
								time.cancel();
								LoginAtm atm = new LoginAtm();
								atm.setVisible(true);
								frame.setVisible(false);
							}
						}
					};
					time.schedule(gorev, 0, 500);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/img/google_wallet_80px.png")));
		setTitle("Dona Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 640, 360);
		w_panel = new JPanel();
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		JLabel lblIntro = new JLabel("");
		lblIntro.setIcon(new ImageIcon(Start.class.getResource("/img/intro.gif")));
		lblIntro.setBounds(0, 0, 640, 360);
		w_panel.add(lblIntro);

	}
}
