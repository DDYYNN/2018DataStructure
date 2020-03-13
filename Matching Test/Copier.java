import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Copier {
	private ArrayList<String> contents;
	private String filname;
	private ArrayList<String> regex, repl;
	public Copier (String fn) {
		this.filname = fn;
		this.regex =new ArrayList<String>();
		this.repl =new ArrayList<String>();
		this.contents =new ArrayList<String>();
	} public void read() throws IOException {
		BufferedReader bu = new BufferedReader(new FileReader(this.filname));
		String s;
		contents.clear ();
		while ((s = bu.readLine()) != null) contents.add(s);
		bu.close();
	} public void addRule (String reg, String re) {
		this.regex.add(reg);
		this.repl.add(re);
	} public void write(boolean	filter) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(this.filname));
		for (String s : contents) {
			if (filter) {
				int i, len = regex.size();
				for (i=0; i<len; ++i) {
					s = s.replaceAll(regex.get(i), repl.get(i));
				}}
			bw.write(s);
			bw.write("\n");
		} bw.close()
	; }}	
//		}//z :)
