import java.util.*;
import java.io.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
class take_input {
	private Scanner terminalScan;
	private String fileName;
	public take_input() {
		terminalScan = new Scanner(System.in);
		fileName = terminalScan.next();
	}
	public String get_inp() {
		return fileName;
	}
}
class stopWords {
	public List<String> remWord;
	public stopWords() {
		remWord = new ArrayList<String>();
	}
	public void add_one(String a) {
		remWord.add(a);
	}
	public void add_many(ArrayList<String> l1) {
		remWord.addAll(l1);
	}
}
class text_parse{
	File myFile;
	Scanner fileScan;
	List<String> words;
	String Extension;
	String[] extract;
	public text_parse(String s){
		//myFile = new File(s);
		for (String k : s.split("[.]")) {
			if(k != null) {
				Extension=k;
			}
		}
		//System.out.println(Extension=="doc");
		if (Extension.equals("doc")) {
			try {
				HWPFDocument docx = new HWPFDocument(new FileInputStream(s));
				WordExtractor we = new WordExtractor(docx);
				String temp = we.getText();
				temp=temp.replaceAll("[\\r\\n\\s]+", " ");
				extract = temp.split(" ");
				we.close();
				//System.out.println(fileScan.next());
			}catch(Exception e) {
			}
		}
		else if(Extension.equals("docx")) {
			try {
				XWPFDocument docx = new XWPFDocument(new FileInputStream(s));
				XWPFWordExtractor we = new XWPFWordExtractor(docx);
				String temp = we.getText();
				temp=temp.replaceAll("[\\r\\n\\s]+", " ");
				extract = temp.split(" ");
				we.close();
				//System.out.println(fileScan.next());
			}catch(Exception e) {
			}
		}
		else if(Extension.equals("pdf")) {
			try {
				File file = new File(s);
			    PDDocument document = PDDocument.load(file);
			    PDFTextStripper pdfStripper = new PDFTextStripper();
			    String text = pdfStripper.getText(document);
				text=text.replaceAll("[\\r\\n\\s]+", " ");
				extract = text.split(" ");
			    document.close();
			}catch(Exception e) {
			}
		}
		words  = new ArrayList<String>();
	}
	public void parse() {
		for(String k : extract) {
			String nextWord;
			nextWord = k.replaceAll("\\s*\\p{Punct}+\\s*$","");
			if (nextWord != null && !nextWord.isEmpty()){
				words.add(nextWord);
				}
		}
	}
	public List<String> get_ret() {
		return words;
	}	
}
class final_data {
	List<String> words;
	Set<String> ma;
	ArrayList<String> w;
	public final_data(List<String> data, ArrayList<String> rem) {
		words= data;
		w = rem;
	}
	public String[][] process(){
		words.removeAll(w);
		ma = new HashSet<String>(words);
		String[][] result = new String[ma.size()][2];
		int i = 0;
		for(String k : ma){
			
			result[i][0]=k;
			result[i][1]=Integer.toString(Collections.frequency(words, k));
			i++;
		}
		return result; 
	}
}
public class Problem1 {
	public static void main(String args[]) throws FileNotFoundException{
		ArrayList<String> w = new ArrayList<String>();
		w.add("the");
		w.add("am");
		w.add("at");
		stopWords another = new stopWords();
		another.add_many(w);
		//remWord is my set of words to be removed
		take_input one = new take_input();
		
		text_parse yet_another = new text_parse(one.get_inp());
		yet_another.parse();		
		final_data print_out = new final_data(yet_another.get_ret(),w);
		String[][] to_betable = print_out.process();
		for(String[] k : to_betable) {
			System.out.println(k[0]+" : "+k[1]);
		}
    }
}