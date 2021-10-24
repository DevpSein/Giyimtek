package tr.com.akarcesme.fe;

import java.awt.BorderLayout;


import javax.swing.JDialog;

import tr.com.akarcesme.interfaces.FeInterfaces;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tr.com.akarcesme.dal.PersonelDal;
import tr.com.akarcesme.types.PersonelContract;



public class PersonelDuzenleFE extends JDialog implements FeInterfaces {

	public PersonelDuzenleFE() {

		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Personel Düzenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.setBorder(BorderFactory.createTitledBorder("Düzenleme Ýþlemleri"));
		JPanel ustPanel = new JPanel(new GridLayout(4, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Personel Düzenle"));
		JLabel personelBoxLabel = new JLabel("Personeller:", JLabel.RIGHT);
		ustPanel.add(personelBoxLabel);
		JComboBox personelBox = new JComboBox(new PersonelDal().GetAll().toArray());
		ustPanel.add(personelBox);
		personelBox.insertItemAt("--Personel Seçiniz--", 0);
		personelBox.setSelectedIndex(0);
		JLabel personelLabel = new JLabel("Personel Adý:", JLabel.RIGHT);
		ustPanel.add(personelLabel);
		JTextField personelAdiField = new JTextField(10);
		ustPanel.add(personelAdiField);
		JLabel eMailLabel = new JLabel("Email:", JLabel.RIGHT);
		ustPanel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		ustPanel.add(eMailField);

		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton ipatalButton = new JButton("Ýptal");
		ustPanel.add(ipatalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				
				if (personelBox.getSelectedIndex() == 0 || personelAdiField.getText().matches("")
						|| eMailField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen tüm bilgileri giriniz!!!");
				} else {
					PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();

					contract.setId(pContract.getId());
					contract.setAdiSoyadi(personelAdiField.getText());
					contract.setEmail(eMailField.getText());

					new PersonelDal().update(contract);
					JOptionPane.showMessageDialog(null,
							pContract.getAdiSoyadi() + " adlý personelin bilgileri baþarýyla güncellenmiþtir.");
				}
			}

		});
		ipatalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initPencere();
				dispose();

			}
		});

		/*
		 * JList personelList = new JList(); personelList.setListData(new
		 * PersonelDal().GetAll().toArray()); JScrollPane pane = new
		 * JScrollPane(personelList);
		 * pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));
		 * personelList.setSelectedIndex(0);
		 * 
		 * personelAdiField.addKeyListener(new KeyListener() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { // TODO Auto-generated method
		 * stub
		 * 
		 * }
		 * 
		 * @Override public void keyReleased(KeyEvent e) { personelList.setListData(new
		 * PersonelDal().GetSearchPersonel(personelAdiField.getText()).toArray());
		 * personelList.setSelectedIndex(0); }
		 * 
		 * @Override public void keyPressed(KeyEvent e) { // TODO Auto-generated method
		 * stub
		 * 
		 * } });
		 */

		panel.add(ustPanel, BorderLayout.NORTH);
		// panel.add(pane, BorderLayout.CENTER);
		return panel;

	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
