package Graph;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaneJFrame extends JFrame {

	public SaneJFrame(final JPanel content) {
		
		this.setSize(content.getSize());
		this.setLocationRelativeTo(null);
		this.setContentPane(content);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
