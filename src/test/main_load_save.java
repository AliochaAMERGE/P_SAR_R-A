package test;

import java.io.File;
import java.io.IOException;

import core.graph.Graph;

public class main_load_save {

	public static void main(String[] args) {
		try
		{	
			String syspath = System.getProperty("user.dir");

			
			String load_file = (syspath + "/src/test_files/testgraph.txt");
			 
			Graph g_test = new Graph(load_file);
					
			System.out.println(g_test);
			
			File save_file = new File(syspath + "/src/test_files/testsave.txt");
			
			save_file.getParentFile().mkdirs();
			
			if(save_file.createNewFile()) {
				System.out.println("File created");
			} else {
				System.out.println("File already exists");
			}
			
			g_test.save(syspath + "/src/test_files/testsave.txt");
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
