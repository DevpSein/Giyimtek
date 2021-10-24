package tr.com.akarcesme.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.akarcesme.dal.KategoriDal;
import tr.com.akarcesme.dal.MusteriDal;
import tr.com.akarcesme.dal.UrunlerDal;
import tr.com.akarcesme.interfaces.FeInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.UrunlerContract;

public class UrunDuzenleFE extends JDialog implements FeInterfaces {

	public UrunDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Ürün Düzenle");
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
		JPanel ustPanel = new JPanel(new GridLayout(5, 2));
		ustPanel.setBorder(BorderFactory.createTitledBorder("Ürün Düzenle"));
		JLabel urunlerBoxLabel = new JLabel("Ürünler:", JLabel.RIGHT);
		ustPanel.add(urunlerBoxLabel);
		JComboBox urunBox = new JComboBox(new UrunlerDal().GetAll().toArray());
		ustPanel.add(urunBox);
		JLabel urunLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		ustPanel.add(urunLabel);
		JTextField urunAdiField = new JTextField(10);
		ustPanel.add(urunAdiField);
		JLabel kategoriLabel = new JLabel("Kategoriler:", JLabel.RIGHT);
		ustPanel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDal().GetAll().toArray());
		ustPanel.add(kategoriBox);
		JLabel fiyatLabel = new JLabel("Fiyat:", JLabel.RIGHT);
		ustPanel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		ustPanel.add(fiyatField);
		JButton kaydetButton = new JButton("Kaydet");
		ustPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		ustPanel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				UrunlerContract uContract = (UrunlerContract) urunBox.getSelectedItem();
				KategoriContract kContract = (KategoriContract) kategoriBox.getSelectedItem();

				if (urunAdiField.getText().matches("") || fiyatField.getText().matches("")) {
					JOptionPane.showMessageDialog(null, "!!!Lütfen gerekli bilgileri giriniz!!!");
				} else {

					contract.setId(uContract.getId());
					contract.setAdi(urunAdiField.getText());
					contract.setKategoriId(kContract.getId());
					contract.setFiyat(Integer.parseInt(fiyatField.getText()));

					new UrunlerDal().update(contract);
					JOptionPane.showMessageDialog(null,
							contract.getAdi() + " adlý ürünün bilgileri baþarýyla deðiþtirilmiþtir");
				}

			}
		});
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initPencere();dispose();				
			}
		});
		/*
		 * JList urunList = new JList(); urunList.setListData(new
		 * UrunlerDal().GetAll().toArray()); JScrollPane pane = new
		 * JScrollPane(urunList);
		 * pane.setBorder(BorderFactory.createTitledBorder("Düzenlenecek Liste"));
		 * urunList.setSelectedIndex(0); urunAdiField.addKeyListener(new KeyListener() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { // TODO Auto-generated method
		 * stub
		 * 
		 * }
		 * 
		 * @Override public void keyReleased(KeyEvent e) { urunList.setListData(new
		 * UrunlerDal().GetSearchUrun(urunAdiField.getText()).toArray());
		 * urunList.setSelectedIndex(0); }
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
