import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;    
import java.io.*;
import java.util.ArrayList;
public class Problem4  
{  
	private JFrame main;
	private JLabel l1;
	private JLabel l;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JTextArea stopwords;
	private JTable jt;
	private boolean t = false;
	public String path;
	private DefaultTableModel m1;
	public Problem4(){
		main = new JFrame();
		main.setLayout(new GridLayout(5, 1));
		main.setSize(800,1000);
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new GridLayout(1,2));
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		l = new JLabel("Hello",JLabel.CENTER);
		l1 = new JLabel("Stopwords",JLabel.CENTER);
		stopwords = new JTextArea();
		stopwords.setRows(10);
		addButtons();
		main.add(l);
		main.add(panel1);
		panel2.add(l1);
		panel2.add(stopwords);
		main.add(panel2);
		main.add(panel3);
		main.add(panel4);
		main.setVisible(true);
	}
	public void addButtons(){
		JButton pick_file = new JButton("Select File");
		pick_file.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fp = new JFileChooser();
				int i = fp.showOpenDialog(main);
				if(i==JFileChooser.APPROVE_OPTION){
					File f = fp.getSelectedFile();
						String filepath = f.getPath();
						l.setText(filepath);
						path=filepath;
					}
			}
		});
		panel1.add(pick_file);
		JButton process = new JButton("Process");
		process.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				text_parse parser = new text_parse(l.getText());
				parser.parse();
				String stop = stopwords.getText();
				stop = stop.replaceAll("[\\r\\n\\s]+", " ");
				stop = stop.replaceAll("\\,"," ");
				String[] w = stop.split(" ");
				ArrayList<String> y = new ArrayList();
				for(String k : w) {
					String nextWord;
					nextWord = k.replaceAll("\\s*\\p{Punct}+\\s*$","");
					if (nextWord != null && !nextWord.isEmpty()){
						y.add(nextWord);
						}
				}
				for(String k:y) {
					System.out.println(k);
				}
				final_data analyzer = new final_data(parser.get_ret(),y);
				String[][] for_display = analyzer.process();
				String column[]={"Words","Frequency"};
				if(!t) {
				m1 = new DefaultTableModel(for_display,column);
				jt = new JTable(m1);
				jt.setPreferredScrollableViewportSize(new Dimension(500,100));
				jt.setFillsViewportHeight(true);
		        JScrollPane sp=new JScrollPane(jt);
		        sp.setVisible(true);
		        panel4.add(sp);
		        t = true;
				}
				else {
					m1.setDataVector(for_display,column);
					m1.fireTableDataChanged();
					jt.repaint();
				}
		        SwingUtilities.updateComponentTreeUI(main);//resize the whole window to display the new value.			
			}
		});
		panel3.add(process);
	}
public static void main(String args[]){
		Problem4 a = new Problem4();

    }  
}