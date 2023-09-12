package CafeTypeShit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Inventory extends JFrame{
	private JTable table;

	public Inventory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Inventory");
		
		JScrollPane jScrollPane1 = new JScrollPane();
		
		JButton btnSimpan = new JButton();
		btnSimpan.setText("Add Item");
		
		JButton btnSimpan_1 = new JButton();
		btnSimpan_1.setText("Remove Item");
		
		JButton btnSimpan_2 = new JButton();
		btnSimpan_2.setText("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSimpan, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSimpan_1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSimpan_2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(133)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(168)
							.addComponent(btnSimpan)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSimpan_1)
							.addGap(18)
							.addComponent(btnSimpan_2)))
					.addContainerGap(250, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Inventory", "Classifier"
			}
		));
		jScrollPane1.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		// TODO Auto-generated constructor stub
	}
}
